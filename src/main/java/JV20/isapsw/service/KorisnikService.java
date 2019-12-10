package JV20.isapsw.service;

import JV20.isapsw.common.TimeProvider;
import JV20.isapsw.model.Authority;
import JV20.isapsw.model.Korisnik;
import JV20.isapsw.model.Pacijent;
import JV20.isapsw.model.UserRequest;
import JV20.isapsw.repository.AuthorityRepository;
import JV20.isapsw.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class KorisnikService{
    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TimeProvider timeProvider;
    @Autowired
    private AuthorityService authService;

    public Korisnik findOne(Long id) throws AccessDeniedException {
        return korisnikRepository.findById(id).orElseGet(null);
    }

    public Korisnik findOneByUsername(String korisnickoIme) throws UsernameNotFoundException {
        return korisnikRepository.findByKorisnickoIme(korisnickoIme);
    }

    public Korisnik findOneByEmail(String email) {
        return korisnikRepository.findByEmail(email);
    }

    public List<Korisnik> findAll() throws AccessDeniedException{
        return korisnikRepository.findAll();
    }

    public Page<Korisnik> findAll(Pageable page) {
        return korisnikRepository.findAll(page);
    }

    public Korisnik save(UserRequest userRequest) throws ParseException {
        Korisnik u = new Korisnik();
        u.setKorisnickoIme(userRequest.getKorisnickoIme());
        u.setLozinka(passwordEncoder.encode(userRequest.getLozinka()));
        u.setIme(userRequest.getIme());
        u.setPrezime(userRequest.getPrezime());
        u.setEmail(userRequest.getEmail());
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date = df.parse(userRequest.getDatumRodjenja());
        u.setDatumRodjenja(date);
        u.setDatumRegistrovanja(timeProvider.now());
        u.setEnabled(true);

        List<Authority> auth = new ArrayList<>();
        auth.add(authService.findByname("ROLE_USER"));
        u.setAuthorities(auth);

        u = this.korisnikRepository.save(u);
        return u;
    }

    public void remove(Long id) {
        korisnikRepository.deleteById(id);
    }
}
