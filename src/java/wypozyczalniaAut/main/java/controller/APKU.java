/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller;

import wypozyczalniaAut.main.java.model.Admin;

/**
 *
 * @author Kamil
 */
public class APKU extends PKU{
    private Admin admin;

    public Admin getAdmin() {
        if(admin == null){
            admin = new Admin();
        }
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
