package JV20.isapsw.controller;

import JV20.isapsw.constants.KlinikaConstants;
import JV20.isapsw.model.Klinika;
import JV20.isapsw.model.Pregled;
import JV20.isapsw.model.UserTokenState;
import JV20.isapsw.security.auth.JwtAuthenticationRequest;
import JV20.isapsw.service.KlinikaService;
import JV20.isapsw.service.PregledService;
import JV20.isapsw.service.SalaService;
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
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalaControllerUnitTest {

    private String accessToken;

    @Autowired
    private TestRestTemplate restTemplate;
    private String URI_PREFIX = "/api/sale/";

    private MediaType contentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Mock
    private PregledService mockPregledService;

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
    public void dodijeliSaluPregleduTest() throws Exception {
        Pregled pregled = new Pregled();
        pregled.setId(6L);

        Mockito.when(mockPregledService.findOne(6L)).thenReturn(pregled);

        String json = asJsonString(pregled);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URI_PREFIX + "dodijeliSaluPregledu/1")
                .header("Authorization", accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();

    }
    /*
      @RequestMapping(method = RequestMethod.POST, value = "/dodijeliSaluPregledu/{brojSale}")
    @PreAuthorize("hasRole('ADMIN_KLINIKE')")
    public ResponseEntity<?> dodijeliSaluPregledu(@RequestBody PregledDTO pregledDTO, @PathVariable Long brojSale) throws AccessDeniedException, InterruptedException {
        Pregled pregled = this.pregledService.findOne(pregledDTO.getId());
        this.salaService.dodijeliSaluPregledu(pregled, brojSale);
        return new ResponseEntity<>(HttpStatus.OK);
    }

     */

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

