package ca.mcgill.ecse223.resto.application;

import java.util.List;

import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.view.RestoAppPage;

public class RestoAppApplication {
	private static RestoApp restoApp;
	
	public static void main(String[] args) {
		// start UI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RestoAppPage().setVisible(true);
            }
        });
        
	}
	
	public static RestoApp getRestoApp() {
		
 		return restoApp;
	}

}
