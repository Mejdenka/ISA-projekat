package JV20.isapsw.service;

import JV20.isapsw.model.Sifarnik;
import JV20.isapsw.repository.SifarnikRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SifarnikService {

    @Autowired
    private SifarnikRepository sifarnikRepository;

    public Sifarnik findOne(Long id) {
        return sifarnikRepository.findById(id).orElseGet(null);
    }

    public List<Sifarnik> findAll() {
        return sifarnikRepository.findAll();
    }

    public Page<Sifarnik> findAll(Pageable page) {
        return sifarnikRepository.findAll(page);
    }

    public Sifarnik save(Sifarnik sifarnik) {
        return sifarnikRepository.save(sifarnik);
    }

    public void remove(Long id) {
        sifarnikRepository.deleteById(id);
    }
}
