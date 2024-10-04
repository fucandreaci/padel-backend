package it.polimi.padel.DTO;
import it.polimi.padel.model.Coupon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponDto {
    private Integer id;
    private String codice;
    private Float valore;
    private Coupon.TipoCoupon tipo;
}
