package JV20.isapsw.controller;

import JV20.isapsw.model.Recept;
import JV20.isapsw.service.ReceptService;
import org.apache.catalina.User;
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
@RequestMapping(value="api/recepti")
public class ReceptController {
    @Autowired
    private ReceptService receptService;

    @RequestMapping(value="/izdajRecept")
    @PreAuthorize("hasRole('DOKTOR')")
    public ResponseEntity<?> izdajRecept(@RequestBody Recept recept) throws AccessDeniedException, ParseException {
        this.receptService.save(recept);
        return new ResponseEntity<User>( HttpStatus.CREATED);
    }
}

