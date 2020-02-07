package JV20.isapsw.controller;

import JV20.isapsw.dto.GodisnjiOdsustvoTerminDTO;
import JV20.isapsw.dto.LekarDTO;
import JV20.isapsw.dto.OperacijaDTO;
import JV20.isapsw.dto.PregledDTO;
import JV20.isapsw.model.GodisnjiOdsustvoTermin;
import JV20.isapsw.model.Klinika;
import JV20.isapsw.model.Lekar;
import JV20.isapsw.model.Pregled;
import JV20.isapsw.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/adminKlinike")
public class AdministratorKlinikeController {
    @Autowired
    private AdministratorKlinikeService administratorKlinikeService;
    @Autowired
    private KlinikaService klinikaService;
    @Autowired
    private GodisnjiOdsustvoService godisnjiOdsustvoService;


    @RequestMapping(method = RequestMethod.GET, value = "/getZahteviZaPreglede/{idKlinike}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public List<PregledDTO> getZahteviZaPreglede(@PathVariable Long idKlinike) throws AccessDeniedException {
        return this.klinikaService.findRezervisanePreglede(idKlinike);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/getZahteviZaOperacije/{idKlinike}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public List<OperacijaDTO> getZahteviZaOperacije(@PathVariable Long idKlinike) throws AccessDeniedException {
        return this.klinikaService.findRezervisaneOperacije(idKlinike);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/ukloniZahtevZaPregled/{klinikaId}/{zahtevId}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public void ukloniZahtevZaPregled(@PathVariable Long zahtevId, @PathVariable Long klinikaId) throws AccessDeniedException {
        this.klinikaService.ukloniZahtevZaPregled(zahtevId, klinikaId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/ukloniZahtevZaOperaciju/{klinikaId}/{zahtevId}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public void ukloniZahtevZaOperaciju(@PathVariable Long zahtevId, @PathVariable Long klinikaId) throws AccessDeniedException {
        this.klinikaService.ukloniZahtevZaOperaciju(zahtevId, klinikaId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/posaljiGoOds")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public void sacuvajGoOds(@RequestBody GodisnjiOdsustvoTerminDTO godisnjiOdsustvoTerminDTO) throws AccessDeniedException {

        this.godisnjiOdsustvoService.save(godisnjiOdsustvoTerminDTO);
    }

}
