package tooltip;

import javax.swing.event.*; 
import java.awt.*; 
import javax.swing.*;

public class ToolTip extends JFrame {
    // frame 
    static JFrame f; 
  
    // text areas 
    static JTextArea t1; 
  
    // main class 
    public static void main(String[] args) 
    { 
        // create a new frame 
        f = new JFrame("frame"); 
  
        // create a object 
        //ToolTip s = new ToolTip(); 
  
        // create a panel 
        JPanel p = new JPanel(); 
  
        // create a text area 
        t1 = new JTextArea(20, 20);
        
        t1.setText("TEXXXXXXXXXX");
        
        final String toolTip_HTML_Text =
                "<html><body>" +
                "<img src='" +
                "http://i.stack.imgur.com/OVOg3.jpg" +
                "' width=160 height=120> " +
                "<img src='" +
                "http://i.stack.imgur.com/lxthA.jpg" +
                "' width=160 height=120>" +
                "<p>Look Ma, no hands!";
  
        // set tooltip text 
        t1.setToolTipText(toolTip_HTML_Text); 
  
        // add text area 
        p.add(t1); 
  
        // add panel 
        f.add(p); 
  
        // set the size of frame 
        f.setSize(300, 300); 
  
        f.show(); 
    } 
}

