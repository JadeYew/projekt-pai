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
    Pracownik pracownik;
    Klient klient;
    wypozyczalniaAut.main.java.model.Zamowienie zamowienie;
    List<wypozyczalniaAut.main.java.model.Zamowienie> zamowieniaDoPotwierdznia = new ArrayList();
    List<wypozyczalniaAut.main.java.model.Zamowienie> zamowieniaDoAnulowania = new ArrayList();
    List<wypozyczalniaAut.main.java.model.Zamowienie> zamowieniaDoZamkniecia = new ArrayList();

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(Zamowienie zamowienie) {
        if(zamowienie == null){
            zamowienie = new Zamowienie();
        }
        this.zamowienie = zamowienie;
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

    public List<Zamowienie> getZamowieniaDoPotwierdznia() {
        if(zamowieniaDoPotwierdznia == null){
            zamowieniaDoPotwierdznia = new ArrayList();
        }
        return zamowieniaDoPotwierdznia;
    }

    public void setZamowieniaDoPotwierdznia(List<Zamowienie> zamowieniaDoPotwierdznia) {
        this.zamowieniaDoPotwierdznia = zamowieniaDoPotwierdznia;
    }

    public List<Zamowienie> getZamowieniaDoAnulowania() {
        if(zamowieniaDoAnulowania == null){
            zamowieniaDoAnulowania = new ArrayList();
        }
        return zamowieniaDoAnulowania;
    }

    public void setZamowieniaDoAnulowania(List<Zamowienie> zamowieniaDoAnulowania) {
        this.zamowieniaDoAnulowania = zamowieniaDoAnulowania;
    }

    public List<Zamowienie> getZamowieniaDoZamkniecia() {
        if(zamowieniaDoZamkniecia == null){
            zamowieniaDoZamkniecia = new ArrayList();
        }
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
        if(klient != null){
            List<wypozyczalniaAut.main.java.model.Zamowienie> poKlientach = new ArrayList();
            if(klient.getNazwisko() != null){
                for(Zamowienie z : tmp){
                    if(z.getIdKlient().getNazwisko().equals(klient.getNazwisko())){
                        poKlientach.add(z);
                    }
                }
                tmp = poKlientach;
                poKlientach = new ArrayList();
            }
            if(klient.getImie() != null){
                for(Zamowienie z : tmp){
                    if(z.getIdKlient().getImie().equals(klient.getImie())){
                        poKlientach.add(z);
                    }
                }
                tmp = poKlientach;
                poKlientach = new ArrayList();
            }
        }
        if(zamowienie != null){
            List<wypozyczalniaAut.main.java.model.Zamowienie> poNumerach = new ArrayList();
            if(zamowienie.getId() != null){
                for(Zamowienie z : tmp){
                    if(z.getId().equals(zamowienie.getId())){
                        poNumerach.add(z);
                    }
                }
                tmp = poNumerach;
            }
        }
        switch(ktore){
            case 1:
                for(Zamowienie z : tmp){
                    if(z.getOplacone() == false && z.getAnulowane() == false){
                        zamowieniaDoPotwierdznia.add(z);
                    }
                }
                break;
            case 2:
                for(Zamowienie z : tmp){
                    if(z.getAnulowane() == true){
                        zamowieniaDoAnulowania.add(z);
                    }
                }
                break;
            case 3:
                for(Zamowienie z : tmp){
                    if(z.getOplacone() == true && z.getAnulowane() == false){
                        zamowieniaDoZamkniecia.add(z);
                    }
                }
                break;
        }
    }
}
