package it.polimi.padel.utils;/*
 * File: ConfigurazioniDefault
 * Project: Padel Backend
 * File Created: 08/09/22 - 15:59
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.service.InformazioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConfigurazioniDefault {
    @Autowired
    private InformazioniService informazioniService;

    @Bean
    public void creaInfoRegole() {
        if (informazioniService.getByChiave(Costanti.REGOLE) == null) {
            informazioniService.creaInformazione(Costanti.REGOLE, "[{\"nome\":\"Regola 1\",\"descrizione\":\"Descr 1\"},{\"nome\":\"Regola 2\",\"descrizione\":\"Descr 2\"}]");
        }
    }

    @Bean
    public void creaOrari() {
        if (informazioniService.getByChiave(Costanti.ORARI) == null) {
            informazioniService.creaInformazione(Costanti.ORARI, "[{\"giorno\":\"Lunedì\",\"dalle\":\"10:00\",\"alle\":\"20:00\"},{\"giorno\":\"Martedì\",\"dalle\":\"10:00\",\"alle\":\"20:00\"},{\"giorno\":\"Mercoledì\",\"dalle\":\"10:00\",\"alle\":\"20:00\"},{\"giorno\":\"Giovedì\",\"dalle\":\"10:00\",\"alle\":\"20:00\"},{\"giorno\":\"Venerdì\",\"dalle\":\"10:00\",\"alle\":\"20:00\"},{\"giorno\":\"Sabato\",\"dalle\":\"10:00\",\"alle\":\"20:00\"},{\"giorno\":\"Domenica\",\"dalle\":\"10:00\",\"alle\":\"20:00\"}]");
        }
    }

    @Bean
    public void creaInfo() {
        if (informazioniService.getByChiave(Costanti.INFO) == null) {
            informazioniService.creaInformazione(Costanti.INFO, "[{\"nome\":\"News\",\"descrizione\":\"Nuova notizia della settimana\"},{\"nome\":\"Limiti_disdetta\",\"descrizione\":\"Massimo 2 giorni prima\"}]");
        }
    }
}
