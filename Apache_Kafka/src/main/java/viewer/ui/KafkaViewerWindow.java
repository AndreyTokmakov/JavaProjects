//============================================================================
//Name        : KafkaViewerWindow.java
//Created on  : Jan 17, 2017
//Author      : Tokmakov Andrey
//Version     :
//Copyright   : Your copyright notice
//Description :
//============================================================================

package viewer.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import viewer.kafka.Consumer;


/** @author tokmakov **/
/** @class  KafkaViewerWindow **/
//@SuppressWarnings("unused")
public class KafkaViewerWindow extends JFrame
					           implements java.awt.event.ActionListener {
	/* I don't know why. But we need this shit. serialVersionUID :*/
	private static final long serialVersionUID = -2686369381789468766L;
	/* Response JTextArea field: */
	private JTextArea responseField = new JTextArea(0, 0);
	/* Kafka consumer: */
	private Consumer consumer = null;
	/** **/
	protected Thread timerThread = null;
	/** **/
	protected JStatusBar statusBar = null;
	/** **/
	private boolean isRunning = true;
	/** **/
	private SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
	/** **/
	private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss a");
	/** Current position of the mouse : **/
	private Point mousePoint = null;
	
	/* Run consummer thread command: */
	private static final String runConsummer   = "RunConsummerCommand";
	private static final String stopConsummer  = "StopConsummerCommand";
	private static final String showTopicList  = "ShowTopicListCommand";
    private static final String consumerCofiguration  = "ConsumerCofigurationDialog";
    private static final String anylyzeFinesCommand  = "anylyzeFinesCommand";
    private static final String runGC  = "runGarbageCollection";

	/** (non-Javadoc)
	 ** @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 **/
	public void actionPerformed(ActionEvent evt) {
		switch (evt.getActionCommand()) {
			case runConsummer :        this.RunConsummer();  break;
			case stopConsummer:        this.StopConsummer(); break;
			case consumerCofiguration: this.OpenServiceConfigurationWindow(); break;
			// case anylyzeFinesCommand:  this.AnalyzeFines();  break;	
			case showTopicList:        this.GetTopics();     break;
			case runGC:        		   this.runGarbageCollection();     break;
			default: System.out.println(evt.getActionCommand()); break;
		}
	}
	
	/** HandleExit **/
    public void HandleExit() {
    	isRunning = false;
        System.exit(0);
    }
	
	/** SetInitialPosistionAndSize **/
	private void SetInitialPosistionAndSize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    Dimension windowSize = this.getSize();
	    this.setLocation((screenSize.width - windowSize.width) / 2, ((screenSize.height - windowSize.height) / 2) - 15);  
	}
	 
	/** Create the edit menu: **/
	protected void CreateMenu() {
		JMenuBar menuBar = new JMenuBar(); {
			menuBar.add(createFileMenu());
			menuBar.add(createViewMenu());
			menuBar.add(createSettingsMenu());
			menuBar.add(createCommandsMenu());
			menuBar.add(createAnalyzerMenu());
			menuBar.add(createSystemMenu());
		}
		setJMenuBar(menuBar);
	}
	/** Create the FILE menu: **/
	protected JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
	    return menu;
	}
	/** Create the VIEW MENU: **/
	protected JMenu createViewMenu() {
		JMenu menu = new JMenu("View");
		
		JCheckBoxMenuItem miWordWrap = new JCheckBoxMenuItem("Word wrap");
		miWordWrap.setMnemonic(KeyEvent.VK_S);
		miWordWrap.setDisplayedMnemonicIndex(5);
		miWordWrap.setSelected(responseField.getLineWrap());

		miWordWrap.addItemListener((ItemEvent e) -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				responseField.setLineWrap(true);
			} else {
				responseField.setLineWrap(false);
			}
		});
		menu.add(miWordWrap);
		
	    return menu;
	}
	/** Create the SETTINGS MENU: **/
	protected JMenu createSettingsMenu() {
		JMenu menu = new JMenu("Settings"); {
			AddMenuItem(menu, "Configure consumer", consumerCofiguration);
		}
	    return menu;
	}
	
	private void AddMenuItem(JMenu parent, String itemName, String cmd) {
		JMenuItem mItem = new JMenuItem(itemName);
		mItem.addActionListener(this);
		mItem.setActionCommand(cmd);
		parent.add(mItem);
	}
	
	/** Create the COMMANDS menu: **/
	protected JMenu createCommandsMenu() {
		JMenu menu = new JMenu("Commands"); {
			AddMenuItem(menu, "Start",       runConsummer);
			AddMenuItem(menu, "Stop",        stopConsummer);
			AddMenuItem(menu, "Show Topics", showTopicList);
		} return menu;
	}	
	
	/** Create the SYSTEM menu: **/
	protected JMenu createSystemMenu() {
		JMenu menu = new JMenu("System"); {
			AddMenuItem(menu, "Garbage Collector", runGC);
		} return menu;
	}
	
	/** Create the ANALYZE menu: **/
	protected JMenu createAnalyzerMenu() {
		JMenu menu = new JMenu("Analyze"); {
			AddMenuItem(menu, "Fines", anylyzeFinesCommand);
		} return menu;
	}
	
	/** RunConsummer : **/
	private void RunConsummer() {                                         
		consumer.start();
	}
	/** StopConsummer : **/
	private void StopConsummer() {                                         
		consumer.Stop();
	}
	
	/** Create status bar : **/
	protected void CreateStatusBar() {
		this.statusBar = new JStatusBar();
	    this.add(statusBar, BorderLayout.SOUTH);
	    this.statusBar.SetStatusOffline();
	}
	
	/** Create popup menu : **/
	protected void CreatePopupMenu() {
	    JPopupMenu pmenu = new JPopupMenu();
	    

	    JMenuItem maxMi = new JMenuItem("Maximize");
        maxMi.addActionListener((ActionEvent e) -> {
            if (getExtendedState() != JFrame.MAXIMIZED_BOTH) {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
        pmenu.add(maxMi);
        
        JMenuItem quitMi = new JMenuItem("Quit");
        quitMi.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        pmenu.add(quitMi);

        JMenuItem miTest = new JMenuItem("Test");
        miTest.addActionListener((ActionEvent e) -> {
        	String line = this.getStringClicked();
    	    // HadleClickedLine(line);
        });
        pmenu.add(miTest);
        
        JMenuItem miClear = new JMenuItem("Clear");
        miClear.addActionListener((ActionEvent e) -> {
        	this.consumer.Clear();
        	responseField.setText("");
        });
        pmenu.add(miClear);

        responseField.setComponentPopupMenu(pmenu);
	    responseField.addMouseMotionListener(MousePosisionTracker()); 
	}
	
	/*
	public boolean HadleClickedLine(String lineText) {
		System.out.println(lineText);
		try {
			JSONObject json = (JSONObject)new JSONParser().parse(lineText);
			
			String type = (String)json.get("type");
			String uid = (String)json.get("uid");
			
			System.out.println(type);
			System.out.println(uid);
			
			if (type.equals("anomalydetector")) {
				JSONObject data = (JSONObject)json.get("data");
				String dataType = (String)data.get("type");

				System.out.println(dataType);
				
			}
			else if (type.equals("geoipstat")) {
				JSONObject data = (JSONObject)json.get("data");
				String dataType = (String)data.get("type");

				System.out.println(dataType);
				
			}

		} catch (ParseException exc) {
			exc.printStackTrace();
			return false;
		} catch (NullPointerException exc) {
			exc.printStackTrace();
			return false;
		} 
		return true;
	}
	*/
	
	protected String getStringClicked() {
		String result =  "";
		int viewToModel = responseField.viewToModel(this.mousePoint);
		if (-1 != viewToModel) {
			try {
             	int line = responseField.getLineOfOffset(viewToModel);
             	int start = responseField.getLineStartOffset(line);
				int end = responseField.getLineEndOffset(line);
				result = responseField.getText(start, end - start);
			} catch (BadLocationException e1) {
                 e1.printStackTrace();
			}
		}
		return result;
	}
	
	/*
	protected void AnalyzeFines()   {                                           
		String infoText = Utilities.AnalyzeFines(responseField);
		if (true == infoText.isEmpty())
			infoText = "Nothing to parse";
		new InformationDialog(this, "Fined IP's statistics", infoText).OpenDialog();
    } 
    */
	
	/** **/
	protected void GetTopics()   {                                           
		String infoText = this.consumer.GetTopics();
		if (true == infoText.isEmpty())
			infoText = "Nothing to parse";
		new InformationDialog(this, "Fined IP's statistics", infoText).OpenDialog();
    } 
	
	/** **/
	protected void runGarbageCollection()   {                                           
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Free memory in JVM before Garbage Collection = " + runtime.freeMemory());
		runtime.gc();
        System.out.println("Free memory in JVM after Garbage Collection = "  + runtime.freeMemory());
    } 
	
	/** **/
	protected void OpenServiceConfigurationWindow()   {                                           
		new ConfigurationDialog(this, "Consumer configuration", this.consumer).OpenDialog();
    } 
	
	/** @method  MouseMotionListener : Mouse posision tracker  **/
	protected MouseMotionListener MousePosisionTracker() {
        return new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent me) {
            	mousePoint = me.getPoint();
            }
            @Override
            public void mouseDragged(MouseEvent me) {
            	mousePoint = me.getPoint();
            }
            /*public void mousePressed(MouseEvent me) { 
	        	mousePoint = me.getPoint();
	        }
            public void mouseReleased(MouseEvent me) { 
	        	mousePoint = me.getPoint();
	        }
            public void mouseEntered(MouseEvent me) { 
	        	mousePoint = me.getPoint();
	        }
            public void mouseExited(MouseEvent me) { 
	        	mousePoint = me.getPoint();
	        }
            public void mouseClicked(MouseEvent me) { 
	        	mousePoint = me.getPoint();
	        } */          
        };
	}
	
	/** **/
	public void WatchDog() {
		while (isRunning) {
			Date currentTime = Calendar.getInstance().getTime();
			statusBar.addDate(dateFormat.format(currentTime));
			statusBar.addTime(timeFormat.format(currentTime));
			
			String tmp = "";
			for(Map.Entry<String, Long> entry : this.consumer.getRcvdMgsByTopics().entrySet()) 
				tmp += entry.getKey() + " : " + entry.getValue() + ",  ";
			tmp += "  Total : " + this.consumer.getMgsReceivedCountTotal();
			
			statusBar.setReceivedMessagesStats(tmp);
	   		
	   		/** Take a little nap : **/
    		try { 
    			Thread.sleep(1000);
    		} catch (InterruptedException e) {
    			/** TODO **/
    		}
		}
	}
	
	/** **/
	public void InitComponents() {
	    this.setMinimumSize(new Dimension(1400, 900));
	    this.setTitle(" Kafke event viewer");
	    
	    /** JScrollPane for Response field: **/
	    JScrollPane spResponsePanel = new JScrollPane(responseField);
	    responseField.setBackground(new Color(15, 25, 35));
	    responseField.setForeground(new Color(225, 225, 225));
	    responseField.setCaretColor(Color.WHITE);
	    
	    Font font = new Font("Times New Roman", Font.PLAIN, 14);
	    responseField.setFont(font);

	    /** Create menu: **/
	    CreateMenu();
	    /** Create Status Bar: **/
	    CreateStatusBar();
	    /** Create popup menu: **/
	    CreatePopupMenu();
	 
	    /** On Exit :**/
	    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    this.addWindowListener(new WindowAdapter() {
	    	@Override
	    	public void windowClosing(WindowEvent event) {
	    		HandleExit();
	    	}
	    });

	    /** **/
	    this.consumer = new Consumer(this.responseField, this.statusBar);
	    
	    /** Running the WatchDog thread. **/
	    this.timerThread = new Thread(() -> { WatchDog(); });
	    this.timerThread.start();

        /** **/
	    this.add(spResponsePanel, BorderLayout.CENTER);
	    this.pack();
	}
	
	/** **/
	public void Run() {
		InitComponents();
		SetInitialPosistionAndSize();
		/** **/
		this.setVisible(true);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/** @param args **/
	public static void main(String[] args) {
		SetNimbusLookAndFeel();
		new KafkaViewerWindow().Run();
	}
	/** SetNimbusLookAndFeel - Initialize the WINDOWS like Look&Feel. **/
	private static void SetNimbusLookAndFeel()  {
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			/*
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
	 				UIManager.setLookAndFeel(info.getClassName());
	 				break;
	 			}
	 		}
	 		*/
		} catch (ClassNotFoundException ex) {
	           // java.util.logging.Logger.getLogger(MyJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
	           // java.util.logging.Logger.getLogger(MyJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    } catch (IllegalAccessException ex) {
	    	//java.util.logging.Logger.getLogger(MyJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    } catch (UnsupportedLookAndFeelException ex) {
	            //java.util.logging.Logger.getLogger(MyJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    }
	}
}