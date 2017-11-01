import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;

import static javax.xml.XMLConstants.*;

public class TryParsingSchemaInstance {
  public static void main(String args[]) {
    if(args.length == 0) {
      System.out.println("No file to process. Usage is:"
               +"\n  java TryParsingSchemaInstance \"xmlFilename\" "
               +"\nor:\n  java TryParsingSchemaInstance \"xmlFilename\" \"schemaFileName\" ");
      return;
    }
    File xmlFile = new File(args[0]);
    File schemaFile = args.length>1 ? new File(args[1]) : null;
    process(xmlFile, schemaFile);
  }

  private static void process(File file, File schemaFile) {
    SAXParserFactory spf = SAXParserFactory.newInstance();
    SAXParser parser = null;
    spf.setNamespaceAware(true);
    try {
     SchemaFactory sf =
                     SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI); 
     spf.setSchema(schemaFile == null ? sf.newSchema() : sf.newSchema(schemaFile));

     parser = spf.newSAXParser();
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
