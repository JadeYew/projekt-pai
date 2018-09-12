/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.commons.lang3.tuple.MutablePair;
import wypozyczalniaAut.main.java.controller.Connect;
import wypozyczalniaAut.main.java.model.Akcesorium;

/**
 *
 * @author Iwo Ryszkowski
 */
@Named(value = "wyborAkcesorii")
@ManagedBean
@ViewScoped
public class WyborAkcesorii {
    private List<MutablePair<Akcesorium, Boolean>>akcesoria = new ArrayList();

    public List<MutablePair<Akcesorium, Boolean>> getAkcesoria() {
        uzupelnijAkcesoria();
        return akcesoria;
    }

    public void setAkcesoria(List<MutablePair<Akcesorium, Boolean>> akcesoria) {
        this.akcesoria = akcesoria;
    }
    
    public void uzupelnijAkcesoria(){
        EntityManager em = Connect.createEntityManager();
        Query q = em.createNamedQuery("Akcesorium.findAll");
        List <Akcesorium> tmp = q.getResultList();
        tmp.stream().map((a) -> new MutablePair(a, false)).forEachOrdered((pair) -> {
            akcesoria.add(pair);
        });
    }
}
