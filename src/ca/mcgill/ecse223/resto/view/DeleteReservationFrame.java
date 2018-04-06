package ca.mcgill.ecse223.resto.view;


import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;
import com.github.lgooddatepicker.components.*;


import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.controller.RestoAppController;
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.Reservation;
import ca.mcgill.ecse223.resto.model.Table;

public class DeleteReservationFrame extends JPanel {
	private Integer selectedReservation;

public DeleteReservationFrame(RestoAppPage app) {
    JFrame frame = new JFrame("DeleteReservationFrame");
    frame.setResizable(false);
    frame.setAlwaysOnTop(true);
    selectedReservation = -1;


    JLabel reservationLabel = new JLabel("Select Reservation");
    DefaultComboBoxModel reservationModel = new DefaultComboBoxModel<Reservation>();
    
    
    JComboBox<Reservation> reservationBox = new JComboBox<Reservation>();
    MyButton deleteRes = new MyButton("Delete");
    MyButton btnClose = new MyButton("Close");
    
	
	reservationBox.setModel(reservationModel);
	reservationBox.setEditable(true);
	reservationBox.setMaximumSize(new Dimension(150,20));
    reservationBox.getEditor().getEditorComponent().setBackground(Color.WHITE);

	
	reservationBox.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
	        JComboBox<String> cb = (JComboBox<String>) evt.getSource();
	        selectedReservation = cb.getSelectedIndex();
		}
	});
	
	
	for (Reservation res : RestoAppController.getReservations()) {
		reservationModel.addElement(res);
	}
    
    //END COMPONENTS
    
    //INIT
    
    //END INIT
    
    btnClose.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();			
		}
	});
    
    deleteRes.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				RestoAppController.deleteReservation((Reservation) reservationBox.getSelectedItem());
				frame.dispose();
				app.refreshData();

			
			}  catch (Exception ex) {
		        JOptionPane.showMessageDialog(null, "Unknown exception: " + ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
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
								.addComponent(reservationLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(reservationBox)
								
								))
				.addGroup(
						layout.createSequentialGroup()
							.addComponent(deleteRes)
							.addComponent(btnClose)
						)
				);
//	layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {reservationLabel, reservationBox});
//	layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {reservationLabel, reservationBox});
			layout.setVerticalGroup(
			   layout.createSequentialGroup()
			      .addGroup(layout.createParallelGroup()
				           .addComponent(reservationLabel)
				           .addComponent(reservationBox))
			      
			      .addGroup(layout.createParallelGroup()
			    		  .addComponent(deleteRes)
			    		  .addComponent(btnClose)
			    		  )

			);
	
    // Set the x, y, width and height properties
    frame.pack();
    frame.setVisible(true);
}
}