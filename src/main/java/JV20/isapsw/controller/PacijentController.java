package JV20.isapsw.controller;

import JV20.isapsw.dto.PacijentDTO;
import JV20.isapsw.dto.PacijentDTO;
import JV20.isapsw.model.Pacijent;
import JV20.isapsw.model.Pacijent;
import JV20.isapsw.model.ULOGA;
import JV20.isapsw.service.PacijentService;
import net.bytebuddy.build.BuildLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping(value = "api/pacijenti")
public class PacijentController {
    @Autowired
    private PacijentService pacijentService;

    //signUp metoda sa fronta za registraciju pacijenata iskljucivo
    @PostMapping(consumes = "application/json")
    public ResponseEntity<PacijentDTO> signUp(@RequestBody PacijentDTO pacijentDTO) throws ParseException {

        Pacijent p = pacijentService.findOneByUsername(pacijentDTO.getKorisnickoIme());
        if(p != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        p = pacijentService.findOneByEmail(pacijentDTO.getEmail());
        if(p != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Pacijent pacijent = new Pacijent();
        pacijent.setIme(pacijentDTO.getIme());
        pacijent.setPrezime(pacijentDTO.getPrezime());
        pacijent.setEmail(pacijentDTO.getEmail());
        pacijent.setKorisnickoIme(pacijentDTO.getKorisnickoIme());
        pacijent.setLozinka(pacijentDTO.getLozinka());

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date = df.parse(pacijentDTO.getDatumRodjenja());

        pacijent.setDatumRodjenja(date);
        pacijent.setDatumRegistrovanja(new Date());

        pacijent = pacijentService.save(pacijent);

        return new ResponseEntity<>(new PacijentDTO(pacijent), HttpStatus.CREATED);
    }

}
