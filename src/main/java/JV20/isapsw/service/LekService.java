package JV20.isapsw.service;

import JV20.isapsw.model.Lek;
import JV20.isapsw.repository.LekRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LekService {

    @Autowired
    private LekRepository lekRepository;

    public Lek findOne(Long id) {
        return lekRepository.findById(id).orElseGet(null);
    }

    public List<Lek> findAll() {
        return lekRepository.findAll();
    }

    public Page<Lek> findAll(Pageable page) {
        return lekRepository.findAll(page);
    }

    public Lek save(Lek lek) {
        return lekRepository.save(lek);
    }

    public void remove(Long id) {
        lekRepository.deleteById(id);
    }
}
