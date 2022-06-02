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

        List<TouristicPackageBookingEntity> touristicPackageBookings = new ArrayList<>();
        List<TouristicPackageReservationEntity> touristicPackagesReservations = new ArrayList<>();
        List<FlightReservationEntity> flightReservationEntities = new ArrayList<>();


        TouristicPackageEntity touristicPackageEntity = new TouristicPackageEntity(1, 123, "paquete turistico", LocalDate.of(2022, 03, 12), user, touristicPackageBookings, touristicPackagesReservations);

        FlightEntity flightEntity = FlightEntityFactory.newFlightEntity();


        HotelBookingEntity hotelBooking = new HotelBookingEntity("team", 1, LocalDate.of(2022, 03, 12), LocalDate.of(2022, 04, 12), "lugano", new HotelEntity(), 2, "double", );

        touristicPackageBookings.add(new TouristicPackageBookingEntity(1, touristicPackageEntity, hotelBooking));
        return new TouristicPackageEntity(1, 123, "paquete turistico", LocalDate.of(2022, 03, 12), user, touristicPackageBookings, touristicPackagesReservations);
    }

    private List<TouristicPackageBookingEntity> refillBookings() {
        List<TouristicPackageBookingEntity> touristicPackageBookings = new ArrayList<>();
        touristicPackageBookings.add(new TouristicPackageBookingEntity(1, new TouristicPackageEntity(1, 123, ), new HotelBookingEntity));
    }

    private List<PersonEntity> refillPeople() {
        List<PersonEntity> people = new ArrayList<>();
        people.add();
    }
}

