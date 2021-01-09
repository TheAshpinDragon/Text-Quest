package com.ashen.ide.WindowedRPG;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
//import javax.swing.SpringLayout;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import net.miginfocom.swing.MigLayout;
//import javax.swing.text.html.HTMLEditorKit;

//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;

// TODO Add input history TODO Add UI for menu TODO Add UI for quests TODO Add UI for shop TODO Add UI for inventory TODO Add UI for player status

public class WDRPG_SwingUI implements WindowListener,ActionListener {
	
	public static JFrame frame = new JFrame("Demo");
	public static Container container = frame.getContentPane();
	public static JTextPane textPane = new JTextPane();
	public static JScrollPane scrollPane = new JScrollPane(textPane);
	
	public static MigLayout mainLayout = new MigLayout();
	public static JFrame mainFrame = new JFrame();
	public static Dimension startSize = new Dimension(1000,1000);
	
	public static JTextField mainField = new JTextField(20);
		//public static ScaledUI mainFieldScale = new ScaledUI(mainField, mainFrame, startSize, true, new Dimension(100, 900), new Dimension(855, 50));
	
	public static JTextPane mainLog = new JTextPane();
		public static ArrayList<String> mainLogEntries = new ArrayList<>(); // Input history
		//public static ScaledUI mainLogScale = new ScaledUI(mainLog, mainFrame, startSize, true, new Dimension(100, 100), new Dimension(855, 780));
	    public static StyledDocument mainLogDoc = mainLog.getStyledDocument();
	    public static JScrollPane mainLogScrollPane = new JScrollPane(mainLog);
		public static Style normalTxt = mainLog.addStyle("", null); // black + normal, used for everything else
		public static Style errorTxt = mainLog.addStyle("", null); // red + bold, used for errors
		public static Style systemTxt = mainLog.addStyle("", null); // yellow + italic, used for system notifications (item pickup, enemy death, game saved, etc.)
		public static Style speechTxt = mainLog.addStyle("", null); // blue + italic, used for in-game dialogue (shop ui, story, tutorial, etc.)
	
	public static JButton menuBtn = new JButton("Click");
		//public static ScaledUI menuBtnScale = new ScaledUI(menuBtn, mainFrame, startSize, true, new Dimension(0, 0), new Dimension(100, 100));
	
	public static JButton expandEntryEditor = new JButton("Click");
	
	public static JProgressBar healthBar = new JProgressBar(0,100);

	public static JProgressBar weightBar = new JProgressBar(0,100);
	
	public static Container mainContainer = mainFrame.getContentPane();
	
	public static void main(String[] args) throws BadLocationException {
		
		WDRPG_SwingUI main = new WDRPG_SwingUI();
		
		//mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//mainContainer.add(mainLogScrollPane);
		//mainFrame.setSize(550, 300);
		//mainFrame.setVisible(true);
		
		StyleConstants.setForeground(normalTxt, Color.black);
		StyleConstants.setForeground(errorTxt, Color.red);
		StyleConstants.setBold(errorTxt, true);
		StyleConstants.setForeground(systemTxt, Color.yellow);
		StyleConstants.setItalic(systemTxt, true);
		StyleConstants.setForeground(speechTxt, Color.blue);
		StyleConstants.setItalic(speechTxt, true);
		
		/*
		mainLogDoc.insertString(mainLogDoc.getLength(), "Normal text, ", normalTxt);
		mainLogDoc.insertString(mainLogDoc.getLength(), "error text, ", errorTxt);
		mainLogDoc.insertString(mainLogDoc.getLength(), "speach text.", speechTxt);
		mainLogDoc.insertString(mainLogDoc.getLength(), "system text, ", systemTxt);
		*/
		
		while(true) {mainFrame.repaint();} //  mainLogScale.update(); mainFieldScale.update(); mainBtnScale.update();
	}

	public WDRPG_SwingUI() throws BadLocationException 
	{
		mainFrame.setTitle("Windowed RPG");
		mainFrame.setLayout(mainLayout);
		mainFrame.setMinimumSize(new Dimension(650,650));
		mainFrame.addWindowListener(this);
		mainFrame.setSize(600,600);
		mainFrame.setVisible(true);

		mainFrame.add(menuBtn, "split, wrap, cell 0 0");
			menuBtn.setPreferredSize(new Dimension(20, 60));
			menuBtn.setText("Menu");
			menuBtn.addActionListener( new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					
				}
			});
		
		mainContainer.add(mainLogScrollPane, "wrap, push, grow, cell 1 1");
			mainLog.setPreferredSize(new Dimension(460, 500));
			mainLog.setSize(100,100);
			mainLogDoc.insertString(mainLogDoc.getLength(), "Welcome to Windowed Quest! (Now with fancy text!)", systemTxt);
			mainLog.setBackground(Color.lightGray);
			mainLog.setEditable(false);
			//mainLogScale.setupFont(new Font("Sarif", Font.PLAIN, 3), 3, "");
		
		mainFrame.add(mainField, "split 10, pushx, growx, cell 1 2");
			mainField.setPreferredSize(new Dimension(1, 27));
			//mainFieldScale.setupFont(new Font("Sarif", Font.PLAIN, 50), 50, mainField.getText());
			mainField.addActionListener( new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(mainField.getText().indexOf(" ") == 0) 
					{
						String s =  mainField.getText().substring(1);
						while(s.indexOf(" ") == 0) 
						{
							s = s.substring(1);
						}
						if(!s.equals(""))
						{
							try {mainLogDoc.insertString(mainLogDoc.getLength(), "\n> " + s, normalTxt);} catch (BadLocationException e1) {e1.printStackTrace();}
							mainField.setText("");
						}
						else {
							try {mainLogDoc.insertString(mainLogDoc.getLength(), "\n" + "Error: Invalid input!", errorTxt);} catch (BadLocationException e1) {e1.printStackTrace();}
							mainField.setText("");
						}
					}
					else if(!mainField.getText().equals("")) {
						try {mainLogDoc.insertString(mainLogDoc.getLength(), "\n> " + mainField.getText(), normalTxt);} catch (BadLocationException e1) {e1.printStackTrace();}
						mainField.setText("");
					}
				}
			});
		
		mainFrame.add(expandEntryEditor, "");
			expandEntryEditor.setPreferredSize(new Dimension(1, 1));
			expandEntryEditor.setText("^");
			expandEntryEditor.addActionListener( new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					
				}
			});
		
		mainFrame.pack();
	}
	
	public void actionPerformed(ActionEvent e){}
	
	public void windowClosing(WindowEvent e)
	{
		mainFrame.dispose();
		System.exit(0);
	}
	
	public static void logText() throws BadLocationException // StyledDocument logDoc, Style textType, String text, boolean keepText
	{
		//if(keepText == true) {logDoc.insertString(logDoc.getLength(), text, textType);}
		//else {logDoc.remove(0, logDoc.getLength()); logDoc.insertString(0, text, textType);}
	}
	
	public void windowOpened(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
}
