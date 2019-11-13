package JV20.isapsw.repository;

import JV20.isapsw.model.Termin;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TerminRepository extends JpaRepository<Termin, Long> {
    //osnovne metode za repo
    Optional<Termin> findById(Long id);
    Page<Termin> findAll(Pageable pageable);
    List<Termin> findAll();
    void deleteById(Long id);
    Termin save(Termin termin);

    Termin findByPocetak(Date pocetak);

}
