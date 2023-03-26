package test_window;


import java.awt.*;
import javax.swing.*;

/**
 *
 * @author toa
 */
public class TestWindow  extends JFrame
{
    private Controller controller = Controller.getInstance();
    private Container container;
    
    public TestWindow() 
    {
        container = getContentPane();
        Init();
    }
    
    public void Run()
    {
        SetInitialPosistionAndSize();
        /** **/
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    private void Init()
    {
        InitMenu();
        this.setTitle(" JAVA Swing test application");  
        this.setMinimumSize(new Dimension(800, 600));
        container.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel controlPanel = new JPanel();
        //controlPanel.setBackground(Color.gray);
        controlPanel.setBounds(5, 5, 500, 200);
       // controlPanel.setLayout(null);
        
        JLabel label = new JLabel("Number :");
        
        JButton button = new JButton();
        button.setText("Button");
        
        JTextField textField = new JTextField();
        textField.setColumns(20);

        controlPanel.add(label);
        controlPanel.add(textField);
        controlPanel.add(button);

        
        JPanel chartsPanel = new JPanel();
        chartsPanel.setBackground(Color.red);
        chartsPanel.setBounds(500, 10, 300, 200);
        
        JLabel chLabel = new JLabel("Number :");
        
        chartsPanel.add(chLabel);
        
        
        container.add(controlPanel);
        container.add(chartsPanel);
        
        this.pack();
    }
    
    private void SetInitialPosistionAndSize()
    {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.setSize(screenSize.width / 2, screenSize.height / 2);
        this.setLocation(screenSize.width / 4, screenSize.height / 4);
    }
    
    private void InitMenu()
    {
        JMenuBar menuBar = new JMenuBar();
        {
            JMenu menu = new JMenu(" Menu ");
            JMenu setings = new JMenu(" Settings ");
            JMenu about = new JMenu(" About ");
  
            menu.add("Open");
            menu.add("Close");
            
            JMenuItem miSettings = new JMenuItem("Settings");
            miSettings.addActionListener(controller);
            miSettings.setActionCommand("OpenSettingsDialog");
            
            setings.add(miSettings);
            
            JMenuItem miAbout = new JMenuItem("About");
            miAbout.addActionListener(controller);
            miAbout.setActionCommand("About");
            
            about.add(miAbout);
            
            menuBar.add(menu);
            menuBar.add(setings);
            menuBar.add(about);
        }
        setJMenuBar(menuBar);
    }
}

