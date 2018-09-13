/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller.beans;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import wypozyczalniaAut.main.java.model.Pracownik;

/**
 *
 * @author Iwo Ryszkowski
 */
@Named(value = "panelPracownik")
@SessionScoped
public class PanelPracownik implements Serializable{
    Pracownik pracownik;

    public Pracownik getPracownik() {
        if(pracownik == null){
            pracownik = new Pracownik();
        }
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }
    
    public void wczytajPracownik(Pracownik pracownik){
        this.pracownik = pracownik;
    }
}
