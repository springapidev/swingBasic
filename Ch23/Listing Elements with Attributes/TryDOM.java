import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ErrorHandler;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NodeList;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;

import java.io.File;
import java.io.IOException;

import static org.w3c.dom.Node.*;

public class TryDOM implements ErrorHandler {
  public static void main(String args[]) {
    if(args.length == 0) {
      System.out.println("No file to process."+
                           "Usage is:\njava TryDOM \"filename\"");
                           
      System.exit(1);
    }
    File xmlFile = new File(args[0]);
    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    builderFactory.setNamespaceAware(true);       // Set namespace aware
    builderFactory.setValidating(true);           // and validating parser feaures
    builderFactory.setIgnoringElementContentWhitespace(true); 
    
    DocumentBuilder builder = null;
    try {
      builder = builderFactory.newDocumentBuilder();  // Create the parser
      builder.setErrorHandler(new TryDOM()); //Error handler is instance of TryDOM

    } catch(ParserConfigurationException e) {
      e.printStackTrace();
      System.exit(1);
    }
    Document xmlDoc = null;

    try {
      xmlDoc = builder.parse(xmlFile);

    } catch(SAXException e) {
      e.printStackTrace();

    } catch(IOException e) {
      e.printStackTrace();
    }
    DocumentType doctype = xmlDoc.getDoctype();       // Get the DOCTYPE node
    if(doctype == null) {                             // If it's not null...
      System.out.println("DOCTYPE is null");
    } else {                                          // ...output it
      System.out.println("DOCTYPE node:\n" + doctype.getInternalSubset());
    }

    System.out.println("\nDocument body contents are:");
    listNodes(xmlDoc.getDocumentElement(),"");         // Root element & children
  }
  
  // output a node and all its child nodes
  static void listNodes(Node node, String indent) {
    // List the current node
    String nodeName = node.getNodeName();
    System.out.println(indent+" Node: "+nodeName);
    short type =node.getNodeType();
    System.out.println(indent+" Node Type: " + nodeType(type));
    if(type == TEXT_NODE){
      System.out.println(indent+" Content is: "+((Text)node).getWholeText());
    } else if(node.hasAttributes()) {
      System.out.println(indent+" Element Attributes are:");
      NamedNodeMap attrs = node.getAttributes();      //...get the attributes
      for(int i = 0 ; i<attrs.getLength() ; i++) {
        Attr attribute = (Attr)attrs.item(i);         // Get an attribute
        System.out.println(indent+ " " + attribute.getName()+" = "+attribute.getValue());
      }
    }
    
    // Now list the child nodes
    NodeList list = node.getChildNodes();       // Get the list of child nodes
    if(list.getLength() > 0) {                  // As long as there are some...
      System.out.println(indent+" Child Nodes of "+nodeName+" are:");
      for(int i = 0 ; i<list.getLength() ; i++) {//...list them & their children...
        listNodes(list.item(i),indent+"  ");    // by calling listNodes() for each  
      }
    }         
  }

  // Method to identify the node type
  static String nodeType(short type) {
    switch(type) {
      case ELEMENT_NODE:                return "Element";
      case DOCUMENT_TYPE_NODE:          return "Document type";
      case ENTITY_NODE:                 return "Entity";
      case ENTITY_REFERENCE_NODE:       return "Entity reference";
      case NOTATION_NODE:               return "Notation";
      case TEXT_NODE:                   return "Text";
      case COMMENT_NODE:                return "Comment";
      case CDATA_SECTION_NODE:          return "CDATA Section";
      case ATTRIBUTE_NODE:              return "Attribute";
      case PROCESSING_INSTRUCTION_NODE: return "Attribute";
    }
    return "Unidentified";
  }

  public void fatalError(SAXParseException spe) throws SAXException {
    System.out.println("Fatal error at line "+spe.getLineNumber());
    System.out.println(spe.getMessage());
    throw spe;
  }

  public void warning(SAXParseException spe) {
    System.out.println("Warning at line "+spe.getLineNumber());
    System.out.println(spe.getMessage());
  }

  public void error(SAXParseException spe) {
    System.out.println("Error at line "+spe.getLineNumber());
    System.out.println(spe.getMessage());
  }
}
