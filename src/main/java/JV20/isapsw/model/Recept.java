package JV20.isapsw.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Recept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lek;
    private String dijagnoza;
    private String izvestaj;
    private boolean overen = false;
    private Long idLekara;
    private Long idSestre;

    public Recept() {}

    public Recept(Recept recept){
        this.lek = recept.lek;
        this.dijagnoza = recept.dijagnoza;
        this.izvestaj = recept.izvestaj;
        this.overen = false;
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

    public boolean isOveren() {
        return overen;
    }

    public void setOveren(boolean overen) {
        this.overen = overen;
    }
}
