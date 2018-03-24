package ca.mcgill.ecse223.resto.controller;

import java.util.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.Table;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Menu;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.OrderItem;
import ca.mcgill.ecse223.resto.model.PricedMenuItem;
import ca.mcgill.ecse223.resto.model.Reservation;



public class RestoAppController {
	
	public RestoAppController() {
	}
	
	public static List<Table> getCurrentTables() {
		return RestoAppApplication.getRestoApp().getCurrentTables();
	}
	
	public static List<Table> getTables() {
		return RestoAppApplication.getRestoApp().getTables();
	}
	
	public static List<Order> getCurrentOrders() {
		return RestoAppApplication.getRestoApp().getCurrentOrders();
	}
	
	public static void deleteTable(Table table) throws InvalidInputException{
		boolean hasReservation = table.hasReservations();
		RestoApp restoApp = RestoAppApplication.getRestoApp();
		String error = null;
		
		if (hasReservation) {
			error = "Table has reservations";
		}
		for (Order currentOrder : restoApp.getCurrentOrders()) {
			List<Table> tableList = currentOrder.getTables();
			if (tableList.contains(table)) {
				error = "Table in use";
				break;
			}
		}
		if (error != null) {
			throw new InvalidInputException(error);
		}
		restoApp.removeCurrentTable(table);
		try {
			RestoAppApplication.save();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static void updateTable(Table table, int newNumber, int numberofSeats) throws InvalidInputException{
	
		if(newNumber < 0 || numberofSeats < 0) {
			throw new InvalidInputException("Cannot has negative number of seats or table number");
		}
		
		if(numberofSeats > 8) {
			throw new InvalidInputException("There are too many seats for the table");
		}
		
		if(table == null) {
			throw new InvalidInputException("There is no table");
		}
		
		RestoApp restoApp = RestoAppApplication.getRestoApp();
		boolean reserved = table.hasReservations();
		
		if(reserved) {
			throw new InvalidInputException("Table is reserved");
		}
		
		for (Order currentOrder : restoApp.getCurrentOrders()) {
			List<Table> tableList = currentOrder.getTables();
			if (tableList.contains(table)) {
				throw new InvalidInputException("Table in use");
			}
		}
		
		if(table.getNumber() == (newNumber)) {
			table.setNumber(newNumber);
			
		}
		else if(!table.setNumber(newNumber)) {
			throw new InvalidInputException("Cannot set due to duplicate number");
		}
			table.setNumber(newNumber);	
		
		
		int n = table.numberOfCurrentSeats();
		if(n < numberofSeats) {
			for(int i = 0; i < numberofSeats - n; i++) {
				Seat seat = table.addSeat();
				table.addCurrentSeat(seat);
			}
		}
		else {
			for(int i = 0; i < n - numberofSeats; i++) {
				Seat seat = table.getCurrentSeat(0);
				table.removeCurrentSeat(seat);
				i++;
			}
		}
		
		try {
			RestoAppApplication.save();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			throw e;
		}
}
	
	public static void addCurrentTable(Table table) throws InvalidInputException{
		if (table == null) {
			throw new InvalidInputException("Invalid Table");
		}
		RestoApp restoApp = RestoAppApplication.getRestoApp(); 
		for(Table t : restoApp.getCurrentTables()) {
			if (t == table) {
				continue;
			}
			if (t.checkOverlap(table.getX(),table.getY(),table.getLength(), table.getWidth())) {
				throw new InvalidInputException("Table overlaps with another current Table");
			}
		}
		restoApp.addCurrentTable(table);
		try {
			RestoAppApplication.save();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
		
	}
	
	public static void addTable(int number, int x, int y, int width, int length, int numberOfSeat) throws InvalidInputException{
		if(x < 0 || y < 0 || width <= 0 || length <= 0 || numberOfSeat >= 8) {
			throw new InvalidInputException("Table specifications invalid");
		}
		
		RestoApp restoApp = RestoAppApplication.getRestoApp();
		List<Table> currentTables = restoApp.getCurrentTables();
		//Checks for overlap with existing tables and if declared table number is already in use
		for(Table t : currentTables) {
			if (t.checkOverlap(x,y,length, width)) {
				throw new InvalidInputException("Position overlaps with existing table");
			}
			if(t.getNumber() == number) {
				throw new InvalidInputException("Table number already exists");
			}
		}
		Table specificTable;
		
		try {
			specificTable = new Table(number, x, y, width, length, restoApp);
		} catch (Exception e) {
			throw new InvalidInputException("Table already exists");
		}
		
		try {
			restoApp.addTable(specificTable);
			restoApp.addCurrentTable(specificTable);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		for(int i = 0; i < numberOfSeat; i++) {
			specificTable.addSeat();
		}
		//save
		try {
			RestoAppApplication.save();
		} catch (Exception e) {

			System.out.println(e.getMessage());
			throw e;
		}
	}

	public static void moveTable(Table table, int x, int y) throws InvalidInputException {
		
		if (x <0 || y < 0) {
			throw new InvalidInputException("Invalid position");
		}
		RestoApp restoApp = RestoAppApplication.getRestoApp();
		int width = table.getWidth();
		int length = table.getLength();
		List<Table> currentTables = restoApp.getCurrentTables();
		for(Table t : currentTables) {
			if (t == table) {
				continue;
			}
			if (t.checkOverlap(x,y,length, width)) {
				throw new InvalidInputException("Table overlap");
			}
		}
		table.setX(x);
		table.setY(y);
		try {
			RestoAppApplication.save();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
		
	}

	public static List<MenuItem> displayMenu(ItemCategory itemCategory) throws InvalidInputException{
		if(itemCategory == null){
			throw new InvalidInputException("Item Category is null");
		}
		List<MenuItem> listItems = new ArrayList<MenuItem>();
		RestoApp restoApp = RestoAppApplication.getRestoApp();
		Menu menu = restoApp.getMenu();
		for(MenuItem menuItem : menu.getMenuItems()){
			boolean current = menuItem.hasCurrentPricedMenuItem();
			ItemCategory category = menuItem.getItemCategory();
			if(current && category.equals(itemCategory)){
				listItems.add(menuItem);	
			}	
		}
		return listItems;
	}
	
	public static List<ItemCategory> getItemCategory(){
		ItemCategory [] itemcategories = ItemCategory.values();
		List <ItemCategory> itemCategories = new ArrayList <ItemCategory>();
		for (ItemCategory aItemcategory:itemcategories) {
			itemCategories.add(aItemcategory);
		}
		RestoAppApplication.save();
		return itemCategories;
	}

	public static void addReservation(Date date, Time time, int numberInParty, String contactName, String contactEmail, String contactPhone, List<Table> tables) throws InvalidInputException{
		if(tables == null){
			throw new InvalidInputException("Select at least one table");
		}
		if(date == null){
			throw new InvalidInputException("Select a date");
		}
		if(time == null) {
			throw new InvalidInputException("Select a time");
		}
		
		if(contactName.isEmpty() || contactEmail.isEmpty() || contactPhone.isEmpty()){
			throw new InvalidInputException("Invalid contact info");
		}

		RestoApp restoApp = RestoAppApplication.getRestoApp();
		List<Table> currentTables = restoApp.getCurrentTables();
		int seatCapacity = 0;
		for(Table table : tables){
			if(!currentTables.contains(table)){
				throw new InvalidInputException("No Tables exist");
			}
			seatCapacity += table.numberOfCurrentSeats();
			List<Reservation> reservations= table.getReservations();
			for(Reservation reservation: reservations){
				if(reservation.checkOverlap(date,time)){
					throw new InvalidInputException("Reservation overlap");
				}
			}
		}
		if(seatCapacity < numberInParty){
			throw new InvalidInputException("Not enough seats");
		}
		Table[] tableArray;
		tableArray = new Table[tables.size()];
		int i = 0;
		for(Table table: tables){
			tableArray[i]=table;
			i++;
		}
		Reservation res = new Reservation(date,time,numberInParty,contactName,contactEmail,contactPhone, restoApp,tableArray);
		
		//save
		try {
			RestoAppApplication.save();
		} catch (Exception e) {

			System.out.println(e.getMessage());
			throw e;
		}
	}

	public static boolean isAvailable(Table table){
		boolean isFree = true;
		RestoApp restoApp = RestoAppApplication.getRestoApp();
		for (Order currentOrder : restoApp.getCurrentOrders()) {
			if (currentOrder.getTables().contains(table)) {
				isFree = false;
				break;
			}
		}
		return isFree;
	}
	
	public static boolean isAvailable(Seat seat){
		boolean isFree = true;
		RestoApp restoApp = RestoAppApplication.getRestoApp();
		for (Order currentOrder : restoApp.getCurrentOrders()) {
			for (OrderItem item : currentOrder.getOrderItems()) {
				if (item.getSeats().contains(seat)) {
					isFree = false;
					break;
				}
			}
		}
		return isFree;
	}
	
	public static void startOVer(List<Table> tables) throws InvalidInputException{
		if (tables == null) {
			throw new InvalidInputException("Invalid Input");
		}
		RestoApp restoApp = RestoAppApplication.getRestoApp();
		List<Table> currentTables = restoApp.getCurrentTables();
		for (Table table : tables) {
			if (!currentTables.contains(table)) {
				throw new InvalidInputException("Table not in CurrentTables list");
			}
		}
		boolean orderCreated = false;
		Order newOrder = null;
		for (Table table : tables) {
			if (orderCreated) {
				table.addToOrder(newOrder);
			}
			else {
				Order lastOrder = null;
				if (table.numberOfOrders() > 0) {
					lastOrder = table.getOrder(table.numberOfOrders()-1);
				}
				table.startOrder();
				if (table.numberOfOrders() > 0 && !table.getOrder(table.numberOfOrders()-1).equals(lastOrder)) {
					orderCreated = true;
					newOrder = table.getOrder(table.numberOfOrders()-1);
				}
			}
		}
		if(orderCreated == false) {
			throw new InvalidInputException();
		}
		restoApp.addCurrentOrder(newOrder);
		try {
			RestoAppApplication.save();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
  
	public static void endOrder(Order order) throws InvalidInputException{
		if (order == null) {
			throw new InvalidInputException("Invalid Order");
		}
		RestoApp restoApp = RestoAppApplication.getRestoApp();
		if (!restoApp.getCurrentOrders().contains(order)) {
			throw new InvalidInputException("Order not active");
		}
		List<Table> tables = order.getTables();
		for (Table table : tables) {
			if (table.numberOfOrders() > 0 && table.getOrder(table.numberOfOrders()-1).equals(order)) {
				endOrder(order);
			}
		}
		if (allTablesAvailableOrDifferentCurrentOrder(tables, order)) {
			restoApp.removeCurrentOrder(order);
		}
		try {
			RestoAppApplication.save();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
		
	}
	
	public static boolean allTablesAvailableOrDifferentCurrentOrder(List<Table> tables, Order order) {
		boolean canRemove = true;
		for (Table table : tables) {
			if (table.getStatusFullName() != "Available") {
				if (table.getOrder(table.numberOfOrders()-1).equals(order)) {
					canRemove = false;
					break;
				}
			}
		}
		return canRemove;
	}
	
	//public void rotateTable(Table table);
}
