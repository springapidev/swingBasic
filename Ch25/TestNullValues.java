import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class TestNullValues {
  public static void main(String[] args) {
    String url = "jdbc:odbc:technical_library";
    String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
    String theStatement =
     "SELECT authid, lastname, firstname, email FROM authors ORDER BY authid";

    try {
      Class.forName(driver);
      Connection connection = DriverManager.getConnection(url, "guest", "guest");
      Statement queryAuthors = connection.createStatement();
      ResultSet results = queryAuthors.executeQuery(theStatement);

      String lastname, firstname, email;
      int id;
      while(results.next()) {
        id = results.getInt(1);
        lastname = results.getString(2);
        firstname = results.getString(3);
        email = results.getString(4);

        if(results.wasNull()) {
          email = "no email";
        }
        System.out.println(Integer.toString(id) + ", " +
                           lastname.trim() + ", " +
                           firstname.trim() +", " +
                           email.trim());
      }
      queryAuthors.close();
    } catch (Exception e) {
      System.err.println(e);
    }
  }
}
