/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.tuple.MutablePair;
import wypozyczalniaAut.main.java.controller.Connect;
import wypozyczalniaAut.main.java.model.Admin;
import wypozyczalniaAut.main.java.model.Akcesorium;
import wypozyczalniaAut.main.java.model.Klient;
import wypozyczalniaAut.main.java.model.Pojazd;
import wypozyczalniaAut.main.java.model.Pracownik;
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
    private Rejestracja rejestracja;
    @ManagedProperty(value ="#{wyszukiwanie}")
    private Wyszukiwanie wyszukiwanie;
    @ManagedProperty(value ="#{login}")
    private Login login;
    @ManagedProperty(value ="#{wyswietlSamochod}")
    private WyswietlSamochod wyswietlSamochod;
    @ManagedProperty(value ="#{uzupelnijDane}")
    private UzupelnijDane uzupelnijDane;
    @ManagedProperty(value ="#{noweZamowienie}")
    private wypozyczalniaAut.main.java.controller.beans.Zamowienie noweZamowienie;

    private Uzytkownik zalogowanyUzytkownik;
    private Klient klient;
    private Admin admin;
    private Pracownik pracownik;
    private boolean zalogowany;
    
    public String mojeKontoPrzcisk(){
        if(zalogowany == true){
            return "mojeKontoRedirect.xhtml";
        }
        return "logowanie.xhtml";
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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }
    
    public boolean getZalogowany(){
        return this.zalogowany;
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
            return "uzupelnijDane.xhtml";
        }
        return null;
    }
    
    public String nowaRejstracja(){
        wyloguj();
        return "rejestracja.xhtml";
    }
    
    public void wyloguj(){
        if(zalogowany == true){
            zalogowany = false;
            zalogowanyUzytkownik = null;
            admin = null;
            pracownik = null;
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
            admin = zalogowanyUzytkownik.getAdmin();
            pracownik = zalogowanyUzytkownik.getPracownik();
            return "index.xhtml";
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
            zalogowany = false;
            zalogowanyUzytkownik = null;
            klient = null;
            return "index.xhtml";
        }
        return "logowanie.xhtml";
    }
    
    public String wyswietlanieSamochodu(int id){
        EntityManager em = Connect.createEntityManager();
        Query q = em.createNamedQuery("Samochod.findById").setParameter("id",id);
        getWyswietlSamochod().setSamochod((Samochod)q.getSingleResult());
        return "wyswietlSamochod.xhtml";
    }
    
    public String zlozZamowienie(){
        if(!zalogowany){
            return "logowanie.xhtml";
        }
        return "zamowienie.xhtml";
    }
    
    public String zmienDane(){
        uzupelnijDane.uzupelnijDane();
        klient = uzupelnijDane.getKlient();
        return "index.xhtml";
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
        return "wystietlZamowienia.xhtml";
    }
    
    public String dodajZamowienie(){
        if(noweZamowienie.isDostepnosc()){
            return "dodajAkcesoria.xhtml";
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
        noweZamowienie.signature();
        //noweZamowienie = new Zamowienie();
        return null;
    }
    
    public String dataFormat(Date data){
        return new SimpleDateFormat("dd-MM-yyyy").format(data);
    }
    
    public String mojeKontoRedirect(){
        if(admin != null){
            return "panelAdmin.xhtml";
        }
        if(pracownik != null){
            return "panelPracownik.xhtml";
        }
        return "mojeKonto";
    }
    
    public String dodajPojazd(List <Samochod> samochodList){
        Samochod s = samochodList.get(0);
        Pojazd p = new Pojazd();
        EntityManager em = Connect.createEntityManager();
        Query q;
        Random r = new Random();
        do{
            p.setId(r.nextInt(10000000));
            q = em.createNamedQuery("Pojazd.findById").setParameter("id", p.getId());
        }while(!q.getResultList().isEmpty());
        p.setIdSamochod(s);
        p.setDostepnosc(true);
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
        return null;
    }
    
    public String uprawnienia(int jakie){
        switch(jakie){
            case 1:
                if(zalogowanyUzytkownik != null){
                    return null;
                }
                break;
            case 2:
                if(zalogowanyUzytkownik != null && (pracownik != null || admin != null)){
                    return null;
                }
                break;
            case 3:
                if(zalogowanyUzytkownik != null && admin != null){
                    return null;
                }
                break;
        }
        return "index.xhtml";
    }
}
