package JV20.isapsw.repository;

import JV20.isapsw.model.Operacija;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface OperacijaRepository extends JpaRepository<Operacija, Long> {
    Optional<Operacija> findById(Long id);
    Page<Operacija> findAll(Pageable pageable);
    List<Operacija> findAll();
    void deleteById(Long id);
}
