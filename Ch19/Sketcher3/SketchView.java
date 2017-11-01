import javax.swing.JComponent;
import java.util.Observer;                  
import java.util.Observable;                  
import java.awt.Graphics;                                
import java.awt.Graphics2D;                                
import java.awt.Color;                                
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

class SketchView extends JComponent implements Observer {
  public SketchView(Sketcher theApp) {
    this.theApp = theApp;
  }

  // Method called by Observable object when it changes
  public void update(Observable o, Object rectangle) {
    // Code to respond to changes in the model...
  }

  public void paint(Graphics g) {
    // Temporary code - to be deleted later...
    Graphics2D g2D = (Graphics2D)g;              // Get a Java 2D device context

    g2D.setPaint(Color.RED);                     // Draw in red

    // Position width and height of first rectangle
    Point2D.Float p1 = new Point2D.Float(50.0f, 10.0f);
    float width1 = 60;
    float height1 = 80;

    // Create and draw the first rectangle
    Rectangle2D.Float rect = new Rectangle2D.Float(p1.x, p1.y, width1, height1);
    g2D.draw(rect);

    // Position width and height of second rectangle
    Point2D.Float p2 = new Point2D.Float(150.0f, 100.0f);
    float width2 = width1 + 30;
    float height2 = height1 + 40;

    // Create and draw the second rectangle
    g2D.draw(new Rectangle2D.Float(
                       (float)(p2.getX()), (float)(p2.getY()), width2, height2));
    g2D.setPaint(Color.BLUE);                    // Draw in blue

    // Draw lines to join corresponding corners of the rectangles
    Line2D.Float line = new Line2D.Float(p1,p2);
    g2D.draw(line);

    p1.setLocation(p1.x + width1, p1.y);
    p2.setLocation(p2.x + width2, p2.y);
    g2D.draw(new Line2D.Float(p1,p2));

    p1.setLocation(p1.x, p1.y + height1);
    p2.setLocation(p2.x, p2.y + height2);
    g2D.draw(new Line2D.Float(p1,p2));

    p1.setLocation(p1.x - width1, p1.y);
    p2.setLocation(p2.x - width2, p2.y);
    g2D.draw(new Line2D.Float(p1, p2));

    p1.setLocation(p1.x, p1.y - height1);
    p2.setLocation(p2.x, p2.y - height2);
    g2D.draw(new Line2D.Float(p1, p2));
    
    g2D.drawString("Lines and rectangles", 60, 250); // Draw some text
  }

  private Sketcher theApp;           // The application object
}
