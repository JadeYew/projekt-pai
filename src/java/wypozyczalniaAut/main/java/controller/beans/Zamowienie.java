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
 * @author Pato
 */
@Named(value = "zamowienie")
@ManagedBean
@RequestScoped
public class Zamowienie implements Serializable{
    private Samochod samochod;
    private String marka;
    private String model;
    private List<String> marka_list = new ArrayList<>();
    private List<String> model_list = new ArrayList<>();
    public List<String> get_marka(){
        
            EntityManager em = Connect.getConnect().createEntityManager();
            Query q = em.createNamedQuery("Samochod.findByMarka").setParameter("marka",samochod.getMarka());
            Vector <String> samochod = (Vector)q.getResultList();
            return marka_list;
    }
    
    public List<String> get_model(){
        
        EntityManager em = Connect.getConnect().createEntityManager();
        Query q = em.createNamedQuery("Samochod.findByModel") .setParameter("model", samochod.getModel());
        Vector <String> samochod = (Vector)q.getResultList();
        return model_list;
    }
    
    public Zamowienie(){        
    }
    
    public String getMarka(){
        return marka;
    }
    
    public List<String> getMarka_list(){
        return marka_list;
    }
    
    public void setMarka(String marka){
        this.marka=marka;
    }
    
    public void setMarka_list(List<String> marka_list){
        this.marka_list = marka_list;
    }
    
   public String getModel(){
       return model;
   }
   
   public List<String> getModel_list(){
       return model_list;
   }
   
   public void setModel(String model){
       this.model=model;
   }
   
    public void setModel_list(List<String> model_list){
        this.model_list = model_list;
    }
}