package ca.mcgill.ecse223.resto.view;

import java.awt.Color;

import javax.swing.JButton;

public class MyButton extends JButton {
	
	 public MyButton(String text) {
         super(text);
         super.setBackground(new Color(224, 224, 224));
         super.setOpaque(true);
         super.setBorder(new RoundedBorder(10));
         
     }
	 
	 public MyButton() {
         super();
         super.setBackground(new Color(224, 224, 224));
         super.setOpaque(true);
         super.setBorder(new RoundedBorder(10));
     }
 }
