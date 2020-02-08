package JV20.isapsw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MedicinskaSestra extends Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Klinika klinikaMedSestre;

    @OneToMany(mappedBy = "medSestraGo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<GodisnjiOdsustvoTermin> rezervisaniGO;

    @OneToMany(mappedBy = "medSestraOds", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<GodisnjiOdsustvoTermin> rezervisanaOdustva;

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

    public Set<GodisnjiOdsustvoTermin> getRezervisaniGO() {
        return rezervisaniGO;
    }

    public void setRezervisaniGO(Set<GodisnjiOdsustvoTermin> rezervisaniGO) {
        this.rezervisaniGO = rezervisaniGO;
    }

    public Set<GodisnjiOdsustvoTermin> getRezervisanaOdustva() {
        return rezervisanaOdustva;
    }

    public void setRezervisanaOdustva(Set<GodisnjiOdsustvoTermin> rezervisanaOdustva) {
        this.rezervisanaOdustva = rezervisanaOdustva;
    }

}

