/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller.beans;

import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import wypozyczalniaAut.main.java.model.Klient;
import wypozyczalniaAut.main.java.model.Uzytkownik;

/**
 *
 * @author Iwo Ryszkowski
 */
@Named(value = "uzupelnijDane")
@ViewScoped
@ManagedBean
public class uzupelnijDane {
    Uzytkownik uzytkownik;
    Klient klient;
    
    void zmienDane(){
        
    }

    public Uzytkownik getUzytkownik() {
        if(uzytkownik == null){
            uzytkownik = new Uzytkownik();
        }
        return uzytkownik;
    }

    public void setUzytkownik(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public Klient getKlient() {
        if(klient == null){
            klient = new Klient();
        }
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }
    
    
}
