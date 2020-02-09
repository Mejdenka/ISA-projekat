package JV20.isapsw.repository;

import JV20.isapsw.model.Pregled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PregledRepositoryTest {
    @Autowired
    private PregledRepository pregledRepository ;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void TestFindAll() {
        List<Pregled> pregledi = pregledRepository.findAll();
        assertThat(pregledi.size()).isEqualTo(7);
    }

}
