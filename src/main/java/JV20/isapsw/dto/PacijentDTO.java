package JV20.isapsw.dto;

import JV20.isapsw.model.Pacijent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PacijentDTO {
    private Long id;
    private String korisnickoIme;
    private String ime;
    private String prezime;
    private String email;
    private String lozinka;
    private String datumRodjenja;
    private Long jbo;

    public PacijentDTO() {}

    public PacijentDTO(Pacijent pacijent){
        this(pacijent.getId(), pacijent.getKorisnickoIme(), pacijent.getIme(), pacijent.getPrezime(), pacijent.getEmail(),
                pacijent.getLozinka(),pacijent.getDatumRodjenja().toString(), pacijent.getJbo());
    }

    public PacijentDTO(Long id, String korisnickoIme, String ime, String prezime, String email, String lozinka, String datumRodjenja, Long jbo) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.lozinka = lozinka;
        this.datumRodjenja = datumRodjenja;
        this.jbo = jbo;
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

    public Long getJbo() {
        return jbo;
    }

}
