package JV20.isapsw.dto;

import JV20.isapsw.model.Termin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TerminDTO {
    private Long id;
    private String pocetak;
    private String kraj;
    //ako na frontu treba u formi yyyy-MM-dd
    private Date pocetakDate;
    private Date krajDate;

    public TerminDTO() {}

    public TerminDTO(Termin termin){
        this.id = termin.getId();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String strPocetak= formatter.format(termin.getPocetak());
        String strKraj= formatter.format(termin.getKraj());
        this.pocetak = strPocetak;
        this.kraj = strKraj;

        this.pocetakDate = termin.getPocetak();
        this.krajDate = termin.getKraj();
    }

    public TerminDTO(Long id, Date pocetak, Date kraj) {
        this.id = id;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String strPocetak= formatter.format(pocetak);
        String strKraj= formatter.format(kraj);
        this.pocetak = strPocetak;
        this.kraj = strKraj;
        this.pocetakDate = pocetak;
        this.krajDate = kraj;
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

    public Date getPocetakDate() {
        return pocetakDate;
    }

    public void setPocetakDate(Date pocetakDate) {
        this.pocetakDate = pocetakDate;
    }

    public Date getKrajDate() {
        return krajDate;
    }

    public void setKrajDate(Date krajDate) {
        this.krajDate = krajDate;
    }
}
