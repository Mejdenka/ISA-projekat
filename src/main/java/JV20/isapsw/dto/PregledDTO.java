package JV20.isapsw.dto;

import JV20.isapsw.model.Klinika;
import JV20.isapsw.model.Pregled;
import JV20.isapsw.model.Sala;
import JV20.isapsw.model.TipPregleda;

public class PregledDTO {
    private Long id;
    private TerminDTO termin;
    private PacijentDTO pacijent;
    private LekarDTO lekar;
    private TipPregleda tipPregleda;
    private Klinika klinikaPregleda;
    private Sala sala;

    public PregledDTO() {}

    public PregledDTO(Pregled pregled){
        this(pregled.getId(),
                new TerminDTO(pregled.getTermin().getId(), pregled.getTermin().getPocetak(), pregled.getTermin().getKraj()),
                new PacijentDTO(pregled.getPacijent().getId(),pregled.getPacijent().getKorisnickoIme(), pregled.getPacijent().getIme(), pregled.getPacijent().getPrezime(), pregled.getPacijent().getEmail(), pregled.getPacijent().getLozinka(), pregled.getPacijent().getDatumRodjenja().toString(), pregled.getPacijent().getJbo()),
                new LekarDTO(pregled.getLekar().getId(), pregled.getLekar().getKorisnickoIme(), pregled.getLekar().getIme(), pregled.getLekar().getPrezime(), pregled.getLekar().getDatumRodjenja(), pregled.getLekar().getEmail(), pregled.getLekar().getOcena(), pregled.getLekar().isSlobodan(), pregled.getLekar().getRadnoVreme()),
                pregled.getTipPregleda(),
                new Klinika(pregled.getKlinikaPregleda().getId(), pregled.getKlinikaPregleda().getNaziv(), pregled.getKlinikaPregleda().getLokacija(), pregled.getKlinikaPregleda().getOpis()),
                new Sala(pregled.getSala().getId(), pregled.getSala().getNaziv(), pregled.getSala().getBroj(), pregled.getSala().isSlobodna(), pregled.getSala().isRezervisana()));
    }

    public PregledDTO(Long id, TerminDTO termin, PacijentDTO pacijent, LekarDTO lekar, TipPregleda tipPregleda, Klinika klinikaPregleda, Sala sala) {
        this.id = id;
        this.termin = termin;
        this.pacijent = pacijent;
        this.lekar = lekar;
        this.tipPregleda = tipPregleda;
        this.klinikaPregleda = klinikaPregleda;
        this.sala = sala;
    }

    public PregledDTO(Long id, TerminDTO termin, LekarDTO lekar, TipPregleda tipPregleda, Klinika klinikaPregleda, Sala sala) {
        this.id = id;
        this.termin = termin;
        this.lekar = lekar;
        this.tipPregleda = tipPregleda;
        this.klinikaPregleda = klinikaPregleda;
        this.sala = sala;
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

    public TipPregleda getTipPregleda() {
        return tipPregleda;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTermin(TerminDTO termin) {
        this.termin = termin;
    }

    public void setPacijent(PacijentDTO pacijent) {
        this.pacijent = pacijent;
    }

    public void setLekar(LekarDTO lekar) {
        this.lekar = lekar;
    }

    public void setTipPregleda(TipPregleda tipPregleda) {
        this.tipPregleda = tipPregleda;
    }

    public Klinika getKlinikaPregleda() {
        return klinikaPregleda;
    }

    public void setKlinikaPregleda(Klinika klinikaPregleda) {
        this.klinikaPregleda = klinikaPregleda;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
