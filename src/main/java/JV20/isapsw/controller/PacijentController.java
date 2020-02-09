package JV20.isapsw.controller;

import JV20.isapsw.dto.PacijentDTO;
import JV20.isapsw.dto.PacijentDTO;
import JV20.isapsw.exception.ResourceConflictException;
import JV20.isapsw.model.*;
import JV20.isapsw.model.Pacijent;
import JV20.isapsw.service.*;
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
import java.util.Set;

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
    @Autowired
    private KlinikaService klinikaService;
    @Autowired
    private PregledService pregledService;

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

    @RequestMapping(method = RequestMethod.GET, value = "/myZdravstveniKarton/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ZdravstveniKarton getMyZdravstveniKarton(@PathVariable Long userId) throws AccessDeniedException {
        return this.pacijentService.findOne(userId).getKarton();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mojeOperacije/{userId}")
    @PreAuthorize("hasRole('USER')")
    public Set<Operacija> mojeOperacije(@PathVariable Long userId) throws AccessDeniedException {
        return this.pacijentService.findOne(userId).getOperacije();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getLekari/{userId}")
    @PreAuthorize("hasRole('USER')")
    public List<Lekar> getLekari(@PathVariable Long userId) throws AccessDeniedException {
        List<Lekar> sviMojiLekari = new ArrayList<Lekar>();

        for( Pregled p : this.pacijentService.findOne(userId).getPregledi()){
            if (p.isObavljen()){
                if (!sviMojiLekari.contains(p.getLekar())){
                    sviMojiLekari.add(p.getLekar());
                }
            }
        }

        return sviMojiLekari;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getKlinike/{userId}")
    @PreAuthorize("hasRole('USER')")
    public List<Klinika> getKlinike(@PathVariable Long userId) throws AccessDeniedException {
        List<Klinika> sveMojeKlinike = new ArrayList<Klinika>();

        for( Pregled p : this.pacijentService.findOne(userId).getPregledi()){
            if (p.isObavljen()){
                if (!sveMojeKlinike.contains(p.getKlinikaPregleda())){
                    sveMojeKlinike.add(p.getKlinikaPregleda());
                }
            }
        }

        return sveMojeKlinike;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/oceniLekara/{lekarId}/{ocena}")
    @PreAuthorize("hasRole('USER')")
    public void oceniLekara(@PathVariable Long lekarId, @PathVariable int ocena) throws AccessDeniedException {
        Lekar lekar = lekarService.findOne(lekarId);
        lekar.setZbirOcena(lekar.getZbirOcena() + ocena);
        lekar.setBrojOcena(lekar.getBrojOcena() + 1);
        lekarService.save(lekar);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/oceniKliniku/{klinikaId}/{ocena}")
    @PreAuthorize("hasRole('USER')")
    public void oceniKliniku(@PathVariable Long klinikaId, @PathVariable int ocena) throws AccessDeniedException {
        Klinika klinika = klinikaService.findOne(klinikaId);
        klinika.setZbirOcena(klinika.getZbirOcena() + ocena);
        klinika.setBrojOcena(klinika.getBrojOcena() + 1);
        klinikaService.save(klinika);
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

    @RequestMapping(method = RequestMethod.POST, value = "/zakaziPregled/{pacijentId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> napraviTerminZaPregled( @RequestBody Pregled pregled, @PathVariable("pacijentId") Long pacijentId) throws AccessDeniedException, ParseException, InterruptedException {
        pregled.setPacijent(pacijentService.findOne(pacijentId));
        pregled.setSala(null);
        this.pregledService.save(pregled);
        emailService.sendPregledLekara(pregled, pregled.getKlinikaPregleda());
        return new ResponseEntity<User>( HttpStatus.OK);
    }
}
