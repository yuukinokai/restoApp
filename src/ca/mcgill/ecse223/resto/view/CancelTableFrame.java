package ca.mcgill.ecse223.resto.view;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.github.lgooddatepicker.components.*;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.controller.RestoAppController;
import ca.mcgill.ecse223.resto.model.Table;
import ca.mcgill.ecse223.resto.model.Table.Status;

public class CancelTableFrame extends JPanel {

	public int selectedTable;
	
public CancelTableFrame(RestoAppPage app) {
    JFrame frame = new JFrame("CancelTable");
    frame.setResizable(false);
    frame.setAlwaysOnTop(true);
    selectedTable = 0;

    JLabel TableLabel = new JLabel("Select Table");
    DefaultComboBoxModel<Table> tableModel = new DefaultComboBoxModel<Table>();
    
    
    JComboBox<Table> TableBox = new JComboBox<Table>();
    MyButton cancel = new MyButton("cancel");
    MyButton Close = new MyButton("Close");
    
	
	TableBox.setModel(tableModel);
	TableBox.setEditable(true);
	TableBox.setMaximumSize(new Dimension(150,20));
    TableBox.getEditor().getEditorComponent().setBackground(Color.WHITE);

	
	TableBox.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
	        JComboBox<String> cb = (JComboBox<String>) evt.getSource();
	        selectedTable = cb.getSelectedIndex();
		}
	});
	
	
	for (Table t : RestoAppController.getCurrentTables()) {
		if(t.getStatus() == Status.Ordered) {
			tableModel.addElement(t);		
			}
		}
    
    //END COMPONENTS
    
    //INIT
    
    //END INIT
    
    Close.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();			
		}
	});
    
    cancel.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				RestoAppController.CancelOrder((Table) TableBox.getSelectedItem());
				frame.dispose();
				app.refreshData();

			
			}  catch (Exception ex) {
		        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
//			 
			
		}
	});
    
    
//    frame.setDefaultCloseOperation(JFrame.);

java.awt.Container contentPane = frame.getContentPane();
    
	GroupLayout layout = new GroupLayout(contentPane);
	contentPane.setLayout(layout);
	layout.setAutoCreateGaps(true);
	layout.setAutoCreateContainerGaps(true);
    
	layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(				
					layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(TableLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(TableBox)
								
								))
				.addGroup(
						layout.createSequentialGroup()
							.addComponent(cancel)
							.addComponent(Close)
						)
				);
			layout.setVerticalGroup(
			   layout.createSequentialGroup()
			      .addGroup(layout.createParallelGroup()
				           .addComponent(TableLabel)
				           .addComponent(TableBox))
			      
			      .addGroup(layout.createParallelGroup()
			    		  .addComponent(cancel)
			    		  .addComponent(Close)
			    		  )

			);
	
    frame.pack();
    frame.setVisible(true);
	}
}