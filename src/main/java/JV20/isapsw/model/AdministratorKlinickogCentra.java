package JV20.isapsw.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;


@Entity
public class AdministratorKlinickogCentra extends Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public AdministratorKlinickogCentra () {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
