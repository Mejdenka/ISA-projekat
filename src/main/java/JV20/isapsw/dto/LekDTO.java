package JV20.isapsw.dto;

import JV20.isapsw.model.Lek;

public class LekDTO {
    private String sifraLeka;
    private String nazivLeka;

    public LekDTO() {}

    public LekDTO(Lek lek) {
        this(lek.getSifraLeka(), lek.getNazivLeka());
    }

    public LekDTO(String sifraLeka, String nazivLeka) {
        this.sifraLeka = sifraLeka;
        this.nazivLeka = nazivLeka;
    }


}
