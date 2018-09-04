/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Iwo Ryszkowski
 */
@Embeddable
public class AkcesoriumDoZamowieniaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_akcesorium")
    private int idAkcesorium;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_zamowienia")
    private int idZamowienia;

    public AkcesoriumDoZamowieniaPK() {
    }

    public AkcesoriumDoZamowieniaPK(int idAkcesorium, int idZamowienia) {
        this.idAkcesorium = idAkcesorium;
        this.idZamowienia = idZamowienia;
    }

    public int getIdAkcesorium() {
        return idAkcesorium;
    }

    public void setIdAkcesorium(int idAkcesorium) {
        this.idAkcesorium = idAkcesorium;
    }

    public int getIdZamowienia() {
        return idZamowienia;
    }

    public void setIdZamowienia(int idZamowienia) {
        this.idZamowienia = idZamowienia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idAkcesorium;
        hash += (int) idZamowienia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AkcesoriumDoZamowieniaPK)) {
            return false;
        }
        AkcesoriumDoZamowieniaPK other = (AkcesoriumDoZamowieniaPK) object;
        if (this.idAkcesorium != other.idAkcesorium) {
            return false;
        }
        if (this.idZamowienia != other.idZamowienia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wypozyczalniaAut.main.java.model.AkcesoriumDoZamowieniaPK[ idAkcesorium=" + idAkcesorium + ", idZamowienia=" + idZamowienia + " ]";
    }
    
}
