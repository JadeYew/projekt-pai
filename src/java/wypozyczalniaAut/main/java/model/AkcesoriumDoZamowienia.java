/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Iwo Ryszkowski
 */
@Entity
@Table(name = "akcesorium_do_zamowienia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AkcesoriumDoZamowienia.findAll", query = "SELECT a FROM AkcesoriumDoZamowienia a")
    , @NamedQuery(name = "AkcesoriumDoZamowienia.findByIdAkcesorium", query = "SELECT a FROM AkcesoriumDoZamowienia a WHERE a.akcesoriumDoZamowieniaPK.idAkcesorium = :idAkcesorium")
    , @NamedQuery(name = "AkcesoriumDoZamowienia.findByIdZamowienia", query = "SELECT a FROM AkcesoriumDoZamowienia a WHERE a.akcesoriumDoZamowieniaPK.idZamowienia = :idZamowienia")
    , @NamedQuery(name = "AkcesoriumDoZamowienia.findByIlosc", query = "SELECT a FROM AkcesoriumDoZamowienia a WHERE a.ilosc = :ilosc")})
public class AkcesoriumDoZamowienia implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AkcesoriumDoZamowieniaPK akcesoriumDoZamowieniaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ilosc")
    private short ilosc;
    @JoinColumn(name = "id_akcesorium", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Akcesorium akcesorium;
    @JoinColumn(name = "id_zamowienia", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Zamowienie zamowienie;

    public AkcesoriumDoZamowienia() {
    }

    public AkcesoriumDoZamowienia(AkcesoriumDoZamowieniaPK akcesoriumDoZamowieniaPK) {
        this.akcesoriumDoZamowieniaPK = akcesoriumDoZamowieniaPK;
    }

    public AkcesoriumDoZamowienia(AkcesoriumDoZamowieniaPK akcesoriumDoZamowieniaPK, short ilosc) {
        this.akcesoriumDoZamowieniaPK = akcesoriumDoZamowieniaPK;
        this.ilosc = ilosc;
    }

    public AkcesoriumDoZamowienia(int idAkcesorium, int idZamowienia) {
        this.akcesoriumDoZamowieniaPK = new AkcesoriumDoZamowieniaPK(idAkcesorium, idZamowienia);
    }

    public AkcesoriumDoZamowieniaPK getAkcesoriumDoZamowieniaPK() {
        return akcesoriumDoZamowieniaPK;
    }

    public void setAkcesoriumDoZamowieniaPK(AkcesoriumDoZamowieniaPK akcesoriumDoZamowieniaPK) {
        this.akcesoriumDoZamowieniaPK = akcesoriumDoZamowieniaPK;
    }

    public short getIlosc() {
        return ilosc;
    }

    public void setIlosc(short ilosc) {
        this.ilosc = ilosc;
    }

    public Akcesorium getAkcesorium() {
        return akcesorium;
    }

    public void setAkcesorium(Akcesorium akcesorium) {
        this.akcesorium = akcesorium;
    }

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (akcesoriumDoZamowieniaPK != null ? akcesoriumDoZamowieniaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AkcesoriumDoZamowienia)) {
            return false;
        }
        AkcesoriumDoZamowienia other = (AkcesoriumDoZamowienia) object;
        if ((this.akcesoriumDoZamowieniaPK == null && other.akcesoriumDoZamowieniaPK != null) || (this.akcesoriumDoZamowieniaPK != null && !this.akcesoriumDoZamowieniaPK.equals(other.akcesoriumDoZamowieniaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wypozyczalniaAut.main.java.model.AkcesoriumDoZamowienia[ akcesoriumDoZamowieniaPK=" + akcesoriumDoZamowieniaPK + " ]";
    }
    
}
