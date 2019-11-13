package JV20.isapsw.repository;

import JV20.isapsw.model.Pacijent;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;


public interface PacijentRepository extends JpaRepository<Pacijent, Long> {
    //osnovne metode za repo
    Optional<Pacijent> findById(Long id);
    Page<Pacijent> findAll(Pageable pageable);
    List<Pacijent> findAll();
    void removeById(Long id);
    Pacijent save(Pacijent pacijent);

    Pacijent findByBrojZdravstvenogKartona(int broj);
    List<Pacijent> findAllByLastName(String lastName);
    List<Pacijent> findByFirstNameAndLastName(String firstName, String lastName);

}
