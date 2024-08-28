//============================================================================
//Name        : InformationDialog.java
//Created on  : Aug 14, 2017
//Author      : Tokmakov Andrey
//Version     :
//Copyright   : Your copyright notice
//Description :
//============================================================================

package viewer.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

/** @author tokmakov **/
/** @class  InformationDialog **/
@SuppressWarnings("unused")
public class InformationDialog extends javax.swing.JDialog
							   implements java.awt.event.ActionListener {
	/** **/
	private static final long serialVersionUID = -8781922424897818045L;
	/** **/
	private Container container = this.getContentPane();
	/** **/
    private Frame parent = null;
    /* Response JTextArea field: */
	private javax.swing.JTextArea info = new javax.swing.JTextArea(0, 0);
	/** **/
	private static final int dialogDefaultWidth = 475;
	private static final int dialogDefaultHeight = 470;

    /** InformationDialog class constructor: **/
    public InformationDialog(KafkaViewerWindow parent, String title, String infoText)  {  
        super(parent, title, true);
        this.parent = parent;
        this.container = this.getContentPane();
        
        /** JScrollPane for Response field: **/
	    javax.swing.JScrollPane scrollPanel = new javax.swing.JScrollPane(info);
	    info.setBackground(new Color(225, 235, 235));
	    info.setForeground(new Color(0, 0, 0));
	    info.setCaretColor(Color.WHITE);

        /** **/
	    this.add(scrollPanel, BorderLayout.CENTER);
	    this.pack();
	    
	    info.setText(infoText);
    }
    
    /** actionPerformed: **/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}	
	
    /** SetDialogWindowBounds: **/
    private void SetDialogWindowBounds()  {
        this.setBounds(parent.getX() + 50, parent.getY() + 50, dialogDefaultWidth, dialogDefaultHeight);
    }
	
    /** OpenDialog: **/
    public Boolean OpenDialog()  { 	
    	SetDialogWindowBounds();
        this.setResizable(true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true); 
        return true;
    }
}
