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
import javax.swing.SwingConstants;

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
	    
	    DefaultListModel<String> listModel2 = new DefaultListModel<String>();
	    JList<String> list2 = new JList<String>(listModel2); 
	    list2.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	    list2.setLayoutOrientation(JList.VERTICAL);
	    list2.setVisibleRowCount(-1);
	    JScrollPane listScroller2 = new JScrollPane(list2);
	    
	    DefaultListModel<String> listModel3 = new DefaultListModel<String>();
	    JList<String> list3 = new JList<String>(listModel3); 
	    list3.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	    list3.setLayoutOrientation(JList.VERTICAL);
	    list3.setVisibleRowCount(-1);
	    JScrollPane listScroller3 = new JScrollPane(list3);
	    
	    DefaultListModel<String> listModel4 = new DefaultListModel<String>();
	    JList<String> list4 = new JList<String>(listModel4); 
	    list4.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	    list4.setLayoutOrientation(JList.VERTICAL);
	    list4.setVisibleRowCount(-1);
	    JScrollPane listScroller4 = new JScrollPane(list4);
	    
	    DefaultListModel<String> listModel5 = new DefaultListModel<String>();
	    JList<String> list5 = new JList<String>(listModel5); 
	    list5.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	    list5.setLayoutOrientation(JList.VERTICAL);
	    list5.setVisibleRowCount(-1);
	    JScrollPane listScroller5 = new JScrollPane(list5);
	    
	    DefaultListModel<String> listModel6 = new DefaultListModel<String>();
	    JList<String> list6 = new JList<String>(listModel6); 
	    list6.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	    list6.setLayoutOrientation(JList.VERTICAL);
	    list6.setVisibleRowCount(-1);
	    JScrollPane listScroller6 = new JScrollPane(list6);
	    
	    DefaultListModel<String> listModel7 = new DefaultListModel<String>();
	    JList<String> list7 = new JList<String>(listModel7); 
	    list7.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	    list7.setLayoutOrientation(JList.VERTICAL);
	    list7.setVisibleRowCount(-1);
	    JScrollPane listScroller7 = new JScrollPane(list7);
	    
	    DefaultListModel<String> listModel8 = new DefaultListModel<String>();
	    JList<String> list8 = new JList<String>(listModel8); 
	    list8.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	    list8.setLayoutOrientation(JList.VERTICAL);
	    list8.setVisibleRowCount(-1);
	    JScrollPane listScroller8 = new JScrollPane(list8);
	    
	    
	    //COMPONENTS
	    JLabel reservation = new JLabel("Reservation Number ");
	    listModel.addElement(String.valueOf(res.getReservationNumber()));
	    
	    JLabel name = new JLabel("Name : ");
	    listModel2.addElement("" + res.getContactName());
	    
	    List<Table> tables = res.getTables();
	    String tableNumbers = "";
	    
	    for(Table table : tables) {
	    	tableNumbers = "#" + String.valueOf(table.getNumber()) + " ";
	    }
	    JLabel table = new JLabel("Table Numbers ");
	    listModel3.addElement(tableNumbers);
	    
	    JLabel phone = new JLabel("Phone Number : ");
	    listModel4.addElement("" + res.getContactPhoneNumber());
	    JLabel email = new JLabel("Email : ");
	    listModel5.addElement("" + res.getContactEmailAddress());
	    
	    JLabel number = new JLabel("Number in party : ");
	    listModel6.addElement("" + res.getNumberInParty());
	    JLabel date = new JLabel("Date : ");
	    listModel7.addElement("" + String.valueOf(res.getDate()));
	    JLabel time = new JLabel("Time : ");
	    listModel8.addElement("" + String.valueOf(res.getTime()));
	    
	    
	   
	    
	    
	    
	    
	    
	    
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
					.addGroup(layout.createSequentialGroup()
								.addComponent(reservation)
								.addComponent(list)
							)
					.addGroup(layout.createSequentialGroup()
							.addComponent(name)
							.addComponent(list2)
							)
					.addGroup(layout.createSequentialGroup()
							.addComponent(table)
							.addComponent(list3)
							)
					.addGroup(layout.createSequentialGroup()
							.addComponent(phone)
							.addComponent(list4)
							)
					.addGroup(layout.createSequentialGroup()
							.addComponent(email)
							.addComponent(list5)
							)
					.addGroup(layout.createSequentialGroup()
							.addComponent(date)
							.addComponent(list7)
							)
					.addGroup(layout.createSequentialGroup()
							.addComponent(time)
							.addComponent(list8)
							)
					.addGroup(layout.createSequentialGroup()
							.addComponent(number)
							.addComponent(list6)
							)
					.addGroup(layout.createSequentialGroup()
							.addComponent(exitViewOrder)
							)
					);

		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup()
						.addComponent(reservation)
						.addComponent(list))
				
				.addGroup(
						layout.createParallelGroup()
						.addComponent(name)
						.addComponent(list2))
				.addGroup(layout.createParallelGroup()
						.addComponent(table)
						.addComponent(list3)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(phone)
						.addComponent(list4)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(email)
						.addComponent(list5)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(date)
						.addComponent(list7)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(time)
						.addComponent(list8)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(number)
						.addComponent(list6)
						)
				
				.addComponent(exitViewOrder)
				);
		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {list, list2, list3, list4, list5, list6, list7, list8});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {list, list2, list3, list4, list5, list6, list7, list8});
		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {reservation, name, table, phone, email,date,time, number});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {reservation, name, table, phone, email,date,time, number});

	    
	    viewOrder.pack();
	    viewOrder.setVisible(true);
	}
}
