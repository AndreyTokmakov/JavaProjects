package SimpleWindow;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;


public class SimpleWindow_Test {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SimpleWindow frame = new SimpleWindow();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			
		});
	}

}


class SimpleWindow extends JFrame {
	private static final int DEFAULT_WIDTH = 1200;
	private static final int DEFAULT_HEIGHT = 800;
	
	public SimpleWindow() {
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setBackground(new Color(15, 25, 35));
	    this.setForeground(new Color(0, 225, 225));
	}
}
