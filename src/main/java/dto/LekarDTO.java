package dto;

import model.Lekar;

import java.util.Date;

public class LekarDTO {
    private Long id;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private Integer ocena;

    public LekarDTO(Lekar lekar){
        this(lekar.getId(), lekar.getIme(), lekar.getPrezime(), lekar.getDatumRodjenja(), lekar.getOcena());
    }

    public LekarDTO(Long id, String ime, String prezime, Date datumRodjenja, Integer ocena) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.ocena = ocena;
    }

    public Long getId() {
        return id;
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
}
