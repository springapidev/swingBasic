// Frame for the Sketcher application
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.KeyStroke;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;

import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Font;
import java.awt.Rectangle;

import java.io.File;
import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.awt.event.InputEvent.*;
import static java.awt.AWTEvent.*;
import static java.awt.Color.*;
import static Constants.SketcherConstants.*;

import java.util.Observer;
import java.util.Observable;

public class SketchFrame extends JFrame implements ActionListener, Observer {  // Constructor
  public SketchFrame(String title, Sketcher theApp) {
    frameTitle = title + ": ";
    setTitle(frameTitle + filename);

    if(!DEFAULT_DIRECTORY.exists()) {
      if(!DEFAULT_DIRECTORY.mkdirs()) {
        JOptionPane.showMessageDialog(this,
                                      "Error creating default directory",
                                      "Directory Creation Error",
                                      JOptionPane.ERROR_MESSAGE);
      }
    }
    files = new JFileChooser(DEFAULT_DIRECTORY);
    this.theApp = theApp;                        // Save application object reference
    setJMenuBar(menuBar);                        // Add the menu bar to the window
    setDefaultCloseOperation(EXIT_ON_CLOSE);     // Default is exit the application

    JMenu fileMenu = new JMenu("File");          // Create File menu
    JMenu elementMenu = new JMenu("Elements");   // Create Elements menu
    JMenu optionsMenu = new JMenu("Options");    // Create options menu
    JMenu helpMenu = new JMenu("Help");          // Create Help menu

    fileMenu.setMnemonic('F');                   // Create shortcut
    elementMenu.setMnemonic('E');                // Create shortcut
    optionsMenu.setMnemonic('O');                // Create shortcut
    helpMenu.setMnemonic('H');                   // Create shortcut 
    
    // Create the action items for the file menu
    newAction = new FileAction("New", KeyStroke.getKeyStroke('N', CTRL_DOWN_MASK),"Create new sketch");
    openAction = new FileAction("Open", KeyStroke.getKeyStroke('O', CTRL_DOWN_MASK),"Open existing sketch");
    closeAction = new FileAction("Close");
    saveAction = new FileAction("Save", KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK), "Save sketch");
    saveAsAction = new FileAction("Save As...");
    printAction = new FileAction("Print", KeyStroke.getKeyStroke('P', CTRL_DOWN_MASK), "Print sketch");

    // Construct the file drop-down menu
    fileMenu.add(new JMenuItem(newAction));
    fileMenu.add(new JMenuItem(openAction));
    fileMenu.add(new JMenuItem(closeAction));
    fileMenu.addSeparator();                     // Add separator
    fileMenu.add(new JMenuItem(saveAction));
    fileMenu.add(new JMenuItem(saveAsAction));
    fileMenu.addSeparator();                     // Add separator
    fileMenu.add(new JMenuItem(printAction));

    // Construct the Element drop-down menu
    elementMenu.add(new JMenuItem(lineAction = new TypeAction("Line", LINE, "Draw lines")));
    elementMenu.add(new JMenuItem(rectangleAction = new TypeAction("Rectangle", RECTANGLE, "Draw rectangles")));
    elementMenu.add(new JMenuItem(circleAction = new TypeAction("Circle", CIRCLE, "Draw circles")));
    elementMenu.add(new JMenuItem(curveAction = new TypeAction("Curve", CURVE, "Draw curves")));  
    elementMenu.add(new JMenuItem(textAction = new TypeAction("Text", TEXT, "Draw text")));
    elementMenu.addSeparator();

    JMenu colorMenu = new JMenu("Color");        // Color sub-menu
    elementMenu.add(colorMenu);                  // Add the sub-menu

    colorMenu.add(new JMenuItem(redAction = new ColorAction("Red", RED, "Draw in red")));
    colorMenu.add(new JMenuItem(yellowAction = new ColorAction("Yellow", YELLOW, "Draw in yellow")));
    colorMenu.add(new JMenuItem(greenAction = new ColorAction("Green", GREEN, "Draw in green")));
    colorMenu.add(new JMenuItem(blueAction = new ColorAction("Blue", BLUE, "Draw in blue")));

