/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.commons.lang3.tuple.MutablePair;
import wypozyczalniaAut.main.java.controller.Connect;
import wypozyczalniaAut.main.java.model.Akcesorium;
import wypozyczalniaAut.main.java.model.Klient;
import wypozyczalniaAut.main.java.model.Samochod;
import wypozyczalniaAut.main.java.model.Uzytkownik;

/**
 *
 * @author Iwo Ryszkowski
 */
@Named(value = "sesja")
@ManagedBean
@SessionScoped
public class Sesja implements Serializable {
    @ManagedProperty(value ="#{rejestracja}")
    Rejestracja rejestracja;
    @ManagedProperty(value ="#{wyszukiwanie}")
    Wyszukiwanie wyszukiwanie;
    @ManagedProperty(value ="#{login}")
    Login login;
    @ManagedProperty(value ="#{wyswietlSamochod}")
    WyswietlSamochod wyswietlSamochod;
    @ManagedProperty(value ="#{uzupelnijDane}")
    UzupelnijDane uzupelnijDane;
    @ManagedProperty(value ="#{noweZamowienie}")
    wypozyczalniaAut.main.java.controller.beans.Zamowienie noweZamowienie;

    Uzytkownik zalogowanyUzytkownik;
    Klient klient;
    boolean zalogowany;
    int pageId = 0;
    
    public String mojeKontoPrzcisk(){
        if(zalogowany == true){
            return "mojeKonto";
        }
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
    
    public Zamowienie getNoweZamowienie() {
        if(this.noweZamowienie == null){
            this.noweZamowienie = new Zamowienie();
        }
        return noweZamowienie;
    }

    public void setNoweZamowienie(Zamowienie noweZamowienie) {
        this.noweZamowienie = noweZamowienie;
    }
    
    public WyswietlSamochod getWyswietlSamochod() {
        if(this.wyswietlSamochod == null){
            this.wyswietlSamochod = new WyswietlSamochod();
        }
        return wyswietlSamochod;
    }

    public void setWyswietlSamochod(WyswietlSamochod wyswietlSamochod) {
        this.wyswietlSamochod = wyswietlSamochod;
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
        zalogowany = rejestracja.addUser();
        if(zalogowany){
            zalogowanyUzytkownik = rejestracja.getUzytkownik();
            return "uzupelnijDane";
        }
        return null;
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
        zalogowany = login.zaloguj();
        if(zalogowany){
            zalogowany = true;
            zalogowanyUzytkownik =login.getUzytkownik();
            if(!zalogowanyUzytkownik.getKlientCollection().isEmpty()){
                klient = (Klient)(zalogowanyUzytkownik.getKlientCollection().toArray())[0];
            }
            return "index";
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
        pageId = 0;
        if(zalogowany){
            zalogowany = false;
            zalogowanyUzytkownik = null;
            klient = null;
            return "index";
        }
        return "logowanie";
    }
    
    public String wyswietlanieSamochodu(int id){
        EntityManager em = Connect.createEntityManager();
        Query q = em.createNamedQuery("Samochod.findById").setParameter("id",id);
        getWyswietlSamochod().samochod = (Samochod)q.getSingleResult();
        return "wyswietlSamochod";
    }
    
    public String zlozZamowienie(){
        if(!zalogowany){
            pageId = 2;
            return "logowanie";
        }
        return "zamowienie";
    }
    
    public String zmienDane(){
        uzupelnijDane.uzupelnijDane();
        klient = uzupelnijDane.getKlient();
        return "index";
    }

    public UzupelnijDane getUzupelnijDane() {
        if(this.uzupelnijDane == null){
            this.uzupelnijDane = new UzupelnijDane();
        }
        return uzupelnijDane;
    }

    public void setUzupelnijDane(UzupelnijDane uzupelnijDane) {
        this.uzupelnijDane = uzupelnijDane;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }
    
    public void wczytajKlienta(){
        getUzupelnijDane().wczytajKlienta(zalogowanyUzytkownik);
    }
    
    public String wystietlZamowienia(){
        return "index";
    }
    
    public String dodajZamowienie(){
        if(noweZamowienie.isDostepnosc()){
            return "dodajAkcesoria";
        }
        return null;
    }
    
    public String dodajAkcesoria(List<MutablePair<Akcesorium, Boolean>>akcesoria){
        getNoweZamowienie().getAkcesoria();
        for(int i = 0; i < akcesoria.size(); i++){
            if(akcesoria.get(i).right){
                noweZamowienie.dodajAkcesorium(akcesoria.get(i).left);
            }
        }
        return "wyswietlZamowienie.xhtml";
    }
    
    public String anulujPotwierdzZamowienie(boolean decyzja){
        if(decyzja){
            noweZamowienie.zapisz(klient);
        }
        noweZamowienie = new Zamowienie();
        return "index.xhtml";
    }
}
