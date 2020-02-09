package JV20.isapsw.repository;
import JV20.isapsw.model.Klinika;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KlinikaRepositoryTest {

    @Autowired
    private KlinikaRepository klinikaRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;


    @Test
    public void TestFindByNaziv() {
        Klinika klinika = klinikaRepository.findByNaziv("Klinika 1");
        assertThat(klinika.getId()).isEqualTo(1L);
        assertThat(klinika.getOpis()).isEqualTo("Prva klinika u centru.");
        assertThat(klinika.getLokacija()).isEqualTo("Janka Veselinovica, 20");
    }

    @Test
    public void TestFindAll() {
        List<Klinika> klinike = klinikaRepository.findAll();
        assertThat(klinike.size()).isEqualTo(1);
    }

}
