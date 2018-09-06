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
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
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

public class Wyszukiwanie implements Serializable{

    private String samochod;
    private Short typ;
    private String marka;
    private List<Short> typList = new ArrayList<Short>();
    private List<String> markaList = new ArrayList<>();
    
    public void uzupelnijTypList(){
        
            EntityManager em = Connect.createEntityManager();
            Query q = em.createNamedQuery("Samochod.findByTyp").setParameter("typ",typ);
            Vector <Samochod> samochody = (Vector)q.getResultList();
            for(Samochod s: samochody){
                typList.add(s.getTyp());
            }
            em.close();
    }
    
    public void uzupelnijMarkaList(){
        
            EntityManager em = Connect.createEntityManager();
            Query q = em.createNamedQuery("Samochod.findByMarka").setParameter("marka",marka);
            Vector <Samochod> samochody = (Vector)q.getResultList();
            for(Samochod s: samochody){
                markaList.add(s.getMarka());
            }
            em.close();
    }
    
    public Wyszukiwanie(){
    }
    
    public Short getTyp() {
        return typ;
    }
    
    public List<Short> getTypList(){
        if(typList.isEmpty()){
            uzupelnijTypList();
        }
        return typList;
    }
    
    public void setTyp(Short typ) {
        this.typ = typ;
    }
    
    public void setTypList(List<Short> typ_list){
       this.typList = typ_list;
   }
    
    public String getMarka(){
        return marka;
    }
    
    public List<String> getMarkaList(){
        if(markaList.isEmpty()){
            uzupelnijMarkaList();
        }
        return markaList;
    }
    
    public void setMarka(String marka){
        this.marka=marka;
    }
    
    public void setMarkaList(List<String> marka_list){
        this.markaList = marka_list;
    }
               
}
