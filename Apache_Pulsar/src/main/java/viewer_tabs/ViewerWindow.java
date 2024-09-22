package viewer_tabs;

import lombok.Getter;
import org.apache.pulsar.client.api.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serial;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;


class Printer extends ConsumerBase
{
    private final JTextArea textField;

    public Printer(BlockingDeque<Message<byte[]>> queue,
                   AtomicBoolean stopFlag, JTextArea textField)
    {
        super(queue, stopFlag);
        this.textField = textField;
    }

    public void run()
    {
        Message<byte[]> msg = null;
        while (isStopRequested())
        {
            try {
                msg = queue.poll(100, TimeUnit.MILLISECONDS);
                if (null != msg)
                {
                    String messageStr = new String(msg.getData());
                    textField.append(messageStr + '\n');
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

@Getter
class ConsumerTabPane extends JScrollPane
{
    private final JTextArea textArea = new JTextArea(0, 0);

    public ConsumerTabPane()
    {
        textArea.setBackground(new Color(15, 25, 35));
        textArea.setForeground(new Color(225, 225, 225));
        textArea.setCaretColor(Color.WHITE);
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        setViewportView(textArea);
    }
}

// TODO: Raname
class Metainfo
{
    @Getter
    private final String topic;

    @Getter
    private final String pulsarHost;

    public ConsumerTask consumer;
    public Printer printer;
    public ConsumerTabPane panel = new ConsumerTabPane();
    public final LinkedBlockingDeque<Message<byte[]>> messages = new LinkedBlockingDeque<Message<byte[]>>();
    public final AtomicBoolean stopFlag = new AtomicBoolean(false);

    public Metainfo(String topic, String pulsarHost)
    {
        this.topic = topic;
        this.pulsarHost = pulsarHost;
        System.out.printf("Consumer(host: %s, topic: %s) created", pulsarHost , topic);
    }

    public void stop() throws InterruptedException
    {
        stopFlag.setRelease(true);
        consumer.join(1000);
        printer.join(1000);
        System.out.printf("Consumer(host: %s, topic: %s) stopped", pulsarHost , topic);
    }

    public void start()
    {
        consumer = new ConsumerTask(messages, stopFlag, pulsarHost, topic);
        printer = new Printer(messages,  stopFlag, panel.getTextArea());
        consumer.start();
        printer.start();

        System.out.printf("Consumer(host: %s, topic: %s) started", pulsarHost , topic);
    }
}


class ConsumerManager
{
    private final String pulsarHost;

    private final Map<String, Metainfo> metaMap = new HashMap<String, Metainfo>();

    @Getter
    private final JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

    ConsumerManager(String pulsarHost) {
        this.pulsarHost = pulsarHost;
    }

    private void removeTab(String title)
    {
        final int idx = tabs.indexOfTab(title);
        if (-1 != idx) {
            tabs.remove(idx);
        }
    }

    public void add(String topic)
    {
        final Metainfo metainfo = new Metainfo(topic, pulsarHost);
        metainfo.start();

        metaMap.put(topic, metainfo);
        tabs.add(topic, metainfo.panel);
    }

    public void remove(String topic) throws InterruptedException
    {   // TODO: Rename
        Metainfo metainfo = metaMap.get(topic);
        if (null != metainfo)
        {
            removeTab(topic);
            metainfo.stop();
        }
    }
}


// TODO: Вынести системные логи куда то (логи Пульсара, ошибки и тд)
//       в отдельную консоль
// TODO: счётчики сообщений

// TODO: Получить список топиков
// TODO: Создавать нужные топики

// TODO: Tools
//       - Send Recovery ORDER
//       - Send Commands
//       - Viewer memory usage
//       - Dump/Save all сщт

// TODO: Анализ
//       - Если получены сообщения из IN и OUT топиков --> иметь возможность проследить messages flow
//       - Последний Message ID
//       - Последний Order ID
//       - Анализ количества Order-ов одного или другого типа

public class ViewerWindow extends JFrame implements java.awt.event.ActionListener
{
    private final static String pulsarHost = "localhost";
    private final JStatusBar statusBar;
    private final ConsumerManager consumerManager = new ConsumerManager(pulsarHost);

    private Point mousePoint = null;

    private enum Command
    {
        RunConsumer,
        StopConsumer,
        ShowTopicList,
        OpenPulsarAdminDialog
    }

    public ViewerWindow()
    {
        statusBar = CreateStatusBar();
        this.add(statusBar, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent evt)
    {
        try {
            handleCommand(Command.valueOf(evt.getActionCommand()));
        }
        catch (final RuntimeException | InterruptedException exc) {
            System.out.println(exc.getMessage());
        }
    }

    private void handleCommand(Command command) throws InterruptedException
    {
        switch (command)
        {
            case Command.RunConsumer :          RunConsumer();   break;
            case Command.StopConsumer:          StopConsumer();  break;
            case Command.OpenPulsarAdminDialog: OpenPulsarAdminDialog(); break;
            case Command.ShowTopicList:         GetTopics();     break;
            default: throw new RuntimeException("");
        }
    }

    public void HandleExit()
    {
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

    protected JMenu createFileMenu()
    {
        return new JMenu("File");
    }

    protected JMenu createViewMenu()
    {
        JMenu menu = new JMenu("View");

        JCheckBoxMenuItem miWordWrap = new JCheckBoxMenuItem("Word wrap");
        miWordWrap.setMnemonic(KeyEvent.VK_S);
        miWordWrap.setDisplayedMnemonicIndex(5);

        // FIXME
        /*
        final JTextArea textAreaOne = consumerPanels.get(0).getTextArea();
        miWordWrap.setSelected(textAreaOne.getLineWrap());
        miWordWrap.addItemListener((ItemEvent e) -> {
            textAreaOne.setLineWrap(e.getStateChange() == ItemEvent.SELECTED);
        });
        */
        menu.add(miWordWrap);

        return menu;
    }

    protected JMenu createSettingsMenu()
    {
        JMenu menu = new JMenu("Settings");
        {
            AddMenuItem(menu, "Pulsar Admin Dialog", Command.OpenPulsarAdminDialog, this);
        }
        return menu;
    }

    private static void AddMenuItem(JMenu parent,
                                    String itemName,
                                    Command command,
                                    ActionListener listener)
    {
        JMenuItem mItem = new JMenuItem(itemName);
        mItem.addActionListener(listener);
        mItem.setActionCommand(command.name());
        parent.add(mItem);
    }

    protected JMenu createCommandsMenu()
    {
        JMenu menu = new JMenu("Commands");
        {
            AddMenuItem(menu, "Start",       Command.RunConsumer, this);
            AddMenuItem(menu, "Stop",        Command.StopConsumer, this);
            AddMenuItem(menu, "Show Topics", Command.ShowTopicList, this);
        }
        return menu;
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
        // String pulsarHost = "192.168.101.2";
        // String topic = "notifications";
        // String topic = "persistent://OPNX-V1/PRETRADE-ME/ORDER-IN-BTC/USDT";

        // threadPool.submit(new ConsumerTask(messages, stopFlag, pulsarHost, topic));
        // threadPool.submit(new Printer(messages, stopFlag, textField));

        // TODO: Disable 'Start' menu item
        // TODO: Set some flag --> to check if consumer running

        statusBar.SetStatusOnline();
    }

    private void StopConsumer() throws InterruptedException
    {
        this.remove(consumerManager.getTabs());

        // FIXME: will block UI
        consumerManager.remove("notifications1");

        this.add(consumerManager.getTabs());
        repaint();

        // TODO: Wait until stopped
        statusBar.SetStatusOffline();
    }

    protected JStatusBar CreateStatusBar()
    {
        JStatusBar statusBar = new JStatusBar();
        statusBar.SetStatusOffline();
        return statusBar;
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


        // FIXME
        /*
        final JTextArea textAreaOne = consumerPanels.get(0).getTextArea();
        JMenuItem miClear = new JMenuItem("Clear");
        miClear.addActionListener((ActionEvent e) -> {
            textAreaOne.setText("");
        });
        pmenu.add(miClear);

        textAreaOne.setComponentPopupMenu(pmenu);
        textAreaOne.addMouseMotionListener(MousePositionTracker());
        */
    }

	/*
	public boolean HadleClickedLine(String lineText)
	{
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
        if (testSwitch) {
            this.remove(tabs);
            testSwitch = false;
        }
        else {
            this.add(tabs);
            testSwitch = true;
        }
        repaint();
        */
    }

    protected void OpenPulsarAdminDialog()
    {
        new AdminDialog(this, "Pulsar admin configuration").OpenDialog();
    }

    // TODO: move to ConsumerManager --> To the TextArea impl
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

    // TODO: move some to Constructor
    public void InitComponents()
    {
        this.setMinimumSize(new Dimension(1400, 900));
        this.setTitle("Pulsar Viewer");

        CreateMenu();

        // TODO: ---> move to ConsumerTab
        CreatePopupMenu();

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                HandleExit();
            }
        });

        consumerManager.add("notifications");
        consumerManager.add("notifications1");
        consumerManager.add("notifications2");

        this.add(consumerManager.getTabs());
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
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            // java.util.logging.Logger.getLogger(MyJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}