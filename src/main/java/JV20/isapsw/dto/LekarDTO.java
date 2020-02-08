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
    private Integer zbirOcena;
    private Integer brojOcena;
    private boolean slobodan;
    private String radnoVreme;

    public LekarDTO(Lekar lekar){
        this(lekar.getId(), lekar.getKorisnickoIme(), lekar.getIme(), lekar.getPrezime(), lekar.getDatumRodjenja(),
                lekar.getEmail(), lekar.getZbirOcena(), lekar.getBrojOcena(), lekar.isSlobodan(), lekar.getRadnoVreme());
    }

    public LekarDTO(Long id, String korisnickoIme, String ime, String prezime, Date datumRodjenja, String email, Integer zbirOcena, Integer brojOcena, boolean slobodan, String radnoVreme) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.email = email;
        this.zbirOcena = zbirOcena;
        this.brojOcena = brojOcena;
        this.slobodan = slobodan;
        this.radnoVreme = radnoVreme;
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

    public Integer getZbirOcena() {
        return zbirOcena;
    }

    public Integer getBrojOcena() {
        return brojOcena;
    }

    public boolean isSlobodan() {
        return slobodan;
    }

    public String getRadnoVreme() {
        return radnoVreme;
    }
}
