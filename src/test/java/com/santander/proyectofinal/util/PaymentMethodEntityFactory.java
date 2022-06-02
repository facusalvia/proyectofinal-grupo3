package com.santander.proyectofinal.util;

import com.santander.proyectofinal.dto.PaymentMethodDTO;
import com.santander.proyectofinal.dto.PersonDTO;
import com.santander.proyectofinal.entity.FlightReservationEntity;
import com.santander.proyectofinal.entity.HotelBookingEntity;
import com.santander.proyectofinal.entity.PaymentMethodEntity;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import java.time.LocalDate;

public class PaymentMethodEntityFactory {
    public static PaymentMethodEntity newPaymentMethodEntity(){
        return new PaymentMethodEntity(1,"type","number",2,null,null);

    }
    public static PaymentMethodDTO newPaymentMethodDTO(){
        return new PaymentMethodDTO("type","number",1);
    }
}
