package JV20.isapsw.controller;

import JV20.isapsw.dto.*;
import JV20.isapsw.exception.ResourceConflictException;
import JV20.isapsw.model.*;
import JV20.isapsw.model.Pacijent;
import JV20.isapsw.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.file.AccessDeniedException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    @Autowired
    private TerminService terminService;
    @Autowired
    private OperacijaService operacijaService;
    @Autowired
    private PregledService pregledService ;
    @Autowired
    private TipPregledaService tipPregledaService ;
    @Autowired
    private EmailService emailService ;


    @RequestMapping(method = RequestMethod.GET, value = "/getZakazaniPregledi/{idLekara}")
    @PreAuthorize("hasRole('DOKTOR')")
    public List<PregledDTO> getZakazaniPregledi(@PathVariable Long idLekara) throws AccessDeniedException {
        return this.lekarService.getZakazaniPregledi(idLekara);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getZakazaneOperacije/{idLekara}")
    @PreAuthorize("hasRole('DOKTOR')")
    public List<OperacijaDTO> getZakazaneOperacije(@PathVariable Long idLekara) throws AccessDeniedException {
        return this.lekarService.getZakazaneOperacije(idLekara);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getLekar/{idLekara}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public LekarDTO getLekar(@PathVariable Long idLekara) throws AccessDeniedException {
        return new LekarDTO(this.lekarService.findOne(idLekara));
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/obrisiLekara/{lekarId}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public Lekar obrisiLekara(@PathVariable Long lekarId) throws AccessDeniedException {
        return this.lekarService.obrisiLekara(lekarId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/izmenaLekara")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public Lekar izmenaLekara(@RequestBody Lekar lekar) throws AccessDeniedException {
        Lekar zaIzmenu = this.lekarService.findOne(lekar.getId());
        if(zaIzmenu == null){
            return null;
        }
        return lekarService.izmenaLekara(lekar, zaIzmenu);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/dodajLekara")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public ResponseEntity<?> dodajLekara( @RequestBody UserRequest userRequest, UriComponentsBuilder ucBuilder) throws AccessDeniedException, ParseException {
        Korisnik existUser = this.korisnikService.findOneByUsername(userRequest.getKorisnickoIme());
        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getId(), "Korisnicko ime je zauzeto.");
        }

        Lekar lekar = this.lekarService.save(userRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/korisnici/korisnik/{userId}").buildAndExpand(lekar.getId()).toUri());
        return new ResponseEntity<User>( HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/napraviTerminZaPregled/{tipPregleda}")
    @PreAuthorize("hasRole('DOKTOR')")
    public ResponseEntity<?> napraviTerminZaPregled( @RequestBody Termin termin,  @PathVariable("tipPregleda") String tipPregleda) throws AccessDeniedException, ParseException, InterruptedException {
        Lekar lekar = (Lekar) this.korisnikService.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Klinika klinika = this.klinikaService.findOne(lekar.getKlinikaLekara().getId());
        TipPregleda tp = tipPregledaService.findOneByNaziv(tipPregleda.replace("%20", " "));
        Pacijent pacijent = (Pacijent)this.korisnikService.findOne(termin.getPacijentId());
        Termin noviTermin = this.terminService.rezervisi(termin, klinika);
        Pregled pregled = this.pregledService.saveNew(noviTermin, pacijent, lekar, tp);
        emailService.sendPregledLekara(pregled, klinika);
        return new ResponseEntity<User>( HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/napraviTerminZaOperaciju")
    @PreAuthorize("hasRole('DOKTOR')")
    public ResponseEntity<?> napraviTerminZaOperaciju( @RequestBody Termin termin) throws AccessDeniedException, ParseException, InterruptedException {
        Lekar lekar = (Lekar) this.korisnikService.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Klinika klinika = this.klinikaService.findOne(lekar.getKlinikaLekara().getId());
        Pacijent pacijent = (Pacijent)this.korisnikService.findOne(termin.getPacijentId());
        Termin noviTermin = this.terminService.rezervisi(termin, klinika);
        Operacija operacija = this.operacijaService.saveNew(noviTermin, pacijent, lekar);
        emailService.sendOperacijaLekara(operacija, klinika);

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/zapoceoPregled/{pacijentId}")
    @PreAuthorize("hasRole('DOKTOR')")
    public boolean zapoceoPregled( @RequestBody boolean zapoceo, @PathVariable("pacijentId") Long pacijentId) throws AccessDeniedException {
        return lekarService.zapocniPregled(zapoceo, pacijentId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rezervisiGoOds")
    @PreAuthorize("hasRole('DOKTOR')")
    public ResponseEntity<?> rezervisiGodisnjiOdmorOdsustvo( @RequestBody GodisnjiOdsustvoTermin godisnjiOdsustvoTermin) throws AccessDeniedException, ParseException {
        Lekar lekar = (Lekar) this.korisnikService.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(godisnjiOdsustvoTermin.isGodisnji()){
            godisnjiOdsustvoTermin.setLekarGO(lekar);
            lekar.getRezervisaniGO().add(godisnjiOdsustvoTermin);
        } else if(godisnjiOdsustvoTermin.isOdsustvo()){
            godisnjiOdsustvoTermin.setLekarOds(lekar);
            lekar.getRezervisanaOdustva().add(godisnjiOdsustvoTermin);
        }
        lekarService.save(lekar);
        return new ResponseEntity<User>( HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getGoOds/{lekarId}")
    @PreAuthorize("hasRole('DOKTOR')")
    public List<GodisnjiOdsustvoTerminDTO> getGodisnjiOdsustva(@PathVariable("lekarId") Long lekarId) throws AccessDeniedException {
        Lekar lekar = lekarService.findOne(lekarId);
        List<GodisnjiOdsustvoTermin> termini = new ArrayList<>();
        termini.addAll(lekar.getRezervisaniGO());
        termini.addAll(lekar.getRezervisanaOdustva());

        List<GodisnjiOdsustvoTerminDTO> retVal = new ArrayList<>();
        for(GodisnjiOdsustvoTermin t : termini){
            if(!t.isObrisan())
                retVal.add(new GodisnjiOdsustvoTerminDTO(t));
        }
        return retVal;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteGoOds/{terminId}")
    @PreAuthorize("hasRole('DOKTOR')")
    public List<GodisnjiOdsustvoTerminDTO> deleteGodisnjiOdsustvo(@PathVariable("terminId") Long terminId) throws AccessDeniedException {
        Lekar lekar = (Lekar) korisnikService.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        List<GodisnjiOdsustvoTermin> termini = new ArrayList<>();
        termini.addAll(lekar.getRezervisaniGO());
        termini.addAll(lekar.getRezervisanaOdustva());

        for(GodisnjiOdsustvoTermin t:termini){
            if(t.getId() == terminId){
                t.setObrisan(true);
            }
        }

        lekarService.save(lekar);

        termini = new ArrayList<>();
        termini.addAll(lekar.getRezervisaniGO());
        termini.addAll(lekar.getRezervisanaOdustva());

        List<GodisnjiOdsustvoTerminDTO> retVal = new ArrayList<>();
        for(GodisnjiOdsustvoTermin t : termini){
            if(!t.isObrisan())
                retVal.add(new GodisnjiOdsustvoTerminDTO(t));
        }
        return retVal;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getPacijenti")
    @PreAuthorize("hasRole('DOKTOR')")
    public List<PacijentDTO> getPacijenti() throws AccessDeniedException {
        Lekar lekar = (Lekar) korisnikService.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Pacijent> pacijenti = klinikaService.findOne(lekar.getKlinikaLekara().getId()).getPacijenti();
        List<PacijentDTO> retVal = new ArrayList<>();
        for(Pacijent p : pacijenti){
            retVal.add(new PacijentDTO(p));
        }
        return retVal;
    }


}
