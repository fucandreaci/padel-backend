package it.polimi.padel.service;

import it.polimi.padel.model.Coupon;
import it.polimi.padel.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CouponService {
    @Autowired
    private CouponRepository couponRepository;

    /**
     * Ritorna il coupon con il codice specificato
     * @param codice
     * @return
     */
    public Coupon getCoupon (String codice) {
        return couponRepository.findByCodice(codice);
    }

    /**
     * Controlla se un cupon è stato già utilizzato
     * @param codice
     * @return
     */
    public boolean isCouponUtilizzato (String codice) {
        return couponRepository.isUtilizato(codice) != null;
    }
}
