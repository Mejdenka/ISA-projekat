package JV20.isapsw.controller;

import JV20.isapsw.dto.AdministratorKlinickogCentraDTO;
import JV20.isapsw.dto.KorisnikDTO;
import JV20.isapsw.exception.ResourceConflictException;
import JV20.isapsw.model.*;
import JV20.isapsw.security.auth.JwtAuthenticationRequest;
import JV20.isapsw.security.TokenUtils;
import JV20.isapsw.service.CustomUserDetailsService;
import JV20.isapsw.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

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

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.text.ParseException;
import java.util.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.util.UriComponentsBuilder;

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

    @Autowired
    private CustomUserDetailsService userDetailsService;


    //Metoda za login
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException, IOException {

        Korisnik korisnik = korisnikService.findOneByUsername(authenticationRequest.getUsername());

        if(korisnik == null || !korisnik.isConfirmed()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        KorisnikDTO korisnikDTO = new KorisnikDTO(korisnik);
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));

        // Ubaci username + password u kontext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token
        Korisnik user = (Korisnik) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();

        // Vrati token kao odgovor na uspesno autentifikaciju
        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/korisnik/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Korisnik loadById(@PathVariable Long userId) throws AccessDeniedException {
        return this.korisnikService.findOne(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getKlinikaAdmina/{userId}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public Klinika getKlinika(@PathVariable Long userId) throws AccessDeniedException {
        AdministratorKlinike ak = (AdministratorKlinike) korisnikService.findOne(userId);
        return ak.getKlinikaAdmina();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/izmenaPodataka")
    @PreAuthorize("hasRole('USER')")
    public Korisnik izmenaPodataka(@RequestBody Korisnik korisnik) throws AccessDeniedException {
        Korisnik k = korisnikService.findOne(korisnik.getId());
        k.setKorisnickoIme(korisnik.getKorisnickoIme());
        k.setIme(korisnik.getIme());
        k.setPrezime(korisnik.getPrezime());
        k.setEmail(korisnik.getEmail());
        k.setJbo(korisnik.getJbo());
        korisnikService.save(k);
        return k;
    }


    @RequestMapping("/whoami")
    @PreAuthorize("hasRole('USER')")
    public Korisnik user() {
        return this.korisnikService.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @RequestMapping("/getMyAuthority")
    @PreAuthorize("hasRole('USER')")
    public String getMyAuthority() {
        Korisnik ulogovan = korisnikService.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        for (GrantedAuthority a : ulogovan.getAuthorities()) {
            //Ako ima administratorsku rolu, vrati nju jer je prioritetnija od korisnicke role
            if (a.getAuthority().equals("ROLE_ADMIN")) {
                return a.getAuthority();
            }

            //ako nema administratorsku, neoj vracati korisnicku nego specificnu ulogu
            if (!a.getAuthority().equals("ROLE_USER")) {
                return a.getAuthority();
            }
        }

        return null;
    }


    @RequestMapping(value = "/changePass", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
        userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/changePassFirstTime", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    public Korisnik changePassFirstTime(@RequestBody PasswordChanger passwordChanger) {
       return userDetailsService.changePasswordFirstTime(passwordChanger.newPassword);
    }

    static class PasswordChanger {
        public String oldPassword;
        public String newPassword;
    }
}
