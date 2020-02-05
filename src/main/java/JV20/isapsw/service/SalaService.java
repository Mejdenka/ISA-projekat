package JV20.isapsw.service;

import JV20.isapsw.model.Operacija;
import JV20.isapsw.model.Pregled;
import JV20.isapsw.model.Sala;
import JV20.isapsw.model.Termin;
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

    public Sala findOne(Long id) {
        return salaRepository.findById(id).orElseGet(null);
    }

    public Sala findOneByNaziv(String naziv) {
        return salaRepository.findByNaziv(naziv);
    }

    public Sala findOneByBroj(Long broj){ return salaRepository.findByBroj(broj);}

    public void dodijeliSaluPregledu(Pregled pregled, Long brojSale){
        Sala sala = findOneByBroj(brojSale);
        pregled.setSala(sala);
        sala.getPregledi().add(pregled);
        pregledService.save(pregled);
        save(sala);
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
