package JV20.isapsw.controller;

import JV20.isapsw.dto.PacijentDTO;
import JV20.isapsw.dto.PacijentDTO;
import JV20.isapsw.exception.ResourceConflictException;
import JV20.isapsw.model.*;
import JV20.isapsw.model.Pacijent;
import JV20.isapsw.service.EmailService;
import JV20.isapsw.service.KorisnikService;
import JV20.isapsw.service.LekarService;
import JV20.isapsw.service.PacijentService;
import net.bytebuddy.build.BuildLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping(value = "api/pacijenti")
public class PacijentController {
    @Autowired
    private PacijentService pacijentService;
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private LekarService lekarService;

    private Logger logger = LoggerFactory.getLogger(PacijentController.class);

    //signUp metoda sa fronta za registraciju pacijenata iskljucivo
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody UserRequest userRequest, UriComponentsBuilder ucBuilder) throws ParseException {
        Korisnik existUser = this.korisnikService.findOneByUsername(userRequest.getKorisnickoIme());
        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getId(), "Korisnicko ime je zauzeto.");
        }

        //Korisnik user = this.korisnikService.save(userRequest);
        Pacijent pacijent = this.pacijentService.save(userRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/korisnici/korisnik/{userId}").buildAndExpand(pacijent.getId()).toUri());
        return new ResponseEntity<User>( HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pacijent/{userId}")
    @PreAuthorize("hasRole('USER')")
    public Pacijent getPacijent(@PathVariable Long userId) throws AccessDeniedException {
        return this.pacijentService.findOne(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/zdravstveniKarton/{userId}")
    @PreAuthorize("hasRole('DOKTOR')")
    public ZdravstveniKarton getZdravstveniKarton(@PathVariable Long userId) throws AccessDeniedException {
        return this.pacijentService.getKartonPacijenta(userId);
    }

    @RequestMapping("/getRequests")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Pacijent> getRequests() throws AccessDeniedException {
        List<Pacijent> pacijenti = new ArrayList<>();

        for (Pacijent p : pacijentService.findAll()){
            if (!p.isConfirmed()){
                pacijenti.add(p);
            }
        }

        return pacijenti;
    }

    @RequestMapping(value = "/confirmRequest", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public void confirmRequest(@RequestBody String username) {
        Korisnik existUser = this.korisnikService.findOneByUsername(username);
        System.out.println(existUser.getEmail());
        try {
            emailService.sendConfirmedEmail(existUser);

        }catch( Exception e ){
            logger.info("Greska prilikom slanja emaila: " + e.getMessage());
        }

        existUser.setConfirmed(true);
        korisnikService.save(existUser);

    }

    @RequestMapping(value = "/rejectRequest", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public void rejectRequest(@RequestBody String username) {
        Korisnik existUser = this.korisnikService.findOneByUsername(username);
        try {
            emailService.sendRejectedEmail(existUser);

        }catch( Exception e ){
            logger.info("Greska prilikom slanja emaila: " + e.getMessage());
        }
        korisnikService.remove(existUser.getId());
    }
}
