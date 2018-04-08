package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.controller.RestoAppController;
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.Table;
import ca.mcgill.ecse223.resto.model.TakeOut;

public class OtherFeaturesFrame extends JPanel {


	private DefaultComboBoxModel model3 = new DefaultComboBoxModel<Order>();

	private JLabel errorMessage;
	private String error = null;

	private RestoAppPage app;
	//AddReservation
	private MyButton createReservation;
	private MyButton deleteReservation;

	private JLabel reservationLabel = new JLabel();

	//END ADD RESERVATION

	//START ORDER
	private JLabel otherFeatures = new JLabel();
	private JLabel orderTables = new JLabel();
	private MyButton startOrder;
	private MyButton startTakeOutOrder;
	private JLabel tablesDesc = new JLabel();
	private JTextField textTables = new JTextField();
	//END START ORDER

	//END ORDER
	private JLabel orderLabel;
	private JComboBox<Order> currentOrderList;
	private MyButton endOrder;
	private Integer selectedOrder = -1;
	private HashMap<Integer, Order> orders;

	//START UPDATE MENU ITEM
	private MyButton updateMenuItem;
	//END UPDATE MENU ITEM

	public OtherFeaturesFrame(RestoAppPage app){

		JFrame frame = new JFrame("Other Features");
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);

		this.app = app;

		//START ORDER
		otherFeatures = new JLabel();
		otherFeatures.setText("Other Features");
		orderTables = new JLabel();
		orderTables.setText("Tables");
		tablesDesc = new JLabel();
		tablesDesc.setText("(enter the table number(s) seperated by a comma)");
		startOrder = new MyButton();
		startOrder.setBorder(new RoundedBorder(10));
		startOrder.setText("Start Order");
		startOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				error = null;
				try {
					startOrderButtonActionPerformed(e);
				} catch (NullPointerException ex) {
					errorMessage.setText("Error");
				}
			}
		});

		startTakeOutOrder = new MyButton();
		startTakeOutOrder.setText("Start Take Out");
		startTakeOutOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				error = null;
				try {
					startTakeOutOrderButtonActionPerformed(e);
				} catch (NullPointerException ex) {
					errorMessage.setText("Error");
				}

			}


		});
		//END START ORDER

		//END ORDER
		orderLabel = new JLabel();
		currentOrderList = new JComboBox<Order>();
		currentOrderList.setModel(model3);
		currentOrderList.setEditable(true);
		currentOrderList.getEditor().getEditorComponent().setBackground(Color.WHITE);
		currentOrderList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedOrder = cb.getSelectedIndex();
			}
		});
		endOrder = new MyButton();
		endOrder.setBorder(new RoundedBorder(10));
		orderLabel.setText("Select Order");
		endOrder.setText("End Order");
		endOrder.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				error = null;
				try {
					endOrderButtonPerformed(evt, (Order)currentOrderList.getSelectedItem());
				}
				catch (NullPointerException ex) {
					error = "Please select a valid Order";
					errorMessage.setText(error);
				}

			}

		});

		//RESERVE TABLE
		reservationLabel.setText("Reservation");
		createReservation = new MyButton();
		createReservation.setText("Reserve Table");
		createReservation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				error = null;
				try {
					createReservationActionPerformed(e);
				} catch (NullPointerException ex) {
					errorMessage.setText("Error");
				}

			}
		});


		deleteReservation = new MyButton();
		deleteReservation.setText("Delete Reservation");
		deleteReservation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				error = null;
				try {
					deleteReservationActionPerformed(e);
				} catch (NullPointerException ex) {
					errorMessage.setText("Error");
				}

			}
		});
		//RESERVE TABLE

		//START MENU ITEM BUTTON
		updateMenuItem = new MyButton();
		updateMenuItem.setText("Update Menu Item");
		updateMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				error = null;
				try {
					updateMenuItemActionPerformed(e);
				} catch (NullPointerException ex) {
					errorMessage.setText("Error");
				}
			}	
		});
		//END MENU ITEM BUTTON


		java.awt.Container contentPane = frame.getContentPane();

		GroupLayout layout = new GroupLayout(contentPane);
		contentPane.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		//HORIZONTAL
		layout.setHorizontalGroup(
				layout.createParallelGroup()
//				
//				//OTHER FEATURES
				.addGroup(layout.createSequentialGroup()
					.addComponent(otherFeatures))
				.addGroup(layout.createSequentialGroup()
						.addComponent(orderTables)
						.addGroup(layout.createParallelGroup()
								.addComponent(textTables, 200, 200, 400)
								.addComponent(tablesDesc)
								.addComponent(startOrder)
								.addComponent(startTakeOutOrder)))
				.addGroup(layout.createSequentialGroup()
						.addComponent(orderLabel)
						.addGroup(layout.createParallelGroup()
								.addComponent(currentOrderList, 200, 200, 400)
								.addComponent(endOrder)))
				.addGroup(layout.createSequentialGroup()
						.addComponent(reservationLabel)
						.addGroup(layout.createParallelGroup()
								.addComponent(createReservation, 200, 200, 400)
								.addComponent(deleteReservation)
								.addComponent(updateMenuItem)))	
			);
