package com.ashen.ide.WindowedRPG;

import java.awt.*;
import javax.swing.*;

// TODO Add size constraint and anchoring support

public class ScaledUI {
	
	private JFrame uiFrame = new JFrame();
	private Component UIE = null;
	private boolean doesScale = true;
	private Dimension uiPosition = new Dimension();
	private Dimension uiScale = new Dimension();
	private Dimension maxSize = new Dimension();
	private Dimension minSize = new Dimension();
	private String text = new String();
	private Font font = new Font("serif", Font.PLAIN, 12);
	private boolean doesfontScale = true;
	private double fontSize = 12;
	private double maxFontSize = 100;
	
	public ScaledUI(Component obj, JFrame frame)
	{
		UIE = obj;
		uiFrame = frame;
	}
	
	public ScaledUI(Component obj, JFrame frame, Dimension initalFrameSize, boolean scale, Dimension position, Dimension size)
	{
		UIE = obj;
		uiFrame = frame;
		doesScale = scale;
		uiPosition = position;
		uiScale = size;
		UIE.setBounds((int)((uiPosition.getWidth() / 1000) * initalFrameSize.getWidth()), (int)((uiPosition.getHeight() / 1000) * initalFrameSize.getHeight()),
				(int)((uiScale.getWidth() / 1000) * initalFrameSize.getWidth()), (int)((uiScale.getHeight() / 1000) * initalFrameSize.getHeight()));
	}
	
	public ScaledUI(Component obj, JFrame frame, Dimension initalFrameSize, boolean scale, Dimension position, Dimension size, Dimension max, Dimension min)
	{
		UIE = obj;
		uiFrame = frame;
		doesScale = scale;
		maxSize = max;
		minSize = min;
		uiPosition = position;
		uiScale = size;
		UIE.setBounds((int)((uiPosition.getWidth() / 1000) * initalFrameSize.getWidth()), (int)((uiPosition.getHeight() / 1000) * initalFrameSize.getHeight()),
				(int)((uiScale.getWidth() / 1000) * initalFrameSize.getWidth()), (int)((uiScale.getHeight() / 1000) * initalFrameSize.getHeight()));
	}
	
	public Component getObj()
	{
		return UIE;
	}
	
	public JFrame getFrame()
	{
		return uiFrame;
	}
	
	public String getText()
	{
		return text;
	}
	
	public void setupFont(Font font, double size, String text)
	{
		fontSize = size; this.text = text;
		setUIFont(UIE, font, size, maxFontSize);
	}
	
	public void update()
	{
		if(doesScale == true)
		{//System.out.println(UIE.getLocation() + " 2");
			setUIBound(UIE, uiFrame, 1000, uiPosition.getWidth(), uiPosition.getHeight(), uiScale.getWidth(), uiScale.getHeight());
			if(doesfontScale == true){setUIFont(UIE, font, fontSize, maxFontSize);}
		}
	}
	
	public void setUIBound(Component UI, JFrame frame, double denomonator, double x, double y, double width, double height)
	{ //System.out.println("ONE: " + (int)((width / denomonator) * frame.getWidth()) + " TWO: " + (int)((height / denomonator) * frame.getHeight()));
		UI.setBounds((int)((x / denomonator) * frame.getWidth()), (int)((y / denomonator) * frame.getHeight()), (int)((width / denomonator) * frame.getWidth()), (int)((height / denomonator) * frame.getHeight()));
	}
	
	public void setUIFont(Component UI, Font font, double fontSize, double maxFontSize)
	{
		int textSize = 0;
		if(UI.getWidth() <= UI.getHeight()) {textSize = (int)((fontSize / maxFontSize) * (UI.getWidth()));}
		else if(UI.getHeight() < UI.getWidth()) {textSize = (int)((fontSize / maxFontSize) * (UI.getHeight()));}
		font = new Font(UI.getFont().getFontName(), Font.PLAIN, textSize);
		UI.setFont(font);
	}
}
