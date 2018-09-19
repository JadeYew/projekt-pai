/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import wypozyczalniaAut.main.java.BCrypt;
import wypozyczalniaAut.main.java.controller.Connect;
import wypozyczalniaAut.main.java.model.Klient;
import wypozyczalniaAut.main.java.model.Uzytkownik;

/**
 *
 * @author Iwo Ryszkowski
 */
@Named(value = "google")
@ViewScoped
@ManagedBean
public class google implements Serializable{
    private String imie;
    private String email;
    private String ident;
    Uzytkownik uzytkownik;
    boolean czyIsnieje;
    
    public google(){
        
    }
    
    @PostConstruct
    public void init() {
        imie = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("name");
        email = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("email");
        ident = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ident");       
    }

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public boolean isCzyIsnieje() {
        return czyIsnieje;
    }

    public void setCzyIsnieje(boolean czyIsnieje) {
        this.czyIsnieje = czyIsnieje;
    }
    
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }
    
    public void czyIsniejeUzytkownik(){
        EntityManager em = Connect.createEntityManager();
        Query q = em.createNamedQuery("Uzytkownik.findByEMail").setParameter("eMail", email);
        if(q.getResultList().isEmpty()){
            czyIsnieje = false;
        }
        em.close();
        czyIsnieje = true;
    }
    
    public String jesliIstnieje(Sesja s){
            EntityManager em = Connect.createEntityManager();
            Query q = em.createNamedQuery("Uzytkownik.findByEMail").setParameter("eMail", email);
            s.setZalogowanyUzytkownik((Uzytkownik)q.getResultList().get(0));
            s.setZalogowany(czyIsnieje);
            s.setKlient((Klient)((List)s.getZalogowanyUzytkownik().getKlientCollection()).get(0));
            return "index.xhtml";
    }
    
    public String dodajUzytkownika(Sesja s){
        if(czyIsnieje){
            return jesliIstnieje(s);
        }
        EntityManager em = Connect.createEntityManager();
        Query q;
        uzytkownik = new Uzytkownik();
        uzytkownik.setEMail(email);
        getUzytkownik().setSalt(BCrypt.gensalt());
        uzytkownik.setPassword(BCrypt.hashpw(ident, uzytkownik.getSalt()));
        imie = imie.replaceAll(" ", "");
        int i = 1;
        q = em.createNamedQuery("Uzytkownik.findByLogin").setParameter("login", imie);
        if(!q.getResultList().isEmpty()){
            imie = imie + Integer.toString(i);
            while(true){
                q = em.createNamedQuery("Uzytkownik.findByLogin").setParameter("login", imie);
                if(q.getResultList().isEmpty()){
                   break; 
                }
                i++;
                imie = imie.substring(0, imie.length() - 1);
                imie = imie + Integer.toString(i);
            }
        }
        uzytkownik.setLogin(imie);
        Random r = new Random();
        do{
            uzytkownik.setId(r.nextInt(10000000));
            q = em.createNamedQuery("Uzytkownik.findById").setParameter("id", uzytkownik.getId());
        }while(!q.getResultList().isEmpty());
        em.getTransaction().begin();
        em.persist(uzytkownik);
        em.getTransaction().commit();
        em.close();
        s.setZalogowany(true);
        s.setZalogowanyUzytkownik(uzytkownik);
        return "uzupelnijDane.xhtml";
    }
}
