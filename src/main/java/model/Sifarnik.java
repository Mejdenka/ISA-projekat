package model;

import java.util.ArrayList;

public class Sifarnik {
    private ArrayList<Lek> lekovi;
    private ArrayList<Dijagnoza> dijagnoze;

    public Sifarnik() {}

    public Sifarnik(ArrayList<Lek> lekovi, ArrayList<Dijagnoza> dijagnoze) {
        this.lekovi = lekovi;
        this.dijagnoze = dijagnoze;
    }

    public ArrayList<Lek> getLekovi() {
        return lekovi;
    }

    public void setLekovi(ArrayList<Lek> lekovi) {
        this.lekovi = lekovi;
    }

    public ArrayList<Dijagnoza> getDijagnoze() {
        return dijagnoze;
    }

    public void setDijagnoze(ArrayList<Dijagnoza> dijagnoze) {
        this.dijagnoze = dijagnoze;
    }
}
