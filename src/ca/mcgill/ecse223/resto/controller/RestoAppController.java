package ca.mcgill.ecse223.resto.controller;

import java.util.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.Table;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Menu;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.model.Order;



public class RestoAppController {
	
	public RestoAppController() {
	}
	
	public static List<Table> getCurrentTables() {
		return RestoAppApplication.getRestoApp().getCurrentTables();
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
				throw new RuntimeException();
			}
		}
		Table specificTable;
		try {
			specificTable = restoApp.addTable(number, x, y, width, length);
		} catch (Exception e) {
			throw new InvalidInputException("Table number already exists");
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
		
		List<MenuItem> listItems = new ArrayList<MenuItem>();
		RestoApp restoApp = RestoAppApplication.load();
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
	
  //public void rotateTable(Table table);
}
