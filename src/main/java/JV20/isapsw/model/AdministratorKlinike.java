package JV20.isapsw.model;

import javax.persistence.*;

@Entity
public class AdministratorKlinike extends Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Klinika klinikaAdmina;

    public AdministratorKlinike() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Klinika getKlinikaAdmina() {
        return klinikaAdmina;
    }

    public void setKlinikaAdmina(Klinika klinikaAdmina) {
        this.klinikaAdmina = klinikaAdmina;
    }
}
