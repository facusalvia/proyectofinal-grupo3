package com.santander.proyectofinal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Table(name = "guests")
@Entity
public class GuestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String dni;
    @Column
    private String name;
    @Column
    private String lastname;
    @Column
    private LocalDate birthDate;
    @Column
    private String mail;
    @ManyToMany
    @JoinTable(
            name = "guest_booking",
            joinColumns = @JoinColumn(name = "guest_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_booking_id", referencedColumnName = "id")
    )
    private List<HotelBookingEntity> hotelBookingEntity;
}
