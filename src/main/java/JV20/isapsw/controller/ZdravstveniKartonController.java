package JV20.isapsw.controller;

import JV20.isapsw.model.ZdravstveniKarton;
import JV20.isapsw.service.ZdravstveniKartonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping(value = "api/kartoni")
public class ZdravstveniKartonController {
    @Autowired
    ZdravstveniKartonService kartonService;

    @RequestMapping(method = RequestMethod.POST, value="/sacuvajIzmene")
    @PreAuthorize("hasRole('DOKTOR')")
    public ResponseEntity<?> sacuvajIzmene(@RequestBody ZdravstveniKarton karton) throws AccessDeniedException, ParseException {
        ZdravstveniKarton k = kartonService.findOne(karton.getId());
        k.setVisina(karton.getVisina());
        k.setMasa(karton.getMasa());
        k.setKrvnaGrupa(karton.getKrvnaGrupa());
        k.setDioptrija(karton.getDioptrija());
        k.setAlergije(karton.getAlergije());
        this.kartonService.save(k);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }
}
