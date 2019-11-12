package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Lekar extends Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int ocena;
    private ArrayList<Operacija> operacije;
    private ArrayList<Pregled> pregledi;

    public Lekar(int ocena) {
        this.ocena = ocena;
    }

    public Lekar(String ime, String prezime, Date datumRegistrovanja, Date datumRodjenja, String korisnickoIme, String lozinka, String email, int ocena) {
        super(ime, prezime, datumRegistrovanja, datumRodjenja, korisnickoIme, lozinka, email);
        this.ocena = ocena;
    }


    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public ArrayList<Operacija> getOperacije() {
        return operacije;
    }

    public void setOperacije(ArrayList<Operacija> operacije) {
        this.operacije = operacije;
    }

    public ArrayList<Pregled> getPregledi() {
        return pregledi;
    }

    public void setPregledi(ArrayList<Pregled> pregledi) {
        this.pregledi = pregledi;
    }
}