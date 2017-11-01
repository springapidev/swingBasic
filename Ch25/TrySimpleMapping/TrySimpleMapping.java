import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrySimpleMapping {
  public static void main (String[] args) {
    TrySimpleMapping SQLtoJavaExample;
    try {
      SQLtoJavaExample = new TrySimpleMapping();
      SQLtoJavaExample.listAuthors();
    } catch(SQLException sqle) {
      System.err.println(sqle);
    } catch(ClassNotFoundException cnfe) {
      System.err.println(cnfe);
    }
  }

  public TrySimpleMapping() throws SQLException, ClassNotFoundException {
    Class.forName (driverName);
    connection = DriverManager.getConnection(sourceURL, user, password);
  }

  public void listAuthors() throws SQLException {
    Author author = null;

    String query = "SELECT authid, lastname, firstname, address1,"+
                   "address2, city, state_prov, postcode, country,"+
                   "phone, fax, email FROM authors";

    Statement statement = connection.createStatement();
    ResultSet authors = statement.executeQuery(query);

    while(authors.next()) {
      int id            = authors.getInt(1);
      String lastname   = authors.getString(2);
      String firstname  = authors.getString(3);

      String[] address  = { authors.getString(4), authors.getString(5)};
      String city       = authors.getString(6);
      String state      = authors.getString(7);
      String postcode   = authors.getString(8);
      String country    = authors.getString(9);
      String phone      = authors.getString(10);
      String fax        = authors.getString(11);
      String email      = authors.getString(12);

      author = new Author(id, lastname, firstname,
                          address, city, state, postcode,
                          country, phone, fax, email);

      System.out.println("\n" + author);
    }
    authors.close();
    connection.close();
  }

  Connection connection;
  String driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
  String sourceURL = "jdbc:odbc:technical_library";
  String user = "guest";
  String password = "guest";
}
