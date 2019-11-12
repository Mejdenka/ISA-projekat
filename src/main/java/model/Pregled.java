package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Pregled {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date datumPregleda;
    private int idPacijenta;
    private int idLekara;

    public Pregled() {}

    public Pregled(Date datumPregleda, int idPacijenta, int idLekara) {
        this.datumPregleda = datumPregleda;
        this.idPacijenta = idPacijenta;
        this.idLekara = idLekara;
    }

    public Date getDatumPregleda() {
        return datumPregleda;
    }

    public void setDatumPregleda(Date datumPregleda) {
        this.datumPregleda = datumPregleda;
    }

    public int getIdPacijenta() {
        return idPacijenta;
    }

    public void setIdPacijenta(int idPacijenta) {
        this.idPacijenta = idPacijenta;
    }

    public int getIdLekara() {
        return idLekara;
    }

    public void setIdLekara(int idLekara) {
        this.idLekara = idLekara;
    }
}
