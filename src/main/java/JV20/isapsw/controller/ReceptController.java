package JV20.isapsw.controller;

import JV20.isapsw.model.Klinika;
import JV20.isapsw.model.Korisnik;
import JV20.isapsw.model.Lekar;
import JV20.isapsw.model.Recept;
import JV20.isapsw.service.KlinikaService;
import JV20.isapsw.service.KorisnikService;
import JV20.isapsw.service.LekarService;
import JV20.isapsw.service.ReceptService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping(value="api/recepti")
public class ReceptController {
    @Autowired
    private ReceptService receptService;
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private KlinikaService klinikaService;

    @RequestMapping(value="/izdajRecept")
    @PreAuthorize("hasRole('DOKTOR')")
    public ResponseEntity<?> izdajRecept(@RequestBody Recept recept) throws AccessDeniedException, ParseException {
        Lekar ulogovan = (Lekar) korisnikService.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Klinika k = klinikaService.findOne(ulogovan.getKlinikaLekara().getId());
        recept.setKlinikaRecept(k);
        this.receptService.save(recept);
        return new ResponseEntity<User>( HttpStatus.CREATED);
    }
}

