package it.polimi.padel.service;

import it.polimi.padel.DTO.*;
import it.polimi.padel.exception.ErrorResponse;
import it.polimi.padel.exception.UserException;
import it.polimi.padel.model.Ruolo;
import it.polimi.padel.model.Utente;
import it.polimi.padel.repository.UtenteRepository;
import it.polimi.padel.security.JwtTokenUtil;
import it.polimi.padel.security.SecurityConfig;
import it.polimi.padel.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * Trova un utente dato l'id
     * @param id
     * @return
     */
    public Utente findById(Integer id) {
        return utenteRepository.findById(id).orElse(null);
    }

    /**
     * Trova un utente dato l'email
     * @param email
     * @return
     */
    public Utente findByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }

    /**
     * Restituisce l'utente loggato
     * @return
     */
    public Utente findFromJWT () {
        JWTPayload jwtPayload = (JWTPayload) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findById(jwtPayload.getId());
    }

    /**
     * Esegue l'autenticazione dati email e password
     * @param email
     * @param password
     * @throws UserException
     */
    private void authenticate(String email, String password) throws UserException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException | BadCredentialsException e) {
            ErrorResponse err = new ErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
            throw new UserException(err.getMessage(), err.getStatus());
        }
    }

    /**
     * Esegue il login, se tutto va a buon fine genera il token JWT
     * @param requestLoginDto
     * @return
     * @throws UserException
     */
    public ResponseLoginDto login (RequestLoginDto requestLoginDto) throws UserException {
        authenticate(requestLoginDto.getEmail(), requestLoginDto.getPassword());
        Utente utente = findByEmail(requestLoginDto.getEmail());

        String token = jwtTokenUtil.generateToken(JWTPayload.parseUser(utente));
        return new ResponseLoginDto(token, utente.getRuolo().name(), utente.getId());
    }

    /**
     * Esegue la registrazione di un nuovo utente
     * @param requestSignupDto
     * @return
     * @throws UserException
     */
    public ResponseSignupDto signup (RequestSignupDto requestSignupDto) throws UserException {
        if (findByEmail(requestSignupDto.getEmail()) != null) {
            throw new UserException("Email già in uso", HttpStatus.BAD_REQUEST);
        }

        if (!Utility.isValidEmail(requestSignupDto.getEmail())) {
            throw new UserException("Email non valida", HttpStatus.BAD_REQUEST);
        }

        Utente utente = DtoManager.getUtenteFromRequestSignupDto(requestSignupDto);
        utente.setRuolo(Ruolo.USER);
        utente.setPassword(SecurityConfig.passwordEncoder().encode(utente.getPassword()));

        utente = utenteRepository.save(utente);
        return DtoManager.getResponseSignupDtoFromUtente(utente);
    }

    /**
     * Restituisce la lista degli utenti che non sono amici dell'utente loggato
     * @param user
     * @return
     */
    public List<ResponseAmiciziaDto> getUtentiNotAmici(Utente user) {
        List<Utente> utentiNonAmici = utenteRepository.findUtentiNonAmici(user);
        return utentiNonAmici.stream().map(DtoManager::getResponseAmiciziaDtoFromUtente).collect(Collectors.toList());
    }

    public Utente updateUtente(Utente utente) {
        return utenteRepository.save(utente);
    }
}
