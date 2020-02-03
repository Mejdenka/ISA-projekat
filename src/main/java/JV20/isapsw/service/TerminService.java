package JV20.isapsw.service;

import JV20.isapsw.model.Klinika;
import JV20.isapsw.model.Termin;
import JV20.isapsw.repository.TerminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminService {

    @Autowired
    private TerminRepository terminRepository;

    public Termin findOne(Long id) {
        return terminRepository.findById(id).orElseGet(null);
    }

    public List<Termin> findAll() {
        return terminRepository.findAll();
    }

    public Page<Termin> findAll(Pageable page) {
        return terminRepository.findAll(page);
    }

    public Termin save(Termin termin) {
        return terminRepository.save(termin);
    }

    public Termin rezervisi(Termin termin, Klinika klinika) {
        termin.setRezervisan(true);
        termin.setKlinikaTermina(klinika);
        return terminRepository.save(termin);
    }

    public void remove(Long id) {
        terminRepository.deleteById(id);
    }
}
