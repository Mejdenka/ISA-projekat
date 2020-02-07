package JV20.isapsw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.awt.*;
import java.util.List;
import java.util.Set;

@Entity
public class Klinika {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;
    private String lokacija;
    private String opis;
    private Double prosecnaOcena;

    @OneToOne
    private Lokacija lokacijaNaMapi;

    @OneToMany(mappedBy = "klinikaAdmina", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<AdministratorKlinike> adminiKlinike;

    @OneToMany(mappedBy = "klinikaLekara", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Lekar> lekari;

    @OneToMany(mappedBy = "klinikaSale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Sala> sale;

    @OneToMany(mappedBy = "klinika", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TipPregleda> tipoviPregleda;

    @OneToMany(mappedBy = "klinikaTermina", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Termin> slobodniTermini;

    @OneToMany(mappedBy = "klinikaPregleda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Pregled> pregledi;

    @OneToMany(mappedBy = "klinikaPacijenta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Pacijent> pacijenti;

    @OneToMany(mappedBy = "klinikaMedSestre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MedicinskaSestra> medicinskeSestre;

    @OneToMany(mappedBy = "klinikaOperacije", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Operacija> operacije;

    @OneToMany(mappedBy = "klinikaRecept", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Recept> recepti;



    public Klinika() {}

    public Klinika(Long id, String naziv, String lokacija, String opis) {
        this.id = id;
        this.naziv = naziv;
        this.lokacija = lokacija;
        this.opis = opis;
    }

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

    public Double getProsecnaOcena() {
        return prosecnaOcena;
    }

    public void setProsecnaOcena(Double prosecnaOcena) {
        this.prosecnaOcena = prosecnaOcena;
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

    public List<TipPregleda> getTipoviPregleda() {
        return tipoviPregleda;
    }

    public void setTipoviPregleda(List<TipPregleda> tipoviPregleda) {
        this.tipoviPregleda = tipoviPregleda;
    }

    public List<Termin> getSlobodniTermini() {
        return slobodniTermini;
    }

    public void setSlobodniTermini(List<Termin> slobodniTermini) {
        this.slobodniTermini = slobodniTermini;
    }

    public List<Pregled> getPregledi() {
        return pregledi;
    }

    public void setPregledi(List<Pregled> pregledi) {
        this.pregledi = pregledi;
    }

    public List<Pacijent> getPacijenti() {
        return pacijenti;
    }

    public void setPacijenti(List<Pacijent> pacijenti) {
        this.pacijenti = pacijenti;
    }

    public List<MedicinskaSestra> getMedicinskeSestre() {
        return medicinskeSestre;
    }

    public void setMedicinskeSestre(List<MedicinskaSestra> medicinskeSestre) {
        this.medicinskeSestre = medicinskeSestre;
    }

    public List<Operacija> getOperacije() {
        return operacije;
    }

    public void setOperacije(List<Operacija> operacije) {
        this.operacije = operacije;
    }

 overa_recepta_izmena_izvestaja
    public List<Recept> getRecepti() {
        return recepti;
    }

    public void setRecepti(List<Recept> recepti) {
        this.recepti = recepti;

    public Lokacija getLokacijaNaMapi() {
        return lokacijaNaMapi;
    }

    public void setLokacijaNaMapi(Lokacija lokacijaNaMapi) {
        this.lokacijaNaMapi = lokacijaNaMapi;
    }

    public List<AdministratorKlinike> getAdminiKlinike() {
        return adminiKlinike;
    }

    public void setAdminiKlinike(List<AdministratorKlinike> adminiKlinike) {
        this.adminiKlinike = adminiKlinike;
    }
}
