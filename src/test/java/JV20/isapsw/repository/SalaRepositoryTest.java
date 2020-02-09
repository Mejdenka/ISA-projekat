package JV20.isapsw.repository;

import JV20.isapsw.model.Sala;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalaRepositoryTest {

    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;


    @Test
    public void TestFindByNaziv() {
        Sala sala = salaRepository.findByNaziv("Sala 1");
        assertThat(sala.getId()).isEqualTo(1L);
        assertThat(sala.getBroj()).isEqualTo(1L);
    }

    @Test
    public void TestFindByBroj() {
        Sala sala = salaRepository.findByBroj(1L);
        assertThat(sala.getId()).isEqualTo(1L);
        assertThat(sala.getNaziv()).isEqualTo("Sala 1");
    }

}
