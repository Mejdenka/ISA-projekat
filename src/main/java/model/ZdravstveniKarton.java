package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.rmi.server.UID;
import java.util.ArrayList;

@Entity
public class ZdravstveniKarton {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int broj;
    private Pacijent pacijent;
    private ArrayList<Dijagnoza> dijagnoze;
    private ArrayList<Pregled> pregledi;

    public ZdravstveniKarton() {}

    public ZdravstveniKarton(int broj, Pacijent pacijent, ArrayList<Dijagnoza> dijagnoze, ArrayList<Pregled> pregledi) {
        this.broj = broj;
        this.pacijent = pacijent;
        this.dijagnoze = dijagnoze;
        this.pregledi = pregledi;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public ArrayList<Dijagnoza> getDijagnoze() {
        return dijagnoze;
    }

    public void setDijagnoze(ArrayList<Dijagnoza> dijagnoze) {
        this.dijagnoze = dijagnoze;
    }

    public ArrayList<Pregled> getPregledi() {
        return pregledi;
    }

    public void setPregledi(ArrayList<Pregled> pregledi) {
        this.pregledi = pregledi;
    }
}
