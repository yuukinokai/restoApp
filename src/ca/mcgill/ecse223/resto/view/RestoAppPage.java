package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.sun.xml.internal.ws.api.server.Container;

import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.controller.RestoAppController;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.model.Table;


public class RestoAppPage extends JFrame{
	
	private static final long serialVersionUID = -3496706717743749508L;
	private DefaultComboBoxModel model = new DefaultComboBoxModel<Table>();

	private JLabel errorMessage;
	private String error = null;
	
	//DELETE TABLE
	private JLabel selectTableLabel;
	private JComboBox<Table> currentTableList;
	private JButton deleteTable;
	private Integer selectedTable = -1;
	private HashMap<Integer, Table> tables;
	//END DELETE TABLE
	
	//UPDATE TABLE
	private JButton updateTable;
	//END UPDATE TABLE
	
	//MOVE TABLE
	private JButton moveTable;
	//END MOVE TABLE
	
	//Display Menu
	String list[] = {"Appetizer", "Main", "Dessert", "AlcoholicBeverage", "NonAlcoholicBeverage"};
	private JLabel selectMenuLabel;
	private JComboBox<String>itemCategoryList;
	private JButton displayMenu;
	private ItemCategory selectedMenu = null;
	private HashMap<Integer, ItemCategory> items;
	//End Display Menu
	
	public RestoAppPage() {
		initComponents();
		refreshData();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// TODO Auto-generated method stub
		//ERROR MESSAGE
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		//END ERROR MESSAGE
		
		//DELETE TABLE
		selectTableLabel = new JLabel();
		currentTableList = new JComboBox<Table>();
		currentTableList.setModel(model);
		currentTableList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
		        JComboBox<String> cb = (JComboBox<String>) evt.getSource();
		        selectedTable = cb.getSelectedIndex();
			}
		});
		deleteTable = new JButton();
		selectTableLabel.setText("Select Table");
		deleteTable.setText("Delete Table");
		deleteTable.addActionListener(new java.awt.event.ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
				error = null;
				try {
					deleteTableButtonActionPerformed(evt, (Table)currentTableList.getSelectedItem());
				}
				catch (NullPointerException ex) {
					error = "Please select a valid table";
					errorMessage.setText(error);
				}
				
			}
		});
		//END DELETE TABLE
		
		//UPDATE TABLE
		updateTable = new JButton();
		updateTable.setText("Update Table");
		updateTable.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				error = null;
				try {
					Table t = (Table)currentTableList.getSelectedItem();
					if (t == null) {
						throw new NullPointerException();
					}
					updateTableButtonActionPerformed(event, t);
				} catch (NullPointerException ex) {
					errorMessage.setText("Please select a valid table");
				}
				
			}
		});
		//END UPDATE TABLE
		
		//MOVE TABLE 
		moveTable = new JButton();
		moveTable.setText("Move Table");
		moveTable.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				error = null;
				try {
					Table t = (Table)currentTableList.getSelectedItem();
					if (t == null) {
						throw new NullPointerException();
					}
					moveTableButtonActionPerformed(e, t);
				} catch (NullPointerException ex) {
					errorMessage.setText("Please select a valid table");
				}
				
			}
		});
		//END MOVE TABLE
		
		//DISPLAY MENU
		selectMenuLabel = new JLabel();
		itemCategoryList = new JComboBox<String>(new String[0]);
		for(String str : list){
			itemCategoryList.addItem(str);
		}
		itemCategoryList.setSelectedIndex(-1);
		itemCategoryList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
		        JComboBox<String> cb = (JComboBox<String>) evt.getSource();
		        selectedMenu = (ItemCategory) cb.getSelectedItem();      
			}
		});
		selectMenuLabel.setText("Select Menu");
		selectedMenu = null;
		displayMenu = new JButton();
		displayMenu.setText("Display Menu");
		displayMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				displayMenuButtonActionPerformed(evt);
			}
		});
		//END DISPLAY MENU
		
		
		//layout
		 

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		//HORIZONTAL
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				//ERROR MESSAGE
				.addComponent(errorMessage)
				//END ERROR MESSAGE
				
				
				//DELETE TABLE HORIZONTAL
				.addGroup(layout.createSequentialGroup()
						.addComponent(selectTableLabel)
						.addGroup(layout.createParallelGroup()
								.addComponent(currentTableList, 200, 200, 400)
								.addComponent(updateTable)
								.addComponent(deleteTable)
								.addComponent(moveTable)))
								
				//END DELETE TABLE 
				
				//DISPLAY MENU HORIZONTAL
				.addGroup(layout.createSequentialGroup()
						.addComponent(selectMenuLabel)
						.addGroup(layout.createParallelGroup()
							.addComponent(itemCategoryList, 200, 200, 400)
							.addComponent(displayMenu)))
				//END DISPLAY MENU
				);
		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {currentTableList, deleteTable});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {currentTableList, deleteTable});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {currentTableList, updateTable});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {currentTableList, moveTable});
		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {itemCategoryList, displayMenu});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {itemCategoryList, displayMenu});
		//VERTICAL
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				//ERROR MESSAGE
				.addComponent(errorMessage)
				//END ERROR MESSAGE
				
				//DELETE TABLE VERTICAL
				.addGroup(layout.createParallelGroup()
						.addComponent(selectTableLabel)
						.addComponent(currentTableList))
				.addGroup(layout.createParallelGroup()
						.addComponent(deleteTable))
				//END DELETE TABLE
				//UPDATE TABLE
				.addGroup(layout.createParallelGroup()
						.addComponent(updateTable))
				//END UPDATE TABLE
				.addGroup(layout.createParallelGroup()
						.addComponent(moveTable))
				//DISPLAY MENU VERTICAL
				.addGroup(layout.createParallelGroup()
						.addComponent(selectMenuLabel)
						.addComponent(itemCategoryList))
				.addGroup(layout.createParallelGroup()
						.addComponent(displayMenu))
				//END DISPLAY MENU
				);
		//END DELETE TABLE
		
		
		pack();
	}
	
	
	protected void moveTableButtonActionPerformed(ActionEvent e, Table t) {
		new MoveTableFrame(this, t);
	}
	
	protected void updateTableButtonActionPerformed(ActionEvent e, Table t) {
		new UpdateTableFrame(this, t);
	}

	protected void deleteTableButtonActionPerformed(ActionEvent evt, Table table) {
		// DELETE TABLE BUTTON
		// clear error message
		error = null;
		
		// call the controller
		try {
			RestoAppController.deleteTable(table);
		} catch (InvalidInputException e) {
			error = e.getMessage();
			errorMessage.setText(error);
		}
		
		// update visuals
		refreshData();
	}
	
	private void displayMenuButtonActionPerformed(ActionEvent evt) {
		// DISPLAY MENU BUTTON
		// clear error message
		error = null;
		
		//call the controller
		try{
			RestoAppController.displayMenu(items.get(selectedMenu));
		}catch(InvalidInputException e){
			error = e.getMessage();
		}
		
	}

	public void refreshData() {
		// TODO Auto-generated method stub
		// error
				errorMessage.setText(error);
				
				if (error == null || error.length() == 0) {
					// populate page with data
				
//					tables = new HashMap<Integer, Table>();
//					currentTableList.removeAllItems();
//					Integer index = 0;

					model.removeAllElements();
					for (Table table : RestoAppController.getCurrentTables()) {
						model.addElement(table);
					};
					selectedTable = -1;
					currentTableList.setSelectedIndex(selectedTable);
				}

		
	}
}
