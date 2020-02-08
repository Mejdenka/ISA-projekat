package JV20.isapsw.controller;


import JV20.isapsw.dto.*;
import JV20.isapsw.model.*;
import JV20.isapsw.service.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/klinike")
public class KlinikaController {
    @Autowired
    private KlinikaService klinikaService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private TipPregledaService tipPregledaService;

    @Autowired
    private LokacijaService lokacijaService;

    @Autowired
    private SalaService salaService;

    @RequestMapping("/getAll")
    @PreAuthorize("hasRole('USER')")
    public List<Klinika> getAll() {
        return this.klinikaService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getSale/{idKlinike}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public List<Sala> getSale(@PathVariable Long idKlinike) throws AccessDeniedException {
        List<Sala> sale = klinikaService.findOne(idKlinike).getSale();
        List<Sala> retVal = new ArrayList<>();
        for(Sala s : sale){
            if(!s.isObrisana()){
                retVal.add(s);
            }
        }
        return retVal;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/daLiJeRezervisanaSala/{brojSale}/{datum}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public String rezervisanaSala(@PathVariable Long brojSale, @PathVariable String datum) throws AccessDeniedException, ParseException {
        return this.salaService.findIfNotReserved(brojSale, datum);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getSala/{brojSale}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public Sala getSala(@PathVariable Long brojSale) throws AccessDeniedException {
        return this.salaService.findOneByBroj(brojSale);
    }



    /* Mozda je ljepse sa salaDTO... problem je sto nije povezano sa rezervacijama */
    @RequestMapping(method = RequestMethod.GET, value = "/getSlobodneSale/{nazivKlinike}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public List<SalaDTO> getSlobodneSale(@PathVariable String nazivKlinike) throws AccessDeniedException {
        List<Sala> sale = klinikaService.findByNaziv(nazivKlinike).getSale();
        List<SalaDTO> retVal = new ArrayList<>();
        for(Sala s : sale){
            if(s.isSlobodna() && !s.isObrisana()){
                retVal.add(new SalaDTO(s));
            }
        }
        return retVal;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllGoOds/{id}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public List<GodisnjiOdsustvoTerminDTO> getAllGoOds(@PathVariable Long id) throws AccessDeniedException {
        Klinika klinika = this.klinikaService.findOne(id);
        return this.klinikaService.getAllGoOds(klinika);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getSlobodniTermini/{idKlinike}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public List<PregledDTO> getSlobodniTermini(@PathVariable Long idKlinike) throws AccessDeniedException {
        Klinika klinika = klinikaService.findOne(idKlinike);
        return klinikaService.pronadjiSlobodnePreglede(klinika);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteTerminZaPregled/{idKlinike}/{idTermina}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public List<PregledDTO> deleteTermin(@PathVariable Long idKlinike, @PathVariable Long idTermina) throws AccessDeniedException {
        Klinika klinika = klinikaService.findOne(idKlinike);
        klinikaService.obrisiTerminZaPregled(klinika, idTermina);
        return klinikaService.pronadjiSlobodnePreglede(klinika);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getLekari/{idKlinike}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public List<LekarDTO> getLekari(@PathVariable Long idKlinike) throws AccessDeniedException {
        List<Lekar> lekari = klinikaService.findOne(idKlinike).getLekari();
        List<LekarDTO> retVal = new ArrayList<>();
        for(Lekar l : lekari){
            if(!l.isObrisan()){
                retVal.add(new LekarDTO(l));
            }
        }
        return retVal;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getPregledi/{idKlinike}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public List<Pregled> getPreglediKlinike(@PathVariable Long idKlinike) throws AccessDeniedException {
        return klinikaService.findPregledi(idKlinike);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getObavljeniPregledi/{idKlinike}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public List<Pregled> getObavljeniPregledi(@PathVariable Long idKlinike) throws AccessDeniedException {
        return klinikaService.findObavljeniPregledi(idKlinike);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getKlinike/{tip}/{datum}/{lokacija}/{ocjena}")
    @PreAuthorize("hasRole('USER')")
    public List<Klinika> getKlinike(@PathVariable String tip, @PathVariable String datum, @PathVariable String lokacija, @PathVariable String ocjena) throws AccessDeniedException {
        return klinikaService.findAllBy(tip, datum, lokacija, ocjena);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getSlobodniLekari/{nazivKlinike}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public List<LekarDTO> getSlobodniLekari(@PathVariable String nazivKlinike) throws AccessDeniedException {
        List<Lekar> lekari = klinikaService.findByNaziv(nazivKlinike).getLekari();
        List<LekarDTO> retVal = new ArrayList<>();
        for(Lekar l : lekari){
            if(!l.isObrisan() && l.isSlobodan() && !l.isNaGodisnjem()){
                retVal.add(new LekarDTO(l));
            }
        }
        return retVal;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getTipoviPregleda/{klinikaId}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public List<TipPregleda> getTipoviPregleda(@PathVariable Long klinikaId) throws AccessDeniedException {
       //if bool zauzeto == true onda ne ubacujes u listu
        List<TipPregleda> tipoviPregleda= klinikaService.findOne(klinikaId).getTipoviPregleda();
        List<TipPregleda> retVal = new ArrayList<>();
        for(TipPregleda tp : tipoviPregleda){
            if(!tp.isObrisan()){
                retVal.add(tp);
            }
        }
        return retVal;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getTipoviPregleda")
    @PreAuthorize("hasRole('DOKTOR')")
    public List<TipPregleda> getTipoviPregledaNoId() throws AccessDeniedException {
        Lekar lekar =  (Lekar) this.korisnikService.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        //if bool zauzeto == true onda ne ubacujes u listu
        List<TipPregleda> tipoviPregleda= klinikaService.findOne(lekar.getKlinikaLekara().getId()).getTipoviPregleda();
        List<TipPregleda> retVal = new ArrayList<>();
        for(TipPregleda tp : tipoviPregleda){
            if(!tp.isObrisan()){
                retVal.add(tp);
            }
        }
        return retVal;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/dodajTipPregleda")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public ResponseEntity<?> dodajTipPregleda(@RequestBody TipPregleda tipPregleda) throws AccessDeniedException {
        TipPregleda tp = this.tipPregledaService.findOneByNaziv(tipPregleda.getNaziv());
        if(tp != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Klinika klinika = this.klinikaService.findOne(tipPregleda.getIdKlinike());

        tipPregledaService.saveNew(tipPregleda, klinika);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/dodajSlobodanPregled")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public ResponseEntity<?> dodajSlobodanTermin(@RequestBody PregledDTO pregledDTO ) throws AccessDeniedException, ParseException {
        this.klinikaService.dodajPregled(pregledDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/izmeniTipPregleda")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public Klinika izmeniTipPregleda(@RequestBody TipPregleda tipPregleda) throws AccessDeniedException {
        return klinikaService.izmeniTipPregleda(tipPregleda);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteTipPregleda/{klinikaId}/{tipPregledaId}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public Klinika deleteTipPregleda(@PathVariable Long tipPregledaId, @PathVariable Long klinikaId) throws AccessDeniedException {
        //if bool zauzeto == true onda ne ubacujes u listu
        return this.klinikaService.deleteTipPregleda(tipPregledaId, klinikaId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/izmenaKlinike")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public ResponseEntity<?> izmenaKlinike(@RequestBody Klinika klinika) throws AccessDeniedException {
        Klinika zaIzmenu = klinikaService.findOne(klinika.getId());
        //ovdje setovati novu kliniku i sacuvati u bazi
        zaIzmenu.setNaziv(klinika.getNaziv());
        zaIzmenu.setLokacija(klinika.getLokacija());
        zaIzmenu.setOpis(klinika.getOpis());
        klinikaService.save(zaIzmenu);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/dodajKliniku")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> dodajKliniku(@RequestBody Klinika klinika) throws AccessDeniedException, ParseException {
        Klinika k = this.klinikaService.findByNaziv(klinika.getNaziv());

        if(k != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.lokacijaService.save(klinika.getLokacijaNaMapi());
        klinika.setProsecnaOcena(0.0);
        this.klinikaService.save(klinika);


        return new ResponseEntity<User>( HttpStatus.CREATED);
    }
}
