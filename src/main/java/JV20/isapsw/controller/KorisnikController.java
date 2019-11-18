package JV20.isapsw.controller;

import JV20.isapsw.dto.KorisnikDTO;
import JV20.isapsw.dto.KorisnikDTO;
import JV20.isapsw.dto.KorisnikDTO;
import JV20.isapsw.model.Korisnik;
import JV20.isapsw.model.Korisnik;
import JV20.isapsw.model.Korisnik;
import JV20.isapsw.service.KorisnikService;
import JV20.isapsw.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "api/korisnici")
public class KorisnikController {
    @Autowired
    private KorisnikService korisnikService;

    //Metoda za login
    @GetMapping(value = "{username}/{password}")
    public ResponseEntity<KorisnikDTO> logIn(@PathVariable String username, @PathVariable String password) {
        Korisnik korisnik = korisnikService.findOneByUsername(username);
        if(korisnik == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        KorisnikDTO korisnikDTO = new KorisnikDTO(korisnik);

        if(password.equals(korisnikDTO.getLozinka())){
            return new ResponseEntity<>(korisnikDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    //Metoda za vraćanje informacija o korisniku
    @GetMapping(value = "{username}")
    public ResponseEntity<KorisnikDTO> vratiKorisnika(@PathVariable String username) {
        Korisnik korisnik = korisnikService.findOneByUsername(username);

        if(korisnik == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        KorisnikDTO korisnikDTO = new KorisnikDTO(korisnik);

        return new ResponseEntity<>(korisnikDTO, HttpStatus.OK);

    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<KorisnikDTO> signUp(@RequestBody KorisnikDTO korisnikDTO) throws ParseException {

        Korisnik k = korisnikService.findOneByUsername(korisnikDTO.getKorisnickoIme());
        if(k != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        k = korisnikService.findOneByEmail(korisnikDTO.getEmail());
        if(k != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Korisnik korisnik = new Korisnik();
        korisnik.setIme(korisnikDTO.getIme());
        korisnik.setPrezime(korisnikDTO.getPrezime());
        korisnik.setEmail(korisnikDTO.getEmail());
        korisnik.setKorisnickoIme(korisnikDTO.getKorisnickoIme());
        korisnik.setLozinka(korisnikDTO.getLozinka());

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date = df.parse(korisnikDTO.getDatumRodjenja());

        korisnik.setDatumRodjenja(date);
        korisnik.setDatumRegistrovanja(new Date());

        korisnik = korisnikService.save(korisnik);

        return new ResponseEntity<>(new KorisnikDTO(korisnik), HttpStatus.CREATED);
    }
}
