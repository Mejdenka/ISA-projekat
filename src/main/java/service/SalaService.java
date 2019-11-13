package service;

import model.Sala;
import repository.SalaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public Sala findOne(Long id) {
        return salaRepository.findById(id).orElseGet(null);
    }

    public List<Sala> findAll() {
        return salaRepository.findAll();
    }

    public Page<Sala> findAll(Pageable page) {
        return salaRepository.findAll(page);
    }

    public Sala save(Sala sala) {
        return salaRepository.save(sala);
    }

    public void remove(Long id) {
        salaRepository.deleteById(id);
    }
}
