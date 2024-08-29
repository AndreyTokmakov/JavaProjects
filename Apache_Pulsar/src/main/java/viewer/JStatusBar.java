package viewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serial;


class JIconLabel extends JLabel
{
	private ImageIcon iconOnline = null;
	private ImageIcon iconOffline = null;

	public JIconLabel(ImageIcon createImageIcon) {
		super(createImageIcon);
	}

	public JIconLabel()
    {
		this.iconOnline  = CreateImageIcon(
                "/home/andtokm/DiskS/ProjectsUbuntu/JavaProjects/Apache_Pulsar/src/main/resources/online.png");
		this.iconOffline = CreateImageIcon(
                "/home/andtokm/DiskS/ProjectsUbuntu/JavaProjects/Apache_Pulsar/src/main/resources/offline.png");
		this.setPreferredSize(new Dimension(90, 17));
		this.setIcon(iconOnline);
		this.setIconTextGap(5);
	}
	
	public void SetOnline()
    {
		this.setIcon(iconOnline);
		this.setText("Connected");
		this.setToolTipText("Viewer is connected to the Kafka cluster.");
	}
	
	public void SetOffline()
    {
		this.setIcon(iconOffline);
		this.setText("Disconnected");
		this.setToolTipText("Viewer is disconnected.");
	}
	
    protected ImageIcon CreateImageIcon(String iconImagePath) {
    	ImageIcon icon = null;
		try {
			BufferedImage img = ImageIO.read(new File(iconImagePath));
			icon = new ImageIcon(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return icon; 
    }
};

class SeparatorPanel extends JPanel
{
    @Serial
    private static final long serialVersionUID = 1L;
    private final Color leftColor;
    private final Color rightColor;
 
    /** SeparatorPanel class constructor : **/
    public SeparatorPanel(Color leftColor, Color rightColor)
    {
        this.leftColor = leftColor;
        this.rightColor = rightColor;
        setOpaque(false);
    }
 
    @Override
    protected void paintComponent(Graphics g)
    {
        g.setColor(leftColor);
        g.drawLine(0, 0, 0, getHeight());
        g.setColor(rightColor);
        g.drawLine(1, 0, 1, getHeight());
    }
}
 

public class JStatusBar extends JPanel
{
    @Serial
    private static final long serialVersionUID = 1L;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private final JIconLabel statusLabel = new JIconLabel();
    private final JLabel dateLabel = new JLabel();
    private final JLabel timeLabel = new JLabel();
    private final JLabel nodesLabel = new JLabel();
    private final JLabel rcvdStats = new JLabel();

    public JStatusBar()
    {
        createPartControl();

        statusLabel.setPreferredSize(new Dimension(110, 17));

        leftPanel.add(statusLabel);
        leftPanel.add(new SeparatorPanel(Color.GRAY, Color.WHITE));
        leftPanel.add(nodesLabel);

        rcvdStats.setHorizontalAlignment(JLabel.CENTER);
	    this.addRightComponent(rcvdStats);
        
	    dateLabel.setHorizontalAlignment(JLabel.CENTER);
	    dateLabel.setPreferredSize(new Dimension(79, 17));
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
    
    public void SetStatusOnline()
    {
    	this.statusLabel.SetOnline();
		this.validate();
		this.repaint();
    }
    
    public void SetStatusOffline()
    {
    	this.statusLabel.SetOffline();
		this.validate();
		this.repaint();
    }
    
    protected void createPartControl()
    {
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
 
    protected void addRightComponent(JComponent component)
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 0));
        panel.add(new SeparatorPanel(Color.GRAY, Color.WHITE));
        panel.add(component);
        rightPanel.add(panel);
    }
    
    protected ImageIcon CreateImageIcon(String iconImagePath)
    {
    	ImageIcon icon = null;
		try {
			BufferedImage img = ImageIO.read(new File(iconImagePath));
			icon = new ImageIcon(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return icon; 
    }
 
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
 
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