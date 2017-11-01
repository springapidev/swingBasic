import java.sql.DriverManager;                  
import java.sql.Connection;                  
import java.sql.PreparedStatement;                  
import java.sql.SQLException;

public class TryPlaceHolders {
  public static void main(String[] args) {
    try {
      String url = "jdbc:odbc:technical_library";
      String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
      String user = "guest";
      String password = "guest";
      Class.forName(driver);
      Connection connection = DriverManager.getConnection(url);
      String changeLastName = "UPDATE authors SET lastname = ? WHERE authid = ?";
      PreparedStatement updateLastName = 
                                       connection.prepareStatement(changeLastName);

      updateLastName.setString(1," Martin");   // Set lastname placeholder value
      updateLastName.setInt(2,4);             // Set author ID placeholder value

      int rowsUpdated = updateLastName.executeUpdate();      // execute the update
      System.out.println("Rows affected: " + rowsUpdated);
      connection.close();
    } catch (ClassNotFoundException cnfe) {
      System.err.println(cnfe);
    } catch (SQLException sqle) {
      System.err.println(sqle);
    }
  }
}
