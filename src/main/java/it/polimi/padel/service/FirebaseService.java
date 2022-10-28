package it.polimi.padel.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import it.polimi.padel.DTO.DtoManager;
import it.polimi.padel.DTO.MessaggioDto;
import it.polimi.padel.DTO.UtenteDto;
import it.polimi.padel.exception.UserException;
import it.polimi.padel.model.Utente;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Service
public class FirebaseService {
    /**
     * Inizializza il servizio di Firebase
     */
    @PostConstruct
    public void initialize() {
        try {
           FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.getApplicationDefault())
                    .setDatabaseUrl("https://padel-69d4a-default-rtdb.europe-west1.firebasedatabase.app")
                    .build();

            if (FirebaseApp.getApps().isEmpty())
                FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Aggiunge un messaggio al database di Firebase
     * @param sender
     * @param receiver
     * @param message
     * @throws UserException
     */
    public void inviaMessaggio (Utente sender, Utente receiver, String message) throws UserException {
        if (sender.getId() == receiver.getId()) {
            throw new UserException("Non puoi inviare un messaggio a te stesso", HttpStatus.BAD_REQUEST);
        }

        int idLower = sender.getId() < receiver.getId() ? sender.getId() : receiver.getId();
        int idHigher = sender.getId() > receiver.getId() ? sender.getId() : receiver.getId();

        UtenteDto senderDto = DtoManager.getUtenteDtoFromUtente(sender);
        UtenteDto receiverDto = DtoManager.getUtenteDtoFromUtente(receiver);

        MessaggioDto messaggioDto = new MessaggioDto(senderDto, receiverDto, message, LocalDateTime.now().toString());

        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection(idLower + "_" + idHigher).add(messaggioDto);
    }
}
