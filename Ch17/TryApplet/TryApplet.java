import javax.swing.JButton;
import javax.swing.JApplet;

import java.awt.Font;
import java.awt.Container;
import java.awt.FlowLayout;


import javax.swing.border.BevelBorder;

public class TryApplet extends JApplet {
  public void init() {
    Container content = getContentPane();                // Get content pane
    content.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Set layout

    Font[] fonts = { new Font("Serif", Font.ITALIC, 10), // Two fonts
                     new Font("Dialog", Font.PLAIN, 14)
                   };

    BevelBorder edge = new BevelBorder(BevelBorder.RAISED); // Bevelled border

    // Add the buttons using alternate fonts
    JButton button = null;                               // Stores a button
    for(int i = 1; i <= 6; i++) {
      content.add(button = new JButton(" Press " + i));  // Add the button
      button.setFont(fonts[i%2]);                        // One of our own fonts
      button.setBorder(edge);                            // Set the button border
    }
  }
}
