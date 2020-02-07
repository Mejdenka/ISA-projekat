package JV20.isapsw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Entity
public class Lekar extends Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ocena;
    private boolean slobodan;
    private boolean naGodisnjem;
    private boolean trajePregled;
    private String radnoVreme;


    @OneToMany(mappedBy = "lekarGO", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<GodisnjiOdsustvoTermin> rezervisaniGO;

    @OneToMany(mappedBy = "lekarOds", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<GodisnjiOdsustvoTermin> rezervisanaOdustva;

    @OneToMany
    @JsonIgnore
    private Set<Operacija> operacije;
    @OneToMany(mappedBy = "lekarPregleda", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Pregled> pregledi;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Klinika klinikaLekara;

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

    public boolean isTrajePregled() {
        return trajePregled;
    }

    public void setTrajePregled(boolean trajePregled) {
        this.trajePregled = trajePregled;
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

    public Klinika getKlinikaLekara() {
        return klinikaLekara;
    }

    public void setKlinikaLekara(Klinika klinikaLekara) {
        this.klinikaLekara = klinikaLekara;
    }

    public String getRadnoVreme() {
        return radnoVreme;
    }

    public void setRadnoVreme(String radnoVreme) {
        this.radnoVreme = radnoVreme;
    }

    public Set<GodisnjiOdsustvoTermin> getRezervisaniGO() {
        return rezervisaniGO;
    }

    public void setRezervisaniGO(Set<GodisnjiOdsustvoTermin> rezervisaniGO) {
        this.rezervisaniGO = rezervisaniGO;
    }

    public Set<GodisnjiOdsustvoTermin> getRezervisanaOdustva() {
        return rezervisanaOdustva;
    }

    public void setRezervisanaOdustva(Set<GodisnjiOdsustvoTermin> rezervisanaOdustva) {
        this.rezervisanaOdustva = rezervisanaOdustva;
    }
}