package it.polimi.padel.service;

import it.polimi.padel.exception.GenericException;
import it.polimi.padel.model.Informazioni;
import it.polimi.padel.model.parsables.*;
import it.polimi.padel.repository.InformazioniRepository;
import it.polimi.padel.utils.Costanti;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InformazioniService {
    @Autowired
    private InformazioniRepository informazioniRepository;

    /**
     * Ritorna tutte le informazioni
     * @return
     */
    public List<Informazioni> getInformazioni () {
        return informazioniRepository.findAll();
    }

    /**
     * Ritorna l'informazione con chiave specificata
     * @param chiave
     * @return
     */
    public Informazioni getByChiave (String chiave) {
        return informazioniRepository.findByChiave(chiave);
    }

    /**
     * Crea una nuova informazione
     * @param informazioni
     * @return
     */
    public Informazioni creaInformazione (Informazioni informazioni) {
        return informazioniRepository.save(informazioni);
    }

    /**
     * Crea una nuova informazione
     * @param chiave
     * @param valore
     * @return
     */
    public Informazioni creaInformazione (String chiave, String valore) {
        Informazioni info = new Informazioni();
        info.setChiave(chiave);
        info.setValore(valore);
        return creaInformazione(info);
    }

    /**
     * Esegue il parse del JSON salvato sul db e lo converte in una lista di {@link OrarioStruttura}
     * @return
     * @throws GenericException
     */
    public List<OrarioStruttura> getOrariApertura () throws GenericException {
        Informazioni info = informazioniRepository.findByChiave(Costanti.ORARI);
        List<OrarioStruttura> orari = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(info.getValore());

        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            OrarioStruttura orario = JsonParsableFactory.getFactory().getOrarioStruttura();
            orario.parseJson(jsonObject);
            orari.add(orario);
        }

        return orari;
    }

    /**
     * Ritorna le news
     * @return
     * @throws GenericException
     */
    public List<InfoVarie> getNews () throws GenericException {
        Informazioni info = informazioniRepository.findByChiave(Costanti.INFO);
        List<InfoVarie> news = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(info.getValore());

        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            InfoVarie newsItem = JsonParsableFactory.getFactory().getInfo();
            newsItem.parseJson(jsonObject);

            if (newsItem.getNome().equalsIgnoreCase("News")) {
                news.add(newsItem);
            }
        }

        return news;
    }

    /**
     * Ritorna le informazioni
     * @return
     * @throws GenericException
     */
    public List<InfoVarie> getInfoVarie () throws GenericException {
        Informazioni info = informazioniRepository.findByChiave(Costanti.INFO);
        List<InfoVarie> news = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(info.getValore());

        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            InfoVarie newsItem = JsonParsableFactory.getFactory().getInfo();
            newsItem.parseJson(jsonObject);

            if (!newsItem.getNome().equalsIgnoreCase("News")) {
                news.add(newsItem);
            }
        }

        return news;
    }

    /**
     * Ritorna i contatti
     * @return
     * @throws GenericException
     */
    public List<Contatto> getContatti () throws GenericException {
        Informazioni info = informazioniRepository.findByChiave(Costanti.CONTATTI);
        List<Contatto> contatti = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(info.getValore());

        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            Contatto contatto = JsonParsableFactory.getFactory().getContatti();
            contatto.parseJson(jsonObject);
            contatti.add(contatto);
        }

        return contatti;
    }

    /**
     * Ritorna le regole
     * @return
     * @throws GenericException
     */
    public List<Regola> getRegole () throws GenericException {
        Informazioni info = informazioniRepository.findByChiave(Costanti.REGOLE);
        List<Regola> regole = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(info.getValore());

        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            Regola regola = JsonParsableFactory.getFactory().getRegola();
            regola.parseJson(jsonObject);
            regole.add(regola);
        }

        return regole;
    }
}