   // Add the font choice item to the options menu
    fontItem = new JMenuItem("Choose font...");
    fontItem.addActionListener(this);
    optionsMenu.add(fontItem);
   
    menuBar.add(fileMenu);                       // Add the file menu
    menuBar.add(elementMenu);                    // Add the element menu
    menuBar.add(optionsMenu);                    // Add the options menu
    menuBar.add(helpMenu);                       // Add the Help menu

    // Create pop-up menu
    popup.add(new JMenuItem(lineAction));
    popup.add(new JMenuItem(rectangleAction));
    popup.add(new JMenuItem(circleAction));
    popup.add(new JMenuItem(curveAction));
    popup.add(new JMenuItem(textAction));
    popup.addSeparator();
    popup.add(new JMenuItem(redAction));
    popup.add(new JMenuItem(yellowAction));
    popup.add(new JMenuItem(greenAction));
    popup.add(new JMenuItem(blueAction));
    customColorItem = popup.add(new JMenuItem("Custom Color...")); // Add the item
    customColorItem.addActionListener(this);     // and add its listener
 
    // Add file buttons
    toolBar.addSeparator();                      // Space at the start
    addToolBarButton(newAction);
    addToolBarButton(openAction);
    addToolBarButton(saveAction);
    addToolBarButton(printAction);
   
    // Add element type buttons
    toolBar.addSeparator();
    addToolBarButton(lineAction);
    addToolBarButton(rectangleAction);
    addToolBarButton(circleAction);
    addToolBarButton(curveAction);
    addToolBarButton(textAction);
    
    // Add element color buttons
    toolBar.addSeparator();
    addToolBarButton(redAction);
    addToolBarButton(yellowAction);
    addToolBarButton(greenAction);
    addToolBarButton(blueAction);
    toolBar.addSeparator();                      // Space at the end

    toolBar.setFloatable(false);                 // Inhibit toolbar floating
    toolBar.putClientProperty("JToolBar.isRollover",Boolean.TRUE);
    toolBar.setBorder(BorderFactory.createEtchedBorder(WHITE, LIGHT_GRAY));
    getContentPane().add(toolBar, BorderLayout.NORTH);

    getContentPane().add(statusBar, BorderLayout.SOUTH);   // Add the statusbar

    // Add the About item to the Help menu
    aboutItem = new JMenuItem("About");          // Create the item
    aboutItem.addActionListener(this);           // Listener is the frame
    helpMenu.add(aboutItem);                     // Add item to menu

