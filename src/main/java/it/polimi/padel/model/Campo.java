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
public class Campo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String immagine;

    @OneToMany(mappedBy = "campo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prenotazione> prenotazioni = new ArrayList<>();
}
