package JV20.isapsw.controller;

import JV20.isapsw.dto.AdministratorKlinickogCentraDTO;
import JV20.isapsw.model.AdministratorKlinickogCentra;
import JV20.isapsw.service.AdministratorKlinickogCentraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "api/adminKC")
public class AdministratorKlinickogCentraController {
    @Autowired
    private AdministratorKlinickogCentraService akcService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<AdministratorKlinickogCentraDTO> registracijaAdminaKC(@RequestBody AdministratorKlinickogCentraDTO akcDTO) throws ParseException {

        AdministratorKlinickogCentra akc = akcService.findOneByUsername(akcDTO.getKorisnickoIme());
        if(akc != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        akc = new AdministratorKlinickogCentra();
        akc.setIme(akcDTO.getIme());
        akc.setPrezime(akcDTO.getPrezime());
        akc.setKorisnickoIme(akcDTO.getKorisnickoIme());
        akc.setLozinka(akcDTO.getLozinka());
        akc.setDatumRodjenja(new Date());

        akc = akcService.save(akc);

        return new ResponseEntity<>(new AdministratorKlinickogCentraDTO(akc), HttpStatus.CREATED);
    }
}
