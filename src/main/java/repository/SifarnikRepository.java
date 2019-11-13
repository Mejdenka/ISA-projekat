package repository;

import model.Sifarnik;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface SifarnikRepository extends JpaRepository<Sifarnik, Long> {
        //osnovne metode za repo
        Sifarnik findById(long id);
        Page<Sifarnik> findAll(Pageable pageable);
        List<Sifarnik> findAll();
        void removeById(long id);
        Sifarnik save(Sifarnik sifarnik);

}
