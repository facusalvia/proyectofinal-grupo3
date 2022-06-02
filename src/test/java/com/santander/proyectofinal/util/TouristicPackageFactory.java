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
        List<PersonEntity> people = refillPeople();
        List<TouristicPackageBookingEntity> touristicPackageBookings = refillBookings();
        List<TouristicPackageReservationEntity> touristicPackagesReservations = new ArrayList<>();
        List<FlightReservationEntity> flightReservationEntities = new ArrayList<>();
        TouristicPackageEntity touristicPackage =

        TouristicPackageEntity touristicPackageEntity = new TouristicPackageEntity(1, 123, "paquete turistico", LocalDate.of(2022, 03, 12), user, touristicPackageBookings, touristicPackagesReservations);

        FlightEntity flightEntity = FlightEntityFactory.newFlightEntity();


        HotelBookingEntity hotelBooking = new HotelBookingEntity("team", 1, LocalDate.of(2022, 03, 12), LocalDate.of(2022, 04, 12), "lugano", new HotelEntity(), 2, "double", );

        touristicPackageBookings.add(new TouristicPackageBookingEntity(1, touristicPackageEntity, hotelBooking));
        return touristicPackage;
    }

    private static List<TouristicPackageBookingEntity> refillBookings(TouristicPackageEntity touristicPackageEntity) {
        List<TouristicPackageBookingEntity> touristicPackageBookings = new ArrayList<>();
        touristicPackageBookings.add(new TouristicPackageBookingEntity(1,touristicPackageEntity,HotelBookingEntityFactory.newHotelBookingEntity()));
        return touristicPackageBookings;
    }


    private static List<TouristicPackageReservationEntity> refillReservations(TouristicPackageEntity touristicPackageEntity) {
        List<TouristicPackageReservationEntity> touristicPackageReservationEntities = new ArrayList<>();
        touristicPackageReservationEntities.add(new TouristicPackageReservationEntity(1,touristicPackageEntity,FlightEntityFactory.newFlightEntity()));
        return touristicPackageReservationEntities;
    }

    private static List<PersonEntity> refillPeople() {
        List<PersonEntity> people = new ArrayList<>();
        people.add(PersonEntityFactory.newPersonEntity());
        people.add(PersonEntityFactory.newPersonEntity());
        return people;
    }

    private static TouristicPackageEntity buildTouristicPackageEntity(UserEntity user) {
        TouristicPackageEntity touristicPackageEntity =  new TouristicPackageEntity();
        touristicPackageEntity.setPackageNumber(123);
        touristicPackageEntity.setUser(user);
        touristicPackageEntity.setTouristicPackageBookings(refillBookings(touristicPackageEntity));
        touristicPackageEntity.setTouristicPackageReservations(refillReservations(touristicPackageEntity));

    }
}

