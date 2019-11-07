package model;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Date;

public class Lekar extends Korisnik {
    private int ocena;
    private ArrayList<UID> operacije;
    private ArrayList<UID> pregledi;

    public Lekar(int ocena) {
        this.ocena = ocena;
    }

    public Lekar(UID id, String ime, String prezime, Date datumRegistrovanja, Date datumRodjenja, String korisnickoIme, String lozinka, String email, int ocena) {
        super(id, ime, prezime, datumRegistrovanja, datumRodjenja, korisnickoIme, lozinka, email);
        this.ocena = ocena;
    }


    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public ArrayList<UID> getOperacije() {
        return operacije;
    }

    public void setOperacije(ArrayList<UID> operacije) {
        this.operacije = operacije;
    }

    public ArrayList<UID> getPregledi() {
        return pregledi;
    }

    public void setPregledi(ArrayList<UID> pregledi) {
        this.pregledi = pregledi;
    }
}