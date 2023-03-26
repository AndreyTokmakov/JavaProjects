/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Observer pattern demo class
*
* @name    : PreferencesWindowTest.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 05, 2020
****************************************************************************/ 

package tests;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class SwingThreadTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new SwingThreadFrame();
			frame.setTitle("SwingThreadTest");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}

class SwingThreadFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public SwingThreadFrame() {
		final JComboBox<Integer> combo = new JComboBox<>();
		combo.insertItemAt(Integer.MAX_VALUE, 0);
		combo.setPrototypeDisplayValue(combo.getItemAt(0));
		combo.setSelectedIndex(0);

		JPanel panel = new JPanel();

		JButton goodButton = new JButton("Good");
		goodButton.addActionListener(event -> new Thread(new GoodWorkerRunnable(combo)).start());
		panel.add(goodButton);
		JButton badButton = new JButton("Bad");
		badButton.addActionListener(event -> new Thread(new BadWorkerRunnable(combo)).start());
		panel.add(badButton);

		panel.add(combo);
		add(panel);
		pack();
	}
}

class BadWorkerRunnable implements Runnable {
	private JComboBox<Integer> combo;
	private Random generator;

	public BadWorkerRunnable(JComboBox<Integer> aCombo) {
		combo = aCombo;
		generator = new Random();
	}

	public void run() {
		try {
			while (true) {
				int i = Math.abs(generator.nextInt());
				if (i % 2 == 0)
					combo.insertItemAt(i, 0);
				else if 
					(combo.getItemCount() > 0)
				combo.removeItemAt(i % combo.getItemCount());
				Thread.sleep(1);
			}
		} catch (InterruptedException e) {
		}
	}
}

class GoodWorkerRunnable implements Runnable {
	private JComboBox<Integer> combo;
	private Random generator;
	
	public GoodWorkerRunnable(JComboBox<Integer> aCombo) {
		combo = aCombo;
		generator = new Random();
	}
	
	public void run() {
		try {
			while (true) {
				EventQueue.invokeLater(() -> {
					int i = Math.abs(generator.nextInt());
					if (i % 2 == 0)
						combo.insertItemAt(i, 0);
					else if (combo.getItemCount() > 0)
						combo.removeItemAt(i % combo.getItemCount());
				});
				Thread.sleep(1);
			}
		} catch (InterruptedException e) {
		}
	}
}