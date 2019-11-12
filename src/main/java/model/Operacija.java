package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.rmi.server.UID;

@Entity
public class Operacija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Pacijent pacijent;
    private Lekar lekar;
    private Termin termin;

    public Operacija() {}

    public Operacija(Pacijent pacijent, Lekar lekar, Termin termin) {
        this.pacijent = pacijent;
        this.lekar = lekar;
        this.termin = termin;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }
}
