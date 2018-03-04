package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Font;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import ca.mcgill.ecse223.resto.model.MenuItem;

public class DisplayMenuPage extends JPanel {
	
	private static final long serialVersionUID = -3257644828027805214L;
	private JLabel menuName;
	private JPanel grid;
	private JPanel description;
	private JLabel price;
	private JLabel itemName;
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
		itemName = new JLabel("Item Selected");
		itemName.setVisible(false);
		price = new JLabel("Price of selected item");
		price.setVisible(false);
		GroupLayout bottomPanel = new GroupLayout(description);
		description.setLayout(bottomPanel);
		bottomPanel.setAutoCreateGaps(true);
		bottomPanel.setAutoCreateContainerGaps(true);
		bottomPanel.setHorizontalGroup(
				bottomPanel.createSequentialGroup()
				.addComponent(itemName)
				.addComponent(price)
		);
		bottomPanel.setVerticalGroup(
				bottomPanel.createParallelGroup()
				.addComponent(itemName)
				.addComponent(price)	
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
					itemName.revalidate();
					itemName.repaint();
					price.revalidate();
					price.repaint();
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

