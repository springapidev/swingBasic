import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TryEncapsulatedMapping {
  public static void main (String[] args) {
    TryEncapsulatedMapping SQLtoJavaExample;
    try {
      SQLtoJavaExample = new TryEncapsulatedMapping();
      SQLtoJavaExample.listAuthors();
    } catch(SQLException sqle) {
      System.err.println(sqle);
    } catch(ClassNotFoundException cnfe) {
      System.err.println(cnfe);
    }
  }

  public TryEncapsulatedMapping() throws SQLException, 
                                         ClassNotFoundException {
    Class.forName (driverName);
    connection = DriverManager.getConnection(sourceURL, user, password);
  }

  public void listAuthors() throws SQLException {
    Author author;
    String query = "SELECT authid, lastname, firstname, address1,"+
                   "address2, city, state_prov, postcode, country,"+
                   "phone, fax, email FROM authors";

    Statement statement = connection.createStatement();
    ResultSet authors = statement.executeQuery(query);

    while(authors.next())
      System.out.println("\n" + Author.fromResults(authors));
    authors.close();
    connection.close();
  }

  Connection connection;
  String driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
  String sourceURL = "jdbc:odbc:technical_library";
  String user = "guest";
  String password = "guest";
}
