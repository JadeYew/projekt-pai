/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Iwo Ryszkowski
 */
@Entity
@Table(name = "akcesorium")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Akcesorium.findAll", query = "SELECT a FROM Akcesorium a")
    , @NamedQuery(name = "Akcesorium.findById", query = "SELECT a FROM Akcesorium a WHERE a.id = :id")
    , @NamedQuery(name = "Akcesorium.findByNazwa", query = "SELECT a FROM Akcesorium a WHERE a.nazwa = :nazwa")
    , @NamedQuery(name = "Akcesorium.findByIlosc", query = "SELECT a FROM Akcesorium a WHERE a.ilosc = :ilosc")
    , @NamedQuery(name = "Akcesorium.findByIloscDostepna", query = "SELECT a FROM Akcesorium a WHERE a.iloscDostepna = :iloscDostepna")
    , @NamedQuery(name = "Akcesorium.findByCena", query = "SELECT a FROM Akcesorium a WHERE a.cena = :cena")})
public class Akcesorium implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 32)
    @Column(name = "nazwa")
    private String nazwa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ilosc")
    private short ilosc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ilosc_dostepna")
    private short iloscDostepna;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cena")
    private short cena;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "akcesorium")
    private Collection<AkcesoriumDoZamowienia> akcesoriumDoZamowieniaCollection;

    public Akcesorium() {
    }

    public Akcesorium(Integer id) {
        this.id = id;
    }

    public Akcesorium(Integer id, short ilosc, short iloscDostepna, short cena) {
        this.id = id;
        this.ilosc = ilosc;
        this.iloscDostepna = iloscDostepna;
        this.cena = cena;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public short getIlosc() {
        return ilosc;
    }

    public void setIlosc(short ilosc) {
        this.ilosc = ilosc;
    }

    public short getIloscDostepna() {
        return iloscDostepna;
    }

    public void setIloscDostepna(short iloscDostepna) {
        this.iloscDostepna = iloscDostepna;
    }

    public short getCena() {
        return cena;
    }

    public void setCena(short cena) {
        this.cena = cena;
    }

    @XmlTransient
    public Collection<AkcesoriumDoZamowienia> getAkcesoriumDoZamowieniaCollection() {
        return akcesoriumDoZamowieniaCollection;
    }

    public void setAkcesoriumDoZamowieniaCollection(Collection<AkcesoriumDoZamowienia> akcesoriumDoZamowieniaCollection) {
        this.akcesoriumDoZamowieniaCollection = akcesoriumDoZamowieniaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Akcesorium)) {
            return false;
        }
        Akcesorium other = (Akcesorium) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wypozyczalniaAut.main.java.model.Akcesorium[ id=" + id + " ]";
    }
    
}
