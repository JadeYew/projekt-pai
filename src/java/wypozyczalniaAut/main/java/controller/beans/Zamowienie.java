/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller.beans;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.SelectEvent;
import wypozyczalniaAut.main.java.controller.Connect;
import wypozyczalniaAut.main.java.model.Akcesorium;
import wypozyczalniaAut.main.java.model.AkcesoriumDoZamowienia;
import wypozyczalniaAut.main.java.model.AkcesoriumDoZamowieniaPK;
import wypozyczalniaAut.main.java.model.Klient;
import wypozyczalniaAut.main.java.model.Pojazd;
import wypozyczalniaAut.main.java.model.Samochod;


/**
 *
 * @author Pato
 */
@Named("zamowienie")
@ManagedBean
@SessionScoped
public class Zamowienie implements Serializable{
    private String marka;
    private String model;
    private Date date1;
    private Date date2;
    private int PojazdId;
    private boolean dostepnosc;
    private List<String> markaList = new ArrayList();
    private List<String> modelList = new ArrayList();
    private List<Akcesorium> akcesoria = new ArrayList();
    wypozyczalniaAut.main.java.model.Zamowienie zamowienie = new wypozyczalniaAut.main.java.model.Zamowienie();

    public wypozyczalniaAut.main.java.model.Zamowienie getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(wypozyczalniaAut.main.java.model.Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }

    
    public void uzupelnijMarkaList(){
            EntityManager em = Connect.createEntityManager();
            Query q = em.createNamedQuery("Samochod.findAll");
            Vector <Samochod> samochody = (Vector)q.getResultList();
            for(Samochod s: samochody){
                markaList.add(s.getMarka());
            }
            for(int i = 0; i < markaList.size() - 1; i++){
                String tmp = markaList.get(i);
                for(int j = i + 1; j < markaList.size(); j++){
                    if(markaList.get(j).equals(tmp)){
                        markaList.remove(j);
                        j--;
                    }
                }
            }
            em.close();
    }
    
    public void uzupelnijModelList(){
            modelList = new ArrayList();
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
    
        public boolean isDostepnosc() {
        return dostepnosc;
    }

    public void setDostepnosc(boolean dostepnosc) {
        this.dostepnosc = dostepnosc;
    }

    public List<Akcesorium> getAkcesoria() {
        if(akcesoria == null){
            akcesoria = new ArrayList();
        }
        return akcesoria;
    }

    public void setAkcesoria(List<Akcesorium> akcesoria) {
        this.akcesoria = akcesoria;
    }
    
    public void setMarkaList(List<String> marka_list){
        this.markaList = marka_list;
    }

    public int getPojazdId() {
        return PojazdId;
    }

    public void setPojazdId(int PojazdId) {
        this.PojazdId = PojazdId;
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
            date1 = new Date();
        }
        return date1;
    }
 
    public void setDate1(Date date1) {
        this.date1 = date1;
    }
 
    public Date getDate2() {
        if(date2 == null){
            date2 = minDate();
        }
        return date2;
    }
 
    public void setDate2(Date date2) {
        this.date2 = date2;
    }
    
    public Date minDate(){
        Calendar c = Calendar.getInstance();
        c.setTime(getDate1());
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }
    
