package model;

import java.util.ArrayList;

public class Recept {
    private ArrayList<Lek> lekovi;

    public Recept() {}

    public Recept(ArrayList<Lek> lekovi) {
        this.lekovi = lekovi;
    }

    public ArrayList<Lek> getLekovi() {
        return lekovi;
    }

    public void setLekovi(ArrayList<Lek> lekovi) {
        this.lekovi = lekovi;
    }
}
