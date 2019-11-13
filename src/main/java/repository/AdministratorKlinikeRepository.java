package repository;

import model.AdministratorKlinike;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface AdministratorKlinikeRepository extends JpaRepository<AdministratorKlinike, Long> {
    Optional<AdministratorKlinike> findById(Long id);
    Page<AdministratorKlinike> findAll(Pageable pageable);
    List<AdministratorKlinike> findAll();
    void deleteById(Long id);
}
