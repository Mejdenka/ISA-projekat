package JV20.isapsw.dto;

import JV20.isapsw.model.Operacija;
import JV20.isapsw.model.Pregled;
import JV20.isapsw.model.Sala;
import JV20.isapsw.model.Termin;

import java.util.Set;

public class SalaDTO {
    private Long id;
    private Set<PregledDTO> pregledi;
    private Set<OperacijaDTO> operacije;
    private Set<TerminDTO> termini;

    public SalaDTO() {}

    public SalaDTO(Sala sala){
        id = sala.getId();

        for (Pregled pregled: sala.getPregledi()) {
            PregledDTO pDTO = new PregledDTO(pregled);
            pregledi.add(pDTO);
        }

        for (Operacija operacija: sala.getOperacije()) {
            OperacijaDTO oDTO = new OperacijaDTO(operacija);
            operacije.add(oDTO);
        }

        for (Termin termin: sala.getTermini()) {
            TerminDTO tDTO = new TerminDTO(termin);
            termini.add(tDTO);
        }
    }

    public SalaDTO(Long id, Set<PregledDTO> pregledi, Set<OperacijaDTO> operacije, Set<TerminDTO> termini){
        this.id = id;
        this.pregledi = pregledi;
        this.operacije = operacije;
        this.termini = termini;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<PregledDTO> getPregledi() {
        return pregledi;
    }

    public void setPregledi(Set<PregledDTO> pregledi) {
        this.pregledi = pregledi;
    }

    public Set<OperacijaDTO> getOperacije() {
        return operacije;
    }

    public void setOperacije(Set<OperacijaDTO> operacije) {
        this.operacije = operacije;
    }

    public Set<TerminDTO> getTermini() {
        return termini;
    }

    public void setTermini(Set<TerminDTO> termini) {
        this.termini = termini;
    }
}
