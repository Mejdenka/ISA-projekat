package JV20.isapsw.controller;

import JV20.isapsw.model.Dijagnoza;
import JV20.isapsw.model.Lek;
import JV20.isapsw.service.DijagnozaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value="/api/dijagnoze")
public class DijagnozaController {

    @Autowired
    DijagnozaService dijagnozaService;

    @RequestMapping(method = RequestMethod.POST, value="/dodajDijagnozu")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> dodajLek(@RequestBody Dijagnoza dijagnoza) throws AccessDeniedException, ParseException {
        Dijagnoza d = this.dijagnozaService.findBySifraDijagnoze(dijagnoza.getSifraDijagnoze());

        if(d != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.dijagnozaService.save(dijagnoza);

        return new ResponseEntity<User>( HttpStatus.CREATED);
    }

    @RequestMapping(value="/getDijagnoze")
    @PreAuthorize("hasRole('DOKTOR')")
    public List<Dijagnoza> getDijagnoze() throws AccessDeniedException {
        return dijagnozaService.findAll();
    }


}
