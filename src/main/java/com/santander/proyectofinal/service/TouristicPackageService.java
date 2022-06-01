package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.request.TouristicPackageRequestDTO;
import com.santander.proyectofinal.entity.*;
import com.santander.proyectofinal.repository.IFlightReservationRepository;
import com.santander.proyectofinal.repository.IHotelBookingRepository;
import com.santander.proyectofinal.repository.ITouristicPackageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TouristicPackageService {

    @Autowired
    ITouristicPackageRepository touristicPackageRepository;

    @Autowired
    IHotelBookingRepository hotelBookingRepository;

    @Autowired
    IFlightReservationRepository flightReservationRepository;

    ModelMapper mapper = new ModelMapper();

    public TouristicPackageRequestDTO addTouristicPackage(TouristicPackageRequestDTO touristicPackageRequestDTO) {

        List<HotelBookingEntity> bookings = new ArrayList<>();
        List<FlightReservationEntity> reservations = new ArrayList<>();

        // busco las reservas
        for (Integer bookingId : touristicPackageRequestDTO.getBookings()) {
            bookings.add(hotelBookingRepository.findById(bookingId).orElseThrow());
        }
        for (Integer reservationId : touristicPackageRequestDTO.getReservations()) {
            reservations.add(flightReservationRepository.findById(reservationId).orElseThrow());
        }

        // TODO: validar que ambas sean exactamente 2
        TouristicPackageEntity touristicPackage = mapper.map(touristicPackageRequestDTO, TouristicPackageEntity.class);

        // creo las entidades "debiles" de booking y reservations
        List<TouristicPackageBookingEntity> touristicPackageBookingEntities = new ArrayList<>();
        for (HotelBookingEntity booking : bookings) {
            touristicPackageBookingEntities.add(new TouristicPackageBookingEntity(null, touristicPackage, booking));
        }

        List<TouristicPackageReservationEntity> touristicPackageReservationsEntities = new ArrayList<>();
        for (FlightReservationEntity reservation : reservations) {
            touristicPackageReservationsEntities.add(new TouristicPackageReservationEntity(null, touristicPackage, reservation));
        }

        // seteo relaciones
        touristicPackage.setTouristicPackageBookings(touristicPackageBookingEntities);
        touristicPackage.setTouristicPackageReservations(touristicPackageReservationsEntities);

        touristicPackage = touristicPackageRepository.save(touristicPackage);

        if (touristicPackage.getId() == null) {
            throw new RuntimeException("Error al cargar paquete turistico");
        }

        return touristicPackageRequestDTO;
    }

    public TouristicPackageRequestDTO update(Integer packageNumber, TouristicPackageRequestDTO touristicPackageRequestDTO) {
        TouristicPackageEntity touristicPackage = touristicPackageRepository.findByPackageNumberEquals(packageNumber).orElseThrow();
        TouristicPackageEntity touristicPackageEntity = buildTouristicPackageEntity(packageNumber,touristicPackageRequestDTO, touristicPackage);
        touristicPackageRepository.save(touristicPackageEntity);
        return touristicPackageRequestDTO;
    }

    private TouristicPackageEntity buildTouristicPackageEntity(Integer packageNumber,TouristicPackageRequestDTO touristicPackageRequestDTO, TouristicPackageEntity touristicPackage) {
        TouristicPackageEntity touristicPackageEntity = mapper.map(touristicPackageRequestDTO, TouristicPackageEntity.class);
        Integer id = touristicPackage.getId();
        touristicPackageEntity.setId(id);
        touristicPackageEntity.setPackageNumber(packageNumber);
        for (int i = 0; i < touristicPackageRequestDTO.getBookings().size(); i++) {
            HotelBookingEntity booking = hotelBookingRepository.findById(touristicPackageRequestDTO.getBookings().get(i)).orElseThrow();
            touristicPackageEntity.getTouristicPackageBookings().add(new TouristicPackageBookingEntity(touristicPackage.getTouristicPackageBookings().get(i).getId(), touristicPackage, booking));
        }
        for (int i = 0; i < touristicPackageRequestDTO.getReservations().size(); i++) {
            FlightReservationEntity flightReservationEntity = flightReservationRepository.findById(touristicPackageRequestDTO.getReservations().get(i)).orElseThrow();
            touristicPackageEntity.getTouristicPackageReservations().add(new TouristicPackageReservationEntity(touristicPackage.getTouristicPackageReservations().get(i).getId(), touristicPackage, flightReservationEntity));
        }

        return touristicPackageEntity;
    }
}
