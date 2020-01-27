package JV20.isapsw.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Lekar extends Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ocena;
    private boolean slobodan;
    private boolean naGodisnjem;
    private boolean obrisan = false;

    @OneToMany
    private Set<Operacija> operacije;
    @OneToMany
    private Set<Pregled> pregledi;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Klinika klinika;

    public Lekar() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }

    public boolean isSlobodan() {
        return slobodan;
    }

    public void setSlobodan(boolean slobodan) {
        this.slobodan = slobodan;
    }

    public boolean isNaGodisnjem() {
        return naGodisnjem;
    }

    public void setNaGodisnjem(boolean naGodisnjem) {
        this.naGodisnjem = naGodisnjem;
    }

    public boolean isObrisan() {
        return obrisan;
    }

    public void setObrisan(boolean obrisan) {
        this.obrisan = obrisan;
    }

    public Set<Operacija> getOperacije() {
        return operacije;
    }

    public void setOperacije(Set<Operacija> operacije) {
        this.operacije = operacije;
    }

    public Set<Pregled> getPregledi() {
        return pregledi;
    }

    public void setPregledi(Set<Pregled> pregledi) {
        this.pregledi = pregledi;
    }

    public Klinika getKlinika() {
        return klinika;
    }

    public void setKlinika(Klinika klinika) {
        this.klinika = klinika;
    }

}