package com.santander.proyectofinal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "clients")
@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String name;
    private String lastname;
    @OneToMany(mappedBy = "client")
    private List<HotelBookingEntity> bookings;
    @OneToMany(mappedBy = "client")
    private List<FlightReservationEntity> reservations;
}
