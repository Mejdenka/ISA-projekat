package JV20.isapsw.controller;

import JV20.isapsw.dto.AdministratorKlinickogCentraDTO;
import JV20.isapsw.dto.KorisnikDTO;
import JV20.isapsw.exception.ResourceConflictException;
import JV20.isapsw.model.*;
import JV20.isapsw.security.auth.JwtAuthenticationRequest;
import JV20.isapsw.security.TokenUtils;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    //Metoda za login
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException, IOException {

        Korisnik korisnik = korisnikService.findOneByUsername(authenticationRequest.getUsername());
        if(korisnik == null){
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

    @RequestMapping("/changePass")
    @PreAuthorize("hasRole('USER')")
    public boolean getMyAuthority(@RequestBody String username, String oldPass, String newPass) {
        Korisnik korisnik = korisnikService.findOneByUsername(username);

        if (korisnik.getPassword() != oldPass ){
            System.out.println(oldPass+ " " + korisnik.getPassword());
            return false;
        }

        korisnik.setLozinka(newPass);
        korisnikService.save(korisnik);
        return true;
    }
}
