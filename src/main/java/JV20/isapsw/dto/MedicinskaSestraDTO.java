package JV20.isapsw.dto;

import JV20.isapsw.model.MedicinskaSestra;

import java.util.Date;

public class MedicinskaSestraDTO {
    private Long id;
    private String ime;
    private String prezime;
    private Date datumRodjenja;

    public MedicinskaSestraDTO(MedicinskaSestra medicinskaSestra){
        this(medicinskaSestra.getId(), medicinskaSestra.getIme(), medicinskaSestra.getPrezime(), medicinskaSestra.getDatumRodjenja());
    }

    public MedicinskaSestraDTO(Long id, String ime, String prezime, Date datumRodjenja) {
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
