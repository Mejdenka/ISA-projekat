package JV20.isapsw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private String idLekara;
    private Long idSestre;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Klinika klinikaRecept;

    public Klinika getKlinikaRecept() {
        return klinikaRecept;
    }

    public void setKlinikaRecept(Klinika klinikaRecept) {
        this.klinikaRecept = klinikaRecept;
    }

    public Recept() {}

    public Recept(Recept recept){
        this.lek = recept.lek;
        this.dijagnoza = recept.dijagnoza;
        this.izvestaj = recept.izvestaj;
        this.idLekara = recept.idLekara;
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

    public String getIdLekara() {
        return idLekara;
    }

    public void setIdLekara(String idLekara) {
        this.idLekara = idLekara;
    }

    public Long getIdSestre() {
        return idSestre;
    }

    public void setIdSestre(Long idSestre) {
        this.idSestre = idSestre;
    }
}
