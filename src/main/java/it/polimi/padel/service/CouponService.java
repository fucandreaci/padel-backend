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

    public Coupon getCoupon (String codice) {
        return couponRepository.findByCodice(codice);
    }

    public boolean isCouponUtilizzato (String codice) {
        return couponRepository.isUtilizato(codice) != null;
    }
}
