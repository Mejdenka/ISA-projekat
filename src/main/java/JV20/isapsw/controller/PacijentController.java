package JV20.isapsw.controller;

import JV20.isapsw.dto.PacijentDTO;
import JV20.isapsw.model.Pacijent;
import JV20.isapsw.service.PacijentService;
import net.bytebuddy.build.BuildLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping(value = "api/pacijenti")
public class PacijentController {
    @Autowired
    private PacijentService pacijentService;




}
