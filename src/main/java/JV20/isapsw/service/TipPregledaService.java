package JV20.isapsw.service;

import JV20.isapsw.model.TipPregleda;
import JV20.isapsw.repository.TipPregledaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipPregledaService {

    @Autowired
    private TipPregledaRepository tipPregledaRepository;

    public TipPregleda findOne(Long id) {
        return tipPregledaRepository.findById(id).orElseGet(null);
    }

    public TipPregleda findOneByNaziv(String naziv) { return tipPregledaRepository.findByNaziv(naziv);}

    public List<TipPregleda> findAll() {
        return tipPregledaRepository.findAll();
    }

    public List<String> findDistinct() {
        List<String> distinct = new ArrayList<>();

        for (TipPregleda tp : tipPregledaRepository.findAll())
        {
            if (!distinct.contains(tp.getNaziv())){
                   distinct.add(tp.getNaziv());
            }
        }

        return distinct;
    }

    public Page<TipPregleda> findAll(Pageable page) {
        return tipPregledaRepository.findAll(page);
    }

    public TipPregleda save(TipPregleda tipPregleda) {
        return tipPregledaRepository.save(tipPregleda);
    }

    public void remove(Long id) {
        tipPregledaRepository.deleteById(id);
    }
}
