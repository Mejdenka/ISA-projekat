package JV20.isapsw.service;

import JV20.isapsw.model.Lekar;
import JV20.isapsw.model.Operacija;
import JV20.isapsw.model.Pacijent;
import JV20.isapsw.model.Termin;
import JV20.isapsw.repository.OperacijaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperacijaService {

    @Autowired
    private OperacijaRepository operacijaRepository;


    public Operacija findOne(Long id) {
        return operacijaRepository.findById(id).orElseGet(null);
    }

    public List<Operacija> findAll() {
        return operacijaRepository.findAll();
    }

    public Page<Operacija> findAll(Pageable page) {
        return operacijaRepository.findAll(page);
    }

    public Operacija save(Operacija operacija) {
        return operacijaRepository.save(operacija);
    }

    public Operacija saveNew(Termin termin, Pacijent pacijent, Lekar lekar) {
        Operacija operacija = new Operacija();
        operacija.setTermin(termin);
        operacija.setKlinikaOperacije(termin.getKlinikaTermina());
        operacija.setPacijent(pacijent);
        operacija.setLekar(lekar);
        lekar.getOperacije().add(operacija);
        pacijent.getOperacije().add(operacija);
        return operacijaRepository.save(operacija);
    }

    public void remove(Long id) {
        operacijaRepository.deleteById(id);
    }
}
