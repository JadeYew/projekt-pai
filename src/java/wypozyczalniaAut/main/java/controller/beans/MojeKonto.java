/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.flow.FlowScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import wypozyczalniaAut.main.java.controller.Connect;
import wypozyczalniaAut.main.java.model.AkcesoriumDoZamowienia;
import wypozyczalniaAut.main.java.model.Klient;
import wypozyczalniaAut.main.java.model.Zamowienie;

/**
 *
 * @author Iwo Ryszkowski
 */
@Named(value = "mojeKonto")
@ManagedBean
@SessionScoped
public class MojeKonto implements Serializable{

    /**
     * @return the zamowieniaZamkniete
     */
    public List <wypozyczalniaAut.main.java.model.Zamowienie> getZamowieniaZamkniete() {
        return zamowieniaZamkniete;
    }

    /**
     * @param zamowieniaZamkniete the zamowieniaZamkniete to set
     */
    public void setZamowieniaZamkniete(List <wypozyczalniaAut.main.java.model.Zamowienie> zamowieniaZamkniete) {
        this.zamowieniaZamkniete = zamowieniaZamkniete;
    }
    private List <wypozyczalniaAut.main.java.model.Zamowienie> zamowienia = new ArrayList();
    private List <wypozyczalniaAut.main.java.model.Zamowienie> zamowieniaZamkniete = new ArrayList();
    private Klient klient;
    private wypozyczalniaAut.main.java.model.Zamowienie doAnulowania;

    public Zamowienie getDoAnulowania() {
        if(doAnulowania == null){
            doAnulowania = new Zamowienie();
        }
        return doAnulowania;
    }

    public void setDoAnulowania(Zamowienie doAnulowania) {
        this.doAnulowania = doAnulowania;
    }

    public Klient getKlient() {
        if(klient == null){
            klient = new Klient();
        }
        return klient;
    }

    public void setKlient(Klient klient) {
        EntityManager em = Connect.createEntityManager();
        klient = (Klient)em.createNamedQuery("Klient.findById").setParameter("id", klient.getId()).getSingleResult();
        em.close();
        this.klient = klient;
    }

    public List<wypozyczalniaAut.main.java.model.Zamowienie> getZamowieniaZakonczone() {
        if(getZamowieniaZamkniete() == null){
            setZamowieniaZamkniete((List<Zamowienie>) new ArrayList());
        }
        return getZamowieniaZamkniete();
    }

    public void setZamowieniaZakonczone(List<wypozyczalniaAut.main.java.model.Zamowienie> zamowieniaZakonczone) {
        this.setZamowieniaZamkniete(zamowieniaZakonczone);
    }

    public List<wypozyczalniaAut.main.java.model.Zamowienie> getZamowienia() {
        if(zamowienia == null){
            zamowienia = new ArrayList();
        }
        return zamowienia;
    }

    public void setZamowienia(List<wypozyczalniaAut.main.java.model.Zamowienie> zamowienia) {
        this.zamowienia = zamowienia;
    }
    
    public void uzupelnijZamowienia(){
        setZamowieniaZamkniete((List<Zamowienie>) new ArrayList());
        setZamowienia((List<Zamowienie>) new ArrayList());
        EntityManager em = Connect.createEntityManager();
        Query q = em.createNamedQuery("Zamowienie.findAll");
        List <wypozyczalniaAut.main.java.model.Zamowienie> tmp = (List)q.getResultList();
        for( wypozyczalniaAut.main.java.model.Zamowienie z : tmp){
            if(z.getAnulowane() == false && z.getIdKlient().equals(getKlient())){
                if(z.getZamkniete()){
                    getZamowieniaZamkniete().add(z);
                }else{
                    getZamowienia().add(z);
                }
            }
        }
    }
    
    public String akcesoria(wypozyczalniaAut.main.java.model.Zamowienie z){
        List<AkcesoriumDoZamowienia> akcesoria = (List)z.getAkcesoriumDoZamowieniaCollection();
        if(akcesoria.isEmpty()){
            return "brak";
        }
        String ret = "";
        for(AkcesoriumDoZamowienia a : akcesoria){
            ret += a.getAkcesorium().getNazwa() + "<br />";
        }
        return ret.substring(0,ret.length() - 1);
    }
    
    public String anulujZamowienieRedirect(Zamowienie z){
        setDoAnulowania(z);
        return "anulujZamowienie.xhtml";
    }
    
     public String anulujZamowienie(){
        EntityManager em = Connect.createEntityManager();
        em.getTransaction().begin();
        setDoAnulowania(em.find(Zamowienie.class, getDoAnulowania().getId()));
        getDoAnulowania().setAnulowane(Boolean.TRUE);
        em.getTransaction().commit();
        em.close();
        return "wystietlZamowienia.xhtml";
     }
}
