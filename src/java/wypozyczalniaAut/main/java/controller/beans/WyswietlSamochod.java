/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller.beans;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.flow.FlowScoped;
import wypozyczalniaAut.main.java.model.Samochod;

/**
 *
 * @author Iwo Ryszkowski
 */
@Named("wyswietlSamochod")
@SessionScoped
@ManagedBean
public class WyswietlSamochod implements Serializable {
    private Samochod samochod;
    
    public Samochod getSamochod() {
        return samochod;
    }

    public void setSamochod(Samochod samochod) {
        this.samochod = samochod;
    }
    
    public String typSamochodu(){
        switch(samochod.getTyp()){
            case 1:
                return "osobowy";
            case 2:
                return "van";
            case 3:
                return "dostawczy";
            default:
                return "błąd";
        }
    }
}
