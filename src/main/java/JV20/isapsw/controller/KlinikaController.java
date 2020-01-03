package JV20.isapsw.controller;


import JV20.isapsw.dto.LekarDTO;
import JV20.isapsw.dto.SalaDTO;
import JV20.isapsw.dto.TerminDTO;
import JV20.isapsw.model.*;
import JV20.isapsw.service.KlinikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/klinike")
public class KlinikaController {
    @Autowired
    private KlinikaService klinikaService;

    @RequestMapping("/getAll")
    @PreAuthorize("hasRole('USER')")
    public List<Klinika> getAll() {
        return this.klinikaService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getSale/{nazivKlinike}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public List<Sala> getSale(@PathVariable String nazivKlinike) throws AccessDeniedException {
        List<Sala> sale = klinikaService.findByNaziv(nazivKlinike).getSale();
        List<Sala> retVal = new ArrayList<>();
        for(Sala s : sale){
            if(!s.isObrisana()){
                retVal.add(s);
            }
        }
        return retVal;
    }

    /* Mozda je ljepse sa salaDTO... problem je sto nije povezano sa rezervacijama */
    @RequestMapping(method = RequestMethod.GET, value = "/getSlobodneSale/{nazivKlinike}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public List<SalaDTO> getSlobodneSale(@PathVariable String nazivKlinike) throws AccessDeniedException {
        List<Sala> sale = klinikaService.findByNaziv(nazivKlinike).getSale();
        List<SalaDTO> retVal = new ArrayList<>();
        for(Sala s : sale){
            if(s.isSlobodna() && !s.isObrisana()){
                retVal.add(new SalaDTO(s));
            }
        }
        return retVal;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getLekari/{nazivKlinike}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public List<LekarDTO> getLekari(@PathVariable String nazivKlinike) throws AccessDeniedException {
        List<Lekar> lekari = klinikaService.findByNaziv(nazivKlinike).getLekari();
        List<LekarDTO> retVal = new ArrayList<>();
        for(Lekar l : lekari){
            if(!l.isObrisan()){
                retVal.add(new LekarDTO(l));
            }
        }
        return retVal;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getSlobodniLekari/{nazivKlinike}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public List<LekarDTO> getSlobodniLekari(@PathVariable String nazivKlinike) throws AccessDeniedException {
        List<Lekar> lekari = klinikaService.findByNaziv(nazivKlinike).getLekari();
        List<LekarDTO> retVal = new ArrayList<>();
        for(Lekar l : lekari){
            if(!l.isObrisan() && l.isSlobodan() && !l.isNaGodisnjem()){
                retVal.add(new LekarDTO(l));
            }
        }
        return retVal;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/izmenaKlinike")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public ResponseEntity<?> izmenaKlinike(@RequestBody Klinika klinika) throws AccessDeniedException {
        Klinika zaIzmenu = klinikaService.findOne(klinika.getId());
        //ovdje setovati novu kliniku i sacuvati u bazi
        zaIzmenu.setNaziv(klinika.getNaziv());
        System.out.println(zaIzmenu.getLokacija() + zaIzmenu.getNaziv());
        zaIzmenu.setLokacija(klinika.getLokacija());
        zaIzmenu.setOpis(klinika.getOpis());
        klinikaService.save(zaIzmenu);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
