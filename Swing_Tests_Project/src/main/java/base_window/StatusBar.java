/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* StatusBar tests application
*
* @name    : StatusBar.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 17, 2020
****************************************************************************/ 

package base_window;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

class StatusLabel extends JLabel {
	/** **/
	private static final long serialVersionUID = 5667395239067273072L;
	/** **/
	private ImageIcon iconOnline = null;
	/** **/
	private ImageIcon iconOffline = null;

	public StatusLabel(ImageIcon createImageIcon) {
		super(createImageIcon);
	}

	public StatusLabel() {
		// TODO: move to constants + add exc handling
		this.iconOnline  = new ImageIcon("src\\base_window\\resources\\online.png");
		this.iconOffline = new ImageIcon("src\\base_window\\resources\\offline.png");
		this.setPreferredSize(new Dimension(90, 17));
		this.setIcon(iconOnline);
		this.setIconTextGap(5);
	}
	
	public void SetOnline() {
		this.setIcon(iconOnline);
		// TODO: move to constants
		this.setText("Connected");
		this.setToolTipText("Viewer is connected to the Kafka cluster.");
	}
	
	public void SetOffline() {
		this.setIcon(iconOffline);
		// TODO: move to constants
		this.setText("Disconnected");
		this.setToolTipText("Viewer is disconnected.");
	}
};

/** @class SeparatorPanel **/
class SeparatorPanel extends JPanel {
	/** **/
	private static final long serialVersionUID = 5124652270584470670L;
	/** Left color : **/
    private Color leftColor;
	/** Right color : **/
    private Color rightColor;
 
    /** SeparatorPanel class constructor : **/
    public SeparatorPanel(Color leftColor, Color rightColor) {
        this.leftColor = leftColor;
        this.rightColor = rightColor;
        setOpaque(false);
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(leftColor);
        g.drawLine(0, 0, 0, getHeight());
        g.setColor(rightColor);
        g.drawLine(1, 0, 1, getHeight());
    }
}
 
/** @class JStatusBar **/
public class StatusBar extends JPanel {
	/** **/
    private static final long serialVersionUID = 1L;
    /** **/
    private JPanel leftPanel;
    /** **/
    private JPanel rightPanel;
    /** **/
    private StatusLabel statusLabel = new StatusLabel();
    
    // TODO: Move to configurable list of JLabel !!!!!!!!!!!!!!
    
    /** **/
    private JLabel dateLabel = new JLabel();
    /** **/
    private JLabel timeLabel = new JLabel();
    /** **/
    private JLabel nodesLabel = new JLabel();
    /** **/
    private JLabel rcvdStats = new JLabel();
    
    private JLabel progressLabel = new JLabel();

    public StatusBar() {
        createPartControl();
        
        leftPanel.add(statusLabel);
        leftPanel.add(new SeparatorPanel(Color.GRAY, Color.WHITE));
        
        leftPanel.add(nodesLabel);
        
        
        /** Add progress bar: **/
	    JProgressBar progressBar = new JProgressBar();
	    progressBar.setStringPainted(true);
	    progressBar.setMinimum(0);
	    progressBar.setMaximum(100);
	    progressBar.setValue(30);
	    progressBar.setSize(100, 11);

	    // this.addRightComponent(progressBar);
	    leftPanel.add(progressBar);
	    leftPanel.add(new SeparatorPanel(Color.GRAY, Color.WHITE));
	    

        rcvdStats.setHorizontalAlignment(JLabel.CENTER);
	    this.addRightComponent(rcvdStats);
        
	    dateLabel.setHorizontalAlignment(JLabel.CENTER);
	    dateLabel.setPreferredSize(new Dimension(90, 17));
	    this.addRightComponent(dateLabel);
	    
	    timeLabel.setHorizontalAlignment(JLabel.CENTER);
	    timeLabel.setPreferredSize(new Dimension(65, 17));
	    this.addRightComponent(timeLabel);
    }
    
    public void setReceivedMessagesStats(String text)  {
    	this.rcvdStats.setText(text);
    }
    
    public void setNodesInfo(String text)  {
    	this.nodesLabel.setText(text);
    }
    
    public void addTime(String text)  {
    	this.timeLabel.setText(text);
    }
 
    public void addDate(String text)  {
    	this.dateLabel.setText(text);
    }
    
    public void SetStatusOnline() {	
    	/** **/
    	this.statusLabel.SetOnline();
    	/** **/
		this.validate();
		this.repaint();
    }
    
    public void SetStatusOffline() {	
    	/** **/
    	this.statusLabel.SetOffline();
    	/** **/
		this.validate();
		this.repaint();
    }
    
    protected void createPartControl() {    
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(getWidth(), 23));
 
        leftPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 3));
        leftPanel.setOpaque(false);
        add(leftPanel, BorderLayout.WEST);
 
        rightPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 5, 3));
        rightPanel.setOpaque(false);
        add(rightPanel, BorderLayout.EAST);
    }
 
    protected void setLeftComponent(JComponent component) {
        leftPanel.add(component);
    }
 
    protected void addRightComponent(JComponent component) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 0));
        panel.add(new SeparatorPanel(Color.GRAY, Color.WHITE));
        panel.add(component);
        rightPanel.add(panel);
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
 
        // TODO: What the fuck is it????
     
        int y = 0;
        g.setColor(new Color(156, 154, 140));
        g.drawLine(0, y, getWidth(), y);
        y++;
 
        g.setColor(new Color(196, 194, 183));
        g.drawLine(0, y, getWidth(), y);
        y++;
 
        g.setColor(new Color(218, 215, 201));
        g.drawLine(0, y, getWidth(), y);
        y++;
 
        g.setColor(new Color(233, 231, 217));
        g.drawLine(0, y, getWidth(), y);
 
        y = getHeight() - 3;
 
        g.setColor(new Color(233, 232, 218));
        g.drawLine(0, y, getWidth(), y);
        y++;
 
        g.setColor(new Color(233, 231, 216));
        g.drawLine(0, y, getWidth(), y);
        y++;
 
        g.setColor(new Color(221, 221, 220));
        g.drawLine(0, y, getWidth(), y);
        
    }
 
}