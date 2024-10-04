package it.polimi.padel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String codice;
    private Float valore;
    private TipoCoupon tipo;

    @OneToOne(mappedBy = "coupon")
    private Prenotazione prenotazione;

    public enum TipoCoupon {
        PERCENTUALE, EURO
    }
}
