package ca.mcgill.ecse223.resto.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Table;

public class TableVisualizer extends JPanel{

	private static final long serialVersionUID = 5765666411683246454L;
	private List<Rectangle2D> rectangles = new ArrayList<Rectangle2D>();
	private HashMap<Rectangle2D, Table> tables;
	private RestoApp restoApp = RestoAppApplication.getRestoApp();
	
	public TableVisualizer(){
		super();
		init();
	}

	private void init() {
		tables = new HashMap<Rectangle2D, Table>();
		
	}
	
	private void drawTables(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		rectangles.clear();
		tables.clear();
		for (Table table : restoApp.getCurrentTables()) {
			if (table != null) {
				Rectangle2D rectangle = new Rectangle2D.Float(table.getLength(),table.getWidth(), table.getX(), table.getY());
				rectangles.add(rectangle);
				tables.put(rectangle, table);
				g2d.setColor(Color.WHITE);
				g2d.fill(rectangle);
				g2d.setColor(Color.BLACK);
				g2d.draw(rectangle);
			}
		}
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawTables(g);
	}

}
