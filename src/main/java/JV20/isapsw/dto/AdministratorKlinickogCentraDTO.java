package JV20.isapsw.dto;

import JV20.isapsw.model.AdministratorKlinickogCentra;
import JV20.isapsw.model.ULOGA;

public class AdministratorKlinickogCentraDTO {
    private Long id;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;
    private String datumRodjenja;
    private ULOGA uloga;

    public AdministratorKlinickogCentraDTO() {}

    public AdministratorKlinickogCentraDTO(AdministratorKlinickogCentra administrator) {
        this(administrator.getId(), administrator.getIme());
    }

    public AdministratorKlinickogCentraDTO(Long id, String ime) {
        this.id = id;
        this.ime = ime;
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public ULOGA getUloga() {
        return uloga;
    }
}
