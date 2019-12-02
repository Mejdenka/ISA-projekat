package JV20.isapsw.service;

import JV20.isapsw.model.Korisnik;
import JV20.isapsw.model.Pacijent;
import JV20.isapsw.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class KorisnikService{
    @Autowired
    private KorisnikRepository korisnikRepository;

    public Korisnik findOne(Long id) {
        return korisnikRepository.findById(id).orElseGet(null);
    }

    public Korisnik findOneByUsername(String korisnickoIme) {
        return korisnikRepository.findByKorisnickoIme(korisnickoIme);
    }

    public Korisnik findOneByEmail(String email) {
        return korisnikRepository.findByEmail(email);
    }

    public List<Korisnik> findAll() {
        return korisnikRepository.findAll();
    }

    public Page<Korisnik> findAll(Pageable page) {
        return korisnikRepository.findAll(page);
    }

    public Korisnik save(Korisnik korisnik) {
        return korisnikRepository.save(korisnik);
    }

    public void remove(Long id) {
        korisnikRepository.deleteById(id);
    }
}
