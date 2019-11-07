package model;

import java.rmi.server.UID;
import java.util.Date;

public class Korisnik {
    private UID id;
    private String ime;
    private String prezime;
    private Date datumRegistrovanja;
    private Date datumRodjenja;
    private String korisnickoIme;
    private String lozinka;
    private String email;

    public Korisnik() {

    }

    public Korisnik(UID id, String ime, String prezime, Date datumRegistrovanja, Date datumRodjenja, String korisnickoIme, String lozinka, String email) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRegistrovanja = datumRegistrovanja;
        this.datumRodjenja = datumRodjenja;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.email = email;
    }

    public UID getId() {
        return id;
    }

    public void setId(UID id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDatumRegistrovanja() {
        return datumRegistrovanja;
    }

    public void setDatumRegistrovanja(Date datumRegistrovanja) {
        this.datumRegistrovanja = datumRegistrovanja;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
