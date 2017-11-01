import java.awt.Color;
import java.awt.Shape;
import java.awt.Point;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D; 
import java.awt.geom.Ellipse2D; 
import java.awt.geom.GeneralPath; 
import java.awt.geom.AffineTransform; 
import java.awt.geom.PathIterator;

import java.util.Vector;

import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Attr;

public abstract class Element implements Serializable {
  public Element(Color color) {
    this.color = color;  
  }

  // Constrcutor to initialize fields from XML document
  protected Element(org.w3c.dom.Element xmlElement) {
    // Get the <color> element
    org.w3c.dom.NodeList list = xmlElement.getElementsByTagName("color");
    setElementColor((org.w3c.dom.Element)list.item(0));        // Set the color

    list = xmlElement.getElementsByTagName("position");        // Get <position>
    setElementPosition((org.w3c.dom.Element)list.item(0));     // Set the position

    angle = Double.parseDouble(xmlElement.getAttribute("angle")); // Set the angle
  }

  public Color getColor() {
    return color;  
  }

  // Set or reset highlight color
  public void setHighlighted(boolean highlighted) {
    this.highlighted = highlighted;
  }

  // Get the current position of the element
  public Point getPosition() {  
    return position;  
  }

  // Draw an element in a given graphics context
  protected void draw(Graphics2D g2D, Shape element) {
    g2D.setPaint(highlighted ? Color.MAGENTA : color);  // Set the element color
    AffineTransform old = g2D.getTransform();           // Save the current transform
    g2D.translate(position.x, position.y);              // Translate to position
    g2D.rotate(angle);                                  // Rotate about position
    g2D.draw(element);                                  // Draw the element
    g2D.setTransform(old);                              // Restore original transform
  }

  protected java.awt.Rectangle getBounds(java.awt.Rectangle bounds) { 
    AffineTransform at = AffineTransform.getTranslateInstance(position.x, position.y);
    at.rotate(angle);
    return at.createTransformedShape(bounds).getBounds();
  }

  // Move an element
  public void move(int deltax, int deltay) {
    position.x += deltax;
    position.y += deltay;
  } 

  // Increment the element rotation angle
  public void rotate(double angle) {  
    this.angle += angle;
  }

  // Create XML <color> element
  protected org.w3c.dom.Element createColorElement(Document doc) {
    org.w3c.dom.Element colorElement = doc.createElement("color");
 
    Attr attr = doc.createAttribute("R");
    attr.setValue(String.valueOf(color.getRed()));
    colorElement.setAttributeNode(attr);  

    attr = doc.createAttribute("G");
    attr.setValue(String.valueOf(color.getGreen()));
    colorElement.setAttributeNode(attr);  

    attr = doc.createAttribute("B");
    attr.setValue(String.valueOf(color.getBlue()));
    colorElement.setAttributeNode(attr);
    return colorElement;      
  }

  // Create XML point-type element
  protected org.w3c.dom.Element createPointTypeElement(Document doc,
                                                       String name,
                                                       String xValue,
                                                       String yValue) { 
    org.w3c.dom.Element element = doc.createElement(name);

    Attr attr = doc.createAttribute("x");         // Create attribute x
    attr.setValue(xValue);                        // and set its value
    element.setAttributeNode(attr);               // Insert the x attribute   

    attr = doc.createAttribute("y");              // Create attribute y
    attr.setValue(yValue);                        // and set its value
    element.setAttributeNode(attr);               // Insert the y attribute   
    return element;              
  }

  // Create XML <position> element
  protected org.w3c.dom.Element createPositionElement(Document doc) {
    return createPointTypeElement(doc, "position",
                                  String.valueOf(position.getX()),
                                  String.valueOf(position.getY()));
  }

  // Set element color from XML <color> element
  protected void setElementColor(org.w3c.dom.Element colorElement) {
      color = new Color(Integer.parseInt(colorElement.getAttribute("R")),
                        Integer.parseInt(colorElement.getAttribute("G")),
                        Integer.parseInt(colorElement.getAttribute("B")));
  }

  // Set element position from XML <position> element
  protected void setElementPosition(org.w3c.dom.Element posElement) {
      position = new Point(); 
      position.setLocation(Double.parseDouble(posElement.getAttribute("x")),
                           Double.parseDouble(posElement.getAttribute("y")));
  }

  public abstract java.awt.Rectangle getBounds();
  public abstract void modify(Point start, Point last);
  public abstract void draw(Graphics2D g2D);
  public abstract void addElementNode(Document document);
  
