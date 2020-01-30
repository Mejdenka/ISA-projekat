package JV20.isapsw.controller;

import JV20.isapsw.common.TimeProvider;
import JV20.isapsw.dto.LekarDTO;
import JV20.isapsw.dto.PacijentDTO;
import JV20.isapsw.dto.PacijentDTO;
import JV20.isapsw.exception.ResourceConflictException;
import JV20.isapsw.model.*;
import JV20.isapsw.model.Pacijent;
import JV20.isapsw.service.KlinikaService;
import JV20.isapsw.service.KorisnikService;
import JV20.isapsw.service.LekarService;
import JV20.isapsw.service.PacijentService;
import net.bytebuddy.build.BuildLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.file.AccessDeniedException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/lekari")
public class LekarController {
    @Autowired
    private LekarService lekarService;
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private KlinikaService klinikaService;

    @RequestMapping(method = RequestMethod.POST, value = "/izmenaLekara")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public ResponseEntity<?> izmenaLekara(@RequestBody Lekar lekar) throws AccessDeniedException {
        Lekar zaIzmenu = this.lekarService.findOne(lekar.getId());
        if(zaIzmenu == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        zaIzmenu.setKorisnickoIme(lekar.getKorisnickoIme());
        zaIzmenu.setIme(lekar.getIme());
        zaIzmenu.setPrezime(lekar.getPrezime());
        zaIzmenu.setEmail(lekar.getEmail());
        zaIzmenu.setDatumRodjenja(lekar.getDatumRodjenja());
        zaIzmenu.setOcena(lekar.getOcena());
        zaIzmenu.setRadnoVreme(lekar.getRadnoVreme());
        lekarService.save(zaIzmenu);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/dodajLekara")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public ResponseEntity<?> dodajLekara( @RequestBody UserRequest userRequest, UriComponentsBuilder ucBuilder) throws AccessDeniedException, ParseException {
        Korisnik existUser = this.korisnikService.findOneByUsername(userRequest.getKorisnickoIme());
        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getId(), "Korisnicko ime je zauzeto.");
        }

        Lekar lekar = this.lekarService.save(userRequest);
        lekar.setKlinika(klinikaService.findOne(userRequest.getIdKlinike()));
        lekar.setRadnoVreme(userRequest.getRadnoVreme());
        lekar = this.lekarService.save(lekar);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/korisnici/korisnik/{userId}").buildAndExpand(lekar.getId()).toUri());
        return new ResponseEntity<User>( HttpStatus.CREATED);
    }
}
