package com.santander.proyectofinal.util;

import com.santander.proyectofinal.entity.HotelBookingEntity;
import com.santander.proyectofinal.entity.PaymentMethodEntity;
import com.santander.proyectofinal.entity.PersonEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelBookingEntityFactory {

    public static HotelBookingEntity newHotelBookingEntity(){
        List<PersonEntity> listPersonEntity = new ArrayList<>();
        PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity(1,"CREDIT","1234",6,null,null);
        return new HotelBookingEntity("Juan",1, LocalDate.of(2022,06,05),
                LocalDate.of(2022,06,10),"La Plata",HotelEntityFactory.newHotelEntity(),1,
                "Double",listPersonEntity,paymentMethodEntity,true,LocalDate.now(),31000D);
    }
}