  protected Color color;                         // Color of a shape
  protected boolean highlighted = false;         // Highlight flag
  final static Point origin = new Point();       // Point 0,0
  protected Point position;                      // Element position
  protected double angle = 0.0;                  // Rotation angle
  
  // Nested class defining a line
  public static class Line extends Element {
    public Line(Point start, Point end, Color color) {
      super(color);
      position = start;
      line = new Line2D.Double(origin, new Point(end.x - position.x, end.y - position.y));
    }

  // Content is <color>, <position>, <endpoint> elements. Attribute is angle.
  public Line(org.w3c.dom.Element xmlElement) {
    super(xmlElement);

    org.w3c.dom.NodeList list = xmlElement.getElementsByTagName("endpoint");
    org.w3c.dom.Element endpoint = (org.w3c.dom.Element)list.item(0);
    line = new Line2D.Double(origin.x, origin.y,
                Double.parseDouble(endpoint.getAttribute("x"))-position.getX(), 
                Double.parseDouble(endpoint.getAttribute("y"))-position.getY());
  } 

    // Method to serialize a line
    private void writeObject(ObjectOutputStream out) throws IOException {
      out.writeDouble(line.x2);
      out.writeDouble(line.y2);
    }

    // Method to deserialize a line
    private void readObject(java.io.ObjectInputStream in)
                               throws IOException, ClassNotFoundException {
      double x2 = in.readDouble();
      double y2 = in.readDouble();
      line = new Line2D.Double(0,0,x2,y2);
    }

    public void draw(Graphics2D g2D) {
      draw(g2D, line);                           // Call base draw method
    }

    // Obtain the rectangle bounding the line
    public java.awt.Rectangle getBounds() {
      return getBounds(line.getBounds());
    }

    // Change the end point for the line
    public void modify(Point start, Point last) {
      line.x2 = last.x - position.x;
      line.y2 = last.y - position.y;
    }

    // Add XML <line> element
    public void addElementNode(Document doc) {
      org.w3c.dom.Element lineElement = doc.createElement("line");
 
      // Create the angle attribute and attach it to the <line> node
      Attr attr = doc.createAttribute("angle");
      attr.setValue(String.valueOf(angle));
      lineElement.setAttributeNode(attr);
  
      // Append the <color>, <position>, and <endpoint> nodes as children
      lineElement.appendChild(createColorElement(doc));
      lineElement.appendChild(createPositionElement(doc));
      lineElement.appendChild(createEndpointElement(doc));
  
      // Append the <line> node to the document root node
      doc.getDocumentElement().appendChild(lineElement); 
    } 

    // Create XML <endpoint> element
    private org.w3c.dom.Element createEndpointElement(Document doc) {
      return createPointTypeElement(doc, "endpoint",
                                    String.valueOf(line.x2+position.x),
                                    String.valueOf(line.y2+position.y));
    }

    private Line2D.Double line;
  }

  // Nested class defining a rectangle
  public static class Rectangle extends Element {
    public Rectangle(Point start, Point end, Color color) {
      super(color);
    position = new Point(Math.min(start.x, end.x),
                         Math.min(start.y, end.y));
    rectangle = new Rectangle2D.Double(origin.x,
                                       origin.y,
                                       Math.abs(start.x - end.x),     // Width
                                       Math.abs(start.y - end.y));    // & height 
    }

    // Rectangle has angle attribute. Content is <color>,<position>,<bottomright>
    public Rectangle(org.w3c.dom.Element xmlElement) {
      super(xmlElement);

      org.w3c.dom.NodeList list = xmlElement.getElementsByTagName("bottomright");
      org.w3c.dom.Element bottomright = (org.w3c.dom.Element)list.item(0);
      rectangle = new Rectangle2D.Double(origin.x, origin.y, 
             Double.parseDouble(bottomright.getAttribute("x"))-position.getX(), 
             Double.parseDouble(bottomright.getAttribute("y"))-position.getY());
    }

    // Method to serialize a rectangle
    private void writeObject(ObjectOutputStream out) throws IOException {
      out.writeDouble(rectangle.width);
      out.writeDouble(rectangle.height);
    }

    // Method to deserialize a rectangle
    private void readObject(ObjectInputStream in)
                 throws IOException, ClassNotFoundException {
      double width = in.readDouble();
      double height = in.readDouble();
      rectangle = new Rectangle2D.Double(0,0,width,height);
    }

    public void draw(Graphics2D g2D) {
      draw(g2D, rectangle);                      // Call base draw method
    }

