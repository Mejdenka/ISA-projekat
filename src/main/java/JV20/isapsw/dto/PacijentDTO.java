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

    public PacijentDTO() {}

    public PacijentDTO(Pacijent pacijent){
        this(pacijent.getId(), pacijent.getIme(), pacijent.getPrezime(), pacijent.getDatumRodjenja().toString());
    }

    public PacijentDTO(Long id, String ime, String prezime, String datumRodjenja) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
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

}
