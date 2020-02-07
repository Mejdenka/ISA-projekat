package JV20.isapsw.controller;

import JV20.isapsw.dto.PacijentDTO;
import JV20.isapsw.dto.ReceptDTO;
import JV20.isapsw.model.Lekar;
import JV20.isapsw.model.MedicinskaSestra;
import JV20.isapsw.model.Pacijent;
import JV20.isapsw.model.Recept;
import JV20.isapsw.service.KlinikaService;
import JV20.isapsw.service.KorisnikService;
import JV20.isapsw.service.MedicinskaSestraService;
import JV20.isapsw.service.ReceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
        System.out.println("Recepti: "+recepti);
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
}
