package JV20.isapsw.controller;


import JV20.isapsw.constants.UserConstants;
import JV20.isapsw.model.Korisnik;
import JV20.isapsw.model.UserTokenState;
import JV20.isapsw.security.auth.JwtAuthenticationRequest;
import JV20.isapsw.service.KorisnikService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PacijentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private String accessToken;

    private String URI_PREFIX = "/api/korisnici/";

    private MediaType contentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    // pre izvrsavanja testa, prijava da bismo dobili token
    @Before
    public void login() {

        ResponseEntity<UserTokenState> responseEntity =
                restTemplate.postForEntity(URI_PREFIX + "login",
                        new JwtAuthenticationRequest("user", "marina"),
                        UserTokenState.class);
        // preuzmemo token jer ce nam trebati za testiranje REST kontrolera
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();
    }

    @Test
    public void testUser() {
        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        //kreiramo objekat koji saljemo u sklopu zahteva
        // objekat nema telo, vec samo zaglavlje, jer je rec o GET zahtevu
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        // posaljemo zahtev koji ima i zaglavlje u kojem je JWT token
        ResponseEntity<Korisnik> responseEntity =
                restTemplate.exchange("/api/korisnici/whoami", HttpMethod.GET, httpEntity, Korisnik.class);

        Korisnik korisnik = responseEntity.getBody();

        System.out.println(korisnik.getIme());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(UserConstants.ID, korisnik.getId());
        assertEquals(UserConstants.FIRST_NAME, korisnik.getIme());
        assertEquals(UserConstants.LAST_NAME, korisnik.getPrezime());
    }

}
