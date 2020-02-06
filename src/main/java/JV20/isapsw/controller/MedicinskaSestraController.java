package JV20.isapsw.controller;

import JV20.isapsw.dto.PacijentDTO;
import JV20.isapsw.model.Lekar;
import JV20.isapsw.model.MedicinskaSestra;
import JV20.isapsw.model.Pacijent;
import JV20.isapsw.service.KlinikaService;
import JV20.isapsw.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="api/sestra")
public class MedicinskaSestraController {
    @Autowired
    KorisnikService korisnikService;
    @Autowired
    KlinikaService klinikaService;

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
}
