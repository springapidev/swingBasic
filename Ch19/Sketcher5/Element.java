import java.awt.Color;
import java.awt.Shape;
import java.awt.Point;
import java.awt.geom.Line2D;

public abstract class Element {
  public Element(Color color) {
    this.color = color;  
  }

  public Color getColor() {
    return color;  
  }

  public abstract Shape getShape();
  public abstract java.awt.Rectangle getBounds();
  public abstract void modify(Point start, Point last);

  protected Color color;                             // Color of a shape

  // Nested class defining a line
  public static class Line extends Element {
    public Line(Point start, Point end, Color color) {
      super(color);
      line = new Line2D.Double(start, end);
    }

    // Method to return the line as a Shape reference
    public Shape getShape() { 
      return line; 
    }

    // Obtain the rectangle bounding the line
    public java.awt.Rectangle getBounds() {
      return line.getBounds();  
    }

    // Change the end point for the line
    public void modify(Point start, Point last) {
      line.x2 = last.x;
      line.y2 = last.y;
    }

    private Line2D.Double line;
  }
}