    public void dateDiff(SelectEvent event){
        if(date1!= null && date2!=null){
            
            try{
                                
                long diff = date2.getTime() - date1.getTime();
                
                long diffDays = diff/(24*60*60*1000);
                
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public int znajdzIdPojazd(Samochod s){
        Pojazd [] listaPojazdow = (Pojazd [])s.getPojazdCollection().toArray();
        for(Pojazd p: listaPojazdow){
                if(p.getDostepnosc()){
                    return p.getId();
                }
        }
        return 0;
    }
    
    public int idKlient(Klient k){
        return k.getId();
    }
    
    public String sprawdzDostepnosc(){
        EntityManager em = Connect.createEntityManager();
        Query q = em.createNamedQuery("Samochod.findByMarka").setParameter("marka", marka);
         Vector <Samochod> res = (Vector<Samochod>)q.getResultList();
        Samochod samochod = new Samochod();
        for(Samochod s : res){
            if(s.getModel().equals(model)){
                samochod = s;
                break;
            }
        }
        Vector<Pojazd> pojazdy = (Vector<Pojazd>)samochod.getPojazdCollection();
        ArrayList <Pojazd> dostepnePojazdy = new ArrayList();
        for(Pojazd p : pojazdy){
            if(dostepnyWCzasie(p.getId())){
                dostepnePojazdy.add(p);
                //return Integer.toString(p.getId());
            }
        }
        if(dostepnePojazdy.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_WARN, "Niestety nie ma dostępnych samochodów tego modelu w podanym przedziale czasowym", ""));
            dostepnosc = false;
            return null;
        }else{
            PojazdId = dostepnePojazdy.get(0).getId();
        }
        dostepnosc = true;
        em.close();
        return "dodajAkcesoria.xhtml?faces-redirect=true";
    }
    
    private boolean dostepnyWCzasie(int pId){
        EntityManager em = Connect.createEntityManager();
        Query q = em.createNamedQuery("Zamowienie.findAll");
        List<wypozyczalniaAut.main.java.model.Zamowienie> zamowienia = (List)q.getResultList();
        for(wypozyczalniaAut.main.java.model.Zamowienie z : zamowienia){
            if(pId == z.getIdPojazd().getId()){
            if((z.getDataRozpoczecia().after(date1) && z.getDataRozpoczecia().before(date2) 
                    || (z.getDataZakonczenia().after(date1) && z.getDataZakonczenia().before(date2)) 
                    || (z.getDataRozpoczecia().before(date1) && z.getDataZakonczenia().after(date2)
                    || z.getDataRozpoczecia().equals(date1)
                    || z.getDataRozpoczecia().equals(date2)
                    || z.getDataZakonczenia().equals(date1)
                    || z.getDataZakonczenia().equals(date2)))){
                return false;
            }
            }
        }
        return true;
    }
    
    public void dodajAkcesorium(Akcesorium a){
        akcesoria.add(a);
    }
    
    public short obliczCene(){
        EntityManager em = Connect.createEntityManager();
        Query q = em.createNamedQuery("Samochod.findByMarka").setParameter("marka", marka);
        Vector <Samochod> res = (Vector<Samochod>)q.getResultList();
        Samochod samochod = new Samochod();
        for(Samochod s : res){
            if(s.getModel().equals(model)){
                samochod = s;
                break;
            }
        }
        em.close();
        long diff = date2.getTime() - date1.getTime();
        diff = diff/(1000 * 60 * 60 * 24);
        short cena = (short)(samochod.getCenaPodstawowa() * diff);
        if(!akcesoria.isEmpty()){
            for(Akcesorium a: akcesoria){
                cena += (short)a.getCena();
            }
        }
        return cena;
    }

    public void zapisz(Klient klient){
        if(dostepnosc){
            EntityManager em = Connect.createEntityManager();
            Query q = em.createNamedQuery("Pojazd.findById").setParameter("id", PojazdId);
            zamowienie = new wypozyczalniaAut.main.java.model.Zamowienie();
            zamowienie.setIdPojazd((Pojazd) q.getSingleResult());
            zamowienie.setIdKlient(klient);
            zamowienie.setDataRozpoczecia(date1);
            zamowienie.setDataZakonczenia(date2);
            zamowienie.setCena(obliczCene());
            zamowienie.setOplacone(false);
            zamowienie.setZamkniete(false);
            zamowienie.setAnulowane(false);
            Random r = new Random();
            do{
                zamowienie.setId(r.nextInt(10000000));
                q = em.createNamedQuery("Zamowienie.findById").setParameter("id", zamowienie.getId());
            }while(!q.getResultList().isEmpty());
            em.getTransaction().begin();
            em.persist(zamowienie);
            em.getTransaction().commit();
            em.close();
            if(!akcesoria.isEmpty()){
                for(Akcesorium a : akcesoria){
                    em = Connect.createEntityManager();
                    AkcesoriumDoZamowienia tmp = new AkcesoriumDoZamowienia();
                    AkcesoriumDoZamowieniaPK tmpPK = new AkcesoriumDoZamowieniaPK();
                    tmpPK.setIdAkcesorium(a.getId());
                    tmpPK.setIdZamowienia(zamowienie.getId());
                    tmp.setAkcesoriumDoZamowieniaPK(tmpPK);
                    tmp.setAkcesorium(a);
                    tmp.setZamowienie(zamowienie);
                    tmp.setIlosc((short)1);
                    em.getTransaction().begin();
                    em.persist(tmp);
                    em.getTransaction().commit();
                    em.close();
                }
            }
        }
    }
    
    public String zamowSamochod(String marka, String model){
        setMarka(marka);
        setModel(model);
        return "zamowienie.xhtml?faces-redirect=true";
    }
    
    public String ipClient(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
    
    public String cena(){
        return Integer.toString(zamowienie.getCena() * 100);
    }
    
    public String opis(){
        String opis = "Wynajem samochodu: " + zamowienie.getIdPojazd().getIdSamochod().getMarka()
                + " " + zamowienie.getIdPojazd().getIdSamochod().getModel()
                + " od " + zamowienie.getDataRozpoczecia()
                + " do " + zamowienie.getDataZakonczenia() + ".";
        return opis;
    }
    
    public String nazwa(){
        String nazwa = zamowienie.getIdPojazd().getIdSamochod().getMarka()
                + " " + zamowienie.getIdPojazd().getIdSamochod().getModel();
        return nazwa;
    }
}