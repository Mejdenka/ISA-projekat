package JV20.isapsw.dto;

import JV20.isapsw.model.Lekar;

import java.util.Date;

public class LekarDTO {
    private Long id;
    private String ime;
    private String prezime;
    private String email;
    private String korisnickoIme;
    private Date datumRodjenja;
    private Integer ocena;
    private boolean slobodan;

    public LekarDTO(Lekar lekar){
        this(lekar.getId(), lekar.getKorisnickoIme(), lekar.getIme(), lekar.getPrezime(), lekar.getDatumRodjenja(), lekar.getEmail(), lekar.getOcena(), lekar.isSlobodan());
    }

    public LekarDTO(Long id, String korisnickoIme, String ime, String prezime, Date datumRodjenja, String email, Integer ocena, boolean slobodan) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.email = email;
        this.ocena = ocena;
        this.slobodan = slobodan;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
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

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public Integer getOcena() {
        return ocena;
    }

    public boolean isSlobodan() {
        return slobodan;
    }
}
