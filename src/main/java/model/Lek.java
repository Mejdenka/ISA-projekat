package model;

public class Lek {
    private int sifra;
    private String naziv;

    public Lek() {}

    public Lek(int sifra, String naziv) {
        this.sifra = sifra;
        this.naziv = naziv;
    }

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
