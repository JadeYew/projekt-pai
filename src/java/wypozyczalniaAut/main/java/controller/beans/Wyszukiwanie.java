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
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import wypozyczalniaAut.main.java.controller.Connect;
import wypozyczalniaAut.main.java.model.Samochod;


/**
 *
 * @author Kamil
 */
@Named("wyszukiwanie")
@SessionScoped
@ManagedBean
public class Wyszukiwanie implements Serializable{

    private List <Samochod> samochodList = new ArrayList<>();
    private String typ;
    private String marka;
    private List<String> typList = new ArrayList<>();
    private List<String> markaList = new ArrayList<>();
    
    public void uzupelnijMarkaList(){
        
            EntityManager em = Connect.createEntityManager();
            Query q = em.createNamedQuery("Samochod.findAll");
            Vector <Samochod> samochody = (Vector)q.getResultList();
            samochody.forEach((s) -> {
                markaList.add(s.getMarka());
        });
            em.close();
    }
    
    public void uzupelnijSamochodList(){
        EntityManager em = Connect.createEntityManager();
        Query q;
        samochodList = new ArrayList<>();
        if(marka != null){
            q = em.createNamedQuery("Samochod.findByMarka").setParameter("marka", marka);
        }else{
            q = em.createNamedQuery("Samochod.findAll");
        }
        List <Samochod> tmp = q.getResultList();
        if(typ != null){
            short typInt = 0;
            switch(typ){
                case "Osobowy":
                    typInt = 1;
                    break;
                case "Van":
                    typInt = 2;
                    break;
                case "Dostawczy":
                    typInt = 3;
                    break;
                default:
                    break;
            }
            for(Samochod s : tmp){
                if(s.getTyp() == typInt){
                    samochodList.add(s);
                }
            }
        }
        else{
            samochodList = tmp;
        }
    }

    public Wyszukiwanie(){
    }
    
    public String getTyp() {
        return typ;
    }
    
    public List<String> getTypList(){
        if(typList.isEmpty()){
            typList.add("Osobowy");
            typList.add("Van");
            typList.add("Dostawczy");
        }
        return typList;
    }
    
    public void setTyp(String typ) {
        this.typ = typ;
    }
    
    public void setTypList(List<String> typ_list){
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
    public List<Samochod> getSamochodList() {
        if(samochodList.isEmpty()){
            uzupelnijSamochodList();
        }
        return samochodList;
    }

    public void setSamochodList(List<Samochod> samochodList) {
        this.samochodList = samochodList;
    }
    
    public String wyszukaj(){
        uzupelnijSamochodList();
        return null;
    }
}
