package wypozyczalniaAut.main.java.controller.beans;

import java.util.Random;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import wypozyczalniaAut.main.java.BCrypt;
import wypozyczalniaAut.main.java.controller.Connect;
import wypozyczalniaAut.main.java.model.Uzytkownik;


@Named(value = "rejestracja")
@ManagedBean
@RequestScoped
public class Rejestracja {
    private Uzytkownik uzytkownik;
    private String passwordAgain;
    private boolean dobreDane = false;
    
    public void setUzytkownik(Uzytkownik uzytkownik){
        this.uzytkownik = uzytkownik;
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
    
    public String getPasswordAgain(){
        return passwordAgain;
    }
    
    public String addUser(){
        if(dobreDane){
            EntityManager em = Connect.getConnect().createEntityManager();
            em.clear();
            Query q;
            Random r = new Random();
            do{
                uzytkownik.setId(new Integer(r.nextInt(2100000000)));
                q = em.createNamedQuery("Uzytkownik.findById").setParameter("id", uzytkownik.getId());
            }while(!q.getResultList().isEmpty());
            em.getTransaction().begin();
            em.persist(uzytkownik);
            em.getTransaction().commit();
            Connect.getConnect().closeEntityManagerFactory();
            return "footer";
        }
        return "rejestracja";
    }
    
    public String sprawdzDane(){
        dobreDane = true;
        if(sprawdzLogin()){
            FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_WARN, "Podany login Juz Istnieje", uzytkownik.getLogin()));
            dobreDane = false;
        }
        if(sprawdzEMail()){
            FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_WARN, "Podany e-mail Juz Istnieje", uzytkownik.geteMail()));
            dobreDane = false;
        }
        if(sprawdzHasla()){
            FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_WARN, "Podano różne hasła", ""));
            dobreDane = false;
        }
        return "";
    }
    
    public boolean sprawdzEMail(){
        EntityManager em = Connect.getConnect().createEntityManager();
        Query q = em.createNamedQuery("Uzytkownik.findByEMail").setParameter("eMail", uzytkownik.geteMail());
        return !q.getResultList().isEmpty();
    }
    
    public boolean sprawdzLogin(){
        EntityManager em = Connect.getConnect().createEntityManager();
        Query q = em.createNamedQuery("Uzytkownik.findByLogin").setParameter("login", uzytkownik.getLogin());
        return !q.getResultList().isEmpty();
    }
    
    public boolean sprawdzHasla(){
        return !uzytkownik.getPassword().equals(BCrypt.hashpw(passwordAgain, uzytkownik.getSalt()));
    }
    
    public void setDobreDane(boolean dobreDane){
        this.dobreDane = dobreDane;
    }
    
    public boolean getDobreDane(){
        return this.dobreDane;
    }
}