    // Method to return the rectangle enclosing this rectangle
    public java.awt.Rectangle getBounds() { 
      return getBounds(rectangle.getBounds());
    }

    // Method to redefine the rectangle
    public void modify(Point start, Point last) {
      position.x = Math.min(start.x, last.x);
      position.y = Math.min(start.y, last.y);
      rectangle.width = Math.abs(start.x - last.x);
      rectangle.height = Math.abs(start.y - last.y);
    }

    // Add an XML <rectangle> element
    public void addElementNode(Document doc) {
      org.w3c.dom.Element rectElement = doc.createElement("rectangle");
 
      // Create the angle attribute and attach it to the <rectangle> node
      Attr attr = doc.createAttribute("angle");
      attr.setValue(String.valueOf(angle));
      rectElement.setAttributeNode(attr);
 
      // Append the <color>, <position>, and <bottomright> nodes as children
      rectElement.appendChild(createColorElement(doc));
      rectElement.appendChild(createPositionElement(doc));
      rectElement.appendChild(createBottomrightElement(doc));
 
      doc.getDocumentElement().appendChild(rectElement);
    }

    // Create XML <bottomright> element
    private org.w3c.dom.Element createBottomrightElement(Document doc) {
      return createPointTypeElement(doc, "bottomright",
                                    String.valueOf(rectangle.width+position.x),
                                    String.valueOf(rectangle.height+position.y));
    }

    private Rectangle2D.Double rectangle;
  }

  // Nested class defining a circle
  public static class Circle extends Element {
    public Circle(Point center, Point circum, Color color) {
      super(color);
  
      // Radius is distance from center to circumference
      double radius = center.distance(circum);
      position = new Point(center.x - (int)radius,
                           center.y - (int)radius);
      
      circle = new Ellipse2D.Double(origin.x, origin.y,     // Position - top-left
                                    2.*radius, 2.*radius ); // Width & height
    }

    // Circle has radius, angle attributes. Content is <color>, <position>
    public Circle(org.w3c.dom.Element xmlElement) {
      super(xmlElement);
      
      double radius = Double.parseDouble(xmlElement.getAttribute("radius")); 
      circle = new Ellipse2D.Double(origin.x, origin.y,     // Position - top-left
                                    2.*radius, 2.*radius);  // Width & height
    }

    // Method to serialze a circle
    private void writeObject(ObjectOutputStream out) throws IOException {
      out.writeDouble(circle.width);
    }

    // Method to deserialize a circle
    private void readObject(ObjectInputStream in)
                               throws IOException, ClassNotFoundException {
      double width = in.readDouble();
      circle = new Ellipse2D.Double(0,0,width,width);
    }

    public void draw(Graphics2D g2D) {
      draw(g2D, circle);                         // Call base draw method
    }

    // Return the rectangle bounding this circle
    public java.awt.Rectangle getBounds() { 
      return getBounds(circle.getBounds());
    }

    // Recreate this circle
    public void modify(Point center, Point circum) {
      double radius = center.distance(circum);
      position.x = center.x - (int)radius;
      position.y = center.y - (int)radius;
      circle.width = circle.height = 2*radius;
    }

    // Add XML <circle> element
    public void addElementNode(Document doc) {
      org.w3c.dom.Element circleElement = doc.createElement("circle");
 
      // Create the radius attribute and attach it to the <circle> node
      Attr attr = doc.createAttribute("radius");
      attr.setValue(String.valueOf(circle.width/2.0));
      circleElement.setAttributeNode(attr);
  
      // Create the angle attribute and attach it to the <circle> node
      attr = doc.createAttribute("angle");
      attr.setValue(String.valueOf(angle));
      circleElement.setAttributeNode(attr);

      // Append the <color> and <position> nodes as children
      circleElement.appendChild(createColorElement(doc));
      circleElement.appendChild(createPositionElement(doc));
 
      doc.getDocumentElement().appendChild(circleElement);
    }

    private Ellipse2D.Double circle;
  }

  // Nested class defining a curve
  public static class Curve extends Element {
    public Curve(Point start, Point next, Color color) {
      super(color);
      curve = new GeneralPath();
      position = start;
      curve.moveTo(origin.x, origin.y);
      curve.lineTo(next.x - position.x,
                   next.y - position.y);
    }

