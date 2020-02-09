package JV20.isapsw.service;

import JV20.isapsw.constants.LekarConstants;
import JV20.isapsw.constants.SalaConstants;
import JV20.isapsw.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SalaServiceTest {
    @Autowired
    private SalaService salaService;

    @Autowired
    private LekarService lekarService;

    @Autowired
    private PregledService pregledService;

    @Test
    public void findOne(){
        Sala sala = salaService.findOne(SalaConstants.ID_SALE);
        assertThat(sala).isNotNull();
        assertThat(sala.getNaziv()).isEqualTo(SalaConstants.NAZIV_SALE);
        assertThat(sala.getBroj()).isEqualTo(SalaConstants.BROJ_SALE);
        assertThat(sala.isRezervisana()).isEqualTo(SalaConstants.REZERVISANA);
        assertThat(sala.isSlobodna()).isEqualTo(SalaConstants.SLOBODNA);
    }

    @Test
    public void findAll(){
        List<Sala> sale = salaService.findAll();
        assertThat(sale).hasSize(SalaConstants.UKUPNO_SALA);
        assertThat(sale.size()).isEqualTo(SalaConstants.UKUPNO_SALA);
    }

    @Test
    public void findOneByNaziv(){
        Sala sala = salaService.findOneByNaziv(SalaConstants.NAZIV_SALE);
        assertThat(sala.getId()).isEqualTo(SalaConstants.ID_SALE);
        assertThat(sala.getBroj()).isEqualTo(SalaConstants.BROJ_SALE);
        assertThat(sala.isRezervisana()).isEqualTo(SalaConstants.REZERVISANA);
        assertThat(sala.isSlobodna()).isEqualTo(SalaConstants.SLOBODNA);
    }

    @Test
    public void findOneByBroj(){
        Sala sala = salaService.findOneByBroj(SalaConstants.BROJ_SALE);
        assertThat(sala.getId()).isEqualTo(SalaConstants.ID_SALE);
        assertThat(sala.getNaziv()).isEqualTo(SalaConstants.NAZIV_SALE);
        assertThat(sala.isRezervisana()).isEqualTo(SalaConstants.REZERVISANA);
        assertThat(sala.isSlobodna()).isEqualTo(SalaConstants.SLOBODNA);
    }

    @Test
    public void saveNew(){
        Sala sala = new Sala(4L, "Sala4", 4L, true, false);
        sala.setIdKlinike(1L);
        salaService.saveNew(sala);

        Sala test = salaService.findOne(4L);
        assertThat(test.getIdKlinike()).isNotNull();
        assertThat(test.getNaziv()).isEqualTo("Sala4");
        assertThat(test.getBroj()).isEqualTo(4L);
        assertThat(test.isSlobodna()).isEqualTo(true);
        assertThat(test.isRezervisana()).isEqualTo(false);

    }

    @Test
    public void dodeliSaluPregledu(){
        Termin termin = new Termin();
        Lekar lekar = new Lekar();
        Pacijent pacijent = new Pacijent();
        Klinika klinika = new Klinika();
        Pregled pregled = new Pregled(6L, termin, pacijent, lekar, false, false, klinika);
        Long brojSale = 1L;
        Sala sala = salaService.findOneByBroj(brojSale);
        pregled.setSala(sala);
        sala.getPregledi().add(pregled);
        pregledService.save(pregled);
        salaService.save(sala);

        Pregled test = pregledService.findOne(pregled.getId());
        assertThat(test.getSala()).isEqualTo(1L);
    }
}
