/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller.beans;

import java.util.Random;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import wypozyczalniaAut.main.java.controller.Connect;
import wypozyczalniaAut.main.java.model.Samochod;


/**
 *
 * @author Kamil
 */
@Named(value = "wyszukiwanie")
@Dependent
@ManagedBean
@RequestScoped
public class Wyszukiwanie {

    private String typ;
    
    public void setUzytkownik(Samochod typ){
        this.typ = typ;
    }
    
    public String getTyp() {
        return typ;
    }
 
    public void setTyp(String typ) {
        this.typ = typ;
    }
    
    public Wyszukiwanie(){
        EntityManager em = Connect.getConnect().createEntityManager();
        Query q = em.createNamedQuery("Samochod.findByTyp").setParameter("typ", samochod.gettyp());
    }
}
