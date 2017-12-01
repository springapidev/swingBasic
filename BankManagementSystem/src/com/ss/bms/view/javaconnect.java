
package com.ss.bms.view;
import java.sql.*;
import javax.swing.JOptionPane;
public class javaconnect {
    Connection conn=null;
   public static Connection ConnecrDb(){
       try {
           Class.forName("");
           Connection conn=DriverManager.getConnection(null);
           return conn;
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
       }
   return null;
   }
}
