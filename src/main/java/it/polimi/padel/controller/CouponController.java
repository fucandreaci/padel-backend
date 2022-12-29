package it.polimi.padel.controller;
import it.polimi.padel.DTO.RequestGenerateCouponDto;
import it.polimi.padel.exception.CouponException;
import it.polimi.padel.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/coupon", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_' + T(it.polimi.padel.model.Ruolo).ADMIN)")
    public ResponseEntity<?> getAll () {
        return ResponseEntity.ok(couponService.getAll());
    }

    /**
     * Genera un coupon
     * @param requestGenerateCouponDto
     * @return
     */
    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_' + T(it.polimi.padel.model.Ruolo).ADMIN)")
    public ResponseEntity<?> createCoupon(@Valid @RequestBody RequestGenerateCouponDto requestGenerateCouponDto) {
        try {
            return ResponseEntity.ok(couponService.generateCoupon(requestGenerateCouponDto));
        } catch (CouponException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
    }

    /**
     * Elimina un coupon
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_' + T(it.polimi.padel.model.Ruolo).ADMIN)")
    public ResponseEntity<?> deleteCoupon(@PathVariable Integer id) {
        try {
            couponService.deleteCoupon(id);
            return ResponseEntity.ok(null);
        } catch (CouponException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
    }
}
