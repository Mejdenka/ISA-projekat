package JV20.isapsw.repository;

import JV20.isapsw.model.Lek;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LekRepository extends JpaRepository<Lek, Long> {
    Optional<Lek> findById(Long id);
    //Page<Lek> findAll(Pageable pageable);
    List<Lek> findAll();
    void deleteById(Long id);
    Lek findByNazivLeka(String nazivLeka);
    Lek findBySifraLeka(String sifraLeka);
}
