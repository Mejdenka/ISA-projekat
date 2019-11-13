package model;

import javax.persistence.*;
import java.rmi.server.UID;
import java.util.Set;
import java.util.Date;
import java.util.Set;

@Entity
public class Lekar extends Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ocena;
    @OneToMany
    private Set<Operacija> operacije;
    @OneToMany
    private Set<Pregled> pregledi;

    public Lekar() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public Set<Operacija> getOperacije() {
        return operacije;
    }

    public void setOperacije(Set<Operacija> operacije) {
        this.operacije = operacije;
    }

    public Set<Pregled> getPregledi() {
        return pregledi;
    }

    public void setPregledi(Set<Pregled> pregledi) {
        this.pregledi = pregledi;
    }
}