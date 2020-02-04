package JV20.isapsw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Lek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sifraLeka;
    private String nazivLeka;

    public Lek() {}

    public Lek(String sifraLeka, String nazivLeka) {
        this.sifraLeka = sifraLeka;
        this.nazivLeka = nazivLeka;
    }

    public Lek(Lek lek){
        this.sifraLeka = lek.sifraLeka;
        this.nazivLeka = lek.nazivLeka;
    }

    public String getSifraLeka() {
        return sifraLeka;
    }

    public void setSifraLeka(String sifraLeka) {
        this.sifraLeka = sifraLeka;
    }

    public String getNazivLeka() {
        return nazivLeka;
    }

    public void setNazivLeka(String nazivLeka) {
        this.nazivLeka = nazivLeka;
    }
}