    // Curve has angle attribute. Content is <color>, <position>, <point>+
    public Curve(org.w3c.dom.Element xmlElement) {
      super(xmlElement);

      curve = new GeneralPath();
      curve.moveTo(origin.x, origin.y);
      org.w3c.dom.NodeList nodes = xmlElement.getElementsByTagName("point");
      for(int i = 0 ; i<nodes.getLength() ; i++) {
        curve.lineTo(
                     (float)(Double.parseDouble(
           ((org.w3c.dom.Element)nodes.item(i)).getAttribute("x")) - position.x),
                     (float)(Double.parseDouble(
           ((org.w3c.dom.Element)nodes.item(i)).getAttribute("y")) - position.y));
      }
    }

    // Method to serialize a curve
    private void writeObject(ObjectOutputStream out) throws IOException {
      PathIterator iterator = curve.getPathIterator(new AffineTransform());
      Vector<Float> coords = new Vector<Float>();
      int maxCoordCount = 6;
      float[] temp = new float[maxCoordCount];   // Stores segment data

      int result = iterator.currentSegment(temp); // Get first segment
      assert(result == iterator.SEG_MOVETO);
      iterator.next();                           // Next segment
      while(!iterator.isDone()) {                // While there are segments
        result = iterator.currentSegment(temp);  // Get the segment data
        assert(result == iterator.SEG_LINETO);

        coords.add(temp[0]);                     // Add x coordinate to Vector
        coords.add(temp[1]);                     // Add y coordinate
        iterator.next();                         // Go to next segment
      }

      out.writeObject(coords);                   // Save the Vector
}

    // Method to deserialize a curve
    private void readObject(ObjectInputStream in)
                        throws IOException, ClassNotFoundException {
      Vector<Float> coords = (Vector<Float>)in.readObject(); // Read vector
      curve = new GeneralPath();                       // Create a path
      curve.moveTo(0,0);                               // Move to the origin

      for(int i = 0 ; i<coords.size() ; i += 2 ) {     // For each pair of elements
        curve.lineTo(coords.get(i), coords.get(i+1));  // Create a line segment
      }
    }

    // Add another segment
    public void modify(Point start, Point next) {
      curve.lineTo(next.x - start.x,
                   next.y - start.y);
    }

    public void draw(Graphics2D g2D) {
      draw(g2D, curve);                          // Call base draw method
    }

    // Return the rectangle bounding the path
    public java.awt.Rectangle getBounds() { 
      return getBounds(curve.getBounds());
    }

    // Add XML <curve> element
    public void addElementNode(Document doc) {
      org.w3c.dom.Element curveElement = doc.createElement("curve");
  
      // Create the angle attribute and attach it to the <curve> node
      Attr attr = doc.createAttribute("angle");
      attr.setValue(String.valueOf(angle));
      curveElement.setAttributeNode(attr);
   
      // Append the <color> and <position> nodes as children
      curveElement.appendChild(createColorElement(doc));
      curveElement.appendChild(createPositionElement(doc));
  
      // Get the defining points via a path iterator
      PathIterator iterator = curve.getPathIterator(new AffineTransform());
      int maxCoordCount = 6;                  // Maximum coordinates for a segment
      float[] temp = new float[maxCoordCount];           // Stores segment data
  
      int result = iterator.currentSegment(temp);        // Get first segment
      assert result == iterator.SEG_MOVETO;              // ... should be move to
  
      iterator.next();                                   // Next segment
      while(!iterator.isDone())   {                      // While you have segments
        result = iterator.currentSegment(temp);          // Get the segment data
        assert result == iterator.SEG_LINETO;            // Should all be lines
   
        // Create a <point> node and add it to the list of children
        curveElement.appendChild(createPointTypeElement(doc, "point",
                                      String.valueOf(temp[0]+position.x),
                                      String.valueOf(temp[1]+position.y)));
        iterator.next();                                   // Go to next segment
      }
  
      doc.getDocumentElement().appendChild(curveElement);
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
      this.position.y -= (int)bounds.getHeight();
      this.text = text;
      this.bounds = new java.awt.Rectangle(origin.x, origin.y,
                                            bounds.width, bounds.height);
    }

