package it.polimi.padel.repository;

import it.polimi.padel.model.Coupon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CouponRepository extends CrudRepository<Coupon, Integer> {
    Coupon findByCodice(String codice);

    @Query("SELECT c FROM Coupon c JOIN Prenotazione  p ON c.id = p.coupon.id WHERE c.codice = ?1")
    Coupon isUtilizato (String codice);
}
