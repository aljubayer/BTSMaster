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

/**
 * Created by atchowdhury on 11/1/2015.
 */
public class DBConnection {
    public Connection conn1;
    String ConnectionString="jdbc:mysql://localhost/myflexml?" + "user=root&password=";

    public DBConnection() throws SQLException {

        try {
            conn1 = DriverManager.getConnection(ConnectionString);
            //boolean statusConn=
            // conn1.close();
        } catch (SQLException e) {

//} finally {
//    conn1.close();
//}
        }
    }

    public void CloseConnection() throws SQLException {
        if(!conn1.isClosed()){

            conn1.close();

        }
    }

    //public ResultSet SelectSql(String sqlString) throws SQLException {

    public JdbcRowSet SelectSql(String sqlString) throws SQLException {
        ResultSet aResultSet=null;
        JdbcRowSet jrs=null;
        Statement st=null;
     //   try {
            st = conn1.createStatement();
             aResultSet = st.executeQuery(sqlString);

            jrs = new JdbcRowSetImpl(aResultSet);





            return jrs;
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } finally {
//
//            try {
//                if (aResultSet != null) {
//                    aResultSet.close();
//                }
//                if (st != null) {
//                    st.close();
//                }
//                if (conn1 != null) {
//                //    conn1.close();
//                }
//            } catch (SQLException ex) {
//               System.out.println(ex.getMessage());
//            }





        //}

      //  return jrs;
    }


    public ResultSet  SelectQueryResultSet(String sqlString) throws SQLException{


        ResultSet aResultSet=null;
        JdbcRowSet jrs=null;
        Statement st=null;
     //   try {
            st = conn1.createStatement();
             aResultSet = st.executeQuery(sqlString);







            return aResultSet;
    }

    public void OpenDBConnection(){

          try {
            conn1 = DriverManager.getConnection("jdbc:mysql://localhost/myflexml?" + "user=root&password=");
            //boolean statusConn=
            // conn1.close();
        } catch (SQLException e) {

//} finally {
//    conn1.close();
//}
        }

    }


}


   //     conn1.close();



       // return aResultSet;









