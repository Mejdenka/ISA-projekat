package dto;

import model.Operacija;
import model.Pacijent;

public class OperacijaDTO {
    private Long id;
    private PacijentDTO pacijent;
    private LekarDTO lekar;
    private TerminDTO termin;

    public OperacijaDTO() {}

    public OperacijaDTO(Operacija operacija){
        this(operacija.getId(),
                new PacijentDTO(operacija.getPacijent().getId(), operacija.getPacijent().getIme(), operacija.getPacijent().getPrezime(), operacija.getPacijent().getDatumRodjenja()),
                new LekarDTO(operacija.getLekar().getId(), operacija.getLekar().getIme(), operacija.getLekar().getPrezime(), operacija.getLekar().getDatumRodjenja(), operacija.getLekar().getOcena()),
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
}
