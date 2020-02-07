package JV20.isapsw.service;


import JV20.isapsw.model.Lokacija;
import JV20.isapsw.repository.LokacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LokacijaService {

    @Autowired
    private LokacijaRepository lokacijaRepository;

    public Lokacija findOne(Long id) {
        return lokacijaRepository.findById(id).orElseGet(null);
    }

    public List<Lokacija> findAll() {
        return lokacijaRepository.findAll();
    }


    public Lokacija save(Lokacija lokacija) {
        return lokacijaRepository.save(lokacija);
    }

    public void remove(Long id) {
        lokacijaRepository.deleteById(id);
    }
}
