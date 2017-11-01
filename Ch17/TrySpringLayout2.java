import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.Spring;

import java.awt.Container; 
import java.awt.Dimension; 
import java.awt.Toolkit;

public class TrySpringLayout2 {
  // The window object
  static JFrame aWindow = new JFrame("This is a Spring Layout"); 

  public static void main(String[] args) {
    Toolkit theKit = aWindow.getToolkit();         // Get the window toolkit
    Dimension wndSize = theKit.getScreenSize();    // Get screen size
 
    // Set the position to screen center & size to two-thirds screen size
    aWindow.setBounds(wndSize.width/6, wndSize.height/6,       // Position
                      2*wndSize.width/3, 2*wndSize.height/3);  // Size
    aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    SpringLayout layout = new SpringLayout();      // Create a layout manager
    Container content = aWindow.getContentPane();  // Get the content pane
    content.setLayout(layout);                     // Set the container layout mgr

    JButton[] buttons = new JButton[6];            // Array to store buttons
    SpringLayout.Constraints constr = null;
    for(int i = 0; i < buttons.length; i++) {
      buttons[i] = new JButton("Press " + (i+1));
      content.add(buttons[i]);                     // Add a Button to content pane
   }

    Spring xSpring = Spring.constant(5,15,25);    // x constraint for first button
    Spring ySpring = Spring.constant(10,30, 50);  // y constraint for first button

    // Connect x,y for first button to left and top of container by springs
    constr = layout.getConstraints(buttons[0]);
    constr.setX(xSpring); 
    constr.setY(ySpring); 

    // Hook buttons together with springs
    for(int i = 1 ; i< buttons.length ; i++) {
      constr = layout.getConstraints(buttons[i]);
      layout.putConstraint(SpringLayout.WEST, buttons[i],
                             xSpring,SpringLayout.EAST, buttons[i-1]);    
      layout.putConstraint(SpringLayout.NORTH, buttons[i],
                             ySpring,SpringLayout.SOUTH, buttons[i-1]);
    }
    SpringLayout.Constraints constraint = layout.getConstraints(content);
    constraint.setConstraint(SpringLayout.EAST,
                     Spring.sum(constr.getConstraint(SpringLayout.EAST),
                                Spring.constant(15)));
    constraint.setConstraint(SpringLayout.SOUTH,
                     Spring.sum(constr.getConstraint(SpringLayout.SOUTH),
                                Spring.constant(10)));
    aWindow.pack();
    aWindow.setVisible(true);                      // Display the window
  }
}
