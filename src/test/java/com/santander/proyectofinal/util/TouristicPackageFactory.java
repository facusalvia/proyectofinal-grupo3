package com.santander.proyectofinal.util;

import com.santander.proyectofinal.entity.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TouristicPackageFactory {


    private FlightEntity flightEntity;
    @ManyToMany(cascade = {CascadeType.ALL, CascadeType.REMOVE})
    private List<PersonEntity> people;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private PaymentMethodEntity paymentMethod;
    private boolean isActive;
    private LocalDate createdAt;
    private Double totalAmount;

    public static TouristicPackageEntity newTouristicPackageEntity() {
        UserEntity user = new UserEntity(1, "team", "123", "admin");
        return buildTouristicPackageEntity(user);

    }

    private static List<TouristicPackageBookingEntity> refillBookings(TouristicPackageEntity touristicPackageEntity) {
        List<TouristicPackageBookingEntity> touristicPackageBookings = new ArrayList<>();
        touristicPackageBookings.add(new TouristicPackageBookingEntity(1, touristicPackageEntity, HotelBookingEntityFactory.newHotelBookingEntity()));
        return touristicPackageBookings;
    }


    private static List<TouristicPackageReservationEntity> refillReservations(TouristicPackageEntity touristicPackageEntity) {
        List<TouristicPackageReservationEntity> touristicPackageReservationEntities = new ArrayList<>();
        touristicPackageReservationEntities.add(new TouristicPackageReservationEntity(1, touristicPackageEntity, FlightReservationFactory.newFlightReservationEntity()));
        return touristicPackageReservationEntities;
    }



    private static TouristicPackageEntity buildTouristicPackageEntity(UserEntity user) {
        TouristicPackageEntity touristicPackageEntity = new TouristicPackageEntity();
        touristicPackageEntity.setPackageNumber(123);
        touristicPackageEntity.setUser(user);
        touristicPackageEntity.setTouristicPackageBookings(refillBookings(touristicPackageEntity));
        touristicPackageEntity.setTouristicPackageReservations(refillReservations(touristicPackageEntity));
        touristicPackageEntity.setId(1);
        return touristicPackageEntity;
    }
}

