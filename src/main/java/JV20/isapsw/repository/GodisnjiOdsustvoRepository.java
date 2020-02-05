package JV20.isapsw.repository;


import JV20.isapsw.model.GodisnjiOdsustvoTermin;
import JV20.isapsw.model.Klinika;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GodisnjiOdsustvoRepository extends JpaRepository<GodisnjiOdsustvoTermin, Long> {
    Optional<GodisnjiOdsustvoTermin> findById(Long id);
    List<GodisnjiOdsustvoTermin> findAll();
    void deleteById(Long id);
    GodisnjiOdsustvoTermin save(GodisnjiOdsustvoTermin godisnjiOdsustvoTermin);

}
