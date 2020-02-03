package JV20.isapsw.dto;

public class KlinikaDTO {
    private long id;
    private String naziv;
    private String lokacija;
    private String opis;

    public KlinikaDTO(String naziv, String lokacija, String opis) {
        this.naziv = naziv;
        this.lokacija = lokacija;
        this.opis = opis;
    }
}
