/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import wypozyczalniaAut.main.java.controller.Connect;
import wypozyczalniaAut.main.java.model.Klient;
import wypozyczalniaAut.main.java.model.Uzytkownik;

/**
 *
 * @author Iwo Ryszkowski
 */
@Named(value = "uzupelnijDane")
@SessionScoped
@ManagedBean
public class UzupelnijDane implements Serializable{
    private Uzytkownik uzytkownik;
    private Klient klient;
    private boolean required;

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
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

    public Klient getKlient() {
        if(klient == null){
            klient = new Klient();
        }
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }
    
    public void wczytajKlienta(Uzytkownik u){
        uzytkownik = u;
        EntityManager em = Connect.createEntityManager();
        Query q = em.createQuery("Select k from Klient k where k.idUzytkownik = :idUzytkownik").setParameter("idUzytkownik", uzytkownik);
        Vector <Klient> list = (Vector)q.getResultList();
        em.close();
        if(list.isEmpty()){
            required = true;
            klient = new Klient();
            Random r = new Random();
            em = Connect.createEntityManager();
            do{
                klient.setId(r.nextInt(10000000));
                q = em.createNamedQuery("Klient.findById").setParameter("id", uzytkownik.getId());
            }while(!q.getResultList().isEmpty());
            klient.setIdUzytkownik(uzytkownik);
        }else{
            required = false;
            klient = list.get(0);
        }
    }
    
    public boolean uzupelnijDane(){
        EntityManager em = Connect.createEntityManager();
            em.getTransaction().begin();
        if(required){
            em.persist(klient);
        }else{
            Klient klientBase = em.find(Klient.class, klient.getId());
            if(klient.getImie() != null){
                klientBase.setImie(klient.getImie());
            }
            if(klient.getNazwisko() != null){
                klientBase.setImie(klient.getNazwisko());
            }
            if(klient.getAdres() != null){
                klientBase.setImie(klient.getAdres());
            }
            if(klient.getKodPocztowy() != null){
                klientBase.setImie(klient.getKodPocztowy());
            }
            if(klient.getMiejscowosc() != null){
                klientBase.setImie(klient.getMiejscowosc());
            }
            if(klient.getWojewodztwo() != null){
                klientBase.setImie(klient.getWojewodztwo());
            }
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }
}
