package com.santander.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "persons")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String dni;
    private String name;
    private String mail;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    @ManyToMany(mappedBy = "people")
    private List<FlightReservationEntity> flightReservationEntities;
    @ManyToMany
    @JoinTable(
            name = "guest_booking",
            joinColumns = @JoinColumn(name = "guest_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_booking_id", referencedColumnName = "id")
    )
    private List<HotelBookingEntity> hotelBookingEntity;
}
