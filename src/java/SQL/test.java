/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Iwo Ryszkowski
 */
public class test {
    
    public static Connection connectToDatabase() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connect = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7254974", "sql7254974", "ZHNDz9f85c");
        return connect;
    }
    
    public static ResultSet query(Connection connection, String what, String from, String where) throws SQLException{
        if(where.contains("'1'='1") || where.contains("DROP TABLE")){
            return null;
        }
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT " + what + " FROM " + from + " WHERE " + where + ";");
        return resultSet;
    }
    
    public static ResultSet query(Connection connection, String what, String from) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT " + what + " FROM " + from + ";");
        return resultSet;
    }
    
    public static void main(String[] args) {
        try {
            Connection connect = connectToDatabase();
            ResultSet resultSet = query(connect, "*", "User");
            while (resultSet.next()){
                System.out.println("ID: " + resultSet.getString(1) + " Login:"+ resultSet.getString(2) + " Password:"+ resultSet.getString(3));
            }
        } catch (Exception ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
