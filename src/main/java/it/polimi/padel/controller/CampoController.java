package it.polimi.padel.controller;

import it.polimi.padel.DTO.RequestCampoDto;
import it.polimi.padel.service.CampoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/campi", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CampoController {

    @Autowired
    private CampoService campoService;

    @PreAuthorize("hasRole('ROLE_' + T(it.polimi.padel.model.Ruolo).ADMIN)")
    @PostMapping("")
    public ResponseEntity<?> aggiungiCampo (@Valid @RequestBody RequestCampoDto requestCampoDto) {
        return ResponseEntity.ok(campoService.aggiungiCampo(requestCampoDto));
    }
}
