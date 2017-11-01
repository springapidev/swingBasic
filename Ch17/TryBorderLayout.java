import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.BorderLayout;

import javax.swing.border.EtchedBorder;

public class TryBorderLayout {
  // The window object
  static JFrame aWindow = new JFrame("This is a Border Layout"); 

  public static void main(String[] args) {
    Toolkit theKit = aWindow.getToolkit();       // Get the window toolkit
    Dimension wndSize = theKit.getScreenSize();  // Get screen size

    // Set the position to screen center & size to half screen size
    aWindow.setBounds(wndSize.width/4, wndSize.height/4,   // Position
                      wndSize.width/2, wndSize.height/2);  // Size
    aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    BorderLayout border = new BorderLayout();        // Create a layout manager
   // border.setHgap(20);  //horizental gape
   // border.setVgap(30);  //vartical gape
    Container content = aWindow.getContentPane();   // Get the content pane
    content.setLayout(border);                    // Set the container layout mgr
    EtchedBorder edge = new EtchedBorder(EtchedBorder.RAISED);  // Button border   
    // Now add five JButton components and set their borders
    JButton button;
    content.add(button = new JButton("EAST"), BorderLayout.EAST); 
    button.setBorder(edge);
    content.add(button = new JButton("WEST"), BorderLayout.WEST); 
    button.setBorder(edge);
    content.add(button = new JButton("NORTH"), BorderLayout.NORTH); 
    button.setBorder(edge);
    content.add(button = new JButton("SOUTH"), BorderLayout.SOUTH); 
    button.setBorder(edge);
    content.add(button = new JButton("CENTER"), BorderLayout.CENTER);
    button.setBorder(edge);

    aWindow.setVisible(true);                              // Display the window
  }
}
