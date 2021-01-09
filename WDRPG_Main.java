package com.ashen.ide.WindowedRPG;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class WDRPG_Main {
	
	public static boolean Loop = true;
	
	public static JFrame frame = new JFrame("Demo");
	public static Container container = frame.getContentPane();
	public static JTextPane textPane = new JTextPane();
	public static JScrollPane scrollPane = new JScrollPane(textPane);
	public static void main(String[] args)
	{
		// TODO Convert TBRPG TODO Add roaming interactions TODO Add shop interactions TODO Add quest system TODO Add menu system TODO Add map
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.add(scrollPane); //
		frame.setSize(550, 300);
		frame.setVisible(true);
		
		while(Loop == true) 
		{
			update();
		}		
	}
	
	public static void update() 
	{
		
	}
	
}
