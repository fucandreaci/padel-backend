package it.polimi.padel.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Amici {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_utente1")
    private Utente utente1;

    @ManyToOne
    @JoinColumn(name = "id_utente2")
    private Utente utente2;

    @ManyToOne
    @JoinColumn(name = "inviato_da")
    private Utente utente;

    private Boolean accettata;
}
