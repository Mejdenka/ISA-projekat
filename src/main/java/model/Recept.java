package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Recept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
