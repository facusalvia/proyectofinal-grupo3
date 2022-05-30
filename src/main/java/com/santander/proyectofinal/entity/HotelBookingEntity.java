package com.santander.proyectofinal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "hotel_bookings")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class HotelBookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //@Column
    //private UserEntity user;
    @Column
    private LocalDate dateFrom;
    @Column
    private LocalDate dateTo;
    @Column
    private String destination;
    //@Column
    //private HotelEntity hotel;
    @Column
    private Integer peopleAmount;
    @Column
    private String roomType;
    //@Column
    //private List<GuestEntity> people;
    //@Column
    //private PaymentMethodEntity paymentMethod;
}
