package JV20.isapsw.service;

import JV20.isapsw.model.*;
import JV20.isapsw.repository.KlinikaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class KlinikaService {
    @Autowired
    private KlinikaRepository klinikaRepository;

    public Klinika findOne(Long id) {
        return klinikaRepository.findById(id).orElseGet(null);
    }

    public Klinika findByNaziv(String naziv) {return klinikaRepository.findByNaziv(naziv);}

    public List<Klinika> findAll() {
        return klinikaRepository.findAll();
    }

    public Klinika save(Klinika klinika) {
        return klinikaRepository.save(klinika);
    }

    public List<Klinika> findAllBy(String tip, String datum, String lokacija, String ocjena){
        List<Klinika> filtrirane = new ArrayList<Klinika>();

        for (Klinika k : klinikaRepository.findAll())
        {

            Boolean nadjeno = false;

            for (Termin t : k.getSlobodniTermini())
            {

                Calendar cal = Calendar.getInstance();
                cal.setTime(t.getPocetak());
                int day= cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH)+1;
                int year = cal.get(Calendar.YEAR);

                if( Integer.parseInt(datum.substring(0,4)) == year )
                {
                    if( Integer.parseInt(datum.substring(5,7)) == month )
                    {
                        if( Integer.parseInt(datum.substring(8,10)) == day )
                        {
                            nadjeno = true;
                        }
                    }
                }
            }
            System.out.println("PRVA TACKA");
            if (!nadjeno)
            {
                continue;
            }
            System.out.println("DRUGA TACKA");

            nadjeno = false;

            if (!lokacija.equals("NULL"))
            {
                if (!k.getLokacija().startsWith(lokacija)){
                    continue;
                }
            }

            if (k.getProsecnaOcena() < Double.parseDouble(ocjena))
            {
                continue;
            }

            for (TipPregleda tp : k.getTipoviPregleda())
            {
                if (tp.getNaziv().equals(tip))
                {
                    nadjeno = true;
                    break;
                }
            }

            if (nadjeno) filtrirane.add(k);
        }

        return filtrirane;
    }

    public void remove(Long id) {
        klinikaRepository.deleteById(id);
    }
}
