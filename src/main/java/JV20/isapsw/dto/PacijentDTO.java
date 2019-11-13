package JV20.isapsw.dto;

import JV20.isapsw.model.Pacijent;

import java.util.Date;

public class PacijentDTO {
    private Long id;
    private String ime;
    private String prezime;
    private Date datumRodjenja;

    public PacijentDTO() {}

    public PacijentDTO(Pacijent pacijent){
        this(pacijent.getId(), pacijent.getIme(), pacijent.getPrezime(), pacijent.getDatumRodjenja());
    }

    public PacijentDTO(Long id, String ime, String prezime, Date datumRodjenja) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
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
}
