package JV20.isapsw.repository;

import JV20.isapsw.model.Lekar;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface LekarRepository extends JpaRepository<Lekar, Long> {
    Optional<Lekar> findById(Long id);
    Page<Lekar> findAll(Pageable pageable);
    List<Lekar> findAll();
    void deleteById(Long id);
}
