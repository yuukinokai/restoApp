package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import ca.mcgill.ecse223.resto.model.MenuItem;
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
	private Integer selectedTable=-1;
	private JButton addCommand;
	//private static List<MenuItem> displayList;
	
	public DisplayMenuPage() {
		super();
		init();
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
		gap = new JLabel("    ");
		gap.setVisible(false);
		itemName = new JLabel("Item Selected");
		itemName.setVisible(false);
		price = new JLabel("Price of selected item");
		price.setVisible(false);
		selectTableLabel = new JLabel("Select Table");
		selectTableLabel.setVisible(false);
		addCommand = new JButton("Add Command");
		addCommand.setVisible(false);
		addCommand.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				error =null;
				try{
					addCommandButtonActionPerformed(evt);
				}
				catch(NullPointerException ex){
					errorMessage.setText("Please select a valid menu");
				}
			}
		});
		tableList = new JComboBox<Table>();
		tableList.setMaximumSize(new Dimension(150,20));
		tableList.setVisible(false);
		tableList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
		        JComboBox<String> cb = (JComboBox<String>) evt.getSource();
		        selectedTable = cb.getSelectedIndex();
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
				.addComponent(addCommand)
		);
		bottomPanel.setVerticalGroup(
				bottomPanel.createParallelGroup()
				.addComponent(itemName)
				.addComponent(price)
				.addComponent(gap)
				.addComponent(selectTableLabel)
				.addComponent(tableList)
				.addComponent(addCommand)
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

	}
	
	protected void addCommandButtonActionPerformed(ActionEvent evt) {
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
					addCommand.setVisible(true);
				}
			});
		}
	}
	
	public JLabel getMenuLabel(){
		return menuName;
	}
	public JPanel getGrid(){
		return grid;
	}
}

