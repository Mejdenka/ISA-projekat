package JV20.isapsw.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private Set<Pregled> pregledi;
    @OneToMany
    private Set<Operacija> operacije;
    @OneToMany
    private Set<Termin> termini;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Klinika klinika;

    public Sala() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Pregled> getPregledi() {
        return pregledi;
    }

    public void setPregledi(Set<Pregled> pregledi) {
        this.pregledi = pregledi;
    }

    public Set<Operacija> getOperacije() {
        return operacije;
    }

    public void setOperacije(Set<Operacija> operacije) {
        this.operacije = operacije;
    }

    public Set<Termin> getTermini() {
        return termini;
    }

    public void setTermini(Set<Termin> termini) {
        this.termini = termini;
    }

    public Klinika getKlinika() {
        return klinika;
    }

    public void setKlinika(Klinika klinika) {
        this.klinika = klinika;
    }
}
