// Defines application wide constants
package Constants;
import java.awt.Color;
import java.awt.Font;
import java.io.File;

public class SketcherConstants { 
  // Element type definitions
  public final static int LINE      = 101;
  public final static int RECTANGLE = 102;
  public final static int CIRCLE    = 103;
  public final static int CURVE     = 104;
  public final static int TEXT      = 105;
  public final static int ELLIPSE   = 106;
  // Initial conditions
  public final static int DEFAULT_ELEMENT_TYPE = LINE;
  public final static Color DEFAULT_ELEMENT_COLOR = Color.BLUE;
  public final static Font DEFAULT_FONT = new Font("SansSerif",Font.PLAIN, 12);

  public final static int pointSizeMin = 8;      // Minimum font point size
  public final static int pointSizeMax = 24;     // Maximum font point size
  public final static int pointSizeStep = 2;     // Point size step

  // Operating modes
  public final static int NORMAL = 0;
  public final static int MOVE   = 1;
  public final static int ROTATE = 2;
  public final static int SCALE  = 3;

  // Constants related to storing a sketch
  public final static File DEFAULT_DIRECTORY = new File("C:/Sketches");
  public final static String DEFAULT_FILENAME = "Sketch.ske";

  // Constants used in creating a sketch in XML
  public final static String QUOTE_ENTITY = "&quot;";
  public final static char QUOTE = '\"';
  public final static char NEWLINE = '\n';
  public final static char TAG_START = '<';
  public final static char TAG_END = '>';
  public final static String EMPTY_TAG_END = "/>";
  public final static String END_TAG_START = "</";
}
