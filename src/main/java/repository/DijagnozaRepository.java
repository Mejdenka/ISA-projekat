package repository;

import model.Dijagnoza;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface DijagnozaRepository extends JpaRepository<Dijagnoza, Long> {
    Optional<Dijagnoza> findById(Long id);
    Page<Dijagnoza> findAll(Pageable pageable);
    List<Dijagnoza> findAll();
    void deleteById(Long id);
}
