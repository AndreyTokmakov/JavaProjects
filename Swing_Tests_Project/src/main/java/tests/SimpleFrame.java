/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* SimpleFrameTest class
*
* @name    : SimpleFrameTest.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : SimpleFrameTest 10, 2020
****************************************************************************/ 

package tests;

import java.awt.EventQueue;
import javax.swing.JFrame;


public class SimpleFrame {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SimpleFrameWindow frame = new SimpleFrameWindow();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}

class SimpleFrameWindow extends JFrame {
	private static final int DEFAULT_WIDTH = 1200;
	private static final int DEFAULT_HEIGHT = 800;
	
	public SimpleFrameWindow() {
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}
