package JV20.isapsw.dto;

import JV20.isapsw.model.Dijagnoza;

public class DijagnozaDTO {
    private String sifraDijagnoze;
    private String nazivDijagnoze;

    public DijagnozaDTO(Dijagnoza dijagnoza) {
        this(dijagnoza.getSifraDijagnoze(), dijagnoza.getNazivDijagnoze());
    }

    public DijagnozaDTO(String sifraDijagnoze, String nazivDijagnoze) {
        this.sifraDijagnoze = sifraDijagnoze;
        this.nazivDijagnoze = nazivDijagnoze;
    }


    public String getSifraDijagnoze() {
        return sifraDijagnoze;
    }

    public String getNazivDijagnoze() {
        return nazivDijagnoze;
    }
}
