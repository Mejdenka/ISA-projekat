package JV20.isapsw.service;

import JV20.isapsw.common.TimeProvider;
import JV20.isapsw.model.*;
import JV20.isapsw.repository.KlinikaRepository;
import JV20.isapsw.repository.LekarRepository;

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
public class LekarService {

    @Autowired
    private LekarRepository lekarRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TimeProvider timeProvider;
    @Autowired
    private AuthorityService authService;

    public Lekar findOne(Long id) {
        return lekarRepository.findById(id).orElseGet(null);
    }

    public List<Lekar> findAll() {
        return lekarRepository.findAll();
    }

    public Page<Lekar> findAll(Pageable page) {
        return lekarRepository.findAll(page);
    }

    public Lekar save(UserRequest userRequest) throws ParseException {
        Lekar lekar = new Lekar();
        lekar.setKorisnickoIme(userRequest.getKorisnickoIme());
        lekar.setLozinka(passwordEncoder.encode(userRequest.getLozinka()));
        lekar.setIme(userRequest.getIme());
        lekar.setPrezime(userRequest.getPrezime());
        lekar.setEmail(userRequest.getEmail());
        lekar.setOcena(5);
        //SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        //Date date = df.parse(userRequest.getDatumRodjenja());
        lekar.setDatumRodjenja(timeProvider.now());
        lekar.setDatumRegistrovanja(timeProvider.now());
        lekar.setEnabled(true);
        lekar.setConfirmed(true);

        List<Authority> auth = new ArrayList<>();
        auth.add(authService.findByname("ROLE_DOKTOR"));
        auth.add(authService.findByname("ROLE_USER"));
        lekar.setAuthorities(auth);

        lekar = this.lekarRepository.save(lekar);
        return lekar;
    }
    public Lekar save(Lekar lekar) {
        return  this.lekarRepository.save(lekar);
    }
    public void remove(Long id) {
        lekarRepository.deleteById(id);
    }
}
