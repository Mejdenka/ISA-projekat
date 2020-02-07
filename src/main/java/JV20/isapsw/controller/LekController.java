package JV20.isapsw.controller;

import JV20.isapsw.model.Klinika;
import JV20.isapsw.model.Lek;
import JV20.isapsw.service.LekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/lekovi")
public class LekController {

    @Autowired
    LekService lekService;

    @RequestMapping(method = RequestMethod.POST, value="/dodajLek")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> dodajLek(@RequestBody Lek lek) throws AccessDeniedException, ParseException {
        Lek l = this.lekService.findBySifraLeka(lek.getSifraLeka());

        if(l != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.lekService.save(lek);

        return new ResponseEntity<User>( HttpStatus.CREATED);
    }

    @RequestMapping(value="/getLekovi")
    @PreAuthorize("hasRole('DOKTOR')")
    public List<Lek> getLekovi() throws AccessDeniedException {
        return lekService.findAll();
    }
}
