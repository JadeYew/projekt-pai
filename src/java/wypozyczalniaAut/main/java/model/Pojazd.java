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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Iwo Ryszkowski
 */
@Entity
@Table(name = "pojazd")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pojazd.findAll", query = "SELECT p FROM Pojazd p")
    , @NamedQuery(name = "Pojazd.findById", query = "SELECT p FROM Pojazd p WHERE p.id = :id")
    , @NamedQuery(name = "Pojazd.findByDostepnosc", query = "SELECT p FROM Pojazd p WHERE p.dostepnosc = :dostepnosc")})
public class Pojazd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "dostepnosc")
    private Boolean dostepnosc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPojazd")
    private Collection<Zamowienie> zamowienieCollection;
    @JoinColumn(name = "id_samochod", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Samochod idSamochod;

    public Pojazd() {
    }

    public Pojazd(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getDostepnosc() {
        return dostepnosc;
    }

    public void setDostepnosc(Boolean dostepnosc) {
        this.dostepnosc = dostepnosc;
    }

    @XmlTransient
    public Collection<Zamowienie> getZamowienieCollection() {
        return zamowienieCollection;
    }

    public void setZamowienieCollection(Collection<Zamowienie> zamowienieCollection) {
        this.zamowienieCollection = zamowienieCollection;
    }

    public Samochod getIdSamochod() {
        return idSamochod;
    }

    public void setIdSamochod(Samochod idSamochod) {
        this.idSamochod = idSamochod;
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
        if (!(object instanceof Pojazd)) {
            return false;
        }
        Pojazd other = (Pojazd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wypozyczalniaAut.main.java.model.Pojazd[ id=" + id + " ]";
    }
    
}
