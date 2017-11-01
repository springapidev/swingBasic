// Class defining a status bar
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Dimension;
import static Constants.SketcherConstants.*;

class StatusBar extends JPanel {
  // Constructor
  public StatusBar() {
    setLayout(new FlowLayout(FlowLayout.LEFT, 10, 3));
    setBackground(Color.LIGHT_GRAY);
    setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
    setColorPane(DEFAULT_ELEMENT_COLOR);
    setTypePane(DEFAULT_ELEMENT_TYPE);
    add(colorPane);                              // Add color pane to status bar
    add(typePane);                               // Add type pane to status bar
  }

  // Set color pane label
  public void setColorPane(Color color) {
    String text = null;                          // Text for the color pane
    if(color.equals(Color.RED)) { 
      text = "RED";
    } else if(color.equals(Color.YELLOW)) {
      text = "YELLOW";
    } else if(color.equals(Color.GREEN)) {
      text = "GREEN";
    } else if(color.equals(Color.BLUE)) {
      text = "BLUE";
    } else {
      text = "CUSTOM COLOR";
    }
    colorPane.setForeground(color);
    colorPane.setText(text);                     // Set the pane text
  }

  // Set type pane label
  public void setTypePane (int elementType) { 
    String text = null;                           // Text for the type pane
    switch(elementType) {
      case LINE:
        text = "LINE";
        break;
      case RECTANGLE:
        text = "RECTANGLE";
        break;
      case CIRCLE:
        text = "CIRCLE";
        break;
      case CURVE:
        text = "CURVE";
        break;
      case TEXT:
        text = "TEXT";
        break;
      default:
        assert false;
    }
    typePane.setText(text);                       // Set the pane text
  }

  // Panes in the status bar
  private StatusPane colorPane = new StatusPane("BLUE");
  private StatusPane typePane = new StatusPane("LINE");

  // Class defining a status bar pane
  class StatusPane extends JLabel {
    public StatusPane(String text) {
      setBackground(Color.LIGHT_GRAY);      // Set background color
      setForeground(Color.BLACK);
      setFont(paneFont);                   // Set the fixed font
      setHorizontalAlignment(CENTER);      // Center the pane text 
      setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
      setPreferredSize(new Dimension(100,20));
      setText(text);                       // Set the text in the pane
    }

    // Font for pane text
    private Font paneFont = new Font("Serif", Font.PLAIN, 10);
  }
}
