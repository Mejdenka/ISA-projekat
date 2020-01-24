package JV20.isapsw.controller;

import JV20.isapsw.dto.TerminDTO;
import JV20.isapsw.exception.ResourceConflictException;
import JV20.isapsw.model.*;
import JV20.isapsw.service.KlinikaService;
import JV20.isapsw.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.file.AccessDeniedException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/sale")
public class SalaController {
    @Autowired
    private SalaService salaService;
    @Autowired
    private KlinikaService klinikaService;

    @RequestMapping(method = RequestMethod.GET, value = "/getTermini/{salaId}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public List<TerminDTO> getTermini(@PathVariable Long salaId) throws AccessDeniedException {
        //konverzija termina sale u terminDTO-ove kako bi datum bio u zadovoljavajucem obliku
        Sala sala = this.salaService.findOne(salaId);
        List<TerminDTO> terminDTOS = new ArrayList<>();
        for(Pregled p : sala.getPregledi()){
            if(!p.getTermin().isObrisan())
                terminDTOS.add(new TerminDTO(p.getTermin()));
        }
        return terminDTOS;
    }
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public ResponseEntity<Sala> deleteSala(@PathVariable("id") Long id) {
        Sala sala = salaService.findOne(id);
        sala.setObrisana(true);
        salaService.save(sala);
        return new ResponseEntity<Sala>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/izmenaSale")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public ResponseEntity<?> izmenaSale(@RequestBody Sala sala) throws AccessDeniedException {
        Sala zaIzmenu = this.salaService.findOne(sala.getId());
        if(zaIzmenu == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        zaIzmenu.setNaziv(sala.getNaziv());
        salaService.save(zaIzmenu);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/dodajSalu")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public ResponseEntity<?> dodajSalu(@RequestBody Sala sala) throws AccessDeniedException, ParseException {
        Sala s = this.salaService.findOneByNaziv(sala.getNaziv());
        if(s != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        sala.setKlinika(this.klinikaService.findOne(sala.getIdKlinike()));
        s = this.salaService.save(sala);
         return new ResponseEntity<User>( HttpStatus.CREATED);
    }
}