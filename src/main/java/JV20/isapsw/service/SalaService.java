package JV20.isapsw.service;

import JV20.isapsw.model.Pregled;
import JV20.isapsw.model.Sala;
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

    public Sala findOne(Long id) {
        return salaRepository.findById(id).orElseGet(null);
    }

    public Sala findOneByNaziv(String naziv) {
        return salaRepository.findByNaziv(naziv);
    }

    public Sala findOneByBroj(Long broj){ return salaRepository.findByBroj(broj);}

    //pomocna metoda da vidimo da li sala ima rezervisanih pregleda za dati datum
    public boolean daLiJeZauzeta(Long broj, String datum){
        Sala sala = findOneByBroj(broj);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Pregled p : sala.getPregledi()) {
            String datumPregleda = df.format(p.getTermin().getPocetak());
            if(datum.equals(datumPregleda.substring(0, 10)) && !p.isObavljen() && !p.isObrisan()){
                System.out.println(datumPregleda.substring(0,10));
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
