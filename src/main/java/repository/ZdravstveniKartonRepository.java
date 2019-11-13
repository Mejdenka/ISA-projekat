package repository;

import model.Pacijent;
import model.ZdravstveniKarton;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
public interface ZdravstveniKartonRepository extends JpaRepository<ZdravstveniKarton, Long> {
    //osnovne metode za repo
    ZdravstveniKarton findById(long id);
    Page<ZdravstveniKarton> findAll(Pageable pageable);
    List<ZdravstveniKarton> findAll();
    void removeById(long id);
    ZdravstveniKarton save(ZdravstveniKarton zdravstveniKarton);

    ZdravstveniKarton findByBrojZdravstvenogKartona(int broj);
    ZdravstveniKarton findByPacijent(Pacijent pacijent);
}