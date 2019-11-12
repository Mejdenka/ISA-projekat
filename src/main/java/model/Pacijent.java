package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Pacijent extends Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ZdravstveniKarton karton;
    private ArrayList<Recept> recepti;

    public Pacijent() {}

    public Pacijent(String ime, String prezime, Date datumRegistrovanja, Date datumRodjenja, String korisnickoIme, String lozinka, String email, ZdravstveniKarton karton) {
        super(ime, prezime, datumRegistrovanja, datumRodjenja, korisnickoIme, lozinka, email);
        this.karton = karton;
    }

    public Pacijent(String ime, String prezime, Date datumRegistrovanja, Date datumRodjenja, String korisnickoIme, String lozinka, String email, ZdravstveniKarton karton, ArrayList<Recept> recepti) {
        super(ime, prezime, datumRegistrovanja, datumRodjenja, korisnickoIme, lozinka, email);
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
