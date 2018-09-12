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
import javax.persistence.OneToOne;
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
@Table(name = "uzytkownik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uzytkownik.findAll", query = "SELECT u FROM Uzytkownik u")
    , @NamedQuery(name = "Uzytkownik.findById", query = "SELECT u FROM Uzytkownik u WHERE u.id = :id")
    , @NamedQuery(name = "Uzytkownik.findByLogin", query = "SELECT u FROM Uzytkownik u WHERE u.login = :login")
    , @NamedQuery(name = "Uzytkownik.findByPassword", query = "SELECT u FROM Uzytkownik u WHERE u.password = :password")
    , @NamedQuery(name = "Uzytkownik.findByEMail", query = "SELECT u FROM Uzytkownik u WHERE u.eMail = :eMail")
    , @NamedQuery(name = "Uzytkownik.findBySalt", query = "SELECT u FROM Uzytkownik u WHERE u.salt = :salt")})
public class Uzytkownik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 32)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "e_mail")
    private String eMail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "salt")
    private String salt;
    @OneToOne(mappedBy = "idUzytkownik")
    private Pracownik pracownik;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idUzytkownik")
    private Admin admin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUzytkownik")
    private Collection<Klient> klientCollection;

    public Uzytkownik() {
    }

    public Uzytkownik(Integer id) {
        this.id = id;
    }

    public Uzytkownik(Integer id, String password, String eMail, String salt) {
        this.id = id;
        this.password = password;
        this.eMail = eMail;
        this.salt = salt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @XmlTransient
    public Collection<Klient> getKlientCollection() {
        return klientCollection;
    }

    public void setKlientCollection(Collection<Klient> klientCollection) {
        this.klientCollection = klientCollection;
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
        if (!(object instanceof Uzytkownik)) {
            return false;
        }
        Uzytkownik other = (Uzytkownik) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wypozyczalniaAut.main.java.model.Uzytkownik[ id=" + id + " ]";
    }
    
}
