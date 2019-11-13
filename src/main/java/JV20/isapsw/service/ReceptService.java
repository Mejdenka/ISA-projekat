package JV20.isapsw.service;

import JV20.isapsw.model.Recept;
import JV20.isapsw.repository.ReceptRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceptService {

    @Autowired
    private ReceptRepository receptRepository;

    public Recept findOne(Long id) {
        return receptRepository.findById(id).orElseGet(null);
    }

    public List<Recept> findAll() {
        return receptRepository.findAll();
    }

    public Page<Recept> findAll(Pageable page) {
        return receptRepository.findAll(page);
    }

    public Recept save(Recept recept) {
        return receptRepository.save(recept);
    }

    public void remove(Long id) {
        receptRepository.deleteById(id);
    }
}
