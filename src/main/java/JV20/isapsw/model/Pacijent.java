package JV20.isapsw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Pacijent extends Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private ZdravstveniKarton karton;

    @OneToMany
    private Set<Recept> recepti;

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
}
