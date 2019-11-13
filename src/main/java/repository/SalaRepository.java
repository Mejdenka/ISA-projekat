package repository;

import model.Pacijent;
import model.Sala;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface SalaRepository extends JpaRepository<Sala, Long> {
    //osnovne metode za repo
    Sala findById(long id);
    Page<Sala> findAll(Pageable pageable);
    List<Sala> findAll();
    void removeById(long id);
    Sala save(Sala sala);

}
