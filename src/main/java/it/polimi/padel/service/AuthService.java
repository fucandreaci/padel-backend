package it.polimi.padel.service;/*
 * File: AuthenticationService
 * Project: BuonAppetito-backend
 * File Created: 31/08/22 - 12:17
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UtenteService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utente user = userService.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Utente non trovato");
        }

        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
