package JV20.isapsw.dto;

import JV20.isapsw.model.Dijagnoza;
import JV20.isapsw.model.Lek;
import JV20.isapsw.model.Recept;

import java.util.Set;

public class ReceptDTO {
    private Long id;
    private String lek;
    private String dijagnoza;
    private String izvestaj;
    private String idLekara;
    private String imeLekara;
    private String przLekara;
    private boolean overen;
    private Long idSestre;

    public ReceptDTO() {}

    public ReceptDTO(Recept recept){
        this(recept.getId(), recept.getLek(), recept.getDijagnoza(), recept.getIzvestaj(), recept.getIdLekara(), recept.isOveren(), recept.getIdSestre());
    }

    public ReceptDTO(Long id, String lek, String dijagnoza, String izvestaj, String idLekara, boolean overen, Long idSestre){
        this.id = id;
        this.lek = lek;
        this.dijagnoza = dijagnoza;
        this.izvestaj = izvestaj;
        this.idLekara = idLekara;
        this.overen = overen;
        this.idSestre = idSestre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLek() {
        return lek;
    }

    public void setLek(String lek) {
        this.lek = lek;
    }

    public String getDijagnoza() {
        return dijagnoza;
    }

    public void setDijagnoza(String dijagnoza) {
        this.dijagnoza = dijagnoza;
    }

    public String getIzvestaj() {
        return izvestaj;
    }

    public void setIzvestaj(String izvestaj) {
        this.izvestaj = izvestaj;
    }
}
