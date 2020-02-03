package JV20.isapsw.service;

import JV20.isapsw.common.TimeProvider;
import JV20.isapsw.model.Authority;
import JV20.isapsw.model.Korisnik;
import JV20.isapsw.model.Pacijent;
import JV20.isapsw.model.UserRequest;
import JV20.isapsw.repository.PacijentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PacijentService {

    @Autowired
    private PacijentRepository pacijentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TimeProvider timeProvider;
    @Autowired
    private AuthorityService authService;

    public Pacijent findOne(Long id) {
        return pacijentRepository.findById(id).orElseGet(null);
    }

    public Pacijent findOneByUsername(String korisnickoIme) {
        return pacijentRepository.findByKorisnickoIme(korisnickoIme);
    }

    public Pacijent findOneByEmail(String email) {
        return pacijentRepository.findByKorisnickoIme(email);
    }

    public List<Pacijent> findAll() {
        return pacijentRepository.findAll();
    }

    public Page<Pacijent> findAll(Pageable page) {
        return pacijentRepository.findAll(page);
    }

    public Pacijent save(UserRequest userRequest) throws ParseException {
        Pacijent pacijent = new Pacijent();
        pacijent.setKorisnickoIme(userRequest.getKorisnickoIme());
        pacijent.setLozinka(passwordEncoder.encode(userRequest.getLozinka()));
        pacijent.setIme(userRequest.getIme());
        pacijent.setPrezime(userRequest.getPrezime());
        pacijent.setEmail(userRequest.getEmail());
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date = df.parse(userRequest.getDatumRodjenja());
        pacijent.setDatumRodjenja(date);
        pacijent.setDatumRegistrovanja(timeProvider.now());
        pacijent.setJbo(userRequest.getJbo());
        pacijent.setEnabled(true);

        List<Authority> auth = new ArrayList<>();
        auth.add(authService.findByname("ROLE_PACIJENT"));
        auth.add(authService.findByname("ROLE_USER"));
        pacijent.setAuthorities(auth);

        pacijent = this.pacijentRepository.save(pacijent);
        return pacijent;
    }

    public Pacijent save(Pacijent pacijent) { return this.pacijentRepository.save(pacijent);}

    public void remove(Long id) {
        pacijentRepository.deleteById(id);
    }
}
