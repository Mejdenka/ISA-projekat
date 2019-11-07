package model;

import java.rmi.server.UID;
import java.util.ArrayList;

public class ZdravstveniKarton {
    private int broj;
    private UID idPacijenta;
    private ArrayList<UID> idDijagnoza;
    private ArrayList<UID> idPregleda;

    public ZdravstveniKarton() {}

    public ZdravstveniKarton(int broj, UID idPacijenta, ArrayList<UID> idDijagnoza, ArrayList<UID> idPregleda) {
        this.broj = broj;
        this.idPacijenta = idPacijenta;
        this.idDijagnoza = idDijagnoza;
        this.idPregleda = idPregleda;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public UID getIdPacijenta() {
        return idPacijenta;
    }

    public void setIdPacijenta(UID idPacijenta) {
        this.idPacijenta = idPacijenta;
    }

    public ArrayList<UID> getIdDijagnoza() {
        return idDijagnoza;
    }

    public void setIdDijagnoza(ArrayList<UID> idDijagnoza) {
        this.idDijagnoza = idDijagnoza;
    }

    public ArrayList<UID> getIdPregleda() {
        return idPregleda;
    }

    public void setIdPregleda(ArrayList<UID> idPregleda) {
        this.idPregleda = idPregleda;
    }
}
