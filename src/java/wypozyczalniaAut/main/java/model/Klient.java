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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Iwo Ryszkowski
 */
@Entity
@Table(name = "klient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klient.findAll", query = "SELECT k FROM Klient k")
    , @NamedQuery(name = "Klient.findById", query = "SELECT k FROM Klient k WHERE k.id = :id")
    , @NamedQuery(name = "Klient.findByImie", query = "SELECT k FROM Klient k WHERE k.imie = :imie")
    , @NamedQuery(name = "Klient.findByNazwisko", query = "SELECT k FROM Klient k WHERE k.nazwisko = :nazwisko")
    , @NamedQuery(name = "Klient.findByAdres", query = "SELECT k FROM Klient k WHERE k.adres = :adres")
    , @NamedQuery(name = "Klient.findByKodPocztowy", query = "SELECT k FROM Klient k WHERE k.kodPocztowy = :kodPocztowy")
    , @NamedQuery(name = "Klient.findByMiejscowosc", query = "SELECT k FROM Klient k WHERE k.miejscowosc = :miejscowosc")
    , @NamedQuery(name = "Klient.findByWojewodztwo", query = "SELECT k FROM Klient k WHERE k.wojewodztwo = :wojewodztwo")})
public class Klient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "imie")
    private String imie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nazwisko")
    private String nazwisko;
    @Size(max = 64)
    @Column(name = "adres")
    private String adres;
    @Size(max = 6)
    @Column(name = "kod_pocztowy")
    private String kodPocztowy;
    @Size(max = 32)
    @Column(name = "miejscowosc")
    private String miejscowosc;
    @Size(max = 32)
    @Column(name = "wojewodztwo")
    private String wojewodztwo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKlient")
    private Collection<Zamowienie> zamowienieCollection;
    @JoinColumn(name = "id_uzytkownik", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Uzytkownik idUzytkownik;

    public Klient() {
    }

    public Klient(Integer id) {
        this.id = id;
    }

    public Klient(Integer id, String imie, String nazwisko) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getWojewodztwo() {
        return wojewodztwo;
    }

    public void setWojewodztwo(String wojewodztwo) {
        this.wojewodztwo = wojewodztwo;
    }

    @XmlTransient
    public Collection<Zamowienie> getZamowienieCollection() {
        return zamowienieCollection;
    }

    public void setZamowienieCollection(Collection<Zamowienie> zamowienieCollection) {
        this.zamowienieCollection = zamowienieCollection;
    }

    public Uzytkownik getIdUzytkownik() {
        return idUzytkownik;
    }

    public void setIdUzytkownik(Uzytkownik idUzytkownik) {
        this.idUzytkownik = idUzytkownik;
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
        if (!(object instanceof Klient)) {
            return false;
        }
        Klient other = (Klient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wypozyczalniaAut.main.java.model.Klient[ id=" + id + " ]";
    }
    
}
