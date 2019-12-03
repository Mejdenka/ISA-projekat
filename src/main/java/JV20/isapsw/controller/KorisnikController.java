package JV20.isapsw.controller;

import JV20.isapsw.dto.KorisnikDTO;
import JV20.isapsw.dto.KorisnikDTO;
import JV20.isapsw.dto.KorisnikDTO;
import JV20.isapsw.model.Korisnik;
import JV20.isapsw.model.Korisnik;
import JV20.isapsw.model.Korisnik;
import JV20.isapsw.model.UserTokenState;
import JV20.isapsw.security.JwtAuthenticationRequest;
import JV20.isapsw.security.TokenUtils;
import JV20.isapsw.service.CustomUserDetailsService;
import JV20.isapsw.service.KorisnikService;
import JV20.isapsw.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping(value = "api/korisnici")
public class KorisnikController {
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Metoda za login
    //@RequestMapping(value = "/login", method = RequestMethod.POST) //ranije radilo i sa ovim sada error 400?????
    @PostMapping(consumes = "application/json")     //sada error 405 kada je u bazi hesirana sifra sta??
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException, IOException {

        Korisnik korisnik = korisnikService.findOneByUsername(authenticationRequest.getUsername());
        if(korisnik == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println(passwordEncoder.encode(authenticationRequest.getPassword()));    //kako poredim sifre????

        KorisnikDTO korisnikDTO = new KorisnikDTO(korisnik);
        System.out.println("USLO1 " + authenticationRequest.getUsername());

        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));

        // Ubaci username + password u kontext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token
        Korisnik user = (Korisnik) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();

        System.out.println(jwt.toString());

        // Vrati token kao odgovor na uspesno autentifikaciju
        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
        //KAKO NA FRONTU DA CUVAM TOKEN???????????????????????????????

        //return new ResponseEntity<>(HttpStatus.OK);

    }

    //Metoda za vraćanje informacija o korisniku
    /*@GetMapping(value = "{username}")
    public ResponseEntity<KorisnikDTO> vratiKorisnika(@PathVariable String username) {
        Korisnik korisnik = korisnikService.findOneByUsername(username);

        if(korisnik == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        KorisnikDTO korisnikDTO = new KorisnikDTO(korisnik);
        return new ResponseEntity<>(korisnikDTO, HttpStatus.OK);

    }
    */

    @RequestMapping(method = RequestMethod.GET, value = "/korisnik/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Korisnik loadById(@PathVariable Long userId) throws AccessDeniedException {
        return this.korisnikService.findOne(userId);
    }

    @RequestMapping("/whoami")
    @PreAuthorize("hasRole('USER')")
    public Korisnik user(Principal user) {
        return this.korisnikService.findOneByUsername(user.getName());
    }
}
