package it.polimi.padel.service;

import it.polimi.padel.DTO.RequestGenerateCouponDto;
import it.polimi.padel.exception.CouponException;
import it.polimi.padel.model.Coupon;
import it.polimi.padel.repository.CouponRepository;
import it.polimi.padel.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CouponService {
    @Autowired
    private CouponRepository couponRepository;

    /**
     * Ritorna il coupon con il codice specificato
     *
     * @param codice
     * @return
     */
    public Coupon getCoupon(String codice) {
        return couponRepository.findByCodice(codice);
    }

    /**
     * Ritorna tutti i coupon
     *
     * @return
     */
    public List<Coupon> getAll() {
        return (List<Coupon>) couponRepository.findAll();
    }

    /**
     * Controlla se un cupon è stato già utilizzato
     *
     * @param codice
     * @return
     */
    public boolean isCouponUtilizzato(String codice) {
        return couponRepository.isUtilizato(codice) != null;
    }

    /**
     * Genera un coupon dato il dto di richiesta
     *
     * @param generateCouponDto
     * @return
     * @throws CouponException
     */
    public Coupon generateCoupon(RequestGenerateCouponDto generateCouponDto) throws CouponException {
        Coupon.TipoCoupon tipo;

        switch (generateCouponDto.getTipo()) {
            case "PERCENTUALE":
                tipo = Coupon.TipoCoupon.PERCENTUALE;
                break;
            case "EURO":
                tipo = Coupon.TipoCoupon.EURO;
                break;
            default:
                throw new CouponException("Tipo di coupon non valido", HttpStatus.BAD_REQUEST);
        }

        if (generateCouponDto.getValore() <= 0) {
            throw new CouponException("Valore del coupon non valido", HttpStatus.BAD_REQUEST);
        }

        String codice;
        do {
            codice = Utility.generateRandomCouponCode(6);
        } while (couponRepository.findByCodice(codice) != null);

        Coupon coupon = new Coupon();
        coupon.setTipo(tipo);
        coupon.setValore(generateCouponDto.getValore());
        coupon.setCodice(codice);

        couponRepository.save(coupon);
        return coupon;
    }

    /**
     * Elimina un coupon
     * @param id
     * @throws CouponException
     */
    public void deleteCoupon(Integer id) throws CouponException {
        Coupon coupon = couponRepository.findById(id).orElse(null);
        if (coupon == null) {
            throw new CouponException("Coupon non trovato", HttpStatus.NOT_FOUND);
        }
        couponRepository.delete(coupon);
    }
}
