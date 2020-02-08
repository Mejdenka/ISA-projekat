package JV20.isapsw.service;

import JV20.isapsw.common.TimeProvider;
import JV20.isapsw.dto.*;
import JV20.isapsw.model.*;
import JV20.isapsw.repository.KlinikaRepository;
import JV20.isapsw.repository.LekarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private KlinikaService klinikaService;

    public Lekar findOne(Long id) {
        return lekarRepository.findById(id).orElseGet(null);
    }

    public List<Lekar> findAll() {
        return lekarRepository.findAll();
    }

    public Page<Lekar> findAll(Pageable page) {
        return lekarRepository.findAll(page);
    }

    public Lekar izmenaLekara(Lekar lekar, Lekar zaIzmenu) {
        Klinika klinika = klinikaService.findOne(zaIzmenu.getKlinikaLekara().getId());
        //AKO IMA PREGLEDA ZAKAZANIH KOD TOG LJEKARA
        for(Pregled pregled : klinika.getPregledi()){
            if(!pregled.isObrisan() && !pregled.isObavljen() && pregled.getLekar().getId().equals(zaIzmenu.getId())){
                return null;
            }
        }
        zaIzmenu.setKorisnickoIme(lekar.getKorisnickoIme());
        zaIzmenu.setIme(lekar.getIme());
        zaIzmenu.setPrezime(lekar.getPrezime());
        zaIzmenu.setEmail(lekar.getEmail());
        zaIzmenu.setDatumRodjenja(lekar.getDatumRodjenja());
        zaIzmenu.setOcena(lekar.getOcena());
        zaIzmenu.setRadnoVreme(lekar.getRadnoVreme());
        return save(zaIzmenu);
    }

    public Lekar obrisiLekara(Long lekarId) {
        Lekar lekar = findOne(lekarId);
        Klinika klinika = klinikaService.findOne(lekar.getKlinikaLekara().getId());
        //AKO IMA PREGLEDA ZAKAZANIH KOD TOG LJEKARA
        for(Pregled pregled : klinika.getPregledi()){
            if(!pregled.isObrisan() && !pregled.isObavljen() && pregled.getLekar().getId().equals(lekarId)){
                return null;
            }
        }
        lekar.setObrisan(true);
        return save(lekar);
    }


    public List<PregledDTO> getZakazaniPregledi(Long lekarId) {
        Lekar lekar = findOne(lekarId);
        List<PregledDTO> retVal = new ArrayList<>();

        for(Pregled p:lekar.getPregledi()){
            if(!p.isObavljen() && !p.isObrisan()){
                PregledDTO pregledDTO = new PregledDTO();
                pregledDTO.setId(p.getId());
                pregledDTO.setLekar(new LekarDTO(p.getLekar()));
                pregledDTO.setTermin(new TerminDTO(p.getTermin()));
                pregledDTO.setTipPregleda(p.getTipPregleda());
                if(p.getSala()!=null)
                    pregledDTO.setSala(p.getSala());
                if(p.getPacijent()!=null)
                    pregledDTO.setPacijent(new PacijentDTO(p.getPacijent()));
                retVal.add(pregledDTO);
            }
        }
        return retVal;
    }

    public List<OperacijaDTO> getZakazaneOperacije(Long lekarId) {
        Lekar lekar = findOne(lekarId);
        List<OperacijaDTO> retVal = new ArrayList<>();

        for(Operacija o:lekar.getOperacije()){
            if(!o.isObavljena() && !o.isObrisana()){
                OperacijaDTO operacijaDTO = new OperacijaDTO();
                operacijaDTO.setId(o.getId());
                operacijaDTO.setLekar(new LekarDTO(o.getLekar()));
                operacijaDTO.setTermin(new TerminDTO(o.getTermin()));
                if(o.getPacijent()!=null)
                    operacijaDTO.setPacijent(new PacijentDTO(o.getPacijent()));
                retVal.add(operacijaDTO);
            }
        }
        return retVal;
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
        Timestamp ts=new Timestamp(timeProvider.now().getTime());
        lekar.setLastPasswordResetDate(ts);

        List<Authority> auth = new ArrayList<>();
        auth.add(authService.findByname("ROLE_DOKTOR"));
        auth.add(authService.findByname("ROLE_USER"));
        lekar.setAuthorities(auth);

        lekar.setKlinikaLekara(klinikaService.findOne(userRequest.getIdKlinike()));
        lekar.setRadnoVreme(userRequest.getRadnoVreme());
        lekar.setPromijenjenaLozinka(false);

        lekar = this.lekarRepository.save(lekar);
        return lekar;
    }

    public Lekar save(Lekar lekar) {
        return  this.lekarRepository.save(lekar);
    }

    public void remove(Long id) {
        lekarRepository.deleteById(id);
    }

    public boolean zapocniPregled(boolean zapoceo, Long pacijentId) {

        Lekar lekar = (Lekar) this.korisnikService.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        for (Pregled pregled : lekar.getPregledi()) {
            if (pregled.getPacijent() != null && pregled.getPacijent().getId().equals(pacijentId)) {
                Calendar datum = Calendar.getInstance();
                datum.setTime(pregled.getTermin().getPocetak());
                Calendar danas = Calendar.getInstance();
                System.out.println(formatter.format(danas.getTime()));
                System.out.println(formatter.format(datum.getTime()));
                if (danas.get(Calendar.HOUR_OF_DAY) == datum.get(Calendar.HOUR_OF_DAY) - 1 && danas.get(Calendar.DATE) == datum.get(Calendar.DATE)) {
                    lekar.setTrajePregled(zapoceo);
                    lekar.setSlobodan(false);
                    save(lekar);
                    return true;
                }
            }
        }
        // OVDE TREBA NAMESTITI - BILO JE FALSE
        return true;
    }
}
