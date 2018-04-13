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
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.OrderItem;
import ca.mcgill.ecse223.resto.model.Table;

public class ViewOrderFrame extends JFrame {
	
	public ViewOrderFrame(RestoAppPage restoAppPage, Table t) {
		// TODO Auto-generated constructor stub
		JFrame viewOrder = new JFrame("View Order");
	    viewOrder.setResizable(false);
	    viewOrder.setAlwaysOnTop(true);
	    
	    //COMPONENTS
	    DefaultListModel<OrderItem> listModel = new DefaultListModel<OrderItem>();
	    JList<OrderItem> list = new JList<OrderItem>(listModel); 


	    
	    list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	    list.setLayoutOrientation(JList.VERTICAL);
	    list.setVisibleRowCount(-1);

	    JScrollPane listScroller = new JScrollPane(list);
	    
	    
	    //JLabel orderDisplay = new JLabel("Orders");
	    MyButton exitViewOrder = new MyButton("Close");
	    //END COMPONENTS
	   
	    //INIT
	    
	    //needs to display whatever orders there is
	    
	    //END INIT
	    
	    exitViewOrder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				viewOrder.dispose();
				
			}
		});
	    List<OrderItem> orderItems = t.getOrder(t.numberOfOrders()-1).getOrderItems();
		for(OrderItem oi : orderItems) {
	    	listModel.addElement(oi);
	    }
//		try {
//			orderItems = RestoAppController.getOrderItems(t);
//		    for(OrderItem oi : orderItems) {
//		    	orderList += oi.getPricedMenuItem().getMenuItem().getName() + "\n";
//		    }
//		} catch (InvalidInputException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

	    java.awt.Container contentPane = viewOrder.getContentPane();
	    
	    GroupLayout layout = new GroupLayout(contentPane);
		contentPane.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup()
					.addGroup(				
						layout.createSequentialGroup()
							.addComponent(list, 400, 400, 800))
					
					.addGroup(layout.createSequentialGroup()
							.addComponent(exitViewOrder)));
		
		layout.setVerticalGroup(
				   layout.createSequentialGroup()
				      .addGroup(layout.createParallelGroup()
				           .addComponent(list))
				      
				      .addGroup(layout.createParallelGroup()
				    		  .addComponent(exitViewOrder)));
	    
	    viewOrder.pack();
	    viewOrder.setVisible(true);
	}
}
