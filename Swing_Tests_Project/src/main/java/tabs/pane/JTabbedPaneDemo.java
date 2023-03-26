/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* TabbedPane.java class
*
* @name    : TabbedPane.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 26, 2020
****************************************************************************/

package tabs.pane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TabbedPane extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JTabbedPane tabs;
	private CoursePanel cource;
	private SelectCoursePanel selectCourse;

    TabbedPane() {
        super("Tabbed Pane Example");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Setting the JTabbedPane Position and Layout as Wrap
        tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

        cource = new CoursePanel();
        selectCourse = new SelectCoursePanel();
        // Adding user defined pannels to JTabbedPane
        tabs.addTab("Cources", cource);
        tabs.addTab("Select Course", selectCourse);
        
        // Adding JPanels to JTabbedPane
        tabs.addTab("Listing", new JPanel());
        tabs.addTab("Comment", new JTextArea(10, 40));

        tabs.addTab("Register", new JPanel());
        tabs.addTab("Contact Us", new JPanel());

        tabs.addTab("More..", new JPanel());

        getContentPane().add(tabs);
    }
}

/* Creating CoursePanel by extending JPanel */
class CoursePanel extends JPanel {
	private static final long serialVersionUID = 9174124986143135680L;
	
	private JButton addCourse;
	private JButton clear;

    CoursePanel() {
        addCourse = new JButton("Add Course");
        clear = new JButton("Clear");

        setLayout(new FlowLayout());

        add(addCourse);
        add(clear);
    }
}
/*Creating SelectCoursePanel by extending JPanel*/
class SelectCoursePanel extends JPanel {
	private static final long serialVersionUID = -5555317583301317169L;
	
	private JCheckBox java;
	private JCheckBox swing;
	private JCheckBox hibernate;

    SelectCoursePanel() {
        java = new JCheckBox("Java");
        swing = new JCheckBox("Spring");
        hibernate = new JCheckBox("Hibernate");

        setLayout(new FlowLayout());
        add(java);
        add(swing);
        add(hibernate);
    }
}

public class JTabbedPaneDemo {
    public static void main(String args[]) throws Exception
    {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            //java.util.logging.Logger.getLogger(MyJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    	
        TabbedPane frame = new TabbedPane();
        frame.setSize(800, 800);
        frame.setVisible(true);
    }
}