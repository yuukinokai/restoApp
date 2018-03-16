package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.controller.RestoAppController;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;


public class DisplayMenuPage extends JPanel {
	
	private static final long serialVersionUID = -3257644828027805214L;
	
	private JLabel errorMessage;
	private String error = null;
	
	private JLabel menuName;
	
	private JPanel grid;
	private JPanel description;
	
	private JLabel price;
	private JLabel gap;
	
	private JLabel itemName;
	
	private JComboBox<Table> tableList;
	private JLabel selectTableLabel;
	private Integer selectedTable = -1;
	
	private JLabel selectSeatLabel;
	private JComboBox<Seat> seatList;
	private Integer selectedSeat = -1;
	private JButton addOrder;

	private DefaultComboBoxModel tableModel = new DefaultComboBoxModel<Table>();
	private DefaultComboBoxModel seatModel = new DefaultComboBoxModel<Seat>();

	public DisplayMenuPage() {
		super();
		init();
		for (Table table : RestoAppController.getCurrentTables()) {
			System.out.println(table.getNumber());
			System.out.println(table.getCurrentSeats().size());
			System.out.println(table.getSeats().size());
		}
	}

	private void init() {
		this.setSize(200, 250);
		setBackground(Color.lightGray);
		
		//JLabel menu name
		menuName = new JLabel("Menu",JLabel.CENTER);
		Font font = new Font("Century Gothic", Font.BOLD, 20);
		menuName.setFont(font);
		menuName.setHorizontalTextPosition(JLabel.CENTER);
		
		//JPanel(grid)
		grid = new JPanel();
		grid.setLayout(new GridLayout(3,3));

		//JPanel description
		description = new JPanel();
		
		//Gap
		gap = new JLabel("    ");
		gap.setVisible(false);
		
		//Item Name
		itemName = new JLabel("Item Selected");
		itemName.setVisible(false);
		
		//Price
		price = new JLabel("Price of selected item");
		price.setVisible(false);
		
		//Table JLabel
		selectTableLabel = new JLabel("Select Table");
		selectTableLabel.setVisible(false);
		
		//Table JComboBox
		tableList = new JComboBox<Table>();
		tableList.setMaximumSize(new Dimension(150,20));
		tableList.setVisible(false);
		tableList.setModel(tableModel);
		tableList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
		        JComboBox<String> cb = (JComboBox<String>) evt.getSource();
		        selectedTable = cb.getSelectedIndex();
			}
		});
		
		//Seat JLabel
		selectSeatLabel = new JLabel("Select Seat");
		selectSeatLabel.setVisible(false);
		
		//Seat JComboBox
		seatList = new JComboBox<Seat>();
		seatList.setMaximumSize(new Dimension(150,20));
		seatList.setVisible(false);
		seatList.setModel(seatModel);
		seatList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
		        JComboBox<String> cb = (JComboBox<String>) evt.getSource();
		        selectedSeat = cb.getSelectedIndex();
		        updateModel();
			}
		});
		
		//Order JButton
		addOrder = new JButton("Add Order");
		addOrder.setVisible(false);
		addOrder.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				error =null;
				try{
					addOrderButtonActionPerformed(evt);
				}
				catch(NullPointerException ex){
					errorMessage.setText("Error");
				}
			}
		});
		
		GroupLayout bottomPanel = new GroupLayout(description);
		description.setLayout(bottomPanel);
		bottomPanel.setAutoCreateGaps(true);
		bottomPanel.setAutoCreateContainerGaps(true);
		bottomPanel.setHorizontalGroup(
				bottomPanel.createSequentialGroup()
				.addComponent(itemName)
				.addComponent(price)
				.addComponent(gap)
				.addComponent(selectTableLabel)
				.addComponent(tableList)
				.addComponent(selectSeatLabel)
				.addComponent(seatList)
				.addComponent(addOrder)
		);
		bottomPanel.setVerticalGroup(
				bottomPanel.createParallelGroup()
				.addComponent(itemName)
				.addComponent(price)
				.addComponent(gap)
				.addComponent(selectTableLabel)
				.addComponent(tableList)
				.addComponent(selectSeatLabel)
				.addComponent(seatList)
				.addComponent(addOrder)
		);
		
		//Final Layout
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(menuName)	
				.addComponent(grid)
				.addComponent(description)
		);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(menuName)
				.addComponent(grid)
				.addComponent(description)
		);
		setVisible(true);
		updateModel();
	}
	
	protected void addOrderButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		//To implement
	}

	public void updateMenu(List<MenuItem> menu){
		grid.removeAll();
		for(MenuItem item : menu){
			JButton button = new JButton(item.getName());
			button.putClientProperty("price",item.getCurrentPricedMenuItem().getPrice());
			grid.add(button);
			button.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				@Override
				public void actionPerformed(ActionEvent evt) {
					itemName.setText("Selected item: " + button.getLabel() + " ");
					price.setText(button.getClientProperty("price")+"$");
					itemName.setVisible(true);
					price.setVisible(true);
					gap.setVisible(true);
					selectTableLabel.setVisible(true);
					tableList.setVisible(true);
					selectSeatLabel.setVisible(true);
					seatList.setVisible(true);
					addOrder.setVisible(true);
				}
			});
		}
		updateModel();
	}
	
	public JLabel getMenuLabel(){
		return menuName;
	}
	public JPanel getGrid(){
		return grid;
	}
	public void updateModel() {
		tableModel.removeAllElements();
		for (Table table : RestoAppController.getCurrentTables()) {
			tableModel.addElement(table);
		}
		selectedTable = -1;
		tableList.setSelectedIndex(selectedTable);

		if(selectedTable != -1){
			seatModel.removeAllElements();
			List<Seat> list = RestoAppApplication.getRestoApp().getTable(selectedTable).getSeats();
			for(Seat seat : list){
				seatModel.addElement(seat);
			}
		}
	}
	
}

