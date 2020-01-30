package JV20.isapsw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class MedicinskaSestra extends Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Klinika klinikaMedSestre;

    public MedicinskaSestra() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Klinika getKlinikaMedSestre() {
        return klinikaMedSestre;
    }

    public void setKlinikaMedSestre(Klinika klinikaMedSestre) {
        this.klinikaMedSestre = klinikaMedSestre;
    }
}

