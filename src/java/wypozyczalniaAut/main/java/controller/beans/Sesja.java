/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import wypozyczalniaAut.main.java.model.Uzytkownik;

/**
 *
 * @author Iwo Ryszkowski
 */
@Named(value = "sesja")
@ManagedBean
@SessionScoped
public class Sesja implements Serializable {
    Rejestracja rejestracja;
    Uzytkownik zalogowanyUzytkownik;
    boolean zalogowany;
    
    public String mojeKontoPrzcisk(){
        if(zalogowany != true){
            return "logowanie";
        }
        return "rejestracja";
    }
    
    public void setRejestracja(Rejestracja rejestracja){
        this.rejestracja = rejestracja;
    }
    
    public Rejestracja getRejestracja(){
        if(this.rejestracja == null){
            this.rejestracja = new Rejestracja();
        }
        return this.rejestracja;
    }
    
    public void setZalogowany(boolean zalogowany){
        this.zalogowany = zalogowany;
    }
    
    public boolean getZalogowany(){
        return this.zalogowany;
    }
    
    public void setZalogowanyUzytkownik(Uzytkownik zalogowanyUzytkownik){
        this.zalogowanyUzytkownik = zalogowanyUzytkownik;
    }
    
    public Uzytkownik getZalogowanyUzytkownik(){
        if(this.zalogowanyUzytkownik == null){
            this.zalogowanyUzytkownik = new Uzytkownik();
        }
        return this.zalogowanyUzytkownik;
    }
    
    public String addUser(){
        rejestracja.addUser();
        return "index";
    }
}
