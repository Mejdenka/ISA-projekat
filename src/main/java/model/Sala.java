package model;

import java.util.ArrayList;

public class Sala {
    private ArrayList<Pregled> pregledi;
    private ArrayList<Operacija> operacije;
    private ArrayList<Termin> termini;

    public Sala() {}

    public Sala(ArrayList<Pregled> pregledi, ArrayList<Operacija> operacije, ArrayList<Termin> termini) {
        this.pregledi = pregledi;
        this.operacije = operacije;
        this.termini = termini;
    }

    public ArrayList<Pregled> getPregledi() {
        return pregledi;
    }

    public void setPregledi(ArrayList<Pregled> pregledi) {
        this.pregledi = pregledi;
    }

    public ArrayList<Operacija> getOperacije() {
        return operacije;
    }

    public void setOperacije(ArrayList<Operacija> operacije) {
        this.operacije = operacije;
    }

    public ArrayList<Termin> getTermini() {
        return termini;
    }

    public void setTermini(ArrayList<Termin> termini) {
        this.termini = termini;
    }
}
