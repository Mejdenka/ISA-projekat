package service;

import model.MedicinskaSestra;
import repository.MedicinskaSestraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicinskaSestraService {

    @Autowired
    private MedicinskaSestraRepository medicinskaSestraRepository;

    public MedicinskaSestra findOne(Long id) {
        return medicinskaSestraRepository.findById(id).orElseGet(null);
    }

    public List<MedicinskaSestra> findAll() {
        return medicinskaSestraRepository.findAll();
    }

    public Page<MedicinskaSestra> findAll(Pageable page) {
        return medicinskaSestraRepository.findAll(page);
    }

    public MedicinskaSestra save(MedicinskaSestra medicinskaSestra) {
        return medicinskaSestraRepository.save(medicinskaSestra);
    }

    public void remove(Long id) {
        medicinskaSestraRepository.deleteById(id);
    }
}
