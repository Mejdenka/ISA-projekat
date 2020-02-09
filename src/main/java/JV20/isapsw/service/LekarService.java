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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private TipPregledaService tipPregledaService;

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
        zaIzmenu.setZbirOcena(lekar.getZbirOcena());
        zaIzmenu.setBrojOcena(lekar.getBrojOcena());
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
        lekar.setBrojOcena(0);
        lekar.setZbirOcena(0);
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
                if (danas.get(Calendar.HOUR_OF_DAY) == datum.get(Calendar.HOUR_OF_DAY) -1 && danas.get(Calendar.DATE) == datum.get(Calendar.DATE)) {
                    lekar.setTrajePregled(zapoceo);
                    lekar.setSlobodan(false);
                    save(lekar);
                    return true;
                }
            }
        }
        /////////////////////////////false
        return true;
    }

    public List<Termin> getSlobodniTermini(Long lekarId, String datum, String tipPregleda){
        List<Termin> termini = new ArrayList<>();

        Lekar l = findOne(lekarId);
        List<Termin> zauzetiTermini = new ArrayList<>();

        for (Pregled p : l.getPregledi()){
            Calendar dat1 = Calendar.getInstance();
            dat1.setTime(p.getTermin().getPocetak());
            int dan = dat1.get(Calendar.DAY_OF_YEAR);
            int mjesec = dat1.get(Calendar.MONTH) + 1;
            int godina = dat1.get(Calendar.YEAR);
            int godina1 = Integer.parseInt(datum.substring(0,4));
            int mjesec1 = Integer.parseInt(datum.substring(5,7));
            int dan1 = Integer.parseInt(datum.substring(8,10));

            if (dan == dan1 && mjesec == mjesec1 && godina == godina1){
                zauzetiTermini.add(p.getTermin());
            }
        }

        zauzetiTermini.sort(Comparator.comparing(Termin::getPocetak));

        int sat = Integer.parseInt(l.getRadnoVreme().substring(0,2));
        int minut = Integer.parseInt(l.getRadnoVreme().substring(3,5));

        if (zauzetiTermini.size() == 0){
            return termini;
        }

        if (zauzetiTermini.size() == 1){
            Calendar cal = Calendar.getInstance();
            cal.setTime(zauzetiTermini.get(0).getPocetak());
            int hour = cal.get(Calendar.HOUR);
            int minute = cal.get(Calendar.MINUTE);

            int period = hour * 60 + minute - sat * 60 + minut;
            if (period > 10){ // Bar 10 minuta od pocetka radnog vremena do prvog pregleda
                Date dateP = zauzetiTermini.get(0).getPocetak();
                dateP.setTime(dateP.getTime()- period *60000);
                Date dateK = zauzetiTermini.get(0).getPocetak();
                Termin t = new Termin(dateP, dateK);
                termini.add(t);
            }

            Date dateP = zauzetiTermini.get(0).getKraj();
            Date dateK = zauzetiTermini.get(0).getKraj();


            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(zauzetiTermini.get(0).getKraj());
            int hour2 = cal2.get(Calendar.HOUR);
            int minute2 = cal2.get(Calendar.MINUTE);

            int satKraja = Integer.parseInt(l.getRadnoVreme().substring(6,8));
            int minutKraja = Integer.parseInt(l.getRadnoVreme().substring(9,11));

            int period2 = satKraja * 60 + minutKraja - hour2 * 60 + minute2;
            if (period2 > 10) { // Bar 10 minuta do kraja radnog vremena

                dateK.setTime(dateK.getTime() + period2 * 60000);
                Termin t = new Termin(dateP, dateK);
                termini.add(t);
            }

            return termini;
        }



        return termini;
    }
}
