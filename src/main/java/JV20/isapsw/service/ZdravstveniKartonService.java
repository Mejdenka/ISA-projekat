package JV20.isapsw.service;

import JV20.isapsw.model.ZdravstveniKarton;
import JV20.isapsw.repository.ZdravstveniKartonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZdravstveniKartonService {

    @Autowired
    private ZdravstveniKartonRepository zdravstveniKartonRepository;

    public ZdravstveniKarton findOne(Long id) {
        return zdravstveniKartonRepository.findById(id).orElseGet(null);
    }

    public List<ZdravstveniKarton> findAll() {
        return zdravstveniKartonRepository.findAll();
    }

    public Page<ZdravstveniKarton> findAll(Pageable page) {
        return zdravstveniKartonRepository.findAll(page);
    }

    public ZdravstveniKarton save(ZdravstveniKarton zdravstveniKarton) {
        return zdravstveniKartonRepository.save(zdravstveniKarton);
    }

    public void remove(Long id) {
        zdravstveniKartonRepository.deleteById(id);
    }
}
