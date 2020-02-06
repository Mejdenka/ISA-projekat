package JV20.isapsw.service;

import JV20.isapsw.model.AdministratorKlinike;
import JV20.isapsw.repository.AdministratorKlinikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorKlinikeService {
    @Autowired
    private AdministratorKlinikeRepository akRepository;

    public AdministratorKlinike findOne(Long id){
        return akRepository.findById(id).orElseGet(null);
    }

    public List<AdministratorKlinike> findAll(){
        return akRepository.findAll();
    }

    public AdministratorKlinike save(AdministratorKlinike ak) {
        return akRepository.save(ak);
    }
}
