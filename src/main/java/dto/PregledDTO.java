package dto;

import model.Pregled;

public class PregledDTO {
    private Long id;
    private TerminDTO termin;
    private PacijentDTO pacijent;
    private LekarDTO lekar;

    public PregledDTO() {}

    public PregledDTO(Pregled pregled){
        this(pregled.getId(),
                new TerminDTO(pregled.getTermin().getId(), pregled.getTermin().getPocetak(), pregled.getTermin().getKraj()),
                new PacijentDTO(pregled.getPacijent().getId(), pregled.getPacijent().getIme(), pregled.getPacijent().getPrezime(), pregled.getPacijent().getDatumRodjenja()),
                new LekarDTO(pregled.getLekar().getId(), pregled.getLekar().getIme(), pregled.getLekar().getPrezime(), pregled.getLekar().getDatumRodjenja(), pregled.getLekar().getOcena())
                );
    }

    public PregledDTO(Long id, TerminDTO termin, PacijentDTO pacijent, LekarDTO lekar) {
        this.id = id;
        this.termin = termin;
        this.pacijent = pacijent;
        this.lekar = lekar;
    }

    public Long getId() {
        return id;
    }

    public TerminDTO getTermin() {
        return termin;
    }

    public PacijentDTO getPacijent() {
        return pacijent;
    }

    public LekarDTO getLekar() {
        return lekar;
    }
}
