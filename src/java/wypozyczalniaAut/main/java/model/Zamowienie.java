/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wypozyczalniaAut.main.java.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Iwo Ryszkowski
 */
@Entity
@Table(name = "zamowienie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zamowienie.findAll", query = "SELECT z FROM Zamowienie z")
    , @NamedQuery(name = "Zamowienie.findById", query = "SELECT z FROM Zamowienie z WHERE z.id = :id")
    , @NamedQuery(name = "Zamowienie.findByCena", query = "SELECT z FROM Zamowienie z WHERE z.cena = :cena")
    , @NamedQuery(name = "Zamowienie.findByDataZakonczenia", query = "SELECT z FROM Zamowienie z WHERE z.dataZakonczenia = :dataZakonczenia")
    , @NamedQuery(name = "Zamowienie.findByOplacone", query = "SELECT z FROM Zamowienie z WHERE z.oplacone = :oplacone")
    , @NamedQuery(name = "Zamowienie.findByZamkniete", query = "SELECT z FROM Zamowienie z WHERE z.zamkniete = :zamkniete")
    , @NamedQuery(name = "Zamowienie.findByDataRozpoczecia", query = "SELECT z FROM Zamowienie z WHERE z.dataRozpoczecia = :dataRozpoczecia")})
public class Zamowienie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cena")
    private short cena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_zakonczenia")
    @Temporal(TemporalType.DATE)
    private Date dataZakonczenia;
    @Column(name = "oplacone")
    private Boolean oplacone;
    @Column(name = "zamkniete")
    private Boolean zamkniete;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_rozpoczecia")
    @Temporal(TemporalType.DATE)
    private Date dataRozpoczecia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zamowienie")
    private Collection<AkcesoriumDoZamowienia> akcesoriumDoZamowieniaCollection;
    @JoinColumn(name = "id_pojazd", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pojazd idPojazd;
    @JoinColumn(name = "id_klient", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Klient idKlient;

    public Zamowienie() {
    }

    public Zamowienie(Integer id) {
        this.id = id;
    }

    public Zamowienie(Integer id, short cena, Date dataZakonczenia, Date dataRozpoczecia) {
        this.id = id;
        this.cena = cena;
        this.dataZakonczenia = dataZakonczenia;
        this.dataRozpoczecia = dataRozpoczecia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getCena() {
        return cena;
    }

    public void setCena(short cena) {
        this.cena = cena;
    }

    public Date getDataZakonczenia() {
        return dataZakonczenia;
    }

    public void setDataZakonczenia(Date dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }

    public Boolean getOplacone() {
        return oplacone;
    }

    public void setOplacone(Boolean oplacone) {
        this.oplacone = oplacone;
    }

    public Boolean getZamkniete() {
        return zamkniete;
    }

    public void setZamkniete(Boolean zamkniete) {
        this.zamkniete = zamkniete;
    }

    public Date getDataRozpoczecia() {
        return dataRozpoczecia;
    }

    public void setDataRozpoczecia(Date dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }

    @XmlTransient
    public Collection<AkcesoriumDoZamowienia> getAkcesoriumDoZamowieniaCollection() {
        return akcesoriumDoZamowieniaCollection;
    }

    public void setAkcesoriumDoZamowieniaCollection(Collection<AkcesoriumDoZamowienia> akcesoriumDoZamowieniaCollection) {
        this.akcesoriumDoZamowieniaCollection = akcesoriumDoZamowieniaCollection;
    }

    public Pojazd getIdPojazd() {
        return idPojazd;
    }

    public void setIdPojazd(Pojazd idPojazd) {
        this.idPojazd = idPojazd;
    }

    public Klient getIdKlient() {
        return idKlient;
    }

    public void setIdKlient(Klient idKlient) {
        this.idKlient = idKlient;
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
        if (!(object instanceof Zamowienie)) {
            return false;
        }
        Zamowienie other = (Zamowienie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wypozyczalniaAut.main.java.model.Zamowienie[ id=" + id + " ]";
    }
    
}
