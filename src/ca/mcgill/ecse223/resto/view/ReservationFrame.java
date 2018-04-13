package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.controller.RestoAppController;
import ca.mcgill.ecse223.resto.model.Bill;
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.OrderItem;
import ca.mcgill.ecse223.resto.model.Reservation;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;

public class ReservationFrame extends JFrame {
	
	public ReservationFrame(Reservation res) {
		// TODO Auto-generated constructor stub
		JFrame viewOrder = new JFrame("Reservation");
	    viewOrder.setResizable(false);
	    viewOrder.setAlwaysOnTop(true);
	    
	    DefaultListModel<String> listModel = new DefaultListModel<String>();
	    JList<String> list = new JList<String>(listModel); 


	    
	    list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	    list.setLayoutOrientation(JList.VERTICAL);
	    list.setVisibleRowCount(-1);

	    JScrollPane listScroller = new JScrollPane(list);
	    
	    
	    //COMPONENTS
	    listModel.addElement("Reservation Number " + res.getReservationNumber());
	    listModel.addElement("Name : " + res.getContactName());
	    List<Table> tables = res.getTables();
	    String tableNumbers = "Table Numbers ";
	    
	    for(Table table : tables) {
	    	tableNumbers = tableNumbers + "#" + String.valueOf(table.getNumber()) + " ";
	    }
	    listModel.addElement(tableNumbers);
	    listModel.addElement("Phone Number : " + res.getContactPhoneNumber());
	    listModel.addElement("Email : " + res.getContactEmailAddress());
	    listModel.addElement("Number in party : " + res.getNumberInParty());
	    listModel.addElement("Date : " + String.valueOf(res.getDate()));
	    listModel.addElement("Time : " + String.valueOf(res.getTime()));
//	    JLabel reservation = new JLabel("Reservation Number " + res.getReservationNumber());
//	    JLabel name = new JLabel("Name : " + res.getContactName());
//	    List<Table> tables = res.getTables();
//	    String tableNumbers = "Table Numbers ";
//	    
//	    for(Table table : tables) {
//	    	tableNumbers = tableNumbers + "#" + String.valueOf(table.getNumber()) + " ";
//	    }
//	    JLabel table = new JLabel(tableNumbers);
//	    JLabel phone = new JLabel("Phone Number : " + res.getContactPhoneNumber());
//	    JLabel email = new JLabel("Email : " + res.getContactEmailAddress());
//	    JLabel number = new JLabel("Number in party : " + res.getNumberInParty());
//	    JLabel date = new JLabel("Date : " + String.valueOf(res.getDate()));
//	    JLabel time = new JLabel("Time : " + String.valueOf(res.getTime()));
	    //JLabel orderDisplay = new JLabel("Orders");
	    MyButton exitViewOrder = new MyButton("Close");
	    //END COMPONENTS
	    //END INIT
	    
	    exitViewOrder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				viewOrder.dispose();
			}
		});

	    java.awt.Container contentPane = viewOrder.getContentPane();
	    
	    GroupLayout layout = new GroupLayout(contentPane);
		contentPane.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup()
					.addGroup(				
						layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup()
									.addComponent(list))
//									.addComponent(reservation)
//									.addComponent(name)
//							           .addComponent(table)
//							           .addComponent(phone)
//							           .addComponent(email)
//							           .addComponent(date)
//							           .addComponent(time)
//							           .addComponent(number))
							
					
					.addGroup(layout.createSequentialGroup()
							.addComponent(exitViewOrder))));
		
		layout.setVerticalGroup(
				   layout.createSequentialGroup()
				      .addGroup(layout.createParallelGroup()
				    		  .addComponent(list))
//				           .addComponent(reservation)
//				           )
//				      
//				      .addGroup(layout.createSequentialGroup()
//								.addComponent(name))
//						.addGroup(layout.createSequentialGroup()
//								.addComponent(table))
//						.addGroup(layout.createSequentialGroup()
//								.addComponent(phone))
//						.addGroup(layout.createSequentialGroup()
//								.addComponent(email))
//						.addGroup(layout.createSequentialGroup()
//								.addComponent(date))
//						.addGroup(layout.createSequentialGroup()
//								.addComponent(time))
//						.addGroup(layout.createSequentialGroup()
//								.addComponent(number))
				      
				      
				      .addGroup(layout.createParallelGroup()
				    		  .addComponent(exitViewOrder)));
	    
	    viewOrder.pack();
	    viewOrder.setVisible(true);
	}
}
