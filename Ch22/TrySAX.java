import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
public class TrySAX {
  public static void main(String args[]) {
    // Create factory object
    SAXParserFactory spf = SAXParserFactory.newInstance(); 
    spf.setNamespaceAware(true);
    spf.setValidating(true);
    System.out.println("Parser will "+(spf.isNamespaceAware()?"":"not ") + 
                       "be namespace aware");
    System.out.println("Parser will "+(spf.isValidating()?"":"not ") +
                       "validate XML");

    SAXParser parser = null;                          // Stores parser reference
    try {
     parser = spf.newSAXParser();                     // Create parser object
    }catch(ParserConfigurationException e){// Thrown if a parser cannot be created
                                           // that is consistent with the 
      e.printStackTrace(System.err);      // configuration in spf
      System.exit(1);    
    } catch(SAXException e) {             // Thrown for any other error
      e.printStackTrace(System.err);
      System.exit(1);    
    } 

    System.out.println("Parser object is: "+ parser);
  }
}
