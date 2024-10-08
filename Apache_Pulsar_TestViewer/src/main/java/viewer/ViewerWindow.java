package viewer;

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

    private Point mousePoint = null;

    @Getter
    private final ViewerWindow viewerWindow;

    public ConsumerTabPane(ViewerWindow viewerWindow)
    {
        this.viewerWindow = viewerWindow;

        textArea.setBackground(new Color(15, 25, 35));
        textArea.setForeground(new Color(225, 225, 225));
        textArea.setCaretColor(Color.WHITE);
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        CreatePopupMenu();
        setViewportView(textArea);
    }

    protected void CreatePopupMenu()
    {
        final JPopupMenu ctxMenu = new JPopupMenu();

        {
            JMenuItem menuItem = new JMenuItem("Maximize");
            menuItem.addActionListener((ActionEvent e) -> {
                if (viewerWindow.getExtendedState() != JFrame.MAXIMIZED_BOTH) {
                    viewerWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
            });
            ctxMenu.add(menuItem);
        }

        {
            JMenuItem menuItem = new JMenuItem("Quit");
            menuItem.addActionListener((ActionEvent e) -> {
                System.exit(0);
            });
            ctxMenu.add(menuItem);
        }

        {
            JMenuItem menuItem = new JMenuItem("Test");
            menuItem.addActionListener((ActionEvent e) -> {
                // String line = this.getStringClicked();
                // HadleClickedLine(line);
                System.out.println("***** TEST ******");
            });
            ctxMenu.add(menuItem);
        }

        {
            JMenuItem menuItem = new JMenuItem("Clear");
            menuItem.addActionListener((ActionEvent e) -> {
                textArea.setText("");
            });
            ctxMenu.add(menuItem);
        }

        textArea.setComponentPopupMenu(ctxMenu);
        textArea.addMouseMotionListener(MousePositionTracker());
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
        };
    }

    public void ClearTextArea() {
        textArea.setText("");
    }

    public void setWorWrap(boolean enabled) {
        textArea.setLineWrap(enabled);
    }

    public boolean getLineWrap() {
        return textArea.getLineWrap();
    }
}

class ConsumerBlock
{
    @Getter
    private final String topic;

    @Getter
    private final String pulsarHost;

    @Getter
    private final ViewerWindow viewerWindow;

    public ConsumerTask consumer;
    public Printer printer;
    public final ConsumerTabPane panel;
    public final LinkedBlockingDeque<Message<byte[]>> messages = new LinkedBlockingDeque<Message<byte[]>>();
    public final AtomicBoolean stopFlag = new AtomicBoolean(false);

    public ConsumerBlock(String pulsarHost,
                         String topic,
                         ViewerWindow viewerWindow)
    {
        this.topic = topic;
        this.pulsarHost = pulsarHost;
        this.viewerWindow = viewerWindow;
        panel = new ConsumerTabPane(viewerWindow);
        System.out.printf("Consumer(host: %s, topic: %s) created", pulsarHost , topic);
    }

    public ConsumerBlock stop() throws InterruptedException
    {
        stopFlag.setRelease(true);
        consumer.join(1000);
        printer.join(1000);

        System.out.printf("Consumer(host: %s, topic: %s) stopped", pulsarHost , topic);
        return this;
    }

    public ConsumerBlock start()
    {
        consumer = new ConsumerTask(messages, stopFlag, pulsarHost, topic);
        printer = new Printer(messages,  stopFlag, panel.getTextArea());
        consumer.start();
        printer.start();

        System.out.printf("Consumer(host: %s, topic: %s) started", pulsarHost , topic);
        return this;
    }

    void Clear() {
        panel.ClearTextArea();
    }

    void setWorWrap(boolean enabled) {
        panel.setWorWrap(enabled);
    }

    public boolean getLineWrap() {
        return panel.getLineWrap();
    }
}


class ConsumerManager
{
    private final Map<String, ConsumerBlock> consumerBlocksMap = new HashMap<String, ConsumerBlock>();
    private final Map<String, String> topicToTitleMap = new HashMap<String, String>();

    @Getter
    private final String pulsarHost;

    @Getter
    private final JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

    @Getter
    private final ViewerWindow viewerWindow;

    ConsumerManager(String pulsarHost,
                    ViewerWindow viewerWindow)
    {
        this.pulsarHost = pulsarHost;
        this.viewerWindow = viewerWindow;
    }

    private void removeTab(String topic)
    {
        final String title = topicToTitleMap.get(topic);
        if (null == title)
            return;

        final int idx = tabs.indexOfTab(title);
        if (-1 != idx) {
            tabs.remove(idx);
            topicToTitleMap.remove(topic);
        }
    }

