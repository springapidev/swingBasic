import javax.swing.JComponent;
import java.util.Observer;                  
import java.util.Observable;                  
import java.awt.Graphics;                                
import java.awt.Graphics2D;                                
import java.awt.Color;                                
import java.awt.geom.Ellipse2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;

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
    Graphics2D g2D = (Graphics2D)g;                // Get a Java 2D device context
    Point2D.Double position = new Point2D.Double(50,10);  // Initial position
    double width = 150;                                   // Width of ellipse
    double height = 100;                                  // Height of ellipse
    double start = 30;                                    // Start angle for arc
    double extent = 120;                                  // Extent of arc
    double diameter = 40;                                 // Diameter of circle

    // Define open arc as an upper segment of an ellipse
    Arc2D.Double top = new Arc2D.Double(position.x, position.y,
                                     width, height,
                                     start, extent,
                                     Arc2D.OPEN);

    // Define open arc as lower segment of ellipse shifted up relative to 1st
    Arc2D.Double bottom = new Arc2D.Double(
                                     position.x, position.y - height + diameter,
                                     width, height,
                                     start + 180, extent,
                                     Arc2D.OPEN);
                                        
    // Create a circle centered between the two arcs
    Ellipse2D.Double circle1 = new Ellipse2D.Double(
                                     position.x + width/2 - diameter/2,position.y,
                                     diameter, diameter);

    // Create a second circle concentric with the first and half the diameter
    Ellipse2D.Double circle2 = new Ellipse2D.Double(
                   position.x + width/2 - diameter/4, position.y + diameter/4,
                   diameter/2, diameter/2);

    // Draw all the shapes
    g2D.setPaint(Color.BLACK);                       // Draw in black
    g2D.draw(top);
    g2D.draw(bottom);

    g2D.setPaint(Color.BLUE);                        // Draw in blue
    g2D.draw(circle1);
    g2D.draw(circle2);
    g2D.drawString("Arcs and ellipses", 80, 100);    // Draw some text
  }

  private Sketcher theApp;           // The application object
}