//		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {orderLabel,otherFeatures, orderTables, reservationLabel});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {orderLabel, otherFeatures, orderTables, reservationLabel});
//		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {textTables, startOrder, startTakeOutOrder, endOrder, currentOrderList});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {tablesDesc,textTables, startOrder, startTakeOutOrder, endOrder, currentOrderList});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {tablesDesc,createReservation, deleteReservation, updateMenuItem});
//		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {createReservation, deleteReservation, updateMenuItem});


		//VERTICAL
		layout.setVerticalGroup(
			layout.createSequentialGroup()
//			
//				//other features
			.addGroup(layout.createParallelGroup()
						.addComponent(otherFeatures))
				.addGroup(layout.createParallelGroup()
						.addComponent(orderTables)
						.addComponent(textTables))
				.addGroup(layout.createParallelGroup()
						.addComponent(tablesDesc))
				.addGroup(layout.createParallelGroup()
						.addComponent(startOrder))
				.addGroup(layout.createParallelGroup()
						.addComponent(startTakeOutOrder))
				.addGroup(layout.createParallelGroup()
						.addComponent(orderLabel)
						.addComponent(currentOrderList))
				.addGroup(layout.createParallelGroup()
						.addComponent(endOrder))
				.addGroup(layout.createParallelGroup()
						.addComponent(reservationLabel)
						.addComponent(createReservation))
				.addGroup(layout.createParallelGroup()
						.addComponent(deleteReservation))
				.addGroup(layout.createParallelGroup()
						.addComponent(updateMenuItem))
				);

		// Set the x, y, width and height properties
		frame.pack();
		frame.setVisible(true);
		refreshData();
	}


	protected void createReservationActionPerformed(ActionEvent e){
		new CreateReservationFrame(app);
	}

	protected void deleteReservationActionPerformed(ActionEvent e) {
		new DeleteReservationFrame(app);
	}
	protected void updateMenuItemActionPerformed(ActionEvent e){
		new UpdateMenuItemPage(app);
	}
	protected void addExistingTableButtonActionPerformed(ActionEvent evt, Table table) {
		error = null;
		try {
			RestoAppController.addCurrentTable(table);
		} catch (Exception e) {
			error = e.getMessage();
			errorMessage.setText(error);
		}

		app.refreshData();
	}

	protected void startOrderButtonActionPerformed(ActionEvent e) {
		try {
			List<Table> tableList = RestoAppController.getCurrentTables();
			List<Table> selectedTables = new ArrayList<Table>();
			String[] tables = textTables.getText().split(",");
			ArrayList<Integer> tableNumbers = new ArrayList<Integer>();
			for (String n: tables) {
				int number = -1;
				try {
					number = Integer.parseInt(n);
				} catch (NumberFormatException ex) {
					error = "Invalid table number";
					errorMessage.setText(error);
					return;
				}
				tableNumbers.add(number);

			}

			for (Table t: tableList) {
				if (tableNumbers.contains(t.getNumber())){
					if (t.getStatusFullName() != "Available") {
						error = "Table(s) already has an oder";
						errorMessage.setText(error);
						return;
					}
					selectedTables.add(t);
				}
			}

			if( selectedTables.size() < tableNumbers.size()) {
				error = "One more more selected table doesn't exist";
				errorMessage.setText(error);
				return;
			}
			RestoAppController.startOrder(selectedTables);

		}catch(Exception ex){
			error =  ex.getMessage();
			errorMessage.setText(error);
		}

		app.refreshData();
	}

	private void startTakeOutOrderButtonActionPerformed(ActionEvent e) {
		try {
			TakeOut takeOut = RestoAppApplication.getRestoApp().getCurrentTakeOut();
			if (takeOut.getStatusFullName() != "Available") {
				error = "Take out already has an oder";
				errorMessage.setText(error);
				return;
			}

			RestoAppController.startOrder(RestoAppApplication.getRestoApp().getCurrentTakeOut());

		}catch(Exception ex){
			error =  ex.getMessage();
			errorMessage.setText(error);
		}

		app.refreshData();

	}
	protected void endOrderButtonPerformed(ActionEvent evt, Order order) {
		error = null;

		// call the controller
		try {
			RestoAppController.endOrder(order);
		} catch (Exception e) {
			error = e.getMessage();
			errorMessage.setText(error);
		}

		// update visuals
		app.refreshData();

	}
	public void refreshData() {
		// error
		errorMessage.setText(error);

		if (error == null || error.length() == 0) {
			// populate page with data

			//					tables = new HashMap<Integer, Table>();
			//					currentTableList.removeAllItems();
			//					Integer index = 0;
			textTables.setText(null);

			model3.removeAllElements();
			for (Order order : RestoAppController.getCurrentOrders()) {
				model3.addElement(order);

			}
		};
		selectedOrder = -1;
		currentOrderList.setSelectedIndex(selectedOrder);
	}



}
