package JV20.isapsw.service;

import JV20.isapsw.dto.GodisnjiOdsustvoTerminDTO;
import JV20.isapsw.model.Dijagnoza;
import JV20.isapsw.model.GodisnjiOdsustvoTermin;
import JV20.isapsw.repository.GodisnjiOdsustvoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class GodisnjiOdsustvoService {

    @Autowired
    private GodisnjiOdsustvoRepository godisnjiOdsustvoRepository;

    public GodisnjiOdsustvoTermin findOne(Long id) {
        return godisnjiOdsustvoRepository.findById(id).orElseGet(null);
    }

    public List<GodisnjiOdsustvoTermin> findAll() {
        return godisnjiOdsustvoRepository.findAll();
    }


    public GodisnjiOdsustvoTermin save(GodisnjiOdsustvoTermin godisnjiOdsustvoTermin) {
        return godisnjiOdsustvoRepository.save(godisnjiOdsustvoTermin);
    }

    public GodisnjiOdsustvoTermin save(GodisnjiOdsustvoTerminDTO godisnjiOdsustvoTerminDTO) {
        System.out.println(godisnjiOdsustvoTerminDTO.getRazlogOdbijanja());
        GodisnjiOdsustvoTermin godisnjiOdsustvoTermin = findOne(godisnjiOdsustvoTerminDTO.getId());
        godisnjiOdsustvoTermin.setOdobren(godisnjiOdsustvoTerminDTO.isOdobren());
        godisnjiOdsustvoTermin.setObrisan(!godisnjiOdsustvoTerminDTO.isOdobren());
        return godisnjiOdsustvoRepository.save(godisnjiOdsustvoTermin);
    }

    public void remove(Long id) {
        godisnjiOdsustvoRepository.deleteById(id);
    }
}
