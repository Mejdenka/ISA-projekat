package JV20.isapsw.repository;

import JV20.isapsw.model.Recept;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface ReceptRepository extends JpaRepository<Recept, Long> {
    Optional<Recept> findById(Long id);
    //Page<Recept> findAll(Pageable pageable);
    List<Recept> findAll();
    void deleteById(Long id);
}
