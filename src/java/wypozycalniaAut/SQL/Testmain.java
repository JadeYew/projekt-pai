/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozycalniaAut.SQL;

import java.sql.SQLException;
import static wypozycalniaAut.SQL.UzytkownicySQL.select;

/**
 *
 * @author Iwo Ryszkowski
 */
public class Testmain {

    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        for(int i = 1; i <= 10; i++){
            UzytkownicySQL.hashPassword(Integer.toString(i));
        }
        
    }
    
}
