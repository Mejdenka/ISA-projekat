package JV20.isapsw.service;

import JV20.isapsw.model.AdministratorKlinickogCentra;
import JV20.isapsw.repository.AdministratorKlinickogCentraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorKlinickogCentraService {

    @Autowired
    private AdministratorKlinickogCentraRepository akcRepository;

    public AdministratorKlinickogCentra findOne(Long id){
        return akcRepository.findById(id).orElseGet(null);
    }

    public List<AdministratorKlinickogCentra> findAll(){
        return akcRepository.findAll();
    }

    public Page<AdministratorKlinickogCentra> findAll(Pageable page) {
        return akcRepository.findAll(page);
    }

    public AdministratorKlinickogCentra save(AdministratorKlinickogCentra akc) {
        return akcRepository.save(akc);
    }

    public void remove(Long id) {
        akcRepository.deleteById(id);
    }
}
