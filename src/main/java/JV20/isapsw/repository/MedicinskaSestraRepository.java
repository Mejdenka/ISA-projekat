package JV20.isapsw.repository;

import JV20.isapsw.model.MedicinskaSestra;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface MedicinskaSestraRepository extends JpaRepository<MedicinskaSestra, Long> {
    Optional<MedicinskaSestra> findById(Long id);
    Page<MedicinskaSestra> findAll(Pageable pageable);
    List<MedicinskaSestra> findAll();
    void deleteById(Long id);
}
