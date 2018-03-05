package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.sun.xml.internal.ws.api.server.Container;

import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.controller.RestoAppController;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.model.Table;


public class RestoAppPage extends JFrame{
	
	private static final long serialVersionUID = -3496706717743749508L;
	private DefaultComboBoxModel model = new DefaultComboBoxModel<Table>();

	private JLabel errorMessage;
	private String error = null;
	private TableVisualizer tableVisualizer;
	private JPanel leftMenu;

	//ADD TABLE
	private JLabel tableNumberLabel;
	private JTextField tableNumberBox;
	private JLabel xyCoordLabel;
	private JTextField xCoordBox;
	private JTextField yCoordBox;
	private JLabel widthLengthLabel;
	private JTextField widthBox;
	private JTextField lengthBox;
	private JLabel numberOfSeatLabel;
	private JTextField numberOfSeatBox;
	private JButton createTable;
	//END ADD TABLE
	
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
	
	//DISPLAY MENU
	String list[] = {"Appetizer", "Main", "Dessert", "AlcoholicBeverage", "NonAlcoholicBeverage"};
	private JLabel selectMenuLabel;
	private JComboBox<String>itemCategoryList;
	private JButton displayMenu;
	private Integer selectedMenu=-1;
	private HashMap<Integer, ItemCategory> items;
	//END DISPLAY MENU
	
	//JPANELS
	private DisplayMenuPage menu = new DisplayMenuPage();
	private TableVisualizer restoMap = new TableVisualizer();
	
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
		tableVisualizer = new TableVisualizer();
		
