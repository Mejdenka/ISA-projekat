package model;

import java.rmi.server.UID;

public class Operacija {
    private UID idPacijenta;
    private UID idLekara;
    private Termin termin;

    public Operacija() {}

    public Operacija(UID idPacijenta, UID idLekara, Termin termin) {
        this.idPacijenta = idPacijenta;
        this.idLekara = idLekara;
        this.termin = termin;
    }

    public UID getIdPacijenta() {
        return idPacijenta;
    }

    public void setIdPacijenta(UID idPacijenta) {
        this.idPacijenta = idPacijenta;
    }

    public UID getIdLekara() {
        return idLekara;
    }

    public void setIdLekara(UID idLekara) {
        this.idLekara = idLekara;
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }
}
