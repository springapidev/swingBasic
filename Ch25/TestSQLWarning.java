import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;

public class TestSQLWarning {
  public static void main(String[] args) {
    TestSQLWarning theApp = new TestSQLWarning();
    try {
      theApp.doQuery();               // Call the method that deals with the DB
    } catch(SQLException sqle) {      // Catch the exception thrown by the method
      do {                            // loop through each exception
        // do something with each exception
        System.err.println("Exception occurred:\nMessage: " + sqle.getMessage());
        System.err.println("SQL state: " + sqle.getSQLState());
        System.err.println("Vendor code: " + sqle.getErrorCode() +
                           "\n----------------");
      } while((sqle = sqle.getNextException()) != null);
    }
  }

  public void doQuery() throws SQLException {
    String url = "jdbc:odbc:technical_library";
    String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
    String user = "guest";
    String password = "guest";
    String theStatement =
             "SELECT title, price FROM books WHERE price <> NULL";
    try {
      Class.forName(driver);
      Connection connection = DriverManager.getConnection(url, user, password);
      Statement queryBooks = connection.createStatement();
      ResultSet results = queryBooks.executeQuery(theStatement);
      int price;
      String title;
      while(results.next()) {
        title = results.getString("title");
        checkForWarning(results.getWarnings());

        price = results.getInt("price");
        checkForWarning(results.getWarnings());

        System.out.println(title + " " + price);
      }
      queryBooks.close();
    } catch (ClassNotFoundException cnfe) {
      System.out.println(cnfe);
    } catch (SQLException sqle) {
      SQLException generatedException =
              new SQLException("SQL operation canceled","S1008", 0);
      SQLException lastException = sqle;
      while(lastException.getNextException() != null)
        lastException = lastException.getNextException();
      lastException.setNextException(generatedException);
      throw sqle;
    }
  }

  boolean checkForWarning(SQLWarning w) {
    if(w == null) {
      return false;
    }
    do {
      System.err.println("Warning:\nMessage: " + w.getMessage());
        System.err.println("SQL state: " + w.getSQLState());
        System.err.println("Vendor code: " + w.getErrorCode() +
                           "\n----------------");
    } while((w = w.getNextWarning())!=null);
    return true;
  }
}
