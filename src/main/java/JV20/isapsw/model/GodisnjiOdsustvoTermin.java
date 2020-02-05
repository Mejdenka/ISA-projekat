package JV20.isapsw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class GodisnjiOdsustvoTermin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean godisnji;
    private boolean odsustvo;

    private Date pocetak;
    private Date kraj;

    private boolean obrisan;
    private boolean odobren;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Lekar lekarGO;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Lekar lekarOds;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private MedicinskaSestra medSestraGo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private MedicinskaSestra medSestraOds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isGodisnji() {
        return godisnji;
    }

    public void setGodisnji(boolean godisnji) {
        this.godisnji = godisnji;
    }

    public boolean isOdsustvo() {
        return odsustvo;
    }

    public void setOdsustvo(boolean odsustvo) {
        this.odsustvo = odsustvo;
    }

    public Date getPocetak() {
        return pocetak;
    }

    public void setPocetak(Date pocetak) {
        this.pocetak = pocetak;
    }

    public Date getKraj() {
        return kraj;
    }

    public void setKraj(Date kraj) {
        this.kraj = kraj;
    }

    public Lekar getLekarGO() {
        return lekarGO;
    }

    public void setLekarGO(Lekar lekarGO) {
        this.lekarGO = lekarGO;
    }

    public Lekar getLekarOds() {
        return lekarOds;
    }

    public void setLekarOds(Lekar lekarOds) {
        this.lekarOds = lekarOds;
    }

    public boolean isOdobren() {
        return odobren;
    }

    public void setOdobren(boolean odobren) {
        this.odobren = odobren;
    }

    public boolean isObrisan() {
        return obrisan;
    }

    public void setObrisan(boolean obrisan) {
        this.obrisan = obrisan;
    }

    public MedicinskaSestra getMedSestraGo() {
        return medSestraGo;
    }

    public void setMedSestraGo(MedicinskaSestra medSestraGo) {
        this.medSestraGo = medSestraGo;
    }

    public MedicinskaSestra getMedSestraOds() {
        return medSestraOds;
    }

    public void setMedSestraOds(MedicinskaSestra medSestraOds) {
        this.medSestraOds = medSestraOds;
    }
}
