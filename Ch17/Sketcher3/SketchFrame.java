// Frame for the Sketcher application
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.ButtonGroup;

public class SketchFrame extends JFrame {
  // Constructor
  public SketchFrame(String title) {
    setTitle(title);                              // Set the window title
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    setJMenuBar(menuBar);                         // Add the menu bar to the window

    JMenu fileMenu = new JMenu("File");           // Create File menu
    JMenu elementMenu = new JMenu("Elements");    // Create Elements menu

    // Construct the file drop-down menu
    newItem = fileMenu.add("New");                // Add New item
    openItem = fileMenu.add("Open");              // Add Open item
    closeItem = fileMenu.add("Close");            // Add Close item
    fileMenu.addSeparator();                      // Add separator
    saveItem = fileMenu.add("Save");              // Add Save item
    saveAsItem = fileMenu.add("Save As...");      // Add Save As item
    fileMenu.addSeparator();                      // Add separator
    printItem = fileMenu.add("Print");            // Add Print item

    // Construct the Element drop-down menu
    elementMenu.add(lineItem = new JRadioButtonMenuItem("Line", true));
    elementMenu.add(rectangleItem = new JRadioButtonMenuItem("Rectangle", false));
    elementMenu.add(circleItem = new JRadioButtonMenuItem("Circle", false));
    elementMenu.add(curveItem = new JRadioButtonMenuItem("Curve", false));
    ButtonGroup types = new ButtonGroup();
    types.add(lineItem);
    types.add(rectangleItem);
    types.add(circleItem);
    types.add(curveItem);

    elementMenu.addSeparator();

    JMenu colorMenu = new JMenu("Color");         // Color submenu
    elementMenu.add(colorMenu);                   // Add the submenu
    colorMenu.add(redItem = new JCheckBoxMenuItem("Red", false));
    colorMenu.add(yellowItem = new JCheckBoxMenuItem("Yellow", false));
    colorMenu.add(greenItem = new JCheckBoxMenuItem("Green", false));
    colorMenu.add(blueItem = new JCheckBoxMenuItem("Blue", true));

    menuBar.add(fileMenu);                       // Add the file menu
    menuBar.add(elementMenu);                    // Add the element menu
  }

  private JMenuBar menuBar = new JMenuBar();     // Window menu bar

  // File menu items
  private JMenuItem newItem,  openItem,   closeItem,       
                    saveItem, saveAsItem, printItem;

  // Element menu items
  private JRadioButtonMenuItem lineItem,  rectangleItem, circleItem,  // Types
                               curveItem, textItem;
  private JCheckBoxMenuItem    redItem,   yellowItem,                 // Colors
                               greenItem, blueItem ;  
}
