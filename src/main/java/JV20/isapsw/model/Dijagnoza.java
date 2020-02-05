package JV20.isapsw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dijagnoza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sifraDijagnoze;
    private String nazivDijagnoze;

    public Dijagnoza() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSifraDijagnoze() {
        return sifraDijagnoze;
    }

    public void setSifraDijagnoze(String sifraDijagnoze) {
        this.sifraDijagnoze = sifraDijagnoze;
    }

    public String getNazivDijagnoze() {
        return nazivDijagnoze;
    }

    public void setNaziv(String nazivDijagnoze) {
        this.nazivDijagnoze = nazivDijagnoze;
    }
}
