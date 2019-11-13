package JV20.isapsw.dto;

import JV20.isapsw.model.Lek;
import JV20.isapsw.model.Recept;

import java.util.Set;

public class ReceptDTO {
    private Long id;
    private Set<LekDTO> lekovi;

    public ReceptDTO() {}

    public ReceptDTO(Recept recept){
        for (Lek lek: recept.getLekovi()) {
            LekDTO lDTO = new LekDTO(lek);
            lekovi.add(lDTO);
        }

        id = recept.getId();
    }

    public ReceptDTO(Long id, Set<LekDTO> lekovi) {
        this.id = id;
        this.lekovi = lekovi;
    }

    public Long getId() {
        return id;
    }

    public Set<LekDTO> getLekovi() {
        return lekovi;
    }
}
