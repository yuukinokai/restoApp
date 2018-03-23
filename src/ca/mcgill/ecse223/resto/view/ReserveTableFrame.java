package ca.mcgill.ecse223.resto.view;


import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;

import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.controller.RestoAppController;
import ca.mcgill.ecse223.resto.model.Table;

public class ReserveTableFrame extends JPanel {

public ReserveTableFrame(RestoAppPage app) {
    JFrame frame = new JFrame("ReserveTableFrame");
    frame.setResizable(false);
    frame.setAlwaysOnTop(true);
  //Date date, Time time, int numberInParty, String contactName, String contactEmail, String contactPhone, List<Table>
    //COMPONENTS
    JLabel numberInParty = new JLabel("# People");
    JLabel contactName = new JLabel("Name");
    JLabel contactEmail = new JLabel("Email");
    JLabel contactPhone = new JLabel("Phone");
    JTextField textNumberInParty  = new JTextField();
    JTextField textName = new JTextField();
    JTextField textEmail = new JTextField();
    JTextField textPhone = new JTextField();
    JButton btnReserve = new JButton("Reserve");
    JButton btnClose = new JButton("Close");
    
    //END COMPONENTS
    
    //INIT
    
    //END INIT
    
    btnClose.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
			// TODO Auto-generated method stub
			
		}
	});
    
    btnReserve.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
//			try {
//				//int x = Integer.parseInt(txtX.getText());
//				//int y = Integer.parseInt(txtY.getText());
//
//				//RestoAppController.moveTable(t, x, y);
//				
//				frame.dispose();
//				app.refreshData();
//			} catch (NumberFormatException ex) {
//		        JOptionPane.showMessageDialog(null, "Please enter coordinates in integer", null, JOptionPane.ERROR_MESSAGE);
//
//			} catch (InvalidInputException ex) {
//		        JOptionPane.showMessageDialog(null, ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
//			}  catch (Exception ex) {
//		        JOptionPane.showMessageDialog(null, "Unknown exception: " + ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
//			}
//			 
			
		}
	});
    
    
//    frame.setDefaultCloseOperation(JFrame.);

java.awt.Container contentPane = frame.getContentPane();
    
	GroupLayout layout = new GroupLayout(contentPane);
	contentPane.setLayout(layout);
	layout.setAutoCreateGaps(true);
	layout.setAutoCreateContainerGaps(true);
    
	layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(				
					layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(contactName)
								.addComponent(contactEmail)
								.addComponent(contactPhone)
								.addComponent(numberInParty))
						.addGroup(layout.createParallelGroup()
								.addComponent(textName)
								.addComponent(textEmail)
								.addComponent(textPhone)
								.addComponent(textNumberInParty)))
				.addGroup(
						layout.createSequentialGroup()
							.addComponent(btnReserve)
							.addComponent(btnClose)
						)
				);
			layout.setVerticalGroup(
			   layout.createSequentialGroup()
			      .addGroup(layout.createParallelGroup()
			           .addComponent(contactName)
			           .addComponent(textName))
			      .addGroup(layout.createParallelGroup()
				           .addComponent(contactEmail)
				           .addComponent(textEmail))
			      .addGroup(layout.createParallelGroup()
				           .addComponent(contactPhone)
				           .addComponent(textPhone))
			      .addGroup(layout.createParallelGroup()
				           .addComponent(numberInParty)
				           .addComponent(textNumberInParty))
			      .addGroup(layout.createParallelGroup()
			    		  .addComponent(btnReserve)
			    		  .addComponent(btnClose)
			    		  )
			);
	
    // Set the x, y, width and height properties
    frame.pack();
    frame.setVisible(true);
}
}