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

    private LekarDTO lekarGO;
    private LekarDTO lekarOds;

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
        if(godisnjiOdsustvoTermin.getLekarGO() != null)
            this.lekarGO = new LekarDTO(godisnjiOdsustvoTermin.getLekarGO());
        if(godisnjiOdsustvoTermin.getLekarOds() != null)
            this.lekarOds = new LekarDTO(godisnjiOdsustvoTermin.getLekarOds());
    }

    public GodisnjiOdsustvoTerminDTO(Long id, Date pocetak, Date kraj, boolean godisnji, boolean odsustvo) {
        this.id = id;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String strPocetak= formatter.format(pocetak);
        String strKraj= formatter.format(kraj);
        this.pocetak = strPocetak;
        this.kraj = strKraj;
        this.godisnji = godisnji;
        this.odsustvo = odsustvo;
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
}
