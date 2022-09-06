package it.polimi.padel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LezionePrivata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_maestro")
    private Maestro maestro;

    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;
}
