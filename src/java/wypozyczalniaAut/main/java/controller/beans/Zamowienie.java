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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.primefaces.event.SelectEvent;
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
    private Date date1;
    private Date date2;
    private List<String> markaList = new ArrayList<>();
    private List<String> modelList = new ArrayList<>();
    
    public void uzupelnijMarkaList(){
        
            EntityManager em = Connect.createEntityManager();
            Query q = em.createNamedQuery("Samochod.findAll");
            Vector <Samochod> samochody = (Vector)q.getResultList();
            for(Samochod s: samochody){
                markaList.add(s.getMarka());
            }
            em.close();
    }
    
    public void uzupelnijModelList(){
        
            EntityManager em = Connect.createEntityManager();
            Query q = em.createNamedQuery("Samochod.findByMarka").setParameter("marka",marka);
            Vector <Samochod> samochody = (Vector)q.getResultList();
            for(Samochod s: samochody){
                modelList.add(s.getModel());
            }
            em.close();
    }
    
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
      
    public Zamowienie(){        
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
    
   public String getModel(){
       return model;
   }
   
   public List<String> getModelList(){
       uzupelnijModelList();
       return modelList;
   }
   
   public void setModel(String model){
       this.model=model;
   }
   
    public void setModelList(List<String> model_list){
        this.modelList = model_list;
    }
    
    public Date getDate1() {
        if(date1 == null){
            Calendar c = Calendar.getInstance();
            date1 = c.getTime();
        }
        return date1;
    }
 
    public void setDate1(Date date1) {
        this.date1 = date1;
    }
 
    public Date getDate2() {
        return date2;
    }
 
    public void setDate2(Date date2) {
        this.date2 = date2;
    }
    
    public Date minDate(){
        Calendar c = Calendar.getInstance();
        c.setTime(date1);
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }
    
}
