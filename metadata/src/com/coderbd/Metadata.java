package com.coderbd;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Metadata {

    static Connection connection = CustomDBConnection.getDBConnection();
    static DatabaseMetaData metadata = null;

    // Static block for initialization
    static {
        try {
            metadata = connection.getMetaData();
        } catch (SQLException e) {
            System.err.println("There was an error getting the metadata: "
                    + e.getMessage());
        }
    }

    public static void printGeneralMetadata() throws SQLException {
        System.out.println("Database Name: "
                + metadata.getDatabaseProductName());
        System.out.println("Database Version: "
                + metadata.getDatabaseProductVersion());
        System.out.println("Logged User: " + metadata.getUserName());
        System.out.println("JDBC Driver: " + metadata.getDriverName());
        System.out.println("Driver Version: " + metadata.getDriverVersion());
        System.out.println("\n");
    }

    public static ArrayList getTablesMetadata() throws SQLException {
        String table[] = {"TABLE"};
        ResultSet rs = null;
        ArrayList tables = null;
        // receive the Type of the object in a String array.
        rs = metadata.getTables(null, null, null, table);
        tables = new ArrayList();
        while (rs.next()) {
            tables.add(rs.getString("TABLE_NAME"));
        }
        return tables;
    }

    public static void getColumnsMetadata(ArrayList tables)
            throws SQLException {
        ResultSet rs = null;
        // Print the columns properties of the actual table
        for (Object actualTable : tables) {
            rs = metadata.getColumns(null, null, actualTable.toString(), null);
            System.out.println(actualTable.toString().toUpperCase());
            while (rs.next()) {
                System.out.println(rs.getString("COLUMN_NAME") + " "
                        + rs.getString("TYPE_NAME") + " "
                        + rs.getString("COLUMN_SIZE"));
            }
            System.out.println("\n");
        }

    }

    public static void main(String[] args) {
        try {

            for (Object obj : getTablesMetadata()) {
                System.out.println("Table Name::: " + obj.toString());
            }

            printGeneralMetadata();
            // Print all the tables of the database scheme, with their names and
            // structure
            getColumnsMetadata(getTablesMetadata());
        } catch (SQLException e) {
            System.err
                    .println("There was an error retrieving the metadata properties: " + e.getMessage());
        }
    }
}
