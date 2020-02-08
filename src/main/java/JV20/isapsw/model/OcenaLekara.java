package JV20.isapsw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OcenaLekara {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Lekar ocenjeniLekar;
    private Pacijent pacijentOcjenjivac;
    private int ocjena;

    public Lekar getOcenjeniLekar() {
        return ocenjeniLekar;
    }

    public void setOcenjeniLekar(Lekar ocenjeniLekar) {
        this.ocenjeniLekar = ocenjeniLekar;
    }

    public Pacijent getPacijentOcjenjivac() {
        return pacijentOcjenjivac;
    }

    public void setPacijentOcjenjivac(Pacijent pacijentOcjenjivac) {
        this.pacijentOcjenjivac = pacijentOcjenjivac;
    }

    public int getOcjena() {
        return ocjena;
    }

    public void setOcjena(int ocjena) {
        this.ocjena = ocjena;
    }

    public OcenaLekara(Lekar ocenjeniLekar, Pacijent pacijentOcjenjivac, int ocjena) {
        this.ocenjeniLekar = ocenjeniLekar;
        this.pacijentOcjenjivac = pacijentOcjenjivac;
        this.ocjena = ocjena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
