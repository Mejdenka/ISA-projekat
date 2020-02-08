package JV20.isapsw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ZdravstveniKarton {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer broj;
    private double visina;
    private double masa;
    private String krvnaGrupa;
    private String dioptrija;
    private String alergije;


    @OneToOne
    @JsonIgnore
    private Pacijent pacijent;
    @OneToMany
    private Set<Dijagnoza> dijagnoze;
    @OneToMany
    private Set<Pregled> pregledi;

    public ZdravstveniKarton() {}

    public ZdravstveniKarton(Integer broj, Pacijent pacijent, Set<Dijagnoza> dijagnoze, Set<Pregled> pregledi, String krvnaGrupa, String dioptrija, String alergije) {
        this.broj = broj;
        this.pacijent = pacijent;
        this.dijagnoze = dijagnoze;
        this.pregledi = pregledi;
        this.krvnaGrupa = krvnaGrupa;
        this.dioptrija = dioptrija;
        this.alergije = alergije;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBroj() {
        return broj;
    }

    public void setBroj(Integer broj) {
        this.broj = broj;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public Set<Dijagnoza> getDijagnoze() {
        return dijagnoze;
    }

    public void setDijagnoze(Set<Dijagnoza> dijagnoze) {
        this.dijagnoze = dijagnoze;
    }

    public Set<Pregled> getPregledi() {
        return pregledi;
    }

    public void setPregledi(Set<Pregled> pregledi) {
        this.pregledi = pregledi;
    }

    public double getVisina() {
        return visina;
    }

    public void setVisina(double visina) {
        this.visina = visina;
    }

    public double getMasa() {
        return masa;
    }

    public void setMasa(double masa) {
        this.masa = masa;
    }

    public String getKrvnaGrupa() {
        return krvnaGrupa;
    }

    public void setKrvnaGrupa(String krvnaGrupa) {
        this.krvnaGrupa = krvnaGrupa;
    }

    public String getDioptrija() {
        return dioptrija;
    }

    public void setDioptrija(String dioptrija) {
        this.dioptrija = dioptrija;
    }

    public String getAlergije() {
        return alergije;
    }

    public void setAlergije(String alergije) {
        this.alergije = alergije;
    }
}
