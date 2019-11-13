package repository;

import model.Pacijent;
import model.Termin;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

public interface TerminRepository extends JpaRepository<Termin, Long> {
    //osnovne metode za repo
    Termin findById(long id);
    Page<Termin> findAll(Pageable pageable);
    List<Termin> findAll();
    void removeById(long id);
    Termin save(Termin termin);

    Termin findByPocetak(Date pocetak);

}
