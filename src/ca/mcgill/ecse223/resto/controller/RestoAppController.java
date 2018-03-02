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
		for(MenuItem menuItem : menu.getMenuItems() ){
			boolean current = menuItem.hasCurrentPricedMenuItem();
			ItemCategory category = menuItem.getItemCategory();
			if(current && category.equals(itemCategory)){
				listItems.add(menuItem);
			}	
		}
		try {
			RestoAppApplication.save();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
		return listItems;
	}
	
  //public void rotateTable(Table table);
}