    public void add(String topic, String title)
    {
        final ConsumerBlock consumerBlock = new ConsumerBlock(pulsarHost, topic, viewerWindow).start();

        topicToTitleMap.put(topic, title);
        consumerBlocksMap.put(topic, consumerBlock);
        tabs.add(title, consumerBlock.panel);
    }

    public void remove(String topic) throws InterruptedException
    {
        // TODO: Add mapping topic -> [topic, index?, tabTitle?  component]
        final ConsumerBlock consumerBlock = consumerBlocksMap.get(topic);
        if (null != consumerBlock)
        {
            removeTab(topic);
            consumerBlock.stop();
        }
    }

    public void Clear()
    {
        consumerBlocksMap.values().forEach(ConsumerBlock::Clear);
    }

    public void SetWorWrap(boolean enable)
    {
        consumerBlocksMap.values().forEach(block -> block.setWorWrap(enable));
    }

    public boolean GetWorWrap()
    {
        return consumerBlocksMap.values().stream().allMatch( ConsumerBlock::getLineWrap);
    }
}

// TODO: Main window menu
//       - CLEAR --> удалять во всех окнах текст


// TODO: Вынести системные логи куда то (логи Пульсара, ошибки и тд)
//       в отдельную консоль
// TODO: счётчики сообщений

// TODO: Получить список топиков
// TODO: Создавать нужные топики

// TODO: Logger && Messages
//       - В отдельную панель ???

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
    // private final static String pulsarHost = "192.168.101.2";
    private final static String pulsarHost = "localhost";
    private final StatusBar statusBar = CreateStatusBar();
    private final ConsumerManager consumerManager = new ConsumerManager(pulsarHost, this);

    private enum Command
    {
        RunConsumer,
        StopConsumer,
        ShowTopicList,
        OpenPulsarAdminDialog,
        ClearAll,
        WordWrap
    }

    public ViewerWindow()
    {
        this.add(statusBar, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                HandleExit();
            }
        });
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
            case Command.RunConsumer :          RunConsumer();  break;
            case Command.StopConsumer:          StopConsumer();  break;
            case Command.OpenPulsarAdminDialog: OpenPulsarAdminDialog();  break;
            case Command.ShowTopicList:         GetTopics();  break;
            case Command.ClearAll:              ClearAllMessages();  break;
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
        miWordWrap.setSelected(consumerManager.GetWorWrap());
        miWordWrap.addItemListener((ItemEvent e) -> {
            consumerManager.SetWorWrap(e.getStateChange() == ItemEvent.SELECTED);
        });
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
            AddMenuItem(menu, "Clear All",   Command.ClearAll, this);
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

    protected StatusBar CreateStatusBar()
    {
        StatusBar statusBar = new StatusBar();
        statusBar.SetStatusOffline();
        return statusBar;
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

    protected void ClearAllMessages()
    {
        consumerManager.Clear();
    }

    protected void OpenPulsarAdminDialog()
    {
        new AdminDialog(this, "Pulsar admin configuration").OpenDialog();
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

        final String marketCode = "BTC-USDT";

        consumerManager.add("persistent://OPNX-V1/PRETRADE-ME/ORDER-IN-"+ marketCode, "ORDER-IN-" + marketCode);
        consumerManager.add("persistent://OPNX-V1/ME-POSTTRADE/ORDER-OUT-" + marketCode, "ORDER-OUT-" + marketCode);
        consumerManager.add("persistent://OPNX-V1/ME-POSTTRADE/ORDER-OUT-BACKUP-" + marketCode, "ORDER-BACKUP-" + marketCode);
        consumerManager.add("persistent://OPNX-V1/PRETRADE-ME/CMD-IN", "CMD-IN");
        consumerManager.add("persistent://OPNX-V1/ME-POSTTRADE/CMD-OUT", "CMD-OUT");
        consumerManager.add("persistent://OPNX-V1/ME-WS/MD-SNAPSHOTS", "MD-SNAPSHOTS");
        consumerManager.add("persistent://OPNX-V1/ME-WS/SNAPSHOTS", "SNAPSHOTS");
        consumerManager.add("non-persistent://OPNX-V1/ME-WS/MD-SNAPSHOT-" + marketCode, "MD-SNAPSHOT-" + marketCode);
        consumerManager.add("non-persistent://OPNX-V1/ME-WS/MD-DIFF-" + marketCode, "MD-DIFF-" + marketCode);
        consumerManager.add("non-persistent://OPNX-V1/ME-WS/MD-BEST-" + marketCode, "MD-BEST-" + marketCode);
        consumerManager.add("non-persistent://OPNX-V1/PRICE-SERVER/MARK-PRICE", "MARK-PRICE");
        consumerManager.add("non-persistent://OPNX-V1/ME-POSTTRADE/HEARTBEAT", "HEARTBEAT");

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