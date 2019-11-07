package model;

import java.util.Date;

public class Pregled {
    private int id;
    private Date datumPregleda;
    private int idPacijenta;
    private int idLekara;

    public Pregled() {}

    public Pregled(int id, Date datumPregleda, int idPacijenta, int idLekara) {
        this.id = id;
        this.datumPregleda = datumPregleda;
        this.idPacijenta = idPacijenta;
        this.idLekara = idLekara;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
