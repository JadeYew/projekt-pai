/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller.beans;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import wypozyczalniaAut.main.java.controller.Connect;
import wypozyczalniaAut.main.java.model.Uzytkownik;

/**
 *
 * @author Iwo Ryszkowski
 */
@Named(value = "resjestracja")
@Dependent
@ManagedBean
@RequestScoped
public class Resjestracja {
    @Inject
    private Uzytkownik uzytkownik;
    @Inject
    private Uzytkownik uzytkownikDuo;
    private String passwordAgain;
    
    public void setUzytkownik(Uzytkownik uzytkownik){
        this.uzytkownik = uzytkownik;
    }
    
    public void setUzytkownikDuo(Uzytkownik uzytkownikDuo){
        this.uzytkownikDuo = uzytkownikDuo;
    }
    
    public void setPasswordAgain(String passwordAgain){
        this.passwordAgain = passwordAgain;
    }
    
    public Uzytkownik getUzytkownik(){
        if(uzytkownik == null){
            uzytkownik =  new Uzytkownik();
        }
        return this.uzytkownik;
    }
    
    public Uzytkownik getUzytkownikDuo(){
        
        if(uzytkownikDuo == null){
            uzytkownikDuo =  new Uzytkownik();
        }
        return this.uzytkownikDuo;
    }
    
    public String getPasswordAgain(){
        return passwordAgain;
    }
    
    public boolean addUser(){
        EntityManager em = Connect.getConnect().createEntityManager();
        Uzytkownik wynikId = (Uzytkownik)em.createNamedQuery("Uzytkownik.findById")
                .setParameter("id", uzytkownik.getId())
                .getSingleResult();
        Uzytkownik wynikLogin = (Uzytkownik)em.createNamedQuery("Uzytkownik.dinfByLogin")
                .setParameter("login", uzytkownik.getLogin())
                .getSingleResult();
        if(uzytkownik.equals(wynikId) || uzytkownik.equals(wynikLogin)){
            return false;
        }
        em.getTransaction().begin();
        em.persist(uzytkownik);
        em.getTransaction().commit();
        return true;
    }
}
