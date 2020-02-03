package JV20.isapsw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Pacijent extends Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long jbo;

    @OneToOne
    @JsonIgnore
    private ZdravstveniKarton karton;

    @OneToMany
    private Set<Recept> recepti;

    @OneToMany(mappedBy = "pacijent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Operacija> operacije;

    @OneToMany(mappedBy = "pacijent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Pregled> pregledi;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Klinika klinikaPacijenta;

    public Pacijent() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZdravstveniKarton getKarton() {
        return karton;
    }

    public void setKarton(ZdravstveniKarton karton) {
        this.karton = karton;
    }

    public Set<Recept> getRecepti() {
        return recepti;
    }

    public void setRecepti(Set<Recept> recepti) {
        this.recepti = recepti;
    }

    public Klinika getKlinikaPacijenta() {
        return klinikaPacijenta;
    }

    public void setKlinikaPacijenta(Klinika klinikaPacijenta) {
        this.klinikaPacijenta = klinikaPacijenta;
    }

    public Long getJbo() {
        return jbo;
    }

    public void setJbo(Long jbo) {
        this.jbo = jbo;
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
}
