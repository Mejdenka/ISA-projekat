package repository;

import model.Pacijent;
import model.ZdravstveniKarton;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;


public interface PacijentRepository extends JpaRepository<Pacijent, Long> {
    //osnovne metode za repo
    Pacijent findById(long id);
    Page<Pacijent> findAll(Pageable pageable);
    List<Pacijent> findAll();
    void removeById(long id);
    Pacijent save(Pacijent pacijent);

    Pacijent findByBrojZdravstvenogKartona(int broj);
    List<Pacijent> findAllByLastName(String lastName);
    List<Pacijent> findByFirstNameAndLastName(String firstName, String lastName);

}
