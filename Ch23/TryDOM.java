import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class TryDOM {
  public static void main(String args[]) {
     DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
     DocumentBuilder builder = null;
     try {
       builder = builderFactory.newDocumentBuilder();
     }
     catch(ParserConfigurationException e) {
       e.printStackTrace();
       System.exit(1);
     }
     System.out.println("Builder Factory = " + builderFactory +"\nBuilder = "
                                                                      + builder);
  }
}
