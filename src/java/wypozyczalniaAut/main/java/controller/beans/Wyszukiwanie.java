/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller.beans;

import java.util.ArrayList;
import java.util.Vector;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import wypozyczalniaAut.main.java.controller.Connect;
import wypozyczalniaAut.main.java.model.Samochod;
import java.util.List;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author Kamil
 */
@Named(value = "wyszukiwanie")
@Dependent
@ManagedBean
@RequestScoped
@ViewScoped

public class Wyszukiwanie {

    private String typ;
    private String marka;
    private List<String> typ_list=new ArrayList<>();
    private List<String> marka_list;
    
    
    public String getTyp() {
        return typ;
    }
    
    public String getMarka() {
        return marka;
    }
    
    public List<String> getTyp_list(){
        return typ_list;
    }
    
    public List<String> getMarka_list(){
        return marka_list;
    }
 
    public void setTyp(String typ) {
        this.typ = typ;
    }
    
    public void setMarka(String marka){
        this.marka = marka;
    }
    
   public void setMarka_list(List<String> marka_list){
       this.typ_list = typ_list;
   }
   
   public void setTyp_list(List<String> typ_list){
       this.marka_list = marka_list;
   }
    
    public Wyszukiwanie(){
        EntityManager em = Connect.createEntityManager();
        Query q = em.createNamedQuery("Samochod.findByTyp").setParameter("typ", typ);
        Vector <Samochod> wyniki = (Vector)q.getResultList();
    }
    
    public List<String> get_typ(){
        try{
            EntityManager em = Connect.createEntityManager();
            Query q = em.createNamedQuery("Samochod.findByTyp").setParameter("typ", typ);
            Vector <Samochod> wyniki = (Vector)q.getResultList();
                
        }catch(Exception e){
            System.out.println(e);
        }
        return typ_list;
    }
    
}
