package it.polimi.padel.service;

import it.polimi.padel.DTO.JWTPayload;
import it.polimi.padel.DTO.RequestLoginDto;
import it.polimi.padel.DTO.ResponseLoginDto;
import it.polimi.padel.exception.UserException;
import it.polimi.padel.model.Utente;
import it.polimi.padel.repository.UtenteRepository;
import it.polimi.padel.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
            throw new UserException(e.getMessage());
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

}
