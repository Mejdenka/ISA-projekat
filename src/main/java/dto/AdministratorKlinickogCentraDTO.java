package dto;

import model.AdministratorKlinickogCentra;

public class AdministratorKlinickogCentraDTO {
    private Long id;
    private String ime;

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
}
