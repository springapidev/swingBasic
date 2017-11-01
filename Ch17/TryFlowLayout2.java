// Version of TryFlowLayout with LEFT alignment specified for the layout manager
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.FlowLayout;

public class TryFlowLayout2 {
  // The window object
  static JFrame aWindow = new JFrame("This is a Flow Layout"); 

  public static void main(String[] args) {
    Toolkit theKit = aWindow.getToolkit();         // Get the window toolkit
    Dimension wndSize = theKit.getScreenSize();    // Get screen size
    // Set the position to screen center & size to half screen size
    aWindow.setBounds(wndSize.width/4, wndSize.height/4,   // Position
                      wndSize.width/2, wndSize.height/2);  // Size
    aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    FlowLayout flow = new FlowLayout(FlowLayout.LEFT);  // Left alignment 
    Container content = aWindow.getContentPane();  // Get the content pane
    content.setLayout(flow);                       // Set the container layout mgr

    // Now add six button components
    for(int i = 1; i <= 6; i++)
      content.add(new JButton("Press " + i));      // Add a Button to content pane

    aWindow.setVisible(true);                      // Display the window
  }
}
