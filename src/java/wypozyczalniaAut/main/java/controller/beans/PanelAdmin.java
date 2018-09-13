/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import wypozyczalniaAut.main.java.BCrypt;
import wypozyczalniaAut.main.java.controller.Connect;
import wypozyczalniaAut.main.java.model.Admin;
import wypozyczalniaAut.main.java.model.Akcesorium;
import wypozyczalniaAut.main.java.model.Klient;
import wypozyczalniaAut.main.java.model.Pracownik;
import wypozyczalniaAut.main.java.model.Samochod;
import wypozyczalniaAut.main.java.model.Uzytkownik;

/**
 *
 * @author Iwo Ryszkowski
 */
@Named(value = "panelAdmin")
@SessionScoped
public class PanelAdmin implements Serializable{
    Admin admin;
    Admin nowyAdmin;
    Pracownik nowyPracownik;
    Klient nowyKlient;
    Uzytkownik nowyUzytkownik;
    Uzytkownik usuwany;
    boolean usun;
    Akcesorium akcesorium;
    Samochod samochod;

    public Samochod getSamochod() {
        if(samochod == null){
            samochod = new Samochod();
        }
        return samochod;
    }

    public void setSamochod(Samochod samochod) {
        this.samochod = samochod;
    }

    public Akcesorium getAkcesorium() {
        if(akcesorium == null){
            akcesorium = new Akcesorium();
        }
        return akcesorium;
    }

    public void setAkcesorium(Akcesorium akcesorium) {
        this.akcesorium = akcesorium;
    }

    public boolean isUsun() {
        return usun;
    }

    public void setUsun(boolean usun) {
        this.usun = usun;
    }

    public Uzytkownik getUsuwany() {
        if(usuwany == null){
            usuwany = new Uzytkownik();
        }
        return usuwany;
    }

    public void setUsuwany(Uzytkownik usuwany) {
        this.usuwany = usuwany;
    }

    public Klient getNowyKlient() {
        if(nowyKlient == null){
            nowyKlient = new Klient();
        }
        return nowyKlient;
    }

    public void setNowyKlient(Klient nowyKlient) {
        this.nowyKlient = nowyKlient;
    }

    public Uzytkownik getNowyUzytkownik() {
        if(nowyUzytkownik == null){
            nowyUzytkownik = new Uzytkownik();
        }
        return nowyUzytkownik;
    }

    public void setnowyUzytkownik(Uzytkownik nowyUzytkownik) {
        this.nowyUzytkownik = nowyUzytkownik;
    }

    public Admin getNowyAdmin() {
        if(nowyAdmin == null){
            nowyAdmin = new Admin();
        }
        return nowyAdmin;
    }

    public void setNowyAdmin(Admin nowyAdmin) {
        this.nowyAdmin = nowyAdmin;
    }

    public Pracownik getNowyPracownik() {
        if(nowyPracownik == null){
            nowyPracownik = new Pracownik();
        }
        return nowyPracownik;
    }

    public void setNowyPracownik(Pracownik nowyPracownik) {
        this.nowyPracownik = nowyPracownik;
    }

