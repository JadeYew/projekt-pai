/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import wypozyczalniaAut.main.java.controller.Connect;
import wypozyczalniaAut.main.java.model.Klient;
import wypozyczalniaAut.main.java.model.Pracownik;
import wypozyczalniaAut.main.java.model.Zamowienie;

/**
 *
 * @author Iwo Ryszkowski
 */
@Named(value = "panelPracownik")
@SessionScoped
public class PanelPracownik implements Serializable{
    private Pracownik pracownik;
    private String nazwisko;
    private String imie;
    private wypozyczalniaAut.main.java.model.Zamowienie zamowienie;
    private List<wypozyczalniaAut.main.java.model.Zamowienie> zamowieniaDoPotwierdznia = new ArrayList();
    private List<wypozyczalniaAut.main.java.model.Zamowienie> zamowieniaDoAnulowania = new ArrayList();
    private List<wypozyczalniaAut.main.java.model.Zamowienie> zamowieniaDoZamkniecia = new ArrayList();

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public Zamowienie getZamowienie() {
        if(zamowienie == null){
            zamowienie = new Zamowienie();
        }
        return zamowienie;
    }

    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }

    public List<Zamowienie> getZamowieniaDoPotwierdznia() {
        wczytajZamowienia(1);
        return zamowieniaDoPotwierdznia;
    }

    public void setZamowieniaDoPotwierdznia(List<Zamowienie> zamowieniaDoPotwierdznia) {
        this.zamowieniaDoPotwierdznia = zamowieniaDoPotwierdznia;
    }

    public List<Zamowienie> getZamowieniaDoAnulowania() {
        wczytajZamowienia(2);
        return zamowieniaDoAnulowania;
    }

    public void setZamowieniaDoAnulowania(List<Zamowienie> zamowieniaDoAnulowania) {
        this.zamowieniaDoAnulowania = zamowieniaDoAnulowania;
    }

    public List<Zamowienie> getZamowieniaDoZamkniecia() {
        wczytajZamowienia(3);
        return zamowieniaDoZamkniecia;
    }

    public void setZamowieniaDoZamkniecia(List<Zamowienie> zamowieniaDoZamkniecia) {
        this.zamowieniaDoZamkniecia = zamowieniaDoZamkniecia;
    }

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
    
    public void wczytajZamowienia(int ktore){
        EntityManager em = Connect.createEntityManager();
        Query q = em.createNamedQuery("Zamowienie.findAll");
        List<wypozyczalniaAut.main.java.model.Zamowienie> tmp = q.getResultList();
        List<wypozyczalniaAut.main.java.model.Zamowienie> tmp2 = new ArrayList();
        if(nazwisko != null){
            for(wypozyczalniaAut.main.java.model.Zamowienie z : tmp){
                if(z.getIdKlient().getNazwisko().equals(nazwisko)){
                    tmp2.add(z);
                }
            }
            tmp = tmp2;
        }
        tmp2 = new ArrayList();
        if(imie != null){
            for(wypozyczalniaAut.main.java.model.Zamowienie z : tmp){
                if(z.getIdKlient().getImie().equals(imie)){
                    tmp2.add(z);
                }
            }
            tmp = tmp2;
        }
        switch(ktore){
            case 1:
                zamowieniaDoPotwierdznia = new ArrayList();
                for(Zamowienie z : tmp){
                    if(z.getOplacone() == false && z.getAnulowane() == false){
                        zamowieniaDoPotwierdznia.add(z);
                    }
                }
                break;
            case 2:
                zamowieniaDoAnulowania = new ArrayList();
                for(Zamowienie z : tmp){
                    if(z.getAnulowane() == true && z.getZamkniete()== false){
                        zamowieniaDoAnulowania.add(z);
                    }
                }
                break;
            case 3:
                zamowieniaDoZamkniecia = new ArrayList();
                for(Zamowienie z : tmp){
                    if(z.getOplacone() == true && z.getAnulowane() == false && z.getZamkniete()== false){
                        zamowieniaDoZamkniecia.add(z);
                    }
                }
                break;
        }
    }
    
    public String wyszukajZamowienia(int ktore){
        if(imie == "")
        {
            imie = null;
        }
        if(nazwisko == "")
        {
            nazwisko = null;
        }
        wczytajZamowienia(ktore);
        return null;
    }
    
    public String zatwierdz(int zamowieniaId){
        EntityManager em = Connect.createEntityManager();
        em.getTransaction().begin();
        zamowienie = em.find(wypozyczalniaAut.main.java.model.Zamowienie.class, zamowieniaId);
        zamowienie.setOplacone(Boolean.TRUE);
        em.getTransaction().commit();
        em.close();
        wczytajZamowienia(1);
        return "";
    }
    
    public String zamknij(int zamowieniaId){
        EntityManager em = Connect.createEntityManager();
        em.getTransaction().begin();
        zamowienie = em.find(wypozyczalniaAut.main.java.model.Zamowienie.class, zamowieniaId);
        zamowienie.setZamkniete(Boolean.TRUE);
        em.getTransaction().commit();
        em.close();
        wczytajZamowienia(3);
        return "";
    }
    
    public String anuluj(int zamowieniaId){
        EntityManager em = Connect.createEntityManager();
        em.getTransaction().begin();
        zamowienie = em.find(wypozyczalniaAut.main.java.model.Zamowienie.class, zamowieniaId);
        zamowienie.setZamkniete(Boolean.TRUE);
        em.getTransaction().commit();
        em.close();
        wczytajZamowienia(2);
        return "";
    }
}
