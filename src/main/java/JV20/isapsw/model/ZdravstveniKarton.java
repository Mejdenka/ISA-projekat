package JV20.isapsw.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ZdravstveniKarton {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer broj;

    @OneToOne
    private Pacijent pacijent;
    @OneToMany
    private Set<Dijagnoza> dijagnoze;
    @OneToMany
    private Set<Pregled> pregledi;

    public ZdravstveniKarton() {}

    public ZdravstveniKarton(Integer broj, Pacijent pacijent, Set<Dijagnoza> dijagnoze, Set<Pregled> pregledi) {
        this.broj = broj;
        this.pacijent = pacijent;
        this.dijagnoze = dijagnoze;
        this.pregledi = pregledi;
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
}
