package JV20.isapsw.service;

import JV20.isapsw.model.Klinika;
import JV20.isapsw.repository.KlinikaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class KlinikaService {
    @Autowired
    private KlinikaRepository klinikaRepository;

    public Klinika findOne(Long id) {
        return klinikaRepository.findById(id).orElseGet(null);
    }

    public Klinika findByNaziv(String naziv) {return klinikaRepository.findByNaziv(naziv);}

    public List<Klinika> findAll() {
        return klinikaRepository.findAll();
    }

    public Klinika save(Klinika klinika) {
        return klinikaRepository.save(klinika);
    }

    public void remove(Long id) {
        klinikaRepository.deleteById(id);
    }
}
