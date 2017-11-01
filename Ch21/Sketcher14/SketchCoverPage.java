import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.print.Printable;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;

class SketchCoverPage implements Printable {
  public SketchCoverPage(Sketcher theApp) {
    this.theApp = theApp;
  }

  // Print the cover page
  public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
    if(pageIndex>0) {
      return NO_SUCH_PAGE;
    }
    Graphics2D g2D = (Graphics2D) g;
    float x = (float)pageFormat.getImageableX();
    float y = (float)pageFormat.getImageableY();

    GeneralPath path = new GeneralPath();
    path.moveTo(x+1, y+1);
    path.lineTo(x+(float)pageFormat.getImageableWidth()-1, y+1);
    path.lineTo(x+(float)pageFormat.getImageableWidth()-1,
                y+(float)pageFormat.getImageableHeight()-1);
    path.lineTo(x+1, y+(float)pageFormat.getImageableHeight()-1);
    path.closePath();

    g2D.setPaint(Color.red);
    g2D.draw(path);

    // Get a 12 pt bold version of the default font
    Font font = g2D.getFont().deriveFont(12.f).deriveFont(Font.BOLD);

    g2D.setFont(font);                    // Set the new font
    String sketchName = theApp.getWindow().getSketchName();
    Rectangle2D textRect = new java.awt.font.TextLayout(sketchName, font,
                            g2D.getFontRenderContext()).getBounds();
    double centerX = pageFormat.getWidth()/2;
    double centerY = pageFormat.getHeight()/2;
    Rectangle2D.Double surround = new Rectangle2D.Double(
                                             centerX-textRect.getWidth(),
                                             centerY-textRect.getHeight(),
                                             2*textRect.getWidth(),
                                             2*textRect.getHeight());
    g2D.draw(surround);

    // Draw text in the middle of the printable area
    g2D.setPaint(Color.blue);
    g2D.drawString(sketchName, (float)(centerX-textRect.getWidth()/2),
                               (float)(centerY+textRect.getHeight()/2));
    return PAGE_EXISTS;
  }

  private Sketcher theApp;
}
