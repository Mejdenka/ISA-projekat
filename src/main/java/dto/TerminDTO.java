package dto;

import model.Termin;

import java.util.Date;

public class TerminDTO {
    private Long id;
    private Date pocetak;
    private Date kraj;

    public TerminDTO() {}

    public TerminDTO(Termin termin){
        this(termin.getId(), termin.getPocetak(), termin.getKraj());
    }

    public TerminDTO(Long id, Date pocetak, Date kraj) {
        this.id = id;
        this.pocetak = pocetak;
        this.kraj = kraj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
