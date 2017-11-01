import java.sql.DriverManager;                  
import java.sql.Connection;                  
import java.sql.Statement;                  
import java.sql.SQLException;

public class BuildTables {
  public static void main(String[] args) {
    try {
      String username = "guest";
      String password = "guest";
      String url = "jdbc:odbc:technical_library";
      String driver = "sun.jdbc.odbc.JdbcOdbcDriver";

      String[] SQLStatements = {
      "CREATE TABLE online_resources (pub_id int, name char(48), url char(80))",
      "INSERT INTO online_resources VALUES(1, 'Wrox Home Page'," +
                                                       " 'http://www.wrox.com')",
      "INSERT INTO online_resources VALUES(2, 'JavaSoft Home Page'," +
                                                   " 'http://www.javasoft.com')",
      "INSERT INTO online_resources VALUES(3, 'Apress Home Page'," +
                                                     " 'http://www.apress.com')",
      "INSERT INTO online_resources VALUES(4, 'Addison Wesley Home Page'," +
                                             " 'http://www.awprofessional.com')",
      "INSERT INTO online_resources VALUES(5, 'Java Developer Connection'," +
                                                       " 'http://java.sun.com')"
      };
      Class.forName(driver);
      Connection connection = DriverManager.getConnection(url, username, password);
      Statement statement = connection.createStatement();

      for (String SQLStatement : SQLStatements) {
        statement.executeUpdate(SQLStatement);
        System.out.println(SQLStatement);
      }
    } catch (ClassNotFoundException cnfe) {
      System.err.println(cnfe);
    } catch (SQLException sqle) {
      System.err.println(sqle);
    }
  }
}
