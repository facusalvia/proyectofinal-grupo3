package com.santander.proyectofinal.exceptions;

public class PaymentMethodDebitCanNotMoreThanOneDueException extends RuntimeException{
    public PaymentMethodDebitCanNotMoreThanOneDueException(){
        super("Payment Method Debit can't have more than 1 due");
    }
}
