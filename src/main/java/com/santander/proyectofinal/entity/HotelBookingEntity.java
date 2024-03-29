package com.santander.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.santander.proyectofinal.dto.PersonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "hotel_bookings")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class HotelBookingEntity {

    private String username;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateFrom;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateTo;
    @Column
    private String destination;
    @ManyToOne
    private HotelEntity hotel;
    @Column
    private Integer peopleAmount;
    @Column
    private String roomType;
    @ManyToOne
    private ClientEntity client;
    @ManyToMany(mappedBy = "hotelBookingEntity",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private List<PersonEntity> people;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private PaymentMethodEntity paymentMethod;
    @Column
    private boolean isActive;
    private LocalDate createdAt;
    private Double totalAmount;
}
