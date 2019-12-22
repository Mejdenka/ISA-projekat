package JV20.isapsw.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Termin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date pocetak;
    private Date kraj;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Klinika klinika;

    public Termin() {}

    public Termin(Date pocetak, Date kraj) {
        this.pocetak = pocetak;
        this.kraj = kraj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPocetak() {
        return pocetak;
    }

    public void setPocetak(Date pocetak) {
        this.pocetak = pocetak;
    }

    public Date getKraj() {
        return kraj;
    }

    public void setKraj(Date kraj) {
        this.kraj = kraj;
    }

    public Klinika getKlinika() {
        return klinika;
    }

    public void setKlinika(Klinika klinika) {
        this.klinika = klinika;
    }
}
