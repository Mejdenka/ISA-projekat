package JV20.isapsw.dto;

import JV20.isapsw.model.Authority;
import JV20.isapsw.model.Korisnik;

import java.util.List;

public class KorisnikDTO {
    private Long id;
    private String korisnickoIme;
    private String ime;
    private String prezime;
    private String email;
    private String lozinka;
    private String datumRodjenja;
    private boolean confirmed;
    private List<Authority> authorities;

    public KorisnikDTO() {}

    public KorisnikDTO(Korisnik korisnik){
        this(korisnik.getId(), korisnik.getKorisnickoIme(), korisnik.getIme(), korisnik.getPrezime(), korisnik.getEmail(),
                korisnik.getLozinka(), korisnik.getDatumRodjenja().toString());
    }

    public KorisnikDTO(Long id, String korisnickoIme, String ime, String prezime, String email, String lozinka, String datumRodjenja) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.lozinka = lozinka;
        this.datumRodjenja = datumRodjenja;
        this.confirmed = false;
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

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
