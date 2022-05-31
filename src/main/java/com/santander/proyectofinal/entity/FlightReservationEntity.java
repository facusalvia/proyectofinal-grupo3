package com.santander.proyectofinal.entity;

import com.santander.proyectofinal.dto.PaymentMethodDTO;
import com.santander.proyectofinal.dto.PersonDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "flightsReservation")
public class FlightReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    @ManyToOne
    private FlightEntity flightEntity;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<PersonEntity> people;
    @OneToOne(cascade = CascadeType.ALL)
    private PaymentMethodEntity paymentMethod;
    private boolean isActive;
}
