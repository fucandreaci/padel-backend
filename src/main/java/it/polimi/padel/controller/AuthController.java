package it.polimi.padel.controller;

import it.polimi.padel.DTO.RequestLoginDto;
import it.polimi.padel.DTO.RequestSignupDto;
import it.polimi.padel.DTO.ResponseLoginDto;
import it.polimi.padel.DTO.ResponseSignupDto;
import it.polimi.padel.exception.UserException;
import it.polimi.padel.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    @Autowired
    private UtenteService utenteService;

    @PostMapping("/login")
    public ResponseEntity<?> login (@Valid @RequestBody RequestLoginDto requestLoginDTO) {
        try {
            ResponseLoginDto response = utenteService.login(requestLoginDTO);
            return ResponseEntity.ok(response);
        } catch (UserException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup (@Valid @RequestBody RequestSignupDto requestSignupDto) {
        try {
            ResponseSignupDto response = utenteService.signup(requestSignupDto);
            return ResponseEntity.ok(response);
        } catch (UserException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
    }

    @PreAuthorize("hasRole('ROLE_' + T(it.polimi.padel.model.Ruolo).USER)")
    @PostMapping("/isValidUser")
    public ResponseEntity<?> isValidUser () {
        return ResponseEntity.ok(true);
    }
}
