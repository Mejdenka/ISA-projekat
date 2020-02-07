package JV20.isapsw.model;

import javax.persistence.*;

@Entity
public class Lokacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double lon;
    private  Double lat;
    private String brojUlice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getBrojUlice() {
        return brojUlice;
    }

    public void setBrojUlice(String brojUlice) {
        this.brojUlice = brojUlice;
    }
}
