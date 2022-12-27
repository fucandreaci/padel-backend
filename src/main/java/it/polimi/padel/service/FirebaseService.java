package it.polimi.padel.service;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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

    /**
     * Restituisce tutti i messaggi tra due utenti
     * @param chatId
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Map<String, MessaggioDto> getMessaggiByChatId(String chatId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(chatId).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        Map<String, MessaggioDto> messaggi = new HashMap<>();
        //List<MessaggioDto> messaggi = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            messaggi.put(document.getId(), document.toObject(MessaggioDto.class));
        }
        return messaggi;
    }

    /**
     * Restituisce l'id dell'utente che ha inviato il messaggio
     * @param chatId
     * @param messageId
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Integer getUserIdSender (String chatId, String messageId) throws ExecutionException, InterruptedException {
        Map<String, MessaggioDto> messaggi = getMessaggiByChatId(chatId);
        return messaggi.get(messageId).getSender().getId();
    }

}
