/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.controller.beans;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import wypozyczalniaAut.main.java.model.Admin;

/**
 *
 * @author Iwo Ryszkowski
 */
@Named(value = "panelAdmin")
@Dependent
public class PanelAdmin {
    Admin admin;
}
