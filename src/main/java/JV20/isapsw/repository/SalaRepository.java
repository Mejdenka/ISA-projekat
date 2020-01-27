package JV20.isapsw.repository;

import JV20.isapsw.model.Sala;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface SalaRepository extends JpaRepository<Sala, Long> {
    //osnovne metode za repo
    Optional<Sala> findById(Long id);
    Sala findByNaziv(String naziv);
    //Page<Sala> findAll(Pageable pageable);
    List<Sala> findAll();
    void deleteById(Long id);
    Sala save(Sala sala);

}
