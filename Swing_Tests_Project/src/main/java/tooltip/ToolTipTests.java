package tooltip;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


class SimpleWindow extends JFrame {
	private static final int DEFAULT_WIDTH = 1200;
	private static final int DEFAULT_HEIGHT = 800;
	
	public SimpleWindow(String toolTipText) {
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		panel.setBackground(new Color(10, 20, 40));
		
		JTextField text = new JTextField(20);
		text.setText("Focus me to see tooltip!");
		text.setToolTipText(toolTipText);
		
		panel.add(text);
		this.add(panel);
	}
	
	public static void runWithCustomToolTipText(String toolTipText) {
		EventQueue.invokeLater(() -> {
			SimpleWindow frame = new SimpleWindow(toolTipText);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}


public class ToolTipTests {

	private static final String toolTip_HTML_Text =
             "<html><body>" +
             "<img src='" +
             "http://i.stack.imgur.com/OVOg3.jpg" +
             "' width=160 height=120> " +
             "<img src='" +
             "http://i.stack.imgur.com/lxthA.jpg" +
             "' width=160 height=120>" +
             "<p>Look Ma, no hands!";
	
	private static final String multiLine = 
			"<html>Line1 <br>"
			+ "Line2 <br>"
			+ "Line3 <br>"
			+ "Line4 <br>"
			+ "Line5 <br>"
			+ "</html>";
	
	
	public static void main(String[] args)
	{
		// SimpleWindow.runWithCustomToolTipText(toolTip_HTML_Text);
		SimpleWindow.runWithCustomToolTipText(multiLine);
	}
}