    fontDlg = new FontDialog(this);              // Create the font dialog
  }

  // Method to add a button to the toolbar
  private JButton addToolBarButton(Action action) {
    JButton button = new JButton(action);
    button.setBorder(BorderFactory.createRaisedBevelBorder());// Add button border
    button.setText(null);
    toolBar.add(button);
    return button;
 }

  // Display a custom file save dialog
  private File showDialog(String dialogTitle,
                          String approveButtonText,
                          String approveButtonTooltip,
                          File file) {           // Current file - if any

    files.setDialogTitle(dialogTitle);
    files.setApproveButtonText(approveButtonText);
    files.setApproveButtonToolTipText(approveButtonTooltip);
    files.setFileSelectionMode(files.FILES_ONLY);
    ExtensionFilter sketchFilter = new ExtensionFilter(".ske",
                                              "Sketch files (*.ske)");
    files.addChoosableFileFilter(sketchFilter);            // Add the filter
    files.setFileFilter(sketchFilter);                     // and select it
    files.rescanCurrentDirectory();
    files.setSelectedFile(file);
    files.rescanCurrentDirectory();
    files.setSelectedFile(file);
    int result = files.showDialog(SketchFrame.this, null);  // Show the dialog
    return (result == files.APPROVE_OPTION) ? files.getSelectedFile() : null;
  }

  // Save the sketch if it is necessary
  private void saveOperation() {
    if(!sketchChanged) {
      return;
    }

    File file = modelFile;
    if(file == null) {
      file = showDialog("Save Sketch",
                        "Save",
                        "Save the sketch",
                        new File(files.getCurrentDirectory(), filename));
      if(file == null ||  (file.exists() &&    // Check for existence
         JOptionPane.NO_OPTION ==              // Overwrite warning
              JOptionPane.showConfirmDialog(SketchFrame.this,
                                      file.getName()+" exists. Overwrite?",
                                      "Confirm Save As",
                                      JOptionPane.YES_NO_OPTION,
                                      JOptionPane.WARNING_MESSAGE)))
            return;                            // No selected file
    }
    saveSketch(file);    
  }

  // Write a sketch to outFile
  private void saveSketch(File outFile) {
    try {
      ObjectOutputStream out =  new ObjectOutputStream(
                                new BufferedOutputStream(
                                new FileOutputStream(outFile)));
      out.writeObject(theApp.getModel());        // Write the sketch to the stream
      out.close();                               // Flush & close it
    } catch(IOException e) {
      System.err.println(e);
      JOptionPane.showMessageDialog(SketchFrame.this,
                                    "Error writing a sketch file.",
                                    "File Output Error",
                                    JOptionPane.ERROR_MESSAGE);
      return;                                    // Serious error - return
    }
    if(outFile != modelFile) {             // If we are saving to a new file
                                           // we must update the window
      modelFile = outFile;                       // Save file reference
      filename = modelFile.getName();            // Update the file name
      setTitle(frameTitle + modelFile.getPath());  // Change the window title
    }
    sketchChanged = false;                       // Set as unchanged
  }

  // Method called by SketchModel object when it changes
  public void update(Observable o, Object obj) {
    sketchChanged = true;
  }

  // Method making the current element color available
  public Color getElementColor() { 
    return elementColor; 
  }

  // Method making the current element type available
  public int getElementType() { 
    return elementType; 
  }

  // Method making the current font available
  public Font getCurrentFont() {  
    return font;  
  }

  // Method to set the current font
  public void setCurrentFont(Font font) {  
    this.font = font;  
  }

  // Retrieve the pop-up menu
  public JPopupMenu getPopup() {  
    return popup;  
  }

  // Handle About menu action events
  public void actionPerformed(ActionEvent e)  {
    if(e.getSource() == aboutItem) {
      // Create about dialog with the menu item as parent
      JOptionPane.showMessageDialog(this,                          // Parent
                            "Sketcher Copyright Jon David 2004", // Message
                            "About Sketcher",                      // Title
                            JOptionPane.INFORMATION_MESSAGE);      // Message type
    } else if(e.getSource() == fontItem) {       // Set the dialog window position
      Rectangle bounds = getBounds();
      fontDlg.setLocation(bounds.x + bounds.width/3, bounds.y + bounds.height/3);
      fontDlg.setVisible(true);                  // Show the dialog
    } else if(e.getSource() == customColorItem) {
      Color color = JColorChooser.showDialog(this, "Select Custom Color",
                                                           elementColor);
      if(color != null) {
        elementColor = color;
        statusBar.setColorPane(color);
      }
    }
  }

  // Inner class defining Action objects for File menu items
  class FileAction extends AbstractAction {    
    // Constructor to implement an accelerator and a tooltip
    FileAction(String name, KeyStroke keystroke, String tooltip) {
      this(name, keystroke);                     // Call the other constructor
      if(tooltip != null) {                      // If there is tooltip text
        putValue(SHORT_DESCRIPTION, tooltip);    // ...squirrel it away
      }
    }

    // Constructor to implement a tooltip
    FileAction(String name, String tooltip) {
      this(name);                                // Call the other constructor
      if(tooltip != null) {                      // If there is tooltip text
        putValue(SHORT_DESCRIPTION, tooltip);    // ...squirrel it away
      }
    }

    // Constructor
    FileAction(String name) {
      super(name);
      String iconFileName = "Images/" + name + ".gif";
      if(new File(iconFileName).exists()) {
        putValue(SMALL_ICON, new ImageIcon(iconFileName));
      }
    }

   // Constructor
    FileAction(String name, KeyStroke keystroke) {
      this(name);
      if(keystroke != null) {
        putValue(ACCELERATOR_KEY, keystroke);
      }
    }

    // Event handler
    public void actionPerformed(ActionEvent e) {
      String name = (String)getValue(NAME);
      if(name.equals(saveAction.getValue(NAME))) {
              saveOperation();
      } else if(name.equals(saveAsAction.getValue(NAME))) {
       File file = showDialog("Save Sketch As",
                              "Save",
                              "Save the sketch",
                              modelFile == null ? new File(
                                                files.getCurrentDirectory(),
                                                filename):modelFile);
       if(file != null) {
          if(file.exists() && !file.equals(modelFile))
           if(JOptionPane.NO_OPTION ==                  // Overwrite warning
              JOptionPane.showConfirmDialog(SketchFrame.this,
                                       file.getName()+" exists. Overwrite?",
                                       "Confirm Save As",
                                       JOptionPane.YES_NO_OPTION,
                                       JOptionPane.WARNING_MESSAGE))
            return;                                     // No file selected
         saveSketch(file);
       }
       return;
      } else if(name.equals(openAction.getValue(NAME))) {
        // Code to handle file Open operation...
      } else if(name.equals(newAction.getValue(NAME))) {
        // Code to handle file New operation...
      } if(name.equals(printAction.getValue(NAME))) {
        // Code to handle Print operation..
      }
    }
  }

  // Inner class defining Action objects for Element type menu items
  class TypeAction extends AbstractAction {    
    // Constructor to implement a tooltip
    TypeAction(String name, int typeID, String tooltip) {
      this(name, typeID);
      if(tooltip != null) {                      // If there is a tooltip
        putValue(SHORT_DESCRIPTION, tooltip);    // ...squirrel it away
      }
    }

    TypeAction(String name, int typeID) {
      super(name);
      this.typeID = typeID;
      String iconFileName = "Images/" + name + ".gif";
      if(new File(iconFileName).exists()) {
        putValue(SMALL_ICON, new ImageIcon(iconFileName));
      }
    }
  
    public void actionPerformed(ActionEvent e) {
      elementType = typeID;  
      statusBar.setTypePane(typeID);
    }

    private int typeID;
  }

  // Handles color menu items
  class ColorAction  extends AbstractAction {
    // Constructor to implement a tooltip
    public ColorAction(String name, Color color, String tooltip) {
      this(name, color);
      if(tooltip != null) {                      // If there is a tooltip
        putValue(SHORT_DESCRIPTION, tooltip);    // ...squirrel it away
      }
    }

    public ColorAction(String name, Color color) {
      super(name);
      this.color = color;
      String iconFileName = "Images/" + name + ".gif";
      if(new File(iconFileName).exists()) {
        putValue(SMALL_ICON, new ImageIcon(iconFileName));
      }
    }

    public void actionPerformed(ActionEvent e) {
      elementColor = color;
      statusBar.setColorPane(color);
    }

    private Color color;
  }


  // File actions
  private FileAction newAction, openAction, closeAction,
                     saveAction, saveAsAction, printAction;

  // Element type actions
  private TypeAction lineAction, rectangleAction, circleAction, curveAction, textAction;
 
  // Element color actions
  private ColorAction redAction, yellowAction,
                      greenAction, blueAction;

  private JMenuBar menuBar = new JMenuBar();           // Window menu bar

  // Sundry menu items
  private JMenuItem aboutItem, fontItem;
  private JMenuItem customColorItem;
  
  private JToolBar toolBar = new JToolBar();           // Window toolbar
  private StatusBar statusBar = new StatusBar();       // Window status bar
  private Color elementColor = DEFAULT_ELEMENT_COLOR;  // Current element color
  private int elementType = DEFAULT_ELEMENT_TYPE;      // Current element type
  private Font font = DEFAULT_FONT;                    // Current font
  private Sketcher theApp;                             // The application object
  private FontDialog fontDlg;                          // The font dialog
  private JPopupMenu popup = new JPopupMenu("General");// Window pop-up

  private String frameTitle;                           // Frame title
  private String filename = DEFAULT_FILENAME;          // Current model file name
  private boolean sketchChanged = false;               // Model changed flag
  private File modelFile;                              // File for the current sketch
  private JFileChooser files;                          // File chooser dialog
}
