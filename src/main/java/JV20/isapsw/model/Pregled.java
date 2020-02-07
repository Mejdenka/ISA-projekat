package JV20.isapsw.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Pregled {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Termin termin;
    @OneToOne
    private Pacijent pacijent;
    @OneToOne
    private Lekar lekar;

    //za listu pregleda u lekaru
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Lekar lekarPregleda;

    private boolean obavljen;
    private boolean obrisan;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Sala sala;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Klinika klinikaPregleda;

    @OneToOne
    private TipPregleda tipPregleda;

    public Pregled() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
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

    public TipPregleda getTipPregleda() {
        return tipPregleda;
    }

    public void setTipPregleda(TipPregleda tipPregleda) {
        this.tipPregleda = tipPregleda;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Klinika getKlinikaPregleda() {
        return klinikaPregleda;
    }

    public void setKlinikaPregleda(Klinika klinikaPregleda) {
        this.klinikaPregleda = klinikaPregleda;
    }

    public boolean isObavljen() {
        return obavljen;
    }

    public void setObavljen(boolean obavljen) {
        this.obavljen = obavljen;
    }

    public boolean isObrisan() {
        return obrisan;
    }

    public void setObrisan(boolean obrisan) {
        this.obrisan = obrisan;
    }

    public Lekar getLekarPregleda() {
        return lekarPregleda;
    }

    public void setLekarPregleda(Lekar lekarPregleda) {
        this.lekarPregleda = lekarPregleda;
    }
}
