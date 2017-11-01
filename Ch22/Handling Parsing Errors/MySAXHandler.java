import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;

public class MySAXHandler extends DefaultHandler {
  public void startDocument() {
  	System.out.println("Start document: ");
  }    
    public void endDocument()  {
  	System.out.println("End document: ");
  }
  
  public void startElement(String uri, String localName, String qname, 
                                                               Attributes attr)
  {
    System.out.println("Start element: local name: " + localName + " qname: " 
                                                        + qname + " uri: "+uri);
    int attrCount = attr.getLength();
    if(attrCount>0) {
      System.out.println("Attributes:"); 
      for(int i = 0 ; i<attrCount ; i++) {
        System.out.println("  Name : " + attr.getQName(i)); 
        System.out.println("  Type : " + attr.getType(i)); 
        System.out.println("  Value: " + attr.getValue(i)); 
      }
    } 
  }
  
  public void endElement(String uri, String localName, String qname) {
    System.out.println("End element: local name: " + localName + " qname: "
                                                         + qname + " uri: "+uri);
  }
  
  public void characters(char[] ch, int start, int length) {
    System.out.println("Characters: " + new String(ch, start, length));
  }

  public void ignorableWhitespace(char[] ch, int start, int length) {
    System.out.println("Ignorable whitespace: " + new String(ch, start, length));
  }

  public void startPrefixMapping(String prefix, String uri) {
    System.out.println("Start \"" + prefix + "\" namespace scope. URI: " + uri); 
  }

  public void endPrefixMapping(String prefix) {
    System.out.println("End \"" + prefix + "\" namespace scope."); 
  }

  public void warning(SAXParseException spe) {
    System.out.println("Warning at line "+spe.getLineNumber());
    System.out.println(spe.getMessage());
  }

  public void fatalError(SAXParseException spe) throws SAXException {
    System.out.println("Fatal error at line "+spe.getLineNumber());
    System.out.println(spe.getMessage());
    throw spe;
  }
}
