package JV20.isapsw.repository;

import JV20.isapsw.model.TipPregleda;
import JV20.isapsw.model.ZdravstveniKarton;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TipPregledaRepository extends JpaRepository<TipPregleda, Long> {
    //osnovne metode za repo
    Optional<TipPregleda> findById(Long id);
    List<TipPregleda> findAll();
    TipPregleda findByNaziv(String naziv);
    void deleteById(Long id);
    TipPregleda save(TipPregleda tipPregleda);
}
