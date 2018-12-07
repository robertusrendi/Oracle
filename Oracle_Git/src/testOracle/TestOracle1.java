/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testOracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class TestOracle1 {

    public static void main(String[] args) {
        try {
            //step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //step2 create  the connection object
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@172.23.9.185:1521:orcl", "puspa", "puspa");
            //step3 create the statement object
            Statement stmt = con.createStatement();
            //step4 execute query
            ResultSet rs = stmt.executeQuery("select * from departments" );
            while (rs.next()) {
                System.out.println( rs.getString(1) + "  " + rs.getString(2));
            }
            //step5 close the connection object
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestOracle1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
