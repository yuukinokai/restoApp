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



public class RestoAppController {
	
	public RestoAppController() {
	}
	
	public static void deleteTable(Table table) throws InvalidInputException{
		boolean hasReservation = table.hasReservations();
		RestoApp restoApp = RestoAppApplication.getRestoApp();
		String error;
		if (hasReservation) {
			error = "Table has reservations";
		}
		
	}
			


  //public void moveTable(Table table, int x, int y);
  //public void rotateTable(Table table);
}
