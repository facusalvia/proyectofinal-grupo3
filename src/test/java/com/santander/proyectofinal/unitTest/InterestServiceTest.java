package com.santander.proyectofinal.unitTest;

import com.santander.proyectofinal.dto.PaymentMethodDTO;
import com.santander.proyectofinal.entity.PaymentMethodEntity;
import com.santander.proyectofinal.exceptions.PaymentMethodDebitCanNotMoreThanOneDueException;
import com.santander.proyectofinal.service.InterestService;
import com.santander.proyectofinal.util.PaymentMethodEntityFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class InterestServiceTest {

    @InjectMocks
    InterestService interestService;


    @Test
    void shouldReturn1PercentageInterestAboutPaymentMethodDebitAndOneDues(){
        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO("DEBIT","1234",1);
        Double expectedInterest = 1D;
        Double obtainInterest = interestService.interestCalculator(paymentMethodDTO);
        assertEquals(expectedInterest,obtainInterest);
    }

    @Test
    void shouldThrowPaymentMethodDebitExceptionWithMoreThanOneDues(){
        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO("DEBIT","1234",3);
        Double expectedInterest = 1D;
        assertThrows(PaymentMethodDebitCanNotMoreThanOneDueException.class,()-> interestService.interestCalculator(paymentMethodDTO));
    }

    @Test
    void shouldReturn05PercentageInterestAboutPaymentMethodCreditAndThreeDues(){
        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO("CREDIT","1234",3);
        Double expectedInterest = 1.05;
        Double obtainInterest = interestService.interestCalculator(paymentMethodDTO);
        assertEquals(expectedInterest,obtainInterest);
    }

    @Test
    void shouldReturn10PercentageInterestAboutPaymentMethodCreditAndSixDues(){
        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO("CREDIT","1234",6);
        Double expectedInterest = 1.1;
        Double obtainInterest = interestService.interestCalculator(paymentMethodDTO);
        assertEquals(expectedInterest,obtainInterest);
    }

    @Test
    void shouldReturn10PercentageInterestAboutPaymentMethodCreditAndTwelveDues(){
        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO("CREDIT","1234",12);
        Double expectedInterest = 1.15;
        Double obtainInterest = interestService.interestCalculator(paymentMethodDTO);
        assertEquals(expectedInterest,obtainInterest);
    }

}
