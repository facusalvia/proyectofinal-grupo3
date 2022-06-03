package com.santander.proyectofinal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "payment_method")
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
public class PaymentMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String type;
    @Column
    private String number;
    @Column
    private Integer dues;
    @OneToOne(mappedBy = "paymentMethod")
    private HotelBookingEntity hotelBookingEntity;
    @OneToOne(mappedBy = "paymentMethod")
    private FlightReservationEntity flightReservationEntity;
}
