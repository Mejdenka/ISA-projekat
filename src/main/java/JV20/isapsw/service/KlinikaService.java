package JV20.isapsw.service;

import JV20.isapsw.dto.GodisnjiOdsustvoTerminDTO;
import JV20.isapsw.dto.OperacijaDTO;
import JV20.isapsw.dto.PregledDTO;
import JV20.isapsw.model.*;
import JV20.isapsw.repository.KlinikaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class KlinikaService {
    @Autowired
    private KlinikaRepository klinikaRepository;
    @Autowired
    private PregledService pregledService ;
    @Autowired
    private OperacijaService operacijaService ;

    public Klinika findOne(Long id) {
        return klinikaRepository.findById(id).orElseGet(null);
    }

    public Klinika findByNaziv(String naziv) {return klinikaRepository.findByNaziv(naziv);}

    public List<Klinika> findAll() {
        return klinikaRepository.findAll();
    }

    public Klinika save(Klinika klinika) {
        return klinikaRepository.save(klinika);
    }

    public void remove(Long id) {
        klinikaRepository.deleteById(id);
    }

    public List<GodisnjiOdsustvoTerminDTO> getAllGoOds(Klinika klinika){
        List<GodisnjiOdsustvoTerminDTO> retVal = new ArrayList<>();
        //prikaz neodobrenih zahtjeva
        for(Lekar lekar : klinika.getLekari()){
            if(!lekar.isObrisan()){
               for(GodisnjiOdsustvoTermin ods : lekar.getRezervisanaOdustva()){
                   if(!ods.isObrisan() && !ods.isOdobren()){
                       retVal.add(new GodisnjiOdsustvoTerminDTO(ods));
                   }
               }
                for(GodisnjiOdsustvoTermin go : lekar.getRezervisaniGO()){
                    if(!go.isObrisan() && !go.isOdobren()){
                        retVal.add(new GodisnjiOdsustvoTerminDTO(go));
                    }
                }
            }
        }

        for(MedicinskaSestra medicinskaSestra : klinika.getMedicinskeSestre()){
            if(!medicinskaSestra.isObrisan()){
                for(GodisnjiOdsustvoTermin ods : medicinskaSestra.getRezervisanaOdustva()){
                    if(!ods.isObrisan() && !ods.isOdobren()){
                        retVal.add(new GodisnjiOdsustvoTerminDTO(ods));
                    }
                }
                for(GodisnjiOdsustvoTermin go : medicinskaSestra.getRezervisaniGO()){
                    if(!go.isObrisan() && !go.isOdobren()){
                        retVal.add(new GodisnjiOdsustvoTerminDTO(go));
                    }
                }
            }
        }

        return retVal;
    }

    public List<Pregled> findPregledi(Long id){
        List<Pregled> retVal = new ArrayList<>();
        for(Pregled p : findOne(id).getPregledi()){
            if(!p.isObrisan()){
                retVal.add(p);
            }
        }
        return retVal;
    }

    public List<PregledDTO> findRezervisanePreglede(Long id){
        List<PregledDTO> retVal = new ArrayList<>();
        for(Pregled p : findOne(id).getPregledi()){
            if(!p.isObavljen() && !p.isObrisan() ){
                //ako mu nije dodijeljena sala
                if(p.getSala() == null){
                    retVal.add(new PregledDTO(p));
                }
            }
        }
        return retVal;
    }

    public List<OperacijaDTO> findRezervisaneOperacije(Long id){
        List<OperacijaDTO> retVal = new ArrayList<>();
        for(Operacija o : findOne(id).getOperacije()){
            if(!o.isObavljena() && !o.isObrisana()){
                retVal.add(new OperacijaDTO(o));
            }
        }
        return retVal;
    }

    public void ukloniZahtevZaPregled (Long zahtevId, Long klinikaId){
        this.pregledService.findOne(zahtevId).setObrisan(true);
        save(findOne(klinikaId));
    }

    public void ukloniZahtevZaOperaciju (Long zahtevId, Long klinikaId){
        this.operacijaService.findOne(zahtevId).setObrisana(true);
        save(findOne(klinikaId));
    }
}
