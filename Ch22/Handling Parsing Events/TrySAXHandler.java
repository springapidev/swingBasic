import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;

public class TrySAXHandler {
  public static void main(String args[]) {
    if(args.length == 0) {
      System.out.println("No file to process. Usage is:"
                                                 +"\njava TrySax \"filename\" ");
      return;
    }
    File xmlFile = new File(args[0]);
    process(xmlFile);
  }

  private static void process(File file) {
    SAXParserFactory spf = SAXParserFactory.newInstance();
    SAXParser parser = null;
    spf.setNamespaceAware(true);
    spf.setValidating(true);
    System.out.println("Parser will "+(spf.isNamespaceAware()?"":"not ")
                                                      + "be namespace aware");
    System.out.println("Parser will "+(spf.isValidating()?"":"not ") 
                                                            + "validate XML");
    try {
     parser = spf.newSAXParser();
     System.out.println("Parser object is: "+ parser);
    }
    catch(SAXException e) {
      e.printStackTrace(System.err);
      System.exit(1);    
    } 
    catch(ParserConfigurationException e) {
      e.printStackTrace(System.err);
      System.exit(1);    
    }

    System.out.println("\nStarting parsing of "+file+"\n");
    MySAXHandler handler = new MySAXHandler(); 
    try {
       parser.parse(file, handler);
    }  catch(IOException e) {
      e.printStackTrace(System.err);
    }
    catch(SAXException e) {
      e.printStackTrace(System.err);
    }
  }
}
