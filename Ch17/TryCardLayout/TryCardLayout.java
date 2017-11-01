import javax.swing.JApplet;
import javax.swing.JButton;

import java.awt.Container;
import java.awt.CardLayout;

import java.awt.event.ActionEvent;                   // Classes to handle events
import java.awt.event.ActionListener;

public class TryCardLayout extends JApplet implements ActionListener {                     
  CardLayout card = new CardLayout(50,50);           // Create layout

  public void init() {
    Container content = getContentPane();
    content.setLayout(card);                         // Set card as the layout mgr
    JButton button;                                  // Stores a button
    for(int i = 1; i <= 6; i++) {
      content.add(button = new JButton(" Press " + i), "Card" + i); // Add a button
      button.addActionListener(this);                // Add listener for button
    }
  }

  // Handle button events
  public void actionPerformed(ActionEvent e) {
      card.next(getContentPane());                  // Switch to the next card
  }
}
