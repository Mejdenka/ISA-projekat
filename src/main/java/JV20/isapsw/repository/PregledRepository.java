package JV20.isapsw.repository;

import JV20.isapsw.model.Pregled;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface PregledRepository extends JpaRepository<Pregled, Long> {
    Optional<Pregled> findById(Long id);
    Page<Pregled> findAll(Pageable pageable);
    List<Pregled> findAll();
    void deleteById(Long id);
}
