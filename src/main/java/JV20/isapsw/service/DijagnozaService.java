package JV20.isapsw.service;

import JV20.isapsw.model.Dijagnoza;
import JV20.isapsw.model.Lek;
import JV20.isapsw.repository.DijagnozaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DijagnozaService {

    @Autowired
    private DijagnozaRepository dijagnozaRepository;

    public Dijagnoza findOne(Long id) {
        return dijagnozaRepository.findById(id).orElseGet(null);
    }

    public List<Dijagnoza> findAll() {
        return dijagnozaRepository.findAll();
    }

    public Page<Dijagnoza> findAll(Pageable page) {
        return dijagnozaRepository.findAll(page);
    }

    public Dijagnoza save(Dijagnoza dijagnoza) {
        return dijagnozaRepository.save(dijagnoza);
    }

    public void remove(Long id) {
        dijagnozaRepository.deleteById(id);
    }

    public Dijagnoza findBySifraDijagnoze(String sifraDijagnoze) {return dijagnozaRepository.findBySifraDijagnoze(sifraDijagnoze);}
}
