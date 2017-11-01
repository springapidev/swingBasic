import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Container;

public class TryGridLayout {
  // The window object
  static JFrame aWindow = new JFrame("This is a Grid Layout"); 

  public static void main(String[] args) {
    Toolkit theKit = aWindow.getToolkit();                 // Get the window toolkit
    Dimension wndSize = theKit.getScreenSize();            // Get screen size

    // Set the position to screen center & size to half screen size
    aWindow.setBounds(wndSize.width/4, wndSize.height/4,   // Position
                      wndSize.width/2, wndSize.height/2);  // Size
    aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GridLayout grid = new GridLayout(3,4,30,20);           // Create a layout manager
    Container content = aWindow.getContentPane();          // Get the content pane
    content. setLayout(grid);                              // Set the container layout mgr

    EtchedBorder edge = new EtchedBorder(EtchedBorder.RAISED);  // Button border   

    // Now add ten Button components
    JButton button;                                        // Stores a button
    for(int i = 1; i <= 10; i++) {
      content.add(button = new JButton(" Press " + i));    // Add a Button 
      button.setBorder(edge);                              // Set the border
    }
    aWindow.pack();                                        // Size for components
    aWindow.setVisible(true);                              // Display the window
  }
}
