package service;

import model.AdministratorKlinike;
import repository.AdministratorKlinikeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorKlinikeService {

    @Autowired
    private AdministratorKlinikeRepository akRepository;

    public AdministratorKlinike findOne(Long id) {
        return AdministratorKlinikeRepository.findById(id).orElseGet(null);
    }

    public List<AdministratorKlinike> findAll() {
        return AdministratorKlinikeRepository.findAll();
    }

    public Page<AdministratorKlinike> findAll(Pageable page) {
        return AdministratorKlinikeRepository.findAll(page);
    }

    public AdministratorKlinike save(AdministratorKlinike administratorKlinike) {
        return AdministratorKlinikeRepository.save(administratorKlinike);
    }

    public void remove(Long id) {
        AdministratorKlinikeRepository.deleteById(id);
    }
}
