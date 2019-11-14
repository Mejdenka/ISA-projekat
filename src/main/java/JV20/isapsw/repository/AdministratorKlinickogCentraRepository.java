package JV20.isapsw.repository;

import JV20.isapsw.model.AdministratorKlinickogCentra;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface AdministratorKlinickogCentraRepository extends JpaRepository<AdministratorKlinickogCentra, Long> {
    Optional<AdministratorKlinickogCentra> findById(Long id);
    //Page<AdministratorKlinickogCentra> findAll(Pageable pageable);
    List<AdministratorKlinickogCentra> findAll();
    void deleteById(Long id);
}
