package model;

import java.util.ArrayList;
import java.util.Date;

public class Pacijent extends Korisnik {
    private ZdravstveniKarton karton;
    private ArrayList<Recept> recepti;

    public Pacijent() {}

    public Pacijent(int id, String ime, String prezime, Date datumRegistrovanja, Date datumRodjenja, String korisnickoIme, String lozinka, String email, ZdravstveniKarton karton) {
        super(id, ime, prezime, datumRegistrovanja, datumRodjenja, korisnickoIme, lozinka, email);
        this.karton = karton;
    }

    public Pacijent(int id, String ime, String prezime, Date datumRegistrovanja, Date datumRodjenja, String korisnickoIme, String lozinka, String email, ZdravstveniKarton karton, ArrayList<Recept> recepti) {
        super(id, ime, prezime, datumRegistrovanja, datumRodjenja, korisnickoIme, lozinka, email);
        this.karton = karton;
        this.recepti = recepti;
    }

    public ZdravstveniKarton getKarton() {
        return karton;
    }

    public void setKarton(ZdravstveniKarton karton) {
        this.karton = karton;
    }

    public ArrayList<Recept> getRecepti() {
        return recepti;
    }

    public void setRecepti(ArrayList<Recept> recepti) {
        this.recepti = recepti;
    }
}
