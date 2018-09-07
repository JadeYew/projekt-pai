/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller.beans;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import wypozyczalniaAut.main.java.model.Samochod;

/**
 *
 * @author Iwo Ryszkowski
 */
@Named(value = "wyswietlSamochod")
@Dependent
@ManagedBean
@ViewScoped

public class WyswietlSamochod implements Serializable {
    Samochod samochod;
    
    public Samochod getSamochod() {
        return samochod;
    }

    public void setSamochod(Samochod samochod) {
        this.samochod = samochod;
    }
}
