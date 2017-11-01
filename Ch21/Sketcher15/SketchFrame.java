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

import javax.print.PrintService;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.awt.print.PrinterException;
import java.awt.print.PageFormat;
import javax.print.attribute.HashPrintRequestAttributeSet;

import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.io.File;
import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import static java.awt.event.InputEvent.*;
import static java.awt.AWTEvent.*;
import static java.awt.Color.*;
import static Constants.SketcherConstants.*;

import java.util.Observer;
import java.util.Observable;

public class SketchFrame extends    JFrame 
                         implements ActionListener, Observer, Printable {  // Constructor
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
    saveAction = new FileAction("Save", KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK), "Save sketch");
    saveAsAction = new FileAction("Save As...");
    printAction = new FileAction("Print", KeyStroke.getKeyStroke('P', CTRL_DOWN_MASK), "Print sketch");
    closeAction = new FileAction("Exit",KeyStroke.getKeyStroke('X', CTRL_DOWN_MASK ), "Exit Sketcher");

    printSetupItem = new JMenuItem("Print Setup...");
    printSetupItem.addActionListener(this);
    printWindowItem = new JMenuItem("Print Window");
    printWindowItem.addActionListener(this);

    // Construct the file drop-down menu
    fileMenu.add(new JMenuItem(newAction));
    fileMenu.add(new JMenuItem(openAction));
    fileMenu.addSeparator();                     // Add separator
    fileMenu.add(new JMenuItem(saveAction));
    fileMenu.add(new JMenuItem(saveAsAction));
    fileMenu.addSeparator();                     // Add separator
    fileMenu.add(new JMenuItem(printAction));
    fileMenu.add(printSetupItem);
    fileMenu.add(printWindowItem);
    fileMenu.addSeparator();                     // Add separator
    fileMenu.add(new JMenuItem(closeAction));

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
    printButton = addToolBarButton(printAction);
   
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

    // Set up the printer and page format objects
    printJob = PrinterJob.getPrinterJob(); // Get a printing object
    pageFormat = printJob.defaultPage();   // Get the page format
    printer = printJob.getPrintService();  // Get the default printer
  }

  // Method to add a button to the toolbar
  private JButton addToolBarButton(Action action) {
    JButton button = new JButton(action);
    button.setBorder(BorderFactory.createRaisedBevelBorder());// Add button border
    button.setText(null);
    toolBar.add(button);
    return button;
 }

  // Print the window
  public int print(Graphics g,
                   PageFormat pageFormat,
                   int pageIndex)
             throws PrinterException {
    if(pageIndex>0)   // Only one page page 0 to be printed
      return NO_SUCH_PAGE;

    // Scale the component to fit
    Graphics2D g2D = (Graphics2D) g;

    // Calculate the scale factor to fit the window to the page
    double scaleX = pageFormat.getImageableWidth()/getWidth();
    double scaleY = pageFormat.getImageableHeight()/getHeight();

    double scale = Math.min(scaleX,scaleY);  // Get minimum scale factor

    // Move paper origin to page printing area corner
    g2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
    g2D.scale(scale,scale);                  // Apply the scale factor

    printAll(g2D);                           // Draw the component
    return PAGE_EXISTS;
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

    // Prompt for save operation when necessary
    public void checkForSave() {
      if(sketchChanged && JOptionPane.YES_OPTION ==
              JOptionPane.showConfirmDialog(SketchFrame.this,
                               "Current file has changed. Save current file?",
                               "Confirm Save Current File",
                               JOptionPane.YES_NO_OPTION,
                               JOptionPane.WARNING_MESSAGE)) {
        saveOperation();
      }
    }

  // Method for opening file
  public void openSketch(File inFile) {
    try {
      ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
                                       new FileInputStream(inFile)));
      theApp.insertModel((SketchModel)in.readObject());
      in.close();
      modelFile = inFile;
      filename = modelFile.getName();            // Update the file name
      setTitle(frameTitle+modelFile.getPath());  // Change the window title
      sketchChanged = false;                     // Status is unchanged
    } catch(Exception e) {
      System.out.println(e);
      JOptionPane.showMessageDialog(SketchFrame.this,
                                    "Error reading a sketch file.",
                                    "File Input Error",
                                    JOptionPane.ERROR_MESSAGE);
    }
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

  // Method to return the name of the current sketch
  public String getSketchName() {
    return filename;
  }

  // Method to return a reference to the current PageFormat object
  public PageFormat getPageFormat() {
    return pageFormat;
  }

  // Handle menu action events
  public void actionPerformed(ActionEvent e)  {
    Object source = e.getSource();
    if(source == aboutItem) {
      // Create about dialog with the menu item as parent
      JOptionPane.showMessageDialog(this,                          // Parent
                            "Sketcher Copyright Jon David 2004", // Message
                            "About Sketcher",                      // Title
                            JOptionPane.INFORMATION_MESSAGE);      // Message type
    } else if(e.getSource() == fontItem) {       // Set the dialog window position
      Rectangle bounds = getBounds();
      fontDlg.setLocation(bounds.x + bounds.width/3, bounds.y + bounds.height/3);
      fontDlg.setVisible(true);                  // Show the dialog
    } else if(source == customColorItem) {
      Color color = JColorChooser.showDialog(this, "Select Custom Color",
                                                           elementColor);
      if(color != null) {
        elementColor = color;
        statusBar.setColorPane(color);
      }
    } else if(source == printSetupItem) {
      PageFormat pf = printJob.pageDialog(printAttr);
      if(pf != null) {
        pageFormat = pf;    // update the page format
      }
    } else if(source == printWindowItem) {
      if(printer == null) {
        JOptionPane.showMessageDialog(this,
                                      "No default printer available.",
                                      "Printer Error",
                                      JOptionPane.ERROR_MESSAGE);
        return;
      }
      // The app window is the page source
      printJob.setPrintable(this, pageFormat);
      try {                                  
        printJob.print();                    // ...then print
      } catch(PrinterException pe) {
        System.out.println(pe);
        JOptionPane.showMessageDialog(SketchFrame.this,
                                      "Error printing the application window.",
                                      "Printer Error",
                                      JOptionPane.ERROR_MESSAGE);
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
        checkForSave();

        // Now open a sketch file
        File file = showDialog(
                           "Open Sketch File",        // Dialog window title
                           "Open",                    // Button lable
                           "Read a sketch from file", // Button tooltip text
                           null);                     // No file selected
        if(file != null) {                            // If a file was selected
          openSketch(file);                        // then read it
        }
      } else if(name.equals(newAction.getValue(NAME))) {
        checkForSave();
        theApp.insertModel(new SketchModel());    // Insert new empty sketch
        modelFile = null;                         // No file for it
        filename = DEFAULT_FILENAME;              // Default name
        setTitle(frameTitle + files.getCurrentDirectory() + "\\" + filename);
        sketchChanged = false;                    // Not changed yet
      } if(name.equals(printAction.getValue(NAME))) {
        // Verify there is a default printer 
        if(printer == null) {
            JOptionPane.showMessageDialog(SketchFrame.this, 
                                          "No default printer available.",
                                          "Printer Error",
                                          JOptionPane.ERROR_MESSAGE);
            return;
        }

        // The view is the page source
        printJob.setPageable(theApp.getView());
        boolean printIt = true;
        if(e.getSource() != printButton) {       // If it's not the toolbar button
          printIt = printJob.printDialog();      // ...display the print dialog
//          printIt = printJob.printDialog(printAttr); // ...display the print dialog
        }
        if(printIt) {                            // If printIt is true...             
          try {                                  
            printJob.print();                    // ...then print
//            printJob.print(printAttr);           // ...then print
          } catch(PrinterException pe) {
            System.out.println(pe);
            JOptionPane.showMessageDialog(SketchFrame.this,
                                          "Error printing a sketch.",
                                          "Printer Error",
                                          JOptionPane.ERROR_MESSAGE);
          }
        }
      } else if(name.equals(closeAction.getValue(NAME))) {
        checkForSave();
        System.exit(0);
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
  private JMenuItem printSetupItem;
  private JMenuItem printWindowItem;
  
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
  private JButton printButton;                         // Toolbar button for printing

  private PrinterJob printJob;                         // The current printer job
  private PageFormat pageFormat;                       // The printing page format
  private PrintService printer;                        // The printer to be used
  private HashPrintRequestAttributeSet printAttr = new  HashPrintRequestAttributeSet();
}
