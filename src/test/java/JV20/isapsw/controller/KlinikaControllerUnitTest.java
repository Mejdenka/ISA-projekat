package JV20.isapsw.controller;


import JV20.isapsw.constants.KlinikaConstants;
import JV20.isapsw.dto.PregledDTO;
import JV20.isapsw.model.Klinika;
import JV20.isapsw.model.UserTokenState;
import JV20.isapsw.security.auth.JwtAuthenticationRequest;
import JV20.isapsw.service.KlinikaService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertTrue;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KlinikaControllerUnitTest {

    private String accessToken;

    @Autowired
    private TestRestTemplate restTemplate;
    private String URI_PREFIX = "/api/klinike/";

    private MediaType contentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Mock
    private KlinikaService mockKlinikaService;

    @PostConstruct
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    // pre izvrsavanja testa, prijava da bismo dobili token
    @Before
    public void login() {

        ResponseEntity<UserTokenState> responseEntity =
                restTemplate.postForEntity("/api/korisnici/login",
                        new JwtAuthenticationRequest("adminK", "marina"),
                        UserTokenState.class);
        // preuzmemo token jer ce nam trebati za testiranje REST kontrolera
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();
    }


    @Test
    public void testGetDefinisaniPregledi() throws Exception {
        Klinika klinika = new Klinika();
        klinika.setId(KlinikaConstants.ID_KLINIKE);
        klinika.setNaziv(KlinikaConstants.IME_KLINIKE);
        klinika.setLokacija(KlinikaConstants.LOKACIJA_KLINIKE);
        klinika.setOpis(KlinikaConstants.OPIS_KLINIKE);

        Mockito.when(mockKlinikaService.findOne(KlinikaConstants.ID_KLINIKE)).thenReturn(klinika);

        mockMvc.perform(MockMvcRequestBuilders.get(URI_PREFIX + "getSlobodniTermini/1")
                //.param("idKlinike", klinika.getId().toString())
                .header("Authorization", accessToken))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.*", hasSize(KlinikaConstants.PREGLEDI_SIZE)));

    }

}