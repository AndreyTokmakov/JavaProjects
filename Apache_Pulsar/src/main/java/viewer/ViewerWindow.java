package viewer;

import javax.swing.*;
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
import java.io.Serial;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


public class ViewerWindow extends JFrame implements java.awt.event.ActionListener
{
    @Serial
    private static final long serialVersionUID = -2686369381789468766L;

    private final JTextArea textField = new JTextArea(0, 0);

    protected JStatusBar statusBar = null;

    protected Thread timerThread = null;

    // private boolean isRunning = true;
    // private final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
    // private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss a");

    private Point mousePoint = null;

    // FIXME: --> To enum ??
    private static final String runConsumer   = "RunConsumerCommand";
    private static final String stopConsumer  = "StopConsumerCommand";
    private static final String showTopicList  = "ShowTopicListCommand";
    private static final String pulsarAdminDialog  = "pulsarAdminDialog";


    public void actionPerformed(ActionEvent evt)
    {
        switch (evt.getActionCommand())
        {
            case runConsumer :          RunConsumer();   break;
            case stopConsumer:          StopConsumer();  break;
            case pulsarAdminDialog:     openPulsarAdminDialog(); break;
            case showTopicList:         GetTopics();     break;
            default: System.out.println(evt.getActionCommand()); break;
        }
    }

    public void HandleExit()
    {
        // isRunning = false;
        System.exit(0);
    }

    private void SetInitialPositionAndSize()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = this.getSize();
        this.setLocation((screenSize.width - windowSize.width) / 2, ((screenSize.height - windowSize.height) / 2) - 15);
    }

    protected void CreateMenu()
    {
        JMenuBar menuBar = new JMenuBar();
        {
            menuBar.add(createFileMenu());
            menuBar.add(createViewMenu());
            menuBar.add(createSettingsMenu());
            menuBar.add(createCommandsMenu());
            menuBar.add(createAnalyzerMenu());
            menuBar.add(createSystemMenu());
        }
        setJMenuBar(menuBar);
    }

    protected JMenu createFileMenu() {
        return new JMenu("File");
    }

    protected JMenu createViewMenu() {
        JMenu menu = new JMenu("View");

        JCheckBoxMenuItem miWordWrap = new JCheckBoxMenuItem("Word wrap");
        miWordWrap.setMnemonic(KeyEvent.VK_S);
        miWordWrap.setDisplayedMnemonicIndex(5);
        miWordWrap.setSelected(textField.getLineWrap());

        miWordWrap.addItemListener((ItemEvent e) -> {
            textField.setLineWrap(e.getStateChange() == ItemEvent.SELECTED);
        });
        menu.add(miWordWrap);

        return menu;
    }

    protected JMenu createSettingsMenu()
    {
        JMenu menu = new JMenu("Settings");
        {
            AddMenuItem(menu, "Pulsar Admin Dialog", pulsarAdminDialog);
        }
        return menu;
    }

    private void AddMenuItem(JMenu parent, String itemName, String cmd)
    {
        JMenuItem mItem = new JMenuItem(itemName);
        mItem.addActionListener(this);
        mItem.setActionCommand(cmd);
        parent.add(mItem);
    }

    protected JMenu createCommandsMenu()
    {
        JMenu menu = new JMenu("Commands");
        {
            AddMenuItem(menu, "Start",       runConsumer);
            AddMenuItem(menu, "Stop",        stopConsumer);
            AddMenuItem(menu, "Show Topics", showTopicList);
        } return menu;
    }

    protected JMenu createSystemMenu()
    {
        JMenu menu = new JMenu("System");
        // AddMenuItem(menu, "Garbage Collector", runGC);
        return menu;
    }

    protected JMenu createAnalyzerMenu()
    {
        JMenu menu = new JMenu("Analyze");
        // AddMenuItem(menu, "Fines", anylyzeFinesCommand);
        return menu;
    }

    private void RunConsumer()
    {
        // consumer.start();
        textField.append("RunConsumer" +  "\n");
    }

    private void StopConsumer() {
        // consumer.Stop();
    }

    protected void CreateStatusBar()
    {
        this.statusBar = new JStatusBar();
        this.add(statusBar, BorderLayout.SOUTH);
        this.statusBar.SetStatusOffline();
    }

    protected void CreatePopupMenu()
    {
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
            // String line = this.getStringClicked();
            // HadleClickedLine(line);
            System.out.println("***** TEST ******");
        });
        pmenu.add(miTest);

        JMenuItem miClear = new JMenuItem("Clear");
        miClear.addActionListener((ActionEvent e) -> {
            // this.consumer.Clear();
            textField.setText("");
        });
        pmenu.add(miClear);

        textField.setComponentPopupMenu(pmenu);
        textField.addMouseMotionListener(MousePositionTracker());
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

    protected void GetTopics()
    {
        System.out.println("GetTopics()");
        /*
        String infoText = this.consumer.GetTopics();
        if (true == infoText.isEmpty())
            infoText = "Nothing to parse";
        new InformationDialog(this, "Fined IP's statistics", infoText).OpenDialog();
        */
    }

    protected void openPulsarAdminDialog()
    {
        new AdminDialog(this, "Pulsar admin configuration").OpenDialog();
    }

    protected MouseMotionListener MousePositionTracker()
    {
        return new MouseMotionListener()
        {
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
    public void WatchDog()
    {
        // FIXME
        /*
        while (isRunning)
        {
            Date currentTime = Calendar.getInstance().getTime();
            statusBar.addDate(dateFormat.format(currentTime));
            statusBar.addTime(timeFormat.format(currentTime));

            String tmp = "";
            for(Map.Entry<String, Long> entry : this.consumer.getRcvdMgsByTopics().entrySet())
                tmp += entry.getKey() + " : " + entry.getValue() + ",  ";
            tmp += "  Total : " + this.consumer.getMgsReceivedCountTotal();

            statusBar.setReceivedMessagesStats(tmp);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO
            }
        }
        */
    }

    /** **/
    public void InitComponents()
    {
        this.setMinimumSize(new Dimension(1400, 900));
        this.setTitle(" Kafke event viewer");

        JScrollPane spResponsePanel = new JScrollPane(textField);
        textField.setBackground(new Color(15, 25, 35));
        textField.setForeground(new Color(225, 225, 225));
        textField.setCaretColor(Color.WHITE);

        Font font = new Font("Times New Roman", Font.PLAIN, 14);
        textField.setFont(font);

        CreateMenu();
        CreateStatusBar();
        CreatePopupMenu();

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                HandleExit();
            }
        });

        // FIXME
        // this.consumer = new Consumer(this.responseField, this.statusBar);

        this.timerThread = new Thread(() -> { WatchDog(); });
        this.timerThread.start();

        this.add(spResponsePanel, BorderLayout.CENTER);
        this.pack();
    }

    public void Run()
    {
        InitComponents();
        SetInitialPositionAndSize();
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        SetNimbusLookAndFeel();
        new ViewerWindow().Run();
    }

    private static void SetNimbusLookAndFeel()
    {
        try {
            javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            // java.util.logging.Logger.getLogger(MyJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}