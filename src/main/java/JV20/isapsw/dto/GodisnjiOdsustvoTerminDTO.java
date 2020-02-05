package JV20.isapsw.dto;

import JV20.isapsw.model.GodisnjiOdsustvoTermin;
import JV20.isapsw.model.Termin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GodisnjiOdsustvoTerminDTO {
    private Long id;
    private String pocetak;
    private String kraj;

    private boolean godisnji;
    private boolean odsustvo;
    private boolean odobren;

    private LekarDTO lekarGO;
    private LekarDTO lekarOds;
    private MedicinskaSestraDTO medSestraGo;
    private MedicinskaSestraDTO medSestraOds;

    //za slanje maile kupi poruku sa fronta samo
    private String razlogOdbijanja;

    public GodisnjiOdsustvoTerminDTO() {}

    public GodisnjiOdsustvoTerminDTO(GodisnjiOdsustvoTermin godisnjiOdsustvoTermin){
        this.id = godisnjiOdsustvoTermin.getId();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String strPocetak= formatter.format(godisnjiOdsustvoTermin.getPocetak());
        String strKraj= formatter.format(godisnjiOdsustvoTermin.getKraj());
        this.pocetak = strPocetak;
        this.kraj = strKraj;
        this.godisnji = godisnjiOdsustvoTermin.isGodisnji();
        this.odsustvo = godisnjiOdsustvoTermin.isOdsustvo();
        this.odobren = godisnjiOdsustvoTermin.isOdobren();
        if(godisnjiOdsustvoTermin.getLekarGO() != null)
            this.lekarGO = new LekarDTO(godisnjiOdsustvoTermin.getLekarGO());
        if(godisnjiOdsustvoTermin.getLekarOds() != null)
            this.lekarOds = new LekarDTO(godisnjiOdsustvoTermin.getLekarOds());
        if(godisnjiOdsustvoTermin.getMedSestraGo() != null)
            this.medSestraGo = new MedicinskaSestraDTO(godisnjiOdsustvoTermin.getMedSestraGo());
        if(godisnjiOdsustvoTermin.getMedSestraOds() != null)
            this.medSestraOds = new MedicinskaSestraDTO(godisnjiOdsustvoTermin.getMedSestraOds());
    }

    public GodisnjiOdsustvoTerminDTO(Long id, String pocetak, String kraj, boolean godisnji, boolean odsustvo, boolean odobren) {
        this.id = id;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.godisnji = godisnji;
        this.odsustvo = odsustvo;
        this.odobren = odobren;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPocetak() {
        return pocetak;
    }

    public void setPocetak(String pocetak) {
        this.pocetak = pocetak;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
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

    public LekarDTO getLekarGO() {
        return lekarGO;
    }

    public void setLekarGO(LekarDTO lekarGO) {
        this.lekarGO = lekarGO;
    }

    public LekarDTO getLekarOds() {
        return lekarOds;
    }

    public void setLekarOds(LekarDTO lekarOds) {
        this.lekarOds = lekarOds;
    }

    public MedicinskaSestraDTO getMedSestraGo() {
        return medSestraGo;
    }

    public void setMedSestraGo(MedicinskaSestraDTO medSestraGo) {
        this.medSestraGo = medSestraGo;
    }

    public MedicinskaSestraDTO getMedSestraOds() {
        return medSestraOds;
    }

    public void setMedSestraOds(MedicinskaSestraDTO medSestraOds) {
        this.medSestraOds = medSestraOds;
    }

    public boolean isOdobren() {
        return odobren;
    }

    public void setOdobren(boolean odobren) {
        this.odobren = odobren;
    }

    public String getRazlogOdbijanja() {
        return razlogOdbijanja;
    }

    public void setRazlogOdbijanja(String razlogOdbijanja) {
        this.razlogOdbijanja = razlogOdbijanja;
    }
}
