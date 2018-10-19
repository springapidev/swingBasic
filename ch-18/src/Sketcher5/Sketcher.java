// Sketching application
package Sketcher5;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.SwingUtilities;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class Sketcher {
  public static void main(String[] args) {
    theApp = new Sketcher();
    SwingUtilities.invokeLater(
       new Runnable() {                          // Anonymous Runnable class object
                        public void run() {      // Run method executed in thread
                          theApp.creatGUI();     // Call static GUI creator
                        }
                      }       );
  }

  // Method to create the application GUI
  private void creatGUI() {
    window = new SketchFrame("Sketcher");        // Create the app window
    Toolkit theKit = window.getToolkit();        // Get the window toolkit
    Dimension wndSize = theKit.getScreenSize();  // Get screen size

    // Set the position to screen center & size to half screen size
    window.setBounds(wndSize.width/4, wndSize.height/4,        // Position
                      wndSize.width/2, wndSize.height/2);      // Size

    window.addWindowListener(new WindowHandler());// Add window listener
    window.setVisible(true);
  }

  // Handler class for window events
  class WindowHandler extends WindowAdapter {
    // Handler for window closing event
    public void windowClosing(WindowEvent e) {
      window.dispose();                          // Release the window resources
      System.exit(0);                            // End the application
    }
  }

  private  SketchFrame window;                   // The application window
  private static  Sketcher theApp;               // The application object
}
