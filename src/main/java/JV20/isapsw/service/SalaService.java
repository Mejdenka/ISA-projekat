package JV20.isapsw.service;

import JV20.isapsw.model.*;
import JV20.isapsw.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private PregledService pregledService;
    @Autowired
    private TerminService terminService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private KlinikaService klinikaService;
    @Autowired
    private OperacijaService operacijaService;

    public Sala findOne(Long id) {
        return salaRepository.findById(id).orElseGet(null);
    }

    public Sala findOneByNaziv(String naziv) {
        return salaRepository.findByNaziv(naziv);
    }

    public Sala findOneByBroj(Long broj){ return salaRepository.findByBroj(broj);}

    public void saveNew(Sala sala) {
        sala.setKlinikaSale(this.klinikaService.findOne(sala.getIdKlinike()));
        sala.setObrisana(false);
        sala.setSlobodna(true);
        sala.setRezervisana(false);
        save(sala);
    }

    public void dodijeliSaluPregledu(Pregled pregled, Long brojSale) throws InterruptedException {
        Sala sala = findOneByBroj(brojSale);
        pregled.setSala(sala);
        sala.getPregledi().add(pregled);
        pregledService.save(pregled);
        save(sala);
        emailService.sendPregledEmail(pregled, brojSale);
    }

    public void dodijeliSaluPregleduAutomatski(Pregled pregled, Klinika klinika) throws ParseException {
        if(pregledService.findOne(pregled.getId()).getSala() != null)
            return;

        Klinika k = klinikaService.findOne(klinika.getId());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strPocetak = dateFormat.format(pregled.getTermin().getPocetak());
        boolean pronasao = false;
        Sala s = new Sala();

        for(Sala sala : k.getSale()){
           if(daLiJeZauzeta(sala.getBroj(), strPocetak)){
               continue;
           }else {
               pronasao = true;
               s = sala;
               break;
           }
        }
        if(pronasao){
            pregled.setSala(s);
            return;
        } else {
            Sala sala = klinika.getSale().get(0);
            dodijeliSaluPregleduIduciTermin(pregled, sala.getBroj(), strPocetak);
        }
    }

    public void dodijeliSaluPregleduIduciTermin(Pregled pregled, Long brojSale, String datumStr) throws ParseException {
        Sala sala = findOneByBroj(brojSale);
        Date datumPocetka = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datumStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datumPocetka);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        Date datumKraja = calendar.getTime();

        pregled.setSala(sala);
        Termin termin = new Termin();
        termin.setRezervisan(true);
        termin.setPocetak(datumPocetka);
        termin.setKraj(datumKraja);
        termin.setPacijentId(pregled.getPacijent().getId());
        termin.setKlinikaTermina(pregled.getKlinikaPregleda());
        terminService.save(termin);
        pregled.setTermin(termin);
        sala.getPregledi().add(pregled);
        pregledService.save(pregled);
        save(sala);
    }

    //pomocna metoda da vidimo da li sala ima rezervisanih pregleda za dati datum
    public boolean daLiJeZauzeta(Long broj, String datum){
        Sala sala = findOneByBroj(broj);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Pregled p : sala.getPregledi()) {
            String datumPregleda = df.format(p.getTermin().getPocetak());
            if(datum.equals(datumPregleda.substring(0, 10)) && !p.isObavljen() && !p.isObrisan()){
                return true;
            }
        }
        for(Operacija o : sala.getOperacije()){
            String datumOperacije = df.format(o.getTermin().getKraj());
            if(datum.equals(datumOperacije.substring(0, 10)) && !o.isObavljena() && !o.isObrisana()){
                System.out.println(datumOperacije.substring(0,10));
                return true;
            }
        }
        return false;
    }

    public String findIfNotReserved(Long broj, String datum) throws ParseException {

        if(!daLiJeZauzeta(broj, datum))
            return ("SLOBODNA");
        else
        {
            do
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                c.setTime(sdf.parse(datum));
                c.add(Calendar.DATE, 1);  // number of days to add
                datum = sdf.format(c.getTime());
                System.out.println(datum);
            } while (daLiJeZauzeta(broj, datum));
            return datum;
        }
    }

    /*public void dodijeliSaluOperaciji(Operacija operacija, Long brojSale) throws InterruptedException {
        Sala sala = findOneByBroj(brojSale);
        operacija.setSalaOperacije(sala);
        sala.getOperacije().add(operacija);
        operacijaService.save(operacija);
        save(sala);
        //emailService.sendPregledEmail(pregled, brojSale);
    }*/

    public List<Sala> findAll() {
        return salaRepository.findAll();
    }

    public Page<Sala> findAll(Pageable page) {
        return salaRepository.findAll(page);
    }

    public Sala save(Sala sala) {
        return salaRepository.save(sala);
    }

    public void remove(Long id) {
        salaRepository.deleteById(id);
    }
}
