/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller;

import wypozyczalniaAut.main.java.model.*;

/**
 *
 * @author Kamil
 */
public class PKU {
    Pracownik pracownik;
    Klient klient;
    Uzytkownik uzytkownik;

    public Pracownik getPracownik() {
        if(pracownik == null){
            pracownik = new Pracownik();
        }
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
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

    public Uzytkownik getUzytkownik() {
        if(uzytkownik == null){
            uzytkownik = new Uzytkownik();
        }
        return uzytkownik;
    }

    public void setUzytkownik(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }
    
}
