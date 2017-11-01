import javax.swing.JApplet;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class StarApplet extends JApplet {
  // Initialize the applet
  public void init() {
    StarPane pane = new StarPane();    // Pane containing stars
    getContentPane().add(pane);        // BorderLayout.CENTER is default position
  }

  // Class defining a pane on which to draw
  class StarPane extends JComponent {
    public void paint(Graphics g) {
      Graphics2D g2D = (Graphics2D)g;
      Star star = new Star(0,0);                 // Create a star
      float delta = 60f;                         // Increment between stars
      float starty = 0f;                         // Starting y position

      // Draw 3 rows of 4 stars
      for(int yCount = 0; yCount < 3; yCount++) {
        starty += delta;                        // Increment row position
        float startx = 0f;                      // Start x position in a row

        // Draw a row of 4 stars
        for(int xCount = 0; xCount<4; xCount++) {
          g2D.draw(star.atLocation(startx += delta, starty));
        }
      }
    }
  }
}
