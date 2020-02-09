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

   /* @Test
    public void do1deliSaluPregledu() throws InterruptedException {
        Pregled pregled = pregledService.findOne(6L);
        Long brojSale = 1L;
        salaService.dodijeliSaluPregledu(pregled, brojSale);

        assertThat(pregled.getSala().getBroj()).isEqualTo(1L);
    }*/
}
