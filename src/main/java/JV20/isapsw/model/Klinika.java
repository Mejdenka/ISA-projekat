package JV20.isapsw.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.awt.*;
import java.util.List;
import java.util.Set;

@Entity
public class Klinika {
    //**************************************TREBA JOS CJENOVNIK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;
    private String lokacija;
    private String opis;
    private int brLekara;
    private int brSala;
    private String imgPath;

    @OneToMany(mappedBy = "klinika", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Lekar> lekari;

    @OneToMany(mappedBy = "klinika", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sala> sale;

    @OneToMany(mappedBy = "klinika", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Termin> termini;

    public Klinika() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public int getBrLekara() {
        return brLekara;
    }

    public void setBrLekara(int brLekara) {
        this.brLekara = brLekara;
    }

    public int getBrSala() {
        return brSala;
    }

    public void setBrSala(int brSala) {
        this.brSala = brSala;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public List<Lekar> getLekari() {
        return lekari;
    }

    public void setLekari(List<Lekar> lekari) {
        this.lekari = lekari;
    }

    public List<Sala> getSale() {
        return sale;
    }

    public void setSale(List<Sala> sale) {
        this.sale = sale;
    }

    public List<Termin> getTermini() {
        return termini;
    }

    public void setTermini(List<Termin> termini) {
        this.termini = termini;
    }
}
