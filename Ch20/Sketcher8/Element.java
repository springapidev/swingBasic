import java.awt.Color;
import java.awt.Shape;
import java.awt.Point;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D; 
import java.awt.geom.Ellipse2D; 
import java.awt.geom.GeneralPath; 

public abstract class Element {
  public Element(Color color) {
    this.color = color;  
  }

  public Color getColor() {
    return color;  
  }

  // Set or reset highlight color
  public void setHighlighted(boolean highlighted) {
    this.highlighted = highlighted;
  }

  public abstract java.awt.Rectangle getBounds();
  public abstract void modify(Point start, Point last);
  public abstract void draw(Graphics2D g2D);

  protected Color color;                         // Color of a shape
  protected boolean highlighted = false;         // Highlight flag
  // Nested class defining a line
  public static class Line extends Element {
    public Line(Point start, Point end, Color color) {
      super(color);
      line = new Line2D.Double(start, end);
    }

    public void draw(Graphics2D g2D) {
      g2D.setPaint(highlighted ? Color.MAGENTA : color);
      g2D.draw(line);                            // Draw the line
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

  // Nested class defining a rectangle
  public static class Rectangle extends Element {
    public Rectangle(Point start, Point end, Color color) {
      super(color);
      rectangle = new Rectangle2D.Double(
        Math.min(start.x, end.x), Math.min(start.y, end.y),    // Top-left corner
        Math.abs(start.x - end.x), Math.abs(start.y - end.y)); // Width & height
    }

    public void draw(Graphics2D g2D) {
      g2D.setPaint(highlighted ? Color.MAGENTA : color);
      g2D.draw(rectangle);                       // Draw the rectangle
    }

    // Method to return the rectangle enclosing this rectangle
    public java.awt.Rectangle getBounds() { 
      return rectangle.getBounds();  
    }

    // Method to redefine the rectangle
    public void modify(Point start, Point last) {
      rectangle.x = Math.min(start.x, last.x);
      rectangle.y = Math.min(start.y, last.y);
      rectangle.width = Math.abs(start.x - last.x);
      rectangle.height = Math.abs(start.y - last.y);
    }

    private Rectangle2D.Double rectangle;
  }

  // Nested class defining a circle
  public static class Circle extends Element {
    public Circle(Point center, Point circum, Color color) {
      super(color);
  
      // Radius is distance from center to circumference
      double radius = center.distance(circum);
      circle = new Ellipse2D.Double(center.x - radius, center.y - radius, 
                                    2.*radius, 2.*radius); 
    }

    public void draw(Graphics2D g2D) {
      g2D.setPaint(highlighted ? Color.MAGENTA : color);
      g2D.draw(circle);                          // Draw the circle
    }

    // Return the rectangle bounding this circle
    public java.awt.Rectangle getBounds() { 
      return circle.getBounds();  
    }

    // Recreate this circle
    public void modify(Point center, Point circum) {
      double radius = center.distance(circum);
      circle.x = center.x - (int)radius;
      circle.y = center.y - (int)radius;
      circle.width = circle.height = 2*radius;
    }

    private Ellipse2D.Double circle;
  }

  // Nested class defining a curve
  public static class Curve extends Element {
    public Curve(Point start, Point next, Color color) {
      super(color);
      curve = new GeneralPath();
      curve.moveTo(start.x, start.y);            // Set current position as start
      curve.lineTo(next.x, next.y);              // Add segment line to next
    }

    // Add another segment
    public void modify(Point start, Point next) {
      curve.lineTo(next.x, next.y);              // Add segment to next point
    }

    public void draw(Graphics2D g2D) {
      g2D.setPaint(highlighted ? Color.MAGENTA : color);
      g2D.draw(curve);                           // Draw the curve
    }

    // Return the rectangle bounding the path
    public java.awt.Rectangle getBounds() { 
      return curve.getBounds();  
    }

    private GeneralPath curve;
  }

  // Nested class defining text elements
  public static class Text extends Element  {
    public Text(Font font, String text, Point position, Color color,
                                        java.awt.Rectangle bounds) {
      super(color);
      this.font = font;
      this.position = position;
      this.text = text;
      this.bounds = bounds;
      this.bounds.setLocation(position.x, position.y - (int)bounds.getHeight());
    }

    public java.awt.Rectangle getBounds() {
      return bounds;
    }

    public void draw(Graphics2D g2D)  {
      g2D.setPaint(highlighted ? Color.MAGENTA : color);
      Font oldFont = g2D.getFont();              // Save the old font
      g2D.setFont(font);                         // Set the new font
      g2D.drawString(text, position.x, position.y);
      g2D.setFont(oldFont);                      // Restore the old font
    }

    public void modify(Point start, Point last) {
      // No code is required here, but we must supply a definition
    }

    private Font font;                           // The font to be used
    private String text;                         // Text to be displayed
    private Point position;                      // Position of the text
    private java.awt.Rectangle bounds;           // The bounding rectangle
  }
}
