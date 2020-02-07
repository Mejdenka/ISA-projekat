package JV20.isapsw.controller;

import JV20.isapsw.dto.TerminDTO;
import JV20.isapsw.exception.ResourceConflictException;
import JV20.isapsw.model.*;
import JV20.isapsw.service.KlinikaService;
import JV20.isapsw.service.SalaService;
import JV20.isapsw.service.TipPregledaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.file.AccessDeniedException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/tipovipregleda")
public class TipPregledaController {
    @Autowired
    private TipPregledaService tipPregledaService;

    @RequestMapping("/getAll")
    @PreAuthorize("hasRole('USER')")
    public List<TipPregleda> getAll() {
        return this.tipPregledaService.findAll();
    }

    @RequestMapping("/getDistinct")
    @PreAuthorize("hasRole('USER')")
    public List<String> getDistinct() {
        return this.tipPregledaService.findDistinct();
    }
}
