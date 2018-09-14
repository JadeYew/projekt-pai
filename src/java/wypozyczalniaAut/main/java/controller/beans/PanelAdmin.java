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
import wypozyczalniaAut.main.java.controller.APKU;
import wypozyczalniaAut.main.java.controller.Connect;
import wypozyczalniaAut.main.java.controller.PKU;
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

    /**
     * @param nowyUzytkownik the nowyUzytkownik to set
     */
    
    private Admin admin;
    private Admin nowyAdmin;
    private Pracownik nowyPracownik;
    private Klient nowyKlient;
    private Uzytkownik nowyUzytkownik;
    private Uzytkownik usuwany;
    private boolean usun;
    private Akcesorium akcesorium;
    private Samochod samochod;
    
    public void setNowyUzytkownik(Uzytkownik nowyUzytkownik) {
        this.nowyUzytkownik = nowyUzytkownik;
    }
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
        this.setNowyUzytkownik(nowyUzytkownik);
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
        this.setAdmin(admin);
    }
    
    public String dodajPracownika(){
        EntityManager em = Connect.createEntityManager();
        Query q;
        Random r = new Random();
        getNowyUzytkownik();
        do{
            getNowyUzytkownik().setId(r.nextInt(10000000));
            q = em.createNamedQuery("Uzytkownik.findById").setParameter("id", getNowyUzytkownik().getId());
        }while(!q.getResultList().isEmpty());
        getNowyUzytkownik().setLogin(getNowyKlient().getImie().substring(0, 1) + getNowyKlient().getNazwisko());
        getNowyUzytkownik().setSalt(BCrypt.gensalt());
        getNowyUzytkownik().setPassword(BCrypt.hashpw("pass1234", getNowyUzytkownik().getSalt()));
        do{
            getNowyKlient().setId(r.nextInt(10000000));
            q = em.createNamedQuery("Klient.findById").setParameter("id", getNowyKlient().getId());
        }while(!q.getResultList().isEmpty());
        getNowyKlient().setIdUzytkownik(getNowyUzytkownik());
        List <Klient> tmp = new ArrayList();
        tmp.add(getNowyKlient());
        getNowyPracownik();
        do{
            getNowyPracownik().setId(r.nextInt(10000000));
            q = em.createNamedQuery("Pracownik.findById").setParameter("id", getNowyPracownik().getId());
        }while(!q.getResultList().isEmpty());
        getNowyPracownik().setIdUzytkownik(getNowyUzytkownik());
        em.getTransaction().begin();
        em.persist(getNowyUzytkownik());
        em.getTransaction().commit();
        em.close();
        em = Connect.createEntityManager();
        em.getTransaction().begin();
        em.persist(getNowyKlient());
        em.getTransaction().commit();
        em.close();
        em = Connect.createEntityManager();
        em.getTransaction().begin();
        em.persist(getNowyPracownik());
        em.getTransaction().commit();
        em.close();
        return "success.xhtml";
    }
    
    public String dodajAdmin(){
        dodajPracownika();
        EntityManager em = Connect.createEntityManager();
        Query q;
        Random r = new Random();
        getNowyAdmin();
        do{
            getNowyAdmin().setId(r.nextInt(10000000));
            q = em.createNamedQuery("Admin.findById").setParameter("id", getNowyAdmin().getId());
        }while(!q.getResultList().isEmpty());
        getNowyAdmin().setIdUzytkownik(getNowyUzytkownik());
        em.getTransaction().begin();
        em.persist(getNowyAdmin());
        em.getTransaction().commit();
        em.close();
        return null;
    }
    
    public void clear(){
        setNowyAdmin(null);
        setNowyPracownik(null);
        setNowyKlient(null);
        setNowyUzytkownik(null);
    }
    
    public void sprawdzUsun(){
        EntityManager em = Connect.createEntityManager();
        Query q = em.createNamedQuery("Uzytkownik.findByLogin").setParameter("login", getUsuwany().getLogin());
        if(q.getResultList().isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_WARN, "Nie ma pracownika o podanym loginie", getUsuwany().getLogin()));
            setUsun(false);
            return;
        }
        setUsun(true);
    }
    
    public String usunPracownika(){
        if(isUsun()){
             EntityManager em = Connect.createEntityManager();
             Query q = em.createNamedQuery("Uzytkownik.findByLogin").setParameter("login", getUsuwany().getLogin());
             setUsuwany((Uzytkownik)q.getSingleResult());
             if(getUsuwany().getPracownik() != null){
                 em.getTransaction().begin();
                em.remove(getUsuwany().getPracownik());
                em.getTransaction().commit();
             }
             List <Klient> tmp = (List)getUsuwany().getKlientCollection();
             if(!tmp.isEmpty()){
                 em.getTransaction().begin();
                 em.remove(tmp.get(0));
                em.getTransaction().commit();
             }
             if(getUsuwany().getAdmin() != null)
             {
                em.getTransaction().begin();
                em.remove(getUsuwany().getAdmin());
                em.getTransaction().commit();
             }
             em.getTransaction().begin();
             em.remove(getUsuwany());
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
            getAkcesorium().setId(r.nextInt(10000000));
            q = em.createNamedQuery("Akcesorium.findById").setParameter("id", getAkcesorium().getId());
        }while(!q.getResultList().isEmpty());
        getAkcesorium().setIloscDostepna(getAkcesorium().getIlosc());
        em.getTransaction().begin();
        em.persist(getAkcesorium());
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
            getSamochod().setId(r.nextInt(10000000));
            q = em.createNamedQuery("Samochod.findById").setParameter("id", getSamochod().getId());
        }while(!q.getResultList().isEmpty());
        getSamochod().setCenaPrzygotowania((short)0);
        em.getTransaction().begin();
        em.persist(getSamochod());
        em.getTransaction().commit();
        em.close();
        return "success.xhtml";
    }
    
    public List<PKU> getPUKList(){
        EntityManager em = Connect.createEntityManager();
        Query q = em.createNamedQuery("Pracownik.findAll");
        List<PKU> ret = new ArrayList();
        List<Pracownik> list = q.getResultList();
        for(Pracownik p : list){
            PKU tmp = new PKU();
            tmp.setPracownik(p);
            tmp.setUzytkownik(p.getIdUzytkownik());
            q = em.createNamedQuery("Klient.findAll");
            for(Klient k: (List<Klient>)q.getResultList()){
                if(k.getIdUzytkownik().getId().equals(tmp.getUzytkownik().getId())){
                    tmp.setKlient(k);
                    break;
                }
            }
            if(tmp.getUzytkownik().getAdmin() == null){
                ret.add(tmp);
            }
        }
        return ret;
    }
    public List<APKU> getAPUKList(){
        EntityManager em = Connect.createEntityManager();
        Query q = em.createNamedQuery("Admin.findAll");
        List<APKU> ret = new ArrayList();
        List<Admin> list = q.getResultList();
        for(Admin a : list){
            APKU tmp = new APKU();
            tmp.setAdmin(a);
            tmp.setUzytkownik(a.getIdUzytkownik());
            tmp.setPracownik(tmp.getUzytkownik().getPracownik());
            q = em.createNamedQuery("Klient.findAll");
            for(Klient k: (List<Klient>)q.getResultList()){
                if(k.getIdUzytkownik().getId().equals(tmp.getUzytkownik().getId())){
                    tmp.setKlient(k);
                    break;
                }
            }
            tmp.setAdmin(tmp.getUzytkownik().getAdmin());
            ret.add(tmp);
        }
        return ret;
    }
    public String usunPKU(PKU pku){
        EntityManager em = Connect.createEntityManager();
        em.getTransaction().begin();
        em.remove(pku.getPracownik());
        em.getTransaction().commit();
        em.getTransaction().begin();
        em.remove(pku.getKlient());
        em.getTransaction().commit();
        em.getTransaction().begin();
        em.remove(pku.getUzytkownik());
        em.getTransaction().commit();
        em.close();
        return "success.xhtml";
    }
    public String usunAPKU(APKU pku){
        EntityManager em = Connect.createEntityManager();
        em.getTransaction().begin();
        em.remove(pku.getAdmin());
        em.getTransaction().commit();
        em.getTransaction().begin();
        em.remove(pku.getPracownik());
        em.getTransaction().commit();
        em.getTransaction().begin();
        em.remove(pku.getKlient());
        em.getTransaction().commit();
        em.getTransaction().begin();
        em.remove(pku.getUzytkownik());
        em.getTransaction().commit();
        em.close();
        return "success.xhtml";
    }
}
