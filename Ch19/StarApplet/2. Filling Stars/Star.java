import java.awt.geom.Point2D;
import java.awt.geom.GeneralPath;
import java.awt.Shape;

public class Star {
  public Star(float x, float y) {
    start = new Point2D.Float(x, y);                      // store start point
    createStar();
  }

  // Create the path from start
  private void createStar() {
    Point2D.Float point = start;
    p = new GeneralPath(GeneralPath.WIND_NON_ZERO);
    p.moveTo(point.x, point.y);
    p.lineTo(point.x + 20.0f, point.y - 5.0f);            // Line from start to A
    point = (Point2D.Float)p.getCurrentPoint();
    p.lineTo(point.x + 5.0f, point.y - 20.0f);            // Line from A to B
    point = (Point2D.Float)p.getCurrentPoint();
    p.lineTo(point.x + 5.0f, point.y + 20.0f);            // Line from B to C
    point = (Point2D.Float)p.getCurrentPoint();
    p.lineTo(point.x + 20.0f, point.y + 5.0f);            // Line from C to D
    point = (Point2D.Float)p.getCurrentPoint();
    p.lineTo(point.x - 20.0f, point.y + 5.0f);            // Line from D to E
    point = (Point2D.Float)p.getCurrentPoint();
    p.lineTo(point.x - 5.0f, point.y + 20.0f);            // Line from E to F
    point = (Point2D.Float)p.getCurrentPoint();
    p.lineTo(point.x - 5.0f, point.y - 20.0f);            // Line from F to g
    p.closePath();                                        // Line from G to start
  }

  // Modify the location of this star
  Shape atLocation(float x, float y) {
    start.setLocation(x, y);                              // Store new start
    p.reset();                                            // Erase current path
    createStar();                                         // create new path
    return p;                                             // Return the path
  }

  // Make the path available
  public Shape getShape() { 
    return p; 
  }

  private Point2D.Float start;                           // Start point for star
  private GeneralPath p;                                 // Star path
}
