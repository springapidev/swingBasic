import java.awt.Toolkit; 
import java.awt.Dimension; 
import java.awt.GridBagLayout; 
import java.awt.GridBagConstraints; 
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class TryGridBagLayout5 {
  // The window object
  static JFrame aWindow = new JFrame("This is a Gridbag Layout"); 

  public static void main(String[] args) {
    Toolkit theKit = aWindow.getToolkit();            // Get the window toolkit
    Dimension wndSize = theKit.getScreenSize();       // Get screen size

    // Set the position to screen center & size to half screen size
    aWindow.setBounds(wndSize.width/4, wndSize.height/4,   // Position
                      wndSize.width/2, wndSize.height/2);  // Size
    aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    GridBagLayout gridbag = new GridBagLayout();      // Create a layout manager
    GridBagConstraints constraints = new GridBagConstraints();
    aWindow.getContentPane().setLayout(gridbag);      // Set the container layout mgr

    // Set constraints and add first button
    constraints.weightx = constraints.weighty = 10.0;
    constraints.fill = constraints.NONE;
    constraints.ipadx = 30;                           // Pad 30 in x
    constraints.ipady = 10;                           // Pad 10 in y
    addButton(" Press ", constraints, gridbag);       // Add the button

    // Set constraints and add second button 
    constraints.weightx = 5.0;                       // Weight half of first
    constraints.fill = constraints.BOTH;             // Expand to fill space
    constraints.ipadx = constraints.ipady = 0;       // No padding
    constraints.insets = new java.awt.Insets(10, 30, 10, 20); // Left 30 & right 20
    constraints.gridwidth = constraints.RELATIVE;    // Rest of the row
    constraints.gridheight = 2;                      // Height 2x "Press"
    addButton("GO", constraints, gridbag);           // Create and add button

    // Set constraints and add third button 
    constraints.gridx = 0;                           // Begin new row
    constraints.fill = constraints.NONE;
    constraints.ipadx = 30;                          // Pad component in x
    constraints.ipady = 10;                          // Pad component in y
    constraints.insets = new java.awt.Insets(0,0,0,0);       // No insets
    constraints.gridwidth = 1;                       // Width as "Press"
    constraints.gridheight = 1;                      // Height as "Press"
    addButton(" Push ", constraints, gridbag);       // Add button to content pane

    aWindow.setVisible(true);                       // Display the window
  }

  static void addButton(String label,
                        GridBagConstraints constraints,
                        GridBagLayout layout) {
    // Create a Border object using a BorderFactory method
    Border edge = BorderFactory.createRaisedBevelBorder(); 

    JButton button = new JButton(label);           // Create a button
    button.setBorder(edge);                        // Add its border
    layout.setConstraints(button, constraints);    // Set the constraints
    aWindow.getContentPane().add(button);          // Add button to content pane
  }
}
