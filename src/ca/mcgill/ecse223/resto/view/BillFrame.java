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
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.controller.RestoAppController;
import ca.mcgill.ecse223.resto.model.Bill;
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.OrderItem;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;

public class BillFrame extends JFrame {
	
	public BillFrame(Seat seat, int number) {
		// TODO Auto-generated constructor stub
		JFrame viewOrder = new JFrame("Bill");
	    viewOrder.setResizable(false);
	    viewOrder.setAlwaysOnTop(true);
	    String orderList = "Bill for seat : " + String.valueOf(number) + " ";
	    //COMPONENTS
	    JLabel order = new JLabel();
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
	    
	    Bill bill = seat.getBill(seat.numberOfBills()-1);
	    for(OrderItem oi : seat.getOrderItems()) {
	    	orderList +=  " || " + oi.getPricedMenuItem().getMenuItem().getName() + " ";
	    	double price = (oi.getPricedMenuItem().getPrice()/oi.getQuantity()); 
	    	orderList += String.valueOf(price);
	    }
		order.setText(orderList);

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
									.addComponent(order))
					
					.addGroup(layout.createSequentialGroup()
							.addComponent(exitViewOrder))));
		
		layout.setVerticalGroup(
				   layout.createSequentialGroup()
				      .addGroup(layout.createParallelGroup()
				           .addComponent(order))
				      
				      .addGroup(layout.createParallelGroup()
				    		  .addComponent(exitViewOrder)));
	    
	    viewOrder.pack();
	    viewOrder.setVisible(true);
	}
}
