package JV20.isapsw.repository;

import JV20.isapsw.model.Lokacija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LokacijaRepository extends JpaRepository<Lokacija, Long> {
    Optional<Lokacija> findById(Long id);
    //Page<Pacijent> findAll(Pageable pageable);
    List<Lokacija> findAll();
    void removeById(Long id);
    Lokacija save(Lokacija lokacija);
}
