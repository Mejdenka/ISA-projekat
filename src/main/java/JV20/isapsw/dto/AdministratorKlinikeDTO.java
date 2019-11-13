package JV20.isapsw.dto;

import JV20.isapsw.model.AdministratorKlinike;

public class AdministratorKlinikeDTO {
    private Long id;
    private String ime;

    public AdministratorKlinikeDTO() {}

    public AdministratorKlinikeDTO(AdministratorKlinike administrator){
        this(administrator.getId(), administrator.getIme());
    }

    public AdministratorKlinikeDTO(Long id, String ime) {
        this.id = id;
        this.ime = ime;
    }

    public Long getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }
}