		//ADD TABLE
		//display errors to fix
		tableNumberLabel = new JLabel();
		tableNumberLabel.setText("Table Number:          ");
		tableNumberBox = new JTextField();
		xyCoordLabel = new JLabel();
		xyCoordLabel.setText("X & Y Coordinates:  ");
		xCoordBox = new JTextField();
		yCoordBox = new JTextField();
		widthLengthLabel = new JLabel();
		widthLengthLabel.setText("Width & Length:       ");
		widthBox = new JTextField();
		lengthBox = new JTextField();
		numberOfSeatLabel = new JLabel();
		numberOfSeatLabel.setText("Number Of seats:    ");
		numberOfSeatBox = new JTextField();
		createTable = new JButton();
		createTable.setText("Create Table");
		createTable.addActionListener(new ActionListener() {
			
			//"CreateTableButtonAxtionPerformed" fused in here
			@Override
			public void actionPerformed(ActionEvent e) {
				error = null;
				if(tableNumberBox.getText() == ("") || 
						xCoordBox.getText() == ("") || 
						yCoordBox.getText() == ("") || 
						 widthBox.getText() == ("") || 
						lengthBox.getText() == ("") || 
						numberOfSeatBox.getText() == ("")) {
					createTable.setEnabled(false);
				} else {
					try {
						RestoAppController.addTable(Integer.parseInt(tableNumberBox.getText()),
								Integer.parseInt(xCoordBox.getText()),
								Integer.parseInt(yCoordBox.getText()),
								Integer.parseInt(widthBox.getText()),
								Integer.parseInt(lengthBox.getText()),
								Integer.parseInt(numberOfSeatBox.getText()));
					} 
					//...what?
					catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InvalidInputException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			}
		});
		//END ADD TABLE

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
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
		       // JComboBox<String> cb = (JComboBox<String>) evt.getSource();
		        //selectedMenu =cb.getSelectedIndex();
				selectedMenu = itemCategoryList.getSelectedIndex();
			}
		});
		selectMenuLabel.setText("Select Menu");
		//selectedMenu = null;
		displayMenu = new JButton("Display Menu");
		selectedMenu = null;
		displayMenu.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				error =null;
				try{
					displayMenuButtonActionPerformed(evt);
				}
				catch(NullPointerException ex){
					errorMessage.setText("Please select a valid menu");
				}
			}
		});
		//END DISPLAY MENU
		
		
		//LEFT MENU LAYOUT(JPANEL)
		 
		leftMenu = new JPanel();
		GroupLayout layout = new GroupLayout(leftMenu);
		leftMenu.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		//HORIZONTAL
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				//ERROR MESSAGE
				.addComponent(errorMessage)
				//END ERROR MESSAGE
				
				//ADD TABLE HORIZONTAL
				.addGroup(layout.createSequentialGroup()
						.addComponent(tableNumberLabel)
						.addGroup(layout.createParallelGroup()
								.addComponent(tableNumberBox, 200, 200, 400)))
				
				.addGroup(layout.createSequentialGroup()
						.addComponent(xyCoordLabel)
						.addGroup(layout.createParallelGroup()
								.addComponent(xCoordBox, 200, 200, 200)
								.addComponent(yCoordBox, 200, 200, 200)))
				
				.addGroup(layout.createSequentialGroup()
						.addComponent(widthLengthLabel)
						.addGroup(layout.createParallelGroup()
								.addComponent(widthBox, 200, 200, 200)
								.addComponent(lengthBox, 200, 200, 200)))
				
				.addGroup(layout.createSequentialGroup()
						.addComponent(numberOfSeatLabel)
						.addGroup(layout.createParallelGroup()
								.addComponent(numberOfSeatBox, 200, 200, 400)
								.addComponent(createTable)))
				//END ADD TABLE

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
				
				//.addComponent(tableVisualizer)
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
				
				//ADD TABLE VERTICAL
				.addGroup(layout.createParallelGroup()
						.addComponent(tableNumberLabel)
						.addComponent(tableNumberBox))
				
				.addGroup(layout.createParallelGroup()
						.addComponent(xyCoordLabel)
						.addComponent(xCoordBox)
						.addComponent(yCoordBox))
				
				.addGroup(layout.createParallelGroup()
						.addComponent(widthLengthLabel)
						.addComponent(widthBox)
						.addComponent(lengthBox))
				
				.addGroup(layout.createParallelGroup()
						.addComponent(numberOfSeatLabel)
						.addComponent(numberOfSeatBox))
				
				.addGroup(layout.createParallelGroup()
						.addComponent(createTable))
				//END ADD TABLE VERTICAL

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
				
				//.addComponent(tableVisualizer)
				);
		
		//END LEFT MENU LAYOUT(JPANEL)

		
		//FINAL LAYOUT
		//this.setSize(1000,1000);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//setUndecorated(true); 
		setVisible(true);
		
		GroupLayout finalWindow = new GroupLayout(getContentPane());
		getContentPane().setLayout(finalWindow);
		finalWindow.setAutoCreateGaps(true);
		finalWindow.setAutoCreateContainerGaps(true);
		
		finalWindow.setHorizontalGroup(
				finalWindow.createSequentialGroup()
				.addComponent(leftMenu)
				.addGroup(finalWindow.createParallelGroup()
				.addComponent(restoMap)
				.addComponent(menu))
				
		);
		finalWindow.setVerticalGroup(
				finalWindow.createParallelGroup()
				.addComponent(leftMenu)
				.addGroup(finalWindow.createSequentialGroup()
					.addComponent(restoMap)
					.addComponent(menu))
				
		);
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
		ItemCategory category;
		//call the controller
		try{
			switch(selectedMenu){
			case 0:category = ItemCategory.Appetizer;
				   menu.getMenuLabel().setText("Appetizer");
				   break;
			case 1:category = ItemCategory.Main;
			   	   menu.getMenuLabel().setText("Main");
			       break;
			case 2:category = ItemCategory.Dessert;
			       menu.getMenuLabel().setText("Dessert");
			       break;
		  	case 3:category = ItemCategory.AlcoholicBeverage;
		  	       menu.getMenuLabel().setText("Alcoholic Beverage");
		  	       break;
			case 4:category = ItemCategory.NonAlcoholicBeverage;
			       menu.getMenuLabel().setText("Non-Alcoholic Beverage");
			       break;
			default:category = null;
			}
			List<MenuItem> list = RestoAppController.displayMenu(category);
			menu.updateMenu(list);
			
			//Update JLabel view
			menu.getMenuLabel().revalidate();
			menu.getMenuLabel().repaint();
			//Update JPanel(grid) view
			menu.getGrid().revalidate();
			menu.getGrid().repaint();
			
		} catch(InvalidInputException e){
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
