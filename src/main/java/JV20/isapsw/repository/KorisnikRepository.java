package JV20.isapsw.repository;

import JV20.isapsw.model.Korisnik;
import JV20.isapsw.model.Pacijent;
import JV20.isapsw.service.KorisnikService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
    Optional<Korisnik> findById(Long id);
    //Page<Korisnik> findAll(Pageable pageable);
    List<Korisnik> findAll();
    void deleteById(Long id);
    Korisnik findByKorisnickoIme(String korisnickoIme);
    Korisnik findByEmail(String email);
    Korisnik save(Korisnik korisnik);


}
