package com.OMCB.Model;

import com.sun.rowset.JdbcRowSetImpl;

import javax.sql.rowset.JdbcRowSet;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {

    public Connection conn1;
    String ConnectionString = "jdbc:mysql://localhost/myflexml?" + "user=root&password=";

    public DBConnection() throws SQLException {

        try {
            conn1 = DriverManager.getConnection(ConnectionString);

        } catch (SQLException e) {

        }
    }

    public void CloseConnection() throws SQLException {
        if (!conn1.isClosed()) {

            conn1.close();

        }
    }

    public JdbcRowSet SelectSql(String sqlString) throws SQLException {
        ResultSet aResultSet = null;
        JdbcRowSet jrs = null;
        Statement st = null;

        st = conn1.createStatement();
        aResultSet = st.executeQuery(sqlString);

        jrs = new JdbcRowSetImpl(aResultSet);
        return jrs;

    }

    public ResultSet SelectQueryResultSet(String sqlString) throws SQLException {

        ResultSet aResultSet = null;
        JdbcRowSet jrs = null;
        Statement st = null;

        st = conn1.createStatement();
        aResultSet = st.executeQuery(sqlString);

        return aResultSet;
    }

    public void OpenDBConnection() {

        try {
            conn1 = DriverManager.getConnection("jdbc:mysql://localhost/myflexml?" + "user=root&password=");

        } catch (SQLException e) {

        }

    }

}
