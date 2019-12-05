package JV20.isapsw.controller;

import JV20.isapsw.dto.KorisnikDTO;
import JV20.isapsw.model.*;
import JV20.isapsw.security.auth.JwtAuthenticationRequest;
import JV20.isapsw.security.TokenUtils;
import JV20.isapsw.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
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
import java.util.Collection;
import java.util.List;

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
        //prvo ce da trazi adminsku ulogu, ako je nema onda ulazi u else if za usera
        if(userHasAuthority(ulogovan, "ROLE_ADMIN")){
            return "ADMIN";
        }
        else if(userHasAuthority(ulogovan, "ROLE_USER")){
            if(ulogovan instanceof Pacijent){
                return "PACIJENT";
            } else if (ulogovan instanceof Lekar){
                return "LEKAR";
            } else if (ulogovan instanceof MedicinskaSestra){
                return "MEDICINSKA_SESTRA";
            }  else if (ulogovan instanceof AdministratorKlinike){
                return "ADMINISTRATOR_KLINIKE";
            } else {
                return "USER";
            }
        }

        return null;
    }

    //pomocne metode
    public static boolean userHasAuthority(Korisnik k, String authority)
    {
        Collection<? extends GrantedAuthority> authorities = getUserAuthorities(k);

        for (GrantedAuthority grantedAuthority : authorities) {
            if (authority.equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }

        return false;
    }

    private static Collection<? extends GrantedAuthority> getUserAuthorities(Korisnik korisnik) {
        return korisnik.getAuthorities();
    }



}
