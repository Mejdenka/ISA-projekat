package JV20.isapsw.dto;

import JV20.isapsw.model.Lek;

public class LekDTO {
    private Long id;
    private Integer sifra;
    private String naziv;

    public LekDTO() {}

    public LekDTO(Lek lek) {
        this(lek.getId(), lek.getSifra(), lek.getNaziv());
    }

    public LekDTO(Long id, Integer sifra, String naziv) {
        this.id = id;
        this.sifra = sifra;
        this.naziv = naziv;
    }

    public Long getId() {
        return id;
    }

    public Integer getSifra() {
        return sifra;
    }

    public String getNaziv() {
        return naziv;
    }
}
