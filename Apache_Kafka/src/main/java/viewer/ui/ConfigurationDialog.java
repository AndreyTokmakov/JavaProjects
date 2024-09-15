//============================================================================
//Name        : ConfigurationDialog.java
//Created on  : Aug 14, 2017
//Author      : Tokmakov Andrey
//Version     :
//Copyright   : Your copyright notice
//Description :
//============================================================================

package viewer.ui;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import viewer.kafka.Consumer;

/** @author tokmakov **/
/** @class  ConfigurationDialog **/
public class ConfigurationDialog extends javax.swing.JDialog
								 implements java.awt.event.ActionListener {
	/** **/
	@Serial
    private static final long serialVersionUID = -8781922424897818045L;
	/** **/
	private Container container = this.getContentPane();
	/** **/
    private Frame parent = null;
    /** **/
    private JButton buttonOk = new JButton();
	/** **/
    private JButton buttonCancel = new JButton();  
    
	/** **/
	private final int dialogDefaultWidth = 575;
	private final int dialogDefaultHeight = 186;
	
	/** Button texts: **/
	private static final String saveButtonText    = "Save";
	private static final String cancelButtonText  = "Cancel";
	
	/** Texts: **/
	private static final String nodesConfigPanelName  = "Kafka cluster nodes : ";
	private static final String kafkaTopics  = "Topics : ";
	private static final String bootstrapServersLabel = "Bootstrap servers : ";	
	private static final String topicLabel = "Topics : ";
	
	/** Commands: **/
	private static final String saveChangesCommand = "SAVE_CHANGES_COMMAND";
	private static final String cancelCommand      = "CANCEL_COMMAND";	
	
	/** **/
	private Consumer consumer = null;
	
	/** **/
	private JTextField nodesListField = new JTextField();
	private JTextField topicListField = new JTextField();

    /** ConfigurationDialog class constructor: **/
    public ConfigurationDialog(KafkaViewerWindow parent, String title, Consumer consumer)  {  
        super(parent, title, true);
        this.parent = parent;
        this.container = this.getContentPane();
        this.consumer = consumer;
        container.setLayout(null);
    }	
    
    /** SetDialogWindowBounds: **/
    private void SetDialogWindowBounds()  {
        this.setBounds(parent.getX() + 50, parent.getY() + 50, dialogDefaultWidth, dialogDefaultHeight);
    }
    
    /** CreateOKButton: **/
    private void CreateOKButton()  {
        buttonOk.setText(saveButtonText);
        buttonOk.setActionCommand(saveChangesCommand);
        buttonOk.addActionListener(this);
        container.add(buttonOk);
        SetOKButtonPosition();
    }
    
    /** SetOKButtonPosition: **/
    private void SetOKButtonPosition()  {
    	buttonOk.setBounds(this.getWidth() - 110, this.getHeight() - 57, 90, 24);
    }
    
    /** CreateCancelButton: **/
    private void CreateCancelButton() {
        buttonCancel.setText(cancelButtonText);
        buttonCancel.setActionCommand(cancelCommand);
        buttonCancel.addActionListener(this);
        container.add(buttonCancel);
        SetCancelButtonPosition();
    }
    
    /** SetCancelButtonPosition: **/
    private void SetCancelButtonPosition()  {
    	buttonCancel.setBounds(this.getWidth() - 120 - buttonOk.getWidth(), this.getHeight() - 57, 90, 24);
    }
    
    /** CreateNodeConfigBlock: **/
    private void CreateNodeConfigBlock() {
    	JPanel nodesBlock = new JPanel();
    	nodesBlock.setBounds(10, 10, dialogDefaultWidth - 24, 55);
    	nodesBlock.setBorder(javax.swing.BorderFactory.createTitledBorder(nodesConfigPanelName));
    	nodesBlock.setLayout(null);
    	
        JLabel label = new JLabel();
        label.setBounds(10, 16, 100, 24);
        label.setText(bootstrapServersLabel);
        nodesBlock.add(label);
        
        nodesListField.setBounds(110, 17, 430 , 24);
        nodesListField.setText(this.consumer.getNodes().toString().replace("[", "").replace("]", ""));
        nodesBlock.add(nodesListField);
    	
    	container.add(nodesBlock);
    }
    
    /** CreateTopicsConfigBlock: **/
    private void CreateTopicsConfigBlock() {
       	JPanel topicBlock = new JPanel();
    	topicBlock.setBounds(10, 70, dialogDefaultWidth - 24, 55);
    	topicBlock.setBorder(javax.swing.BorderFactory.createTitledBorder(kafkaTopics));
    	topicBlock.setLayout(null);
    	
        JLabel label = new JLabel();
        label.setBounds(10, 16, 100, 24);
        label.setText(topicLabel);
        topicBlock.add(label);
        
        topicListField.setBounds(110, 17, 430 , 24);
        topicListField.setText(consumer.getTopics().toString().replace("[", "").replace("]", ""));
        topicBlock.add(topicListField);
    	
    	container.add(topicBlock);
    }    
    
    /** OpenDialog: **/
    public Boolean OpenDialog()  { 	
    	SetDialogWindowBounds();
    	CreateNodeConfigBlock();
    	CreateTopicsConfigBlock();
        CreateOKButton();   
        CreateCancelButton();
        
        this.setResizable(false);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true); 
        return true;
    }
	

    @Override
    public synchronized void actionPerformed(ActionEvent actionEvent)  {
		switch (actionEvent.getActionCommand()) {
			case saveChangesCommand : 
				HandleSaveOperation(); 
				break;
			case cancelCommand:   
				/* TODO */;    
				break;
			default: 
				System.out.println(actionEvent.getActionCommand()); 
				break;
		}
        notify();
        dispose();
    }

    /** HandleSaveOperation: **/
    private void HandleSaveOperation() {
    	consumer.clearTopics();
    	Arrays.asList(topicListField.getText().split(",")).forEach(node -> { 
    		consumer.addTopic(node.trim());
    	});
    	
    	Arrays.asList(nodesListField.getText().split(",")).forEach(server -> { 
        	System.out.println(server.trim());
    	});

    	
    	/** Stop consumer : **/
    	consumer.Stop();

    	/** Start consummer : **/
    	// consumer.start();
    }
}
