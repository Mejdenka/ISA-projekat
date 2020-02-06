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

    public ReceptDTO() {}

    public ReceptDTO(Recept recept){
        this(recept.getId(), recept.getLek(), recept.getDijagnoza(), recept.getIzvestaj());
    }

    public ReceptDTO(Long id, String lek, String dijagnoza, String izvestaj){
        this.id = id;
        this.lek = lek;
        this.dijagnoza = dijagnoza;
        this.izvestaj = izvestaj;
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
