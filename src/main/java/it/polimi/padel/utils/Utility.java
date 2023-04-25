package it.polimi.padel.utils;

import it.polimi.padel.DTO.MessaggioDto;
import it.polimi.padel.exception.GenericException;
import it.polimi.padel.model.parsables.OrarioStruttura;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.regex.Pattern;

public class Utility {
    /**
     * Controllo che la mail sia valida
     * @param email
     * @return
     */
    public static boolean isValidEmail (String email) {
        final String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        return Pattern.compile(regexPattern).matcher(email).matches();
    }

    /**
     * Controllo che le date specificate siano valide
     * @param da
     * @param a
     * @return
     */
    public static boolean isValidDaADate (LocalDateTime da, LocalDateTime a) {
        return da.isBefore(a);
    }

    /**
     * Check se la data è compresa tra le date di apertura e chiusura della struttura
     * @param orarioStruttura
     * @param da
     * @param a
     * @return
     */
    public static boolean isStrutturaAperta (OrarioStruttura orarioStruttura, LocalDateTime da, LocalDateTime a) {
        LocalTime daTime = da.toLocalTime();
        LocalTime aTime = a.toLocalTime();

        return (daTime.isAfter(orarioStruttura.getDalle()) || daTime.equals(orarioStruttura.getDalle())) && (aTime.isBefore(orarioStruttura.getAlle()) || aTime.equals(orarioStruttura.getAlle()));
    }

    /**
     * Ottieni i messaggi precedenti e successivi al messaggio specificato di un offset pari a quanto specificato
     * @param messaggi
     * @param messageId
     * @param offset
     * @return
     */
    public static List<MessaggioDto> filterMessages (Map<String, MessaggioDto> messaggi, String messageId, int offset) {
        int index = getIndex(messaggi.keySet(), messageId);
        if (index == -1) {
            return new ArrayList<>();
        }

        List<MessaggioDto> filteredMessages = new ArrayList<>();
        int start = index - offset;
        int end = index + offset;
        for (int i = start; i <= end; i++) {
            if (i >= 0 && i < messaggi.size()) {
                filteredMessages.add(messaggi.get(messaggi.keySet().toArray()[i]));
            }
        }

        return filteredMessages;
    }

    /**
     * Ottieni l'indice di un elemento in un Set
     * @param set
     * @param value
     * @return
     * @param <T>
     */
    public static <T> int getIndex(Set<T> set, T value) {
        int result = 0;
        for (T entry:set) {
            if (entry.equals(value)) return result;
            result++;
        }
        return -1;
    }

    /**
     * Ordina i messaggi in base alla data d' invio
     * @param messages
     * @return
     */
    public static Map<String, MessaggioDto> sortMessages(Map<String, MessaggioDto> messages) {
        return sortByValue(messages);
    }

    /**
     * Ordina una mappa in base ai valori
     * @param map
     * @return
     * @param <K>
     * @param <V>
     */
    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    /**
     * Genera un codice coupon randomico
     * @return
     */
    public static String generateRandomCouponCode (Integer length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }


    /**
     * Converte una data da UTC a quella locale (Europe/Rome)
     * @param date
     * @return
     * @throws Exception
     */
    public static LocalDateTime getTimeZonedDate (LocalDateTime date) throws Exception {
        ZonedDateTime daZonedDateTime = ZonedDateTime.of(date, ZoneId.of("UTC"));
        return daZonedDateTime.withZoneSameInstant(ZoneId.of("Europe/Rome")).toLocalDateTime();
    }

    public static boolean isOrariValid (List<OrarioStruttura> orari) throws GenericException {
        List<String> giorni = new ArrayList<>();
        giorni.add("LUNEDI");
        giorni.add("MARTEDI");
        giorni.add("MERCOLEDI");
        giorni.add("GIOVEDI");
        giorni.add("VENERDI");
        giorni.add("SABATO");
        giorni.add("DOMENICA");

        for (OrarioStruttura orario : orari) {
            if (orario.getDalle() == null || orario.getAlle() == null || orario.getGiorno() == null) {
                throw new GenericException(HttpStatus.BAD_REQUEST, "Giorno e orari obbligatori");
            }

            if (!giorni.contains(orario.getGiorno().toUpperCase())) {
                throw new GenericException(HttpStatus.BAD_REQUEST, "Giorno non valido");
            }

            if (orario.getDalle().isAfter(orario.getAlle())) {
                throw new GenericException(HttpStatus.BAD_REQUEST, "Orario di apertura non valido");
            }

            if (orario.getAlle().isBefore(orario.getDalle())) {
                throw new GenericException(HttpStatus.BAD_REQUEST, "Orario di chiusura non valido");
            }

            if (orario.getDalle().equals(orario.getAlle())) {
                throw new GenericException(HttpStatus.BAD_REQUEST, "Orario di apertura e chiusura non validi");
            }

            if (orario.getDalle().isBefore(LocalTime.of(0, 0)) || orario.getDalle().isAfter(LocalTime.of(23, 59))) {
                throw new GenericException(HttpStatus.BAD_REQUEST, "Orario di apertura non valido");
            }

            if (orario.getAlle().isBefore(LocalTime.of(0, 0)) || orario.getAlle().isAfter(LocalTime.of(23, 59))) {
                throw new GenericException(HttpStatus.BAD_REQUEST, "Orario di chiusura non valido");
            }

            giorni.remove(orario.getGiorno().toUpperCase());
        }

        if (giorni.size() > 0) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Giorni mancanti");
        }

        return true;
    }
}
