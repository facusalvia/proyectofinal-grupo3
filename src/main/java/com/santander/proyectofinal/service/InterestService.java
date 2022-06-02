package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.PaymentMethodDTO;
import com.santander.proyectofinal.exceptions.PaymentMethodDebitCanNotMoreThanOneDueException;
import org.springframework.stereotype.Service;

@Service
public class InterestService {

    public double interestCalculator(PaymentMethodDTO paymentMethodDTO) {
        double interest = creditInterestCalculator(paymentMethodDTO);
        String paymentType = paymentMethodDTO.getType();
        if (paymentType.equalsIgnoreCase("debit")) {
            if (paymentMethodDTO.getDues() != 1) {
                throw new PaymentMethodDebitCanNotMoreThanOneDueException();
            }
        }
        return interest;
    }

    private double creditInterestCalculator(PaymentMethodDTO paymentMethodDTO) {
        double interest = 1;
        String paymentType = paymentMethodDTO.getType();
        if (paymentType.equalsIgnoreCase("credit")) {
            if (paymentMethodDTO.getDues() >= 1 && paymentMethodDTO.getDues() <= 3) {
                interest = 1.05;
            } else if (paymentMethodDTO.getDues() > 3 && paymentMethodDTO.getDues() <= 6) {
                interest = 1.1;
            } else {
                interest = 1.15;
            }
        }
        return interest;
    }
}
