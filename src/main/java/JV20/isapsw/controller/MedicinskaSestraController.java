package JV20.isapsw.controller;

import JV20.isapsw.dto.GodisnjiOdsustvoTerminDTO;
import JV20.isapsw.dto.PacijentDTO;
import JV20.isapsw.dto.ReceptDTO;
import JV20.isapsw.model.*;
import JV20.isapsw.service.KlinikaService;
import JV20.isapsw.service.KorisnikService;
import JV20.isapsw.service.MedicinskaSestraService;
import JV20.isapsw.service.ReceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="api/sestra")
public class MedicinskaSestraController {
    @Autowired
    KorisnikService korisnikService;
    @Autowired
    KlinikaService klinikaService;
    @Autowired
    MedicinskaSestraService sestraService;
    @Autowired
    ReceptService receptService;

    @RequestMapping(method = RequestMethod.GET, value = "/getPacijenti")
    @PreAuthorize("hasRole('MEDICINSKA_SESTRA')")
    public List<PacijentDTO> getPacijenti() throws AccessDeniedException {
        MedicinskaSestra sestra = (MedicinskaSestra) korisnikService.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Pacijent> pacijenti = klinikaService.findOne(sestra.getKlinikaMedSestre().getId()).getPacijenti();
        List<PacijentDTO> retVal = new ArrayList<>();
        for(Pacijent p : pacijenti){
            retVal.add(new PacijentDTO(p));
        }
        return retVal;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getRecepti")
    @PreAuthorize("hasRole('MEDICINSKA_SESTRA')")
    public List<ReceptDTO> getRecepti() throws AccessDeniedException {
        MedicinskaSestra sestra = (MedicinskaSestra) korisnikService.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Recept> recepti = klinikaService.findOne(sestra.getKlinikaMedSestre().getId()).getRecepti();
        List<ReceptDTO> retVal = new ArrayList<>();
        for(Recept r : recepti){
            retVal.add(new ReceptDTO(r));
        }
        return retVal;
    }

    @RequestMapping(method = RequestMethod.POST, value="/overiRecept")
    @PreAuthorize("hasRole('MEDICINSKA_SESTRA')")
    public ResponseEntity<?> overiRecept(@RequestBody Recept recept) throws AccessDeniedException, ParseException {
        MedicinskaSestra ulogovan = (MedicinskaSestra) korisnikService.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Recept r = receptService.findOne(recept.getId());
        r.setIdSestre(ulogovan.getId());
        r.setOveren(true);
        this.receptService.save(r);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rezervisiGoOds")
    @PreAuthorize("hasRole('MEDICINSKA_SESTRA')")
    public ResponseEntity<?> rezervisiGodisnjiOdmorOdsustvo( @RequestBody GodisnjiOdsustvoTermin godisnjiOdsustvoTermin) throws AccessDeniedException, ParseException {
        MedicinskaSestra sestra = (MedicinskaSestra) this.korisnikService.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(godisnjiOdsustvoTermin.isGodisnji()){
            godisnjiOdsustvoTermin.setMedSestraGo(sestra);
            sestra.getRezervisaniGO().add(godisnjiOdsustvoTermin);
        } else if(godisnjiOdsustvoTermin.isOdsustvo()){
            godisnjiOdsustvoTermin.setMedSestraOds(sestra);
            sestra.getRezervisanaOdustva().add(godisnjiOdsustvoTermin);
        }
        sestraService.save(sestra);
        return new ResponseEntity<User>( HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getGoOds/{sestraId}")
    @PreAuthorize("hasRole('MEDICINSKA_SESTRA')")
    public List<GodisnjiOdsustvoTerminDTO> getGodisnjiOdsustva(@PathVariable("sestraId") Long sestraId) throws AccessDeniedException {
        MedicinskaSestra sestra = sestraService.findOne(sestraId);
        List<GodisnjiOdsustvoTermin> termini = new ArrayList<>();
        termini.addAll(sestra.getRezervisaniGO());
        termini.addAll(sestra.getRezervisanaOdustva());

        List<GodisnjiOdsustvoTerminDTO> retVal = new ArrayList<>();
        for(GodisnjiOdsustvoTermin t : termini){
            if(!t.isObrisan())
                retVal.add(new GodisnjiOdsustvoTerminDTO(t));
        }
        return retVal;
    }
}
