/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import java.sql.*;
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
    
    public static void insert(String tableName, String [] columns, String values[]){
        try {
            Connection conn = connectToDatabase();
            String query = "insert into " + tableName + " (";
            for(String s : columns){
                query += s + ", ";
            }
            query = query.substring(0, query.length() - 2);
            query += ") values (";
            for(int i = 0; i < columns.length; i++){
                query += "?, ";
            }
            query = query.substring(0, query.length() - 2);
            query += ")";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            for(int i = 0; i < values.length; i++){
                preparedStmt.setString(i + 1, values[i]);
            }
            preparedStmt.execute();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
       
    }
}
