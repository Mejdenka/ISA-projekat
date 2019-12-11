package JV20.isapsw.repository;

import JV20.isapsw.model.Klinika;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface KlinikaRepository extends JpaRepository<Klinika, Long> {
    Optional<Klinika> findById(Long id);
    //Page<Klinika> findAll(Pageable pageable);
    List<Klinika> findAll();
    Klinika findByNaziv(String naziv);
    void deleteById(Long id);
}
