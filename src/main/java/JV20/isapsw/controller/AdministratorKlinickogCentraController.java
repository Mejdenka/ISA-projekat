package JV20.isapsw.controller;

import JV20.isapsw.dto.AdministratorKlinickogCentraDTO;
import JV20.isapsw.exception.ResourceConflictException;
import JV20.isapsw.model.AdministratorKlinickogCentra;
import JV20.isapsw.model.Korisnik;
import JV20.isapsw.model.Pacijent;
import JV20.isapsw.model.UserRequest;
import JV20.isapsw.service.AdministratorKlinickogCentraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "api/adminKC")
public class AdministratorKlinickogCentraController {
    @Autowired
    private AdministratorKlinickogCentraService akcService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addAKC(@RequestBody UserRequest userRequest, UriComponentsBuilder ucBuilder) throws ParseException {
        AdministratorKlinickogCentra akc = this.akcService.findOneByUsername(userRequest.getKorisnickoIme());
        if (akc != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        akc = akcService.save(userRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/korisnici/korisnik/{userId}").buildAndExpand(akc.getId()).toUri());

        return new ResponseEntity<>(new AdministratorKlinickogCentraDTO(akc), HttpStatus.CREATED);
    }
}
