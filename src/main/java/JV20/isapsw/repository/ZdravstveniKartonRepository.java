package JV20.isapsw.repository;

import JV20.isapsw.model.Pacijent;
import JV20.isapsw.model.ZdravstveniKarton;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface ZdravstveniKartonRepository extends JpaRepository<ZdravstveniKarton, Long> {
    //osnovne metode za repo
    Optional<ZdravstveniKarton> findById(Long id);
    //Page<ZdravstveniKarton> findAll(Pageable pageable);
    List<ZdravstveniKarton> findAll();
    void deleteById(Long id);
    ZdravstveniKarton save(ZdravstveniKarton zdravstveniKarton);
}