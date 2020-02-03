package JV20.isapsw.service;

import JV20.isapsw.model.*;
import JV20.isapsw.repository.PregledRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PregledService {

    @Autowired
    private PregledRepository pregledRepository;

    public Pregled findOne(Long id) {
        return pregledRepository.findById(id).orElseGet(null);
    }

    public List<Pregled> findAll() {
        return pregledRepository.findAll();
    }

    public Page<Pregled> findAll(Pageable page) {
        return pregledRepository.findAll(page);
    }

    public Pregled save(Pregled pregled) {
        return pregledRepository.save(pregled);
    }

    public Pregled saveNew(Termin termin, Pacijent pacijent, Lekar lekar, TipPregleda tipPregleda) {
        Pregled pregled = new Pregled() ;
        pregled.setTermin(termin);
        pregled.setKlinikaPregleda(termin.getKlinikaTermina());
        pregled.setPacijent(pacijent);
        pregled.setLekar(lekar);
        pregled.setTipPregleda(tipPregleda);
        lekar.getPregledi().add(pregled);
        pacijent.getPregledi().add(pregled);

        return pregledRepository.save(pregled);
    }

    public void remove(Long id) {
        pregledRepository.deleteById(id);
    }

}
