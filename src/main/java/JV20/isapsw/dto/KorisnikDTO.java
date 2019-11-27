package JV20.isapsw.dto;

import JV20.isapsw.common.TimeProvider;
import JV20.isapsw.model.Korisnik;
import JV20.isapsw.model.ULOGA;

import java.text.SimpleDateFormat;
import java.util.Date;

public class KorisnikDTO {
    private Long id;
    private String korisnickoIme;
    private String ime;
    private String prezime;
    private String email;
    private String lozinka;
    private String datumRodjenja;
    private ULOGA uloga;

    public KorisnikDTO() {}

    public KorisnikDTO(Korisnik korisnik){
        this(korisnik.getId(), korisnik.getKorisnickoIme(), korisnik.getIme(), korisnik.getPrezime(), korisnik.getEmail(),
                korisnik.getLozinka(),korisnik.getDatumRodjenja().toString(), korisnik.getUloga());
    }

    public KorisnikDTO(Long id, String korisnickoIme, String ime, String prezime, String email, String lozinka, String datumRodjenja, ULOGA uloga) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.lozinka = lozinka;
        this.datumRodjenja = datumRodjenja;
        this.uloga = uloga;
    }

    public Long getId() {
        return id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getEmail() {
        return email;
    }

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public String getLozinka() {
        return lozinka;
    }

    public ULOGA getUloga() {
        return uloga;
    }
}
