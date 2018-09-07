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
@Table(name = "samochod")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Samochod.findAll", query = "SELECT s FROM Samochod s")
    , @NamedQuery(name = "Samochod.findById", query = "SELECT s FROM Samochod s WHERE s.id = :id")
    , @NamedQuery(name = "Samochod.findByMarka", query = "SELECT s FROM Samochod s WHERE s.marka = :marka")
    , @NamedQuery(name = "Samochod.findByModel", query = "SELECT s FROM Samochod s WHERE s.model = :model")
    , @NamedQuery(name = "Samochod.findByCentralnyZamek", query = "SELECT s FROM Samochod s WHERE s.centralnyZamek = :centralnyZamek")
    , @NamedQuery(name = "Samochod.findByKomputerPokladowy", query = "SELECT s FROM Samochod s WHERE s.komputerPokladowy = :komputerPokladowy")
    , @NamedQuery(name = "Samochod.findByElektryczneSzyby", query = "SELECT s FROM Samochod s WHERE s.elektryczneSzyby = :elektryczneSzyby")
    , @NamedQuery(name = "Samochod.findByElektryczneLusterka", query = "SELECT s FROM Samochod s WHERE s.elektryczneLusterka = :elektryczneLusterka")
    , @NamedQuery(name = "Samochod.findByKlimatyzacjaReczna", query = "SELECT s FROM Samochod s WHERE s.klimatyzacjaReczna = :klimatyzacjaReczna")
    , @NamedQuery(name = "Samochod.findByKlimatyzacjaAutomatyczna", query = "SELECT s FROM Samochod s WHERE s.klimatyzacjaAutomatyczna = :klimatyzacjaAutomatyczna")
    , @NamedQuery(name = "Samochod.findByPodgrzewaneLusterka", query = "SELECT s FROM Samochod s WHERE s.podgrzewaneLusterka = :podgrzewaneLusterka")
    , @NamedQuery(name = "Samochod.findByAbs", query = "SELECT s FROM Samochod s WHERE s.abs = :abs")
    , @NamedQuery(name = "Samochod.findByAutomatycznaSkrzynia", query = "SELECT s FROM Samochod s WHERE s.automatycznaSkrzynia = :automatycznaSkrzynia")
    , @NamedQuery(name = "Samochod.findByPoduszkiPowietrzne", query = "SELECT s FROM Samochod s WHERE s.poduszkiPowietrzne = :poduszkiPowietrzne")
    , @NamedQuery(name = "Samochod.findByIloscMiejsc", query = "SELECT s FROM Samochod s WHERE s.iloscMiejsc = :iloscMiejsc")
    , @NamedQuery(name = "Samochod.findByIloscDrzwi", query = "SELECT s FROM Samochod s WHERE s.iloscDrzwi = :iloscDrzwi")
    , @NamedQuery(name = "Samochod.findByRodzajPaliwa", query = "SELECT s FROM Samochod s WHERE s.rodzajPaliwa = :rodzajPaliwa")
    , @NamedQuery(name = "Samochod.findByPojemnoscSilnika", query = "SELECT s FROM Samochod s WHERE s.pojemnoscSilnika = :pojemnoscSilnika")
    , @NamedQuery(name = "Samochod.findByMoc", query = "SELECT s FROM Samochod s WHERE s.moc = :moc")
    , @NamedQuery(name = "Samochod.findByZuzycieMiasto", query = "SELECT s FROM Samochod s WHERE s.zuzycieMiasto = :zuzycieMiasto")
    , @NamedQuery(name = "Samochod.findByZuzycie90", query = "SELECT s FROM Samochod s WHERE s.zuzycie90 = :zuzycie90")
    , @NamedQuery(name = "Samochod.findByCenaPodstawowa", query = "SELECT s FROM Samochod s WHERE s.cenaPodstawowa = :cenaPodstawowa")
    , @NamedQuery(name = "Samochod.findByCenaPrzygotowania", query = "SELECT s FROM Samochod s WHERE s.cenaPrzygotowania = :cenaPrzygotowania")
    , @NamedQuery(name = "Samochod.findByTyp", query = "SELECT s FROM Samochod s WHERE s.typ = :typ")
    , @NamedQuery(name = "Samochod.findByOpisPlik", query = "SELECT s FROM Samochod s WHERE s.opisPlik = :opisPlik")
    , @NamedQuery(name = "Samochod.findByObrazPlik", query = "SELECT s FROM Samochod s WHERE s.obrazPlik = :obrazPlik")})
