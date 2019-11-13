package JV20.isapsw.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Sifarnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private Set<Lek> lekovi;
    @OneToMany
    private Set<Dijagnoza> dijagnoze;

    public Sifarnik() {}

    public Sifarnik(Set<Lek> lekovi, Set<Dijagnoza> dijagnoze) {
        this.lekovi = lekovi;
        this.dijagnoze = dijagnoze;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Lek> getLekovi() {
        return lekovi;
    }

    public void setLekovi(Set<Lek> lekovi) {
        this.lekovi = lekovi;
    }

    public Set<Dijagnoza> getDijagnoze() {
        return dijagnoze;
    }

    public void setDijagnoze(Set<Dijagnoza> dijagnoze) {
        this.dijagnoze = dijagnoze;
    }
}
