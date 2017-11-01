import java.awt.Toolkit; 
import java.awt.Dimension; 
import java.awt.GridBagLayout; 
import java.awt.GridBagConstraints; 
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class TryGridBagLayout {
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
    constraints.fill = constraints.BOTH;              // Fill the space
    addButton(" Press ", constraints, gridbag);       // Add the button

    // Set constraints and add second button 
    constraints.gridwidth = constraints.REMAINDER;    // Rest of the row
    addButton("GO", constraints, gridbag);            // Create and add button

    aWindow.setVisible(true);                         // Display the window
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