public class Samochod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "marka")
    private String marka;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "model")
    private String model;
    @Column(name = "centralny_zamek")
    private Boolean centralnyZamek;
    @Column(name = "komputer_pokladowy")
    private Boolean komputerPokladowy;
    @Column(name = "elektryczne_szyby")
    private Boolean elektryczneSzyby;
    @Column(name = "elektryczne_lusterka")
    private Boolean elektryczneLusterka;
    @Column(name = "klimatyzacja_reczna")
    private Boolean klimatyzacjaReczna;
    @Column(name = "klimatyzacja_automatyczna")
    private Boolean klimatyzacjaAutomatyczna;
    @Column(name = "podgrzewane_lusterka")
    private Boolean podgrzewaneLusterka;
    @Column(name = "abs")
    private Boolean abs;
    @Column(name = "automatyczna_skrzynia")
    private Boolean automatycznaSkrzynia;
    @Column(name = "poduszki_powietrzne")
    private Boolean poduszkiPowietrzne;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ilosc_miejsc")
    private short iloscMiejsc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ilosc_drzwi")
    private short iloscDrzwi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "rodzaj_paliwa")
    private String rodzajPaliwa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pojemnosc_silnika")
    private short pojemnoscSilnika;
    @Basic(optional = false)
    @NotNull
    @Column(name = "moc")
    private short moc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "zuzycie_miasto")
    private float zuzycieMiasto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "zuzycie_90")
    private float zuzycie90;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cena_podstawowa")
    private short cenaPodstawowa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cena_przygotowania")
    private short cenaPrzygotowania;
    @Basic(optional = false)
    @NotNull
    @Column(name = "typ")
    private short typ;
    @Size(max = 128)
    @Column(name = "opis_plik")
    private String opisPlik;
    @Size(max = 128)
    @Column(name = "obraz_plik")
    private String obrazPlik;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSamochod")
    private Collection<Pojazd> pojazdCollection;

    public Samochod() {
    }

    public Samochod(Integer id) {
        this.id = id;
    }

    public Samochod(Integer id, String marka, String model, short iloscMiejsc, short iloscDrzwi, String rodzajPaliwa, short pojemnoscSilnika, short moc, float zuzycieMiasto, float zuzycie90, short cenaPodstawowa, short cenaPrzygotowania, short typ) {
        this.id = id;
        this.marka = marka;
        this.model = model;
        this.iloscMiejsc = iloscMiejsc;
        this.iloscDrzwi = iloscDrzwi;
        this.rodzajPaliwa = rodzajPaliwa;
        this.pojemnoscSilnika = pojemnoscSilnika;
        this.moc = moc;
        this.zuzycieMiasto = zuzycieMiasto;
        this.zuzycie90 = zuzycie90;
        this.cenaPodstawowa = cenaPodstawowa;
        this.cenaPrzygotowania = cenaPrzygotowania;
        this.typ = typ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getCentralnyZamek() {
        return centralnyZamek;
    }

    public void setCentralnyZamek(Boolean centralnyZamek) {
        this.centralnyZamek = centralnyZamek;
    }

    public Boolean getKomputerPokladowy() {
        return komputerPokladowy;
    }

    public void setKomputerPokladowy(Boolean komputerPokladowy) {
        this.komputerPokladowy = komputerPokladowy;
    }

    public Boolean getElektryczneSzyby() {
        return elektryczneSzyby;
    }

    public void setElektryczneSzyby(Boolean elektryczneSzyby) {
        this.elektryczneSzyby = elektryczneSzyby;
    }

    public Boolean getElektryczneLusterka() {
        return elektryczneLusterka;
    }

    public void setElektryczneLusterka(Boolean elektryczneLusterka) {
        this.elektryczneLusterka = elektryczneLusterka;
    }

    public Boolean getKlimatyzacjaReczna() {
        return klimatyzacjaReczna;
    }

    public void setKlimatyzacjaReczna(Boolean klimatyzacjaReczna) {
        this.klimatyzacjaReczna = klimatyzacjaReczna;
    }

    public Boolean getKlimatyzacjaAutomatyczna() {
        return klimatyzacjaAutomatyczna;
    }

    public void setKlimatyzacjaAutomatyczna(Boolean klimatyzacjaAutomatyczna) {
        this.klimatyzacjaAutomatyczna = klimatyzacjaAutomatyczna;
    }

    public Boolean getPodgrzewaneLusterka() {
        return podgrzewaneLusterka;
    }

    public void setPodgrzewaneLusterka(Boolean podgrzewaneLusterka) {
        this.podgrzewaneLusterka = podgrzewaneLusterka;
    }

    public Boolean getAbs() {
        return abs;
    }

    public void setAbs(Boolean abs) {
        this.abs = abs;
    }

    public Boolean getAutomatycznaSkrzynia() {
        return automatycznaSkrzynia;
    }

    public void setAutomatycznaSkrzynia(Boolean automatycznaSkrzynia) {
        this.automatycznaSkrzynia = automatycznaSkrzynia;
    }

    public Boolean getPoduszkiPowietrzne() {
        return poduszkiPowietrzne;
    }

    public void setPoduszkiPowietrzne(Boolean poduszkiPowietrzne) {
        this.poduszkiPowietrzne = poduszkiPowietrzne;
    }

    public short getIloscMiejsc() {
        return iloscMiejsc;
    }

    public void setIloscMiejsc(short iloscMiejsc) {
        this.iloscMiejsc = iloscMiejsc;
    }

    public short getIloscDrzwi() {
        return iloscDrzwi;
    }

    public void setIloscDrzwi(short iloscDrzwi) {
        this.iloscDrzwi = iloscDrzwi;
    }

    public String getRodzajPaliwa() {
        return rodzajPaliwa;
    }

    public void setRodzajPaliwa(String rodzajPaliwa) {
        this.rodzajPaliwa = rodzajPaliwa;
    }

    public short getPojemnoscSilnika() {
        return pojemnoscSilnika;
    }

    public void setPojemnoscSilnika(short pojemnoscSilnika) {
        this.pojemnoscSilnika = pojemnoscSilnika;
    }

    public short getMoc() {
        return moc;
    }

    public void setMoc(short moc) {
        this.moc = moc;
    }

    public float getZuzycieMiasto() {
        return zuzycieMiasto;
    }

    public void setZuzycieMiasto(float zuzycieMiasto) {
        this.zuzycieMiasto = zuzycieMiasto;
    }

    public float getZuzycie90() {
        return zuzycie90;
    }

    public void setZuzycie90(float zuzycie90) {
        this.zuzycie90 = zuzycie90;
    }

    public short getCenaPodstawowa() {
        return cenaPodstawowa;
    }

    public void setCenaPodstawowa(short cenaPodstawowa) {
        this.cenaPodstawowa = cenaPodstawowa;
    }

    public short getCenaPrzygotowania() {
        return cenaPrzygotowania;
    }

    public void setCenaPrzygotowania(short cenaPrzygotowania) {
        this.cenaPrzygotowania = cenaPrzygotowania;
    }

    public short getTyp() {
        return typ;
    }

    public void setTyp(short typ) {
        this.typ = typ;
    }

    public String getOpisPlik() {
        return opisPlik;
    }

    public void setOpisPlik(String opisPlik) {
        this.opisPlik = opisPlik;
    }

    public String getObrazPlik() {
        return obrazPlik;
    }

    public void setObrazPlik(String obrazPlik) {
        this.obrazPlik = obrazPlik;
    }

    @XmlTransient
    public Collection<Pojazd> getPojazdCollection() {
        return pojazdCollection;
    }

    public void setPojazdCollection(Collection<Pojazd> pojazdCollection) {
        this.pojazdCollection = pojazdCollection;
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
        if (!(object instanceof Samochod)) {
            return false;
        }
        Samochod other = (Samochod) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wypozyczalniaAut.main.java.model.Samochod[ id=" + id + " ]";
    }
    
}