    public Admin getAdmin() {
        if(admin == null){
            admin = new Admin();
        }
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    
    public void wczytajAdmin(Admin admin){
        this.admin = admin;
    }
    
    public String dodajPracownika(){
        EntityManager em = Connect.createEntityManager();
        Query q;
        Random r = new Random();
        do{
            nowyUzytkownik.setId(r.nextInt(10000000));
            q = em.createNamedQuery("Uzytkownik.findById").setParameter("id", nowyUzytkownik.getId());
        }while(!q.getResultList().isEmpty());
        nowyUzytkownik.setLogin(nowyKlient.getImie().substring(0, 1) + nowyKlient.getNazwisko());
        nowyUzytkownik.setSalt(BCrypt.gensalt());
        nowyUzytkownik.setPassword(BCrypt.hashpw("pass1234", nowyUzytkownik.getSalt()));
        do{
            nowyKlient.setId(r.nextInt(10000000));
            q = em.createNamedQuery("Klient.findById").setParameter("id", nowyKlient.getId());
        }while(!q.getResultList().isEmpty());
        nowyKlient.setIdUzytkownik(nowyUzytkownik);
        List <Klient> tmp = new ArrayList();
        tmp.add(nowyKlient);
        getNowyPracownik();
        do{
            nowyPracownik.setId(r.nextInt(10000000));
            q = em.createNamedQuery("Pracownik.findById").setParameter("id", nowyPracownik.getId());
        }while(!q.getResultList().isEmpty());
        nowyPracownik.setIdUzytkownik(nowyUzytkownik);
        em.getTransaction().begin();
        em.persist(nowyUzytkownik);
        em.getTransaction().commit();
        em.close();
        em = Connect.createEntityManager();
        em.getTransaction().begin();
        em.persist(nowyKlient);
        em.getTransaction().commit();
        em.close();
        em = Connect.createEntityManager();
        em.getTransaction().begin();
        em.persist(nowyPracownik);
        em.getTransaction().commit();
        em.close();
        return "success.xhtml";
    }
    
    public String dodajAdmin(){
        dodajPracownika();
        EntityManager em = Connect.createEntityManager();
        Query q;
        Random r = new Random();
        do{
            nowyAdmin.setId(r.nextInt(10000000));
            q = em.createNamedQuery("Admin.findById").setParameter("id", nowyAdmin.getId());
        }while(!q.getResultList().isEmpty());
        nowyAdmin.setIdUzytkownik(nowyUzytkownik);
        em.getTransaction().begin();
        em.persist(nowyAdmin);
        em.getTransaction().commit();
        em.close();
        return null;
    }
    
    public void clear(){
        nowyAdmin = null;
        nowyPracownik = null;
        nowyKlient = null;
        nowyUzytkownik = null;
    }
    
    public void sprawdzUsun(){
        EntityManager em = Connect.createEntityManager();
        Query q = em.createNamedQuery("Uzytkownik.findByLogin").setParameter("login", usuwany.getLogin());
        if(q.getResultList().isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_WARN, "Nie ma pracownika o podanym loginie", usuwany.getLogin()));
            usun = false;
            return;
        }
        usun = true;
    }
    
    public String usunPracownika(){
        if(usun){
             EntityManager em = Connect.createEntityManager();
             Query q = em.createNamedQuery("Uzytkownik.findByLogin").setParameter("login", usuwany.getLogin());
             usuwany = (Uzytkownik)q.getSingleResult();
             if(usuwany.getPracownik() != null){
                 em.getTransaction().begin();
                em.remove(usuwany.getPracownik());
                em.getTransaction().commit();
             }
             List <Klient> tmp = (List)usuwany.getKlientCollection();
             if(!tmp.isEmpty()){
                 em.getTransaction().begin();
                 em.remove(tmp.get(0));
                em.getTransaction().commit();
             }
             if(usuwany.getAdmin() != null)
             {
                em.getTransaction().begin();
                em.remove(usuwany.getAdmin());
                em.getTransaction().commit();
             }
             em.getTransaction().begin();
             em.remove(usuwany);
             em.getTransaction().commit();
             em.close();
        }
        return "success.xhtml";
    }
    
    public String dodajAkcesorium(){
        EntityManager em = Connect.createEntityManager();
        Query q;
        Random r = new Random();
        do{
            akcesorium.setId(r.nextInt(10000000));
            q = em.createNamedQuery("Akcesorium.findById").setParameter("id", akcesorium.getId());
        }while(!q.getResultList().isEmpty());
        akcesorium.setIloscDostepna(akcesorium.getIlosc());
        em.getTransaction().begin();
        em.persist(akcesorium);
        em.getTransaction().commit();
        em.close();
        return "success.xhtml";
    }
    
    public String usunAkcesorium(Akcesorium a){
        EntityManager em = Connect.createEntityManager();
        em.getTransaction().begin();
        em.remove(a);
        em.getTransaction().commit();
        em.close();
        return "success.xhtml";
    }
    
    public List<Akcesorium> getAkcesoria(){
         EntityManager em = Connect.createEntityManager();
        Query q = em.createNamedQuery("Akcesorium.findAll");
        return (List<Akcesorium>)q.getResultList();
    }
    
    public List<Samochod> getSamochody(){
         EntityManager em = Connect.createEntityManager();
        Query q = em.createNamedQuery("Samochod.findAll");
        return (List<Samochod>)q.getResultList();
    }
    
    public String usunSamochod(Samochod s){
        EntityManager em = Connect.createEntityManager();
        em.getTransaction().begin();
        em.remove(s);
        em.getTransaction().commit();
        em.close();
        return "success.xhtml";
    }
    
    public String dodajSamochod(){
        EntityManager em = Connect.createEntityManager();
        Query q;
        Random r = new Random();
        do{
            samochod.setId(r.nextInt(10000000));
            q = em.createNamedQuery("Samochod.findById").setParameter("id", samochod.getId());
        }while(!q.getResultList().isEmpty());
        samochod.setCenaPrzygotowania((short)0);
        em.getTransaction().begin();
        em.persist(samochod);
        em.getTransaction().commit();
        em.close();
        return "success.xhtml";
    }
}
