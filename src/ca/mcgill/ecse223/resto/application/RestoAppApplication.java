package ca.mcgill.ecse223.resto.application;

import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.persistence.PersistenceObjectStream;
import ca.mcgill.ecse223.resto.view.RestoAppPage;


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
		else {
			restoApp.reinitialize();
		}
	    if (restoApp.getCurrentTakeOut() == null) {
	    		restoApp.createDefaultTakeOut();
	    }

//		Table t = restoApp.addTable(0, 0, 0, 5, 10);
//		restoApp.addCurrentTable(t);
//		t = restoApp.addTable(1, 20,20, 10, 10);
//		restoApp.addCurrentTable(t);
		return restoApp;
	}
	
	public static void setFilename(String newFilename) {
		filename = newFilename;
	}
	


}
