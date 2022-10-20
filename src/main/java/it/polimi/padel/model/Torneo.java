package it.polimi.padel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Torneo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer maxPartecipanti;
    private Boolean prenotazioneAperta;
    private String descrizione;

    @ManyToMany
    @JoinTable(
            name = "torneo_utenti",
            joinColumns = @JoinColumn(name = "id_torneo", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_utente", referencedColumnName = "id")
    )
    private List<Utente> utenti = new ArrayList<>();
}
