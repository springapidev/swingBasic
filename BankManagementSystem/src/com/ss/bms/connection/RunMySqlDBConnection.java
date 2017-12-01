package com.ss.bms.connection;

import java.sql.Connection;

/**
 *
 * @author Rajail Islam
 */
public class RunMySqlDBConnection {

    public static Connection connMysql = MySqlDbConnection.getConnection("root", "1234");


}
