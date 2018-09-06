/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller.beans;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
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
    Wyszukiwanie wyszukiwanie;
    Login login;
    boolean zalogowany = false;
    int pageId = 0;
    
    public String mojeKontoPrzcisk(){
        if(zalogowany == true){
            return "index";
        }
        pageId = 1;
        return "logowanie";
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
    
    public void setLogin(Login login){
        this.login = login;
    }
    
    public Login getLogin(){
        if(this.login == null){
            this.login = new Login();
        }
        return this.login;
    }
    
    public void setZalogowany(boolean zalogowany){
        this.zalogowany = zalogowany;
    }
    
    public boolean getZalogowany(){
        return this.zalogowany;
    }
    
    public void setPageId(int pageId){
        this.pageId = pageId;
    }
    
    public int getPageId(){
        return this.pageId;
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
    
    public void setWyszukiwanie(Wyszukiwanie wyszukiwanie){
        this.wyszukiwanie = wyszukiwanie;
    }
    
    public Wyszukiwanie getWyszukiwanie(){
        if(this.wyszukiwanie == null){
            this.wyszukiwanie = new Wyszukiwanie();
        }
        return this.wyszukiwanie;
    }
    
    public String addUser(){
        String ret = rejestracja.addUser();
        if(ret != null){
            zalogowanyUzytkownik = rejestracja.getUzytkownik();
            rejestracja.setUzytkownik(null);
            zalogowany = true;
        }
        return ret;
    }
    
    public String nowaRejstracja(){
        wyloguj();
        return "rejestracja";
    }
    
    public void wyloguj(){
        if(zalogowany == true){
            zalogowany = false;
            zalogowanyUzytkownik = null;
        }
    }
    
    public String zaloguj(){
        zalogowany = getLogin().zaloguj();
        if(zalogowany){
            zalogowanyUzytkownik = getLogin().getUzytkownik();
            login.setUzytkownik(null);
            switch(pageId){
                case 1:
                    return "mojeKonto";
                default:
                    return "index";
            }
        }
        return null;
    }
    
    public String podajLoginUzytkownika(){
        if(zalogowany){
            return zalogowanyUzytkownik.getLogin();
        }
        return "gościu";
    }
    
    public String zalogujWylogujWiadomosc(){
        if(zalogowany){
            return "Wyloguj się";
        }
        return "Zaloguj się";
    }
    
    public String zalogujWyloguj(){
        if(zalogowany){
            zalogowanyUzytkownik = null;
            zalogowany = false;
            return "index";
        }
        return "logowanie";
    }
}
