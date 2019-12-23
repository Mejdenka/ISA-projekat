package JV20.isapsw.controller;


import JV20.isapsw.model.Klinika;
import JV20.isapsw.model.Korisnik;
import JV20.isapsw.model.Lekar;
import JV20.isapsw.model.Sala;
import JV20.isapsw.service.KlinikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
            if(s.isSlobodna()){
                retVal.add(s);
            }
        }
        return retVal;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getLekari/{nazivKlinike}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public List<Lekar> getLekari(@PathVariable String nazivKlinike) throws AccessDeniedException {
        List<Lekar> lekari = klinikaService.findByNaziv(nazivKlinike).getLekari();
        List<Lekar> retVal = new ArrayList<>();
        for(Lekar l : lekari){
            if(l.isSlobodan() && !l.isNaGodisnjem()){
                retVal.add(l);
            }
        }
        return retVal;
    }
}
