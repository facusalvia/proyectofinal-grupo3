package com.santander.proyectofinal.entity;

import com.santander.proyectofinal.entity.FlightReservationEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "flights")
public class FlightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String flightNumber;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateFrom;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateTo;
    private double pricePerPerson;
    private String origin;
    private String destiny;
    private String seatType;
    @OneToMany(mappedBy = "flightEntity",cascade = CascadeType.REMOVE)
    private List<FlightReservationEntity> flightReservationEntityList;
}
