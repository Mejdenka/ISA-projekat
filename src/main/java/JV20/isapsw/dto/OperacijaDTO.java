package JV20.isapsw.dto;

import JV20.isapsw.model.Operacija;

public class OperacijaDTO {
    private Long id;
    private PacijentDTO pacijent;
    private LekarDTO lekar;
    private TerminDTO termin;

    public OperacijaDTO() {}

    public OperacijaDTO(Operacija operacija){
        this(operacija.getId(),
                new PacijentDTO(operacija.getPacijent().getId(),operacija.getPacijent().getKorisnickoIme(), operacija.getPacijent().getIme(), operacija.getPacijent().getPrezime(), operacija.getPacijent().getEmail(), operacija.getPacijent().getLozinka(), operacija.getPacijent().getDatumRodjenja().toString(), operacija.getPacijent().getJbo()),
                new LekarDTO(operacija.getLekar().getId(), operacija.getLekar().getKorisnickoIme(), operacija.getLekar().getIme(), operacija.getLekar().getPrezime(), operacija.getLekar().getDatumRodjenja(), operacija.getLekar().getEmail() ,operacija.getLekar().getZbirOcena(), operacija.getLekar().getBrojOcena(), operacija.getLekar().isSlobodan(), operacija.getLekar().getRadnoVreme()),
                new TerminDTO(operacija.getTermin().getId(), operacija.getTermin().getPocetak(), operacija.getTermin().getKraj()));
    }

    public OperacijaDTO(Long id, PacijentDTO pacijent, LekarDTO lekar, TerminDTO termin) {
        this.id = id;
        this.pacijent = pacijent;
        this.lekar = lekar;
        this.termin = termin;
    }

    public Long getId() {
        return id;
    }

    public PacijentDTO getPacijent() {
        return pacijent;
    }

    public LekarDTO getLekar() {
        return lekar;
    }

    public TerminDTO getTermin() {
        return termin;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPacijent(PacijentDTO pacijent) {
        this.pacijent = pacijent;
    }

    public void setLekar(LekarDTO lekar) {
        this.lekar = lekar;
    }

    public void setTermin(TerminDTO termin) {
        this.termin = termin;
    }
}
