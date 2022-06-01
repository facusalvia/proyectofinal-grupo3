package com.santander.proyectofinal.entity;

import com.santander.proyectofinal.dto.PaymentMethodDTO;
import com.santander.proyectofinal.dto.PersonDTO;
import com.santander.proyectofinal.entity.FlightEntity;
import com.santander.proyectofinal.entity.PaymentMethodEntity;
import com.santander.proyectofinal.entity.PersonEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "flightsReservation")
public class FlightReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private LocalDate goingDate;
    private LocalDate returnDate;
    private String origin;
    private String destination;
    private Integer seats;
    private String seatType;
    @ManyToOne
    private FlightEntity flightEntity;
    @ManyToMany(cascade = {CascadeType.ALL, CascadeType.REMOVE})
    private List<PersonEntity> people;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private PaymentMethodEntity paymentMethod;
    private boolean isActive;
    private LocalDate createdAt;
    private Double totalAmount;
}
