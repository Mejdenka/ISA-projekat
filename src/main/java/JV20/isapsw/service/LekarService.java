package JV20.isapsw.service;

import JV20.isapsw.model.Lekar;
import JV20.isapsw.repository.LekarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LekarService {

    @Autowired
    private LekarRepository lekarRepository;

    public Lekar findOne(Long id) {
        return lekarRepository.findById(id).orElseGet(null);
    }

    public List<Lekar> findAll() {
        return lekarRepository.findAll();
    }

    public Page<Lekar> findAll(Pageable page) {
        return lekarRepository.findAll(page);
    }

    public Lekar save(Lekar lekar) {
        return lekarRepository.save(lekar);
    }

    public void remove(Long id) {
        lekarRepository.deleteById(id);
    }
}
