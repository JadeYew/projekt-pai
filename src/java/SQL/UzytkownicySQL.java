/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Iwo Ryszkowski
 */
public class UzytkownicySQL {
    public static void insert(String [] values){
        insert(Integer.getInteger(values[0]), values[1], values[2], values[3]);
    }
    
    public static void insert(int id, String login, String password, String e_mail){
        String [] values = {Integer.toString(id), new String("\"" + login  + "\""),
                new String("\"" + password  + "\""), new String("\"" + e_mail  + "\"")};
        String [] colums = {"id", "login", "password", "e_mail"};
        test.insert("uzytkownik", colums, values);
    }
    
    public static ResultSet select(String what, String where){
        try {
            return test.query(test.connectToDatabase(), what, "uzytkownik", where);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static ResultSet select(String what){
        try {
            return test.query(test.connectToDatabase(), what, "uzytkownik");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
