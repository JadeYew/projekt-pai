/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller.beans;

import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import wypozyczalniaAut.main.java.controller.Connect;
import wypozyczalniaAut.main.java.model.Samochod;
import wypozyczalniaAut.main.java.model.Uzytkownik;


/**
 *
 * @author Kamil
 */
@Named(value = "wyszukiwanie")
@Dependent
@ManagedBean
@RequestScoped

public class Wyszukiwanie implements Serializable{

    private Samochod samochod;
    private String typ;
    private String marka;
    private List<String> typList=new ArrayList<>();
    private List<String> markaList=new ArrayList<>();
    
    
    public String getTyp() {
        return typ;
    }
    
    public String getMarka() {
        return marka;
    }
    
    public List<String> getTypList(){
        return typList;
    }
    
    public List<String> getMarkaList(){
        return markaList;
    }
 
    public void setTyp(String typ) {
        this.typ = typ;
    }
    
    public void setMarka(String marka){
        this.marka = marka;
    }
    
   public void setMarka_list(List<String> marka_list){
       this.typList = typList;
   }
   
   public void setTyp_list(List<String> typ_list){
       this.markaList = markaList;
   }
    
    public Wyszukiwanie(){
        EntityManager em = Connect.createEntityManager();
        Query q = em.createNamedQuery("Samochod.findByTyp").setParameter("typ", typ);
        Vector <Samochod> wyniki = (Vector)q.getResultList();
    }
    
    public List<String> get_typ(){
        
            EntityManager em = Connect.createEntityManager();
            Query q = em.createNamedQuery("Samochod.findByTyp").setParameter("typ",samochod.getTyp());
            Vector <String> samochod = (Vector)q.getResultList();
            return typList;
    }
    
    public List<String> get_marka(){
        
            EntityManager em = Connect.createEntityManager();
            Query q = em.createNamedQuery("Samochod.findByMarka").setParameter("marka",samochod.getMarka());
            Vector <String> samochod = (Vector)q.getResultList();
            return markaList;
    }
               
}