    // Text has angle attribute. Content is <color>, <position>, <font>, <string>
    // <font> has attributes fontname, fontstyle, pointsize
    // fontstyle is "plain", "bold", "italic", or "bold-italic"
    // <string> content is text plus <bounds>
    public Text(org.w3c.dom.Element xmlElement) {
      super(xmlElement);

      // Get the font details
      org.w3c.dom.NodeList list = xmlElement.getElementsByTagName("font");
      org.w3c.dom.Element fontElement = (org.w3c.dom.Element)list.item(0);
      String styleStr = fontElement.getAttribute("fontstyle");
      int style = 0;
      if(styleStr.equals("plain")) {
        style = Font.PLAIN;
      } else if(styleStr.equals("bold")) {
        style = Font.BOLD;
      } else if(styleStr.equals("italic")) {
        style = Font.ITALIC;
      } else if(styleStr.equals("bold-italic")) {
        style = Font.BOLD + Font.ITALIC;
      } else {
        assert false;
      } 
      font = new Font(fontElement.getAttribute("fontname"), style,
                      Integer.parseInt(fontElement.getAttribute("pointsize")));    

      // Get string bounds
      list = xmlElement.getElementsByTagName("bounds");
      org.w3c.dom.Element boundsElement = (org.w3c.dom.Element)list.item(0);
      
      this.bounds = new java.awt.Rectangle(origin.x, origin.y,
                          Integer.parseInt(boundsElement.getAttribute("width")),
                          Integer.parseInt(boundsElement.getAttribute("height")));
      
      // Get the string
      list = xmlElement.getElementsByTagName("string");
      org.w3c.dom.Element string = (org.w3c.dom.Element)list.item(0);
      list = string.getChildNodes();

     StringBuffer textStr = new StringBuffer();
     for(int i = 0 ; i<list.getLength() ; i++) {
        if(list.item(i).getNodeType()==org.w3c.dom.Node.TEXT_NODE) {
          textStr.append(((org.w3c.dom.Text)list.item(i)).getData());
        }
     }
     text = textStr.toString().trim();
   }  

    public java.awt.Rectangle getBounds() {
      return getBounds(bounds);
    }

    public void draw(Graphics2D g2D)  {
      g2D.setPaint(highlighted ? Color.MAGENTA : color);
      Font oldFont = g2D.getFont();                   // Save the old font
      g2D.setFont(font);                              // Set the new font

      AffineTransform old = g2D.getTransform();       // Save the current transform
      g2D.translate(position.x, position.y);          // Translate to position
      g2D.rotate(angle);                              // Rotate about position
      g2D.drawString(text, origin.x, origin.y+(int)bounds.getHeight());
      g2D.setTransform(old);                          // Restore original transform
      g2D.setFont(oldFont);                           // Restore the old font
    }

    public void modify(Point start, Point last) {
      // No code is required here, but we must supply a definition
    }

    // Add XML <text> element
    public void addElementNode(Document doc) {
      org.w3c.dom.Element textElement = doc.createElement("text");
 
      // Create the angle attribute and attach it to the <text> node
      Attr attr = doc.createAttribute("angle");
      attr.setValue(String.valueOf(angle));
      textElement.setAttributeNode(attr);
     
      // Append the <color> and <position> nodes as children
      textElement.appendChild(createColorElement(doc));
      textElement.appendChild(createPositionElement(doc));
   
      // Create and apppend the <font> node 
      org.w3c.dom.Element fontElement = doc.createElement("font");
      attr = doc.createAttribute("fontname");
      attr.setValue(font.getName());
      fontElement.setAttributeNode(attr);
  
      attr = doc.createAttribute("fontstyle");
      String style = null;
      int styleCode = font.getStyle();
      if(styleCode == Font.PLAIN) {
        style = "plain";
      } else if(styleCode == Font.BOLD) {
        style = "bold";
      } else if(styleCode == Font.ITALIC) {
        style = "italic";
      } else if(styleCode == Font.ITALIC+Font.BOLD) {
          style = "bold-italic";
      }
      assert style != null;
      attr.setValue(style);
      fontElement.setAttributeNode(attr);
 
      attr = doc.createAttribute("pointsize");
      attr.setValue(String.valueOf(font.getSize()));
      fontElement.setAttributeNode(attr);
      textElement.appendChild(fontElement);
 
      // Create the <string> node
      org.w3c.dom.Element string = doc.createElement("string");
 
      // Create the <bounds> node and its attributes
      org.w3c.dom.Element bounds = doc.createElement("bounds");
      attr = doc.createAttribute("width");
      attr.setValue(String.valueOf(this.bounds.width));
      bounds.setAttributeNode(attr);
      attr = doc.createAttribute("height");
      attr.setValue(String.valueOf(this.bounds.height));
      bounds.setAttributeNode(attr);
      string.appendChild(bounds);      // Set <bounds> element as <string> content

      string.appendChild(doc.createTextNode(text));
      textElement.appendChild(string);// Set <text> as <string> content
      doc.getDocumentElement().appendChild(textElement);
    }

    private Font font;                           // The font to be used
    private String text;                         // Text to be displayed
    private java.awt.Rectangle bounds;           // The bounding rectangle
  }
}
