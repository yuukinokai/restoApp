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
		try {
			restoApp.removeCurrentTable(table);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static List<MenuItem> displayMenu(ItemCategory itemCategory) throws InvalidInputException{
		
		List<MenuItem> listItems = new ArrayList<MenuItem>();
		RestoApp restoApp = RestoAppApplication.load();
		//Menu menu = restoApp.getMenu();
		Menu menu =new Menu();
		MenuItem a = new MenuItem("Apple", menu);
		a.setItemCategory(ItemCategory.Appetizer);
		MenuItem b = new MenuItem("Banana", menu);
		a.setItemCategory(ItemCategory.Main);
		MenuItem c = new MenuItem("Carrot", menu);
		a.setItemCategory(ItemCategory.Dessert);
		MenuItem d = new MenuItem("Dinosaur", menu);
		a.setItemCategory(ItemCategory.AlcoholicBeverage);
		MenuItem e = new MenuItem("Elephant", menu);
		a.setItemCategory(ItemCategory.NonAlcoholicBeverage);
		menu.addMenuItem(a);
		menu.addMenuItem(b);
		menu.addMenuItem(c);
		menu.addMenuItem(d);
		menu.addMenuItem(e);
		for(MenuItem menuItem : menu.getMenuItems() ){
			//boolean current = menuItem.hasCurrentPricedMenuItem();
			ItemCategory category = menuItem.getItemCategory();
			//if(current && category.equals(itemCategory)){
			//	listItems.add(menuItem);
			//}
			if(category.equals(itemCategory)){
				listItems.add(menuItem);
			}
		}
		
		for(MenuItem menuItem: listItems){
			System.out.println(menuItem.getName());
		}
		return listItems;
	}
	
			


  //public void moveTable(Table table, int x, int y);
  //public void rotateTable(Table table);
}
