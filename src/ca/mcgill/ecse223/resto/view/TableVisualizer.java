package ca.mcgill.ecse223.resto.view;

import java.awt.color.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.controller.RestoAppController;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;

public class TableVisualizer extends JPanel{

	private static final long serialVersionUID = 5765666411683246454L;
	private List<RoundRectangle2D> rectangles = new ArrayList<RoundRectangle2D>();
	private HashMap<RoundRectangle2D, Table> tables;
	private List<Table> tablesList = new ArrayList<Table>();
	private Table selectedTable;
	private JLabel tableName;
	
	public TableVisualizer(){
		super();
		init();
	}

	private void init() {
		this.setSize(500,300);
		this.setBackground(Color.WHITE);
		
		tableName = new JLabel("Select Table To See Number");
		Font font = new Font("Century Gothic", Font.BOLD, 20);
		tableName.setFont(font);
		//tableName.setHorizontalTextPosition(JLabel.LEFT);
		this.add(tableName);
		tables = new HashMap<RoundRectangle2D, Table>();
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				for (RoundRectangle2D rectangle : rectangles) {
					if (rectangle.contains(x, y)) {
						selectedTable = tables.get(rectangle);
						break;
					}
				}
				repaint();
			}
		});
		repaint();
	}
	
	public void setTables(List<Table> tablesList) {
		this.tablesList = tablesList;
		repaint();
	}
	
	private void drawTables(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		rectangles.clear();
		tables.clear();
		for (Table table : tablesList) {
			if (table != null) {
				RoundRectangle2D rectangle = new RoundRectangle2D.Float(table.getX()*10, table.getY()*10, table.getLength()*10,table.getWidth()*10, 10, 10 );
				
				rectangles.add(rectangle);
				
				tables.put(rectangle, table);
				if (table.getStatusFullName() == "Available") {
					g2d.setColor(new Color(152,255,153));
				}
				else {
					g2d.setColor(new Color(255, 102, 102));
				}
				
				g2d.fill(rectangle);
				g2d.setColor(Color.BLACK);
				g2d.draw(rectangle);
				
				int seatsAvailable = table.getCurrentSeats().size();
				for (Seat seat : table.getCurrentSeats()) {
					if (!RestoAppController.isAvailable(seat)) {
						seatsAvailable -= 1;
					}
				}
				String seatNumber = String.valueOf(seatsAvailable);
				
				int width = (int)(g.getFontMetrics().getStringBounds(seatNumber, g).getWidth()/2);
				int height = (int)(g.getFontMetrics().getStringBounds(seatNumber, g).getHeight()/2);
				g2d.drawString(Integer.toString(table.getSeats().size()),  table.getX()*10+table.getLength()*10/2-width,  table.getY()*10+table.getWidth()*10/2);
				
				//String tableDetails;
				if (selectedTable != null && selectedTable.equals(table)) {
					tableName.setText("Table Number " + RestoAppController.getTableNumber(selectedTable));
					//tableDetails = "Table number : " + RestoAppController.getTableNumber(selectedTable);
					//g2d.drawString(tableDetails, table.getX()*10+table.getLength()*10,  table.getY()*10+table.getWidth()*10/2);
				}
			}
		}	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawTables(g);
	}
}
