package JV20.isapsw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Operacija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Pacijent pacijent;
    @OneToOne
    @JsonIgnore
    private Lekar lekar;
    @OneToOne
    private Termin termin;

    private boolean obavljena;
    private boolean obrisana;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Sala salaOperacije;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Klinika klinikaOperacije;

    public Operacija() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }

    public Sala getSalaOperacije() {
        return salaOperacije;
    }

    public void setSalaOperacije(Sala salaOperacije) {
        this.salaOperacije = salaOperacije;
    }

    public Klinika getKlinikaOperacije() {
        return klinikaOperacije;
    }

    public void setKlinikaOperacije(Klinika klinikaOperacije) {
        this.klinikaOperacije = klinikaOperacije;
    }

    public boolean isObavljena() {
        return obavljena;
    }

    public void setObavljena(boolean obavljena) {
        this.obavljena = obavljena;
    }

    public boolean isObrisana() {
        return obrisana;
    }

    public void setObrisana(boolean obrisana) {
        this.obrisana = obrisana;
    }
}
