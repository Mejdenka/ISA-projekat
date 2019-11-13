package JV20.isapsw.repository;

import JV20.isapsw.model.Sifarnik;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface SifarnikRepository extends JpaRepository<Sifarnik, Long> {
        //osnovne metode za repo
        Optional<Sifarnik> findById(Long id);
        Page<Sifarnik> findAll(Pageable pageable);
        List<Sifarnik> findAll();
        void deleteById(Long id);
        Sifarnik save(Sifarnik sifarnik);

}
