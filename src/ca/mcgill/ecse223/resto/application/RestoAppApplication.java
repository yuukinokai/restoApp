package ca.mcgill.ecse223.resto.application;

import java.util.List;

import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.view.RestoAppPage;
import ca.mcgill.ecse223.resto.persistence.PersistenceObjectStream;


public class RestoAppApplication {
	private static RestoApp restoApp;
	private static String filename = "menu.resto";
	
	public static void main(String[] args) {
		// start UI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RestoAppPage().setVisible(true);
            }
        });
        
	}
	public static RestoApp getRestoApp() {
		if(restoApp == null){
			restoApp = load();
		}
 		return restoApp;
	}
	
	public static void save() {
		PersistenceObjectStream.serialize(restoApp);
	}
	
	public static RestoApp load() {
		PersistenceObjectStream.setFilename(filename);
		restoApp = (RestoApp) PersistenceObjectStream.deserialize();
		// model cannot be loaded - create empty restoApp
		if (restoApp == null) {
			restoApp = new RestoApp();
		}
		//else {
		//	restoApp.reinitialize();
		//}
		return restoApp;
	}
	
	public static void setFilename(String newFilename) {
		filename = newFilename;
	}
	


}
