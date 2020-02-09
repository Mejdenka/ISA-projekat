package JV20.isapsw.service;

import JV20.isapsw.constants.KlinikaConstants;
import JV20.isapsw.constants.SalaConstants;
import JV20.isapsw.model.Klinika;
import JV20.isapsw.model.Sala;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KlinikaServiceTest {

    @Autowired
    KlinikaService klinikaService;

    @Test
    public void findOne(){
        Klinika klinika = klinikaService.findOne(KlinikaConstants.ID_KLINIKE);
        assertThat(klinika).isNotNull();
        assertThat(klinika.getNaziv()).isEqualTo(KlinikaConstants.NAZIV_KLINIKE);
        assertThat(klinika.getLokacija()).isEqualTo(KlinikaConstants.LOKACIJA_KLINIKE);
        assertThat(klinika.getOpis()).isEqualTo(KlinikaConstants.OPIS);
        assertThat(klinika.getZbirOcena()).isEqualTo(KlinikaConstants.ZBIR_OCENA);
        assertThat(klinika.getBrojOcena()).isEqualTo(KlinikaConstants.BROJ_OCENA);
    }

    @Test
    public void findByNaziv(){
        Klinika klinika = klinikaService.findByNaziv(KlinikaConstants.NAZIV_KLINIKE);
        assertThat(klinika).isNotNull();
        assertThat(klinika.getId()).isEqualTo(KlinikaConstants.ID_KLINIKE);
        assertThat(klinika.getLokacija()).isEqualTo(KlinikaConstants.LOKACIJA_KLINIKE);
        assertThat(klinika.getOpis()).isEqualTo(KlinikaConstants.OPIS);
        assertThat(klinika.getZbirOcena()).isEqualTo(KlinikaConstants.ZBIR_OCENA);
        assertThat(klinika.getBrojOcena()).isEqualTo(KlinikaConstants.BROJ_OCENA);
    }

    @Test
    public void findAll(){
        List<Klinika> klinike = klinikaService.findAll();
        assertThat(klinike).hasSize(KlinikaConstants.COUNT_KLINIKE);
        assertThat(klinike.size()).isEqualTo(KlinikaConstants.COUNT_KLINIKE);
    }


}
