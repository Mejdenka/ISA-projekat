package dto;

import model.Dijagnoza;

public class DijagnozaDTO {
    private Long id;
    private Integer sifra;
    private String naziv;

    public DijagnozaDTO(Dijagnoza dijagnoza) {
        this(dijagnoza.getId(), dijagnoza.getSifra(), dijagnoza.getNaziv());
    }

    public DijagnozaDTO(Long id, Integer sifra, String naziv) {
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
