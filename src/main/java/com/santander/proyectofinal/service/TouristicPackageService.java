package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.request.TouristicPackageRequestDTO;
import com.santander.proyectofinal.dto.response.ListTouristicPackageResponseDTO;
import com.santander.proyectofinal.dto.response.TouristicPackageInfoResponseDTO;
import com.santander.proyectofinal.dto.response.TouristicPackageResponseDTO;
import com.santander.proyectofinal.entity.*;
import com.santander.proyectofinal.repository.IFlightReservationRepository;
import com.santander.proyectofinal.repository.IHotelBookingRepository;
import com.santander.proyectofinal.repository.ITouristicPackageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        for (Integer bookingId: touristicPackageRequestDTO.getBookings()) {
            bookings.add(hotelBookingRepository.findById(bookingId).orElseThrow());
        }
        for (Integer reservationId: touristicPackageRequestDTO.getReservations()) {
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

        if(touristicPackage.getId() == null){
            throw new RuntimeException("Error al cargar paquete turistico");
        }

        return touristicPackageRequestDTO;
    }

    public ListTouristicPackageResponseDTO getTouristicPackages() {
        List<TouristicPackageEntity> touristicPackageEntityList = touristicPackageRepository.findAll();

        List<TouristicPackageResponseDTO> touristicPackageResponseDTOList = new ArrayList<>();

        for (TouristicPackageEntity touristicPackageEntity: touristicPackageEntityList) {

            TouristicPackageResponseDTO touristicPackageResponseDTO = getTouristicPackageResponseDTO(touristicPackageEntity);

            TouristicPackageInfoResponseDTO touristicPackageInfoResponseDTO = mapper.map(touristicPackageEntity, TouristicPackageInfoResponseDTO.class);
            touristicPackageResponseDTO.setTouristicPackageInfoResponseDTO(touristicPackageInfoResponseDTO);
            touristicPackageResponseDTOList.add(touristicPackageResponseDTO);
        }

        return new ListTouristicPackageResponseDTO(touristicPackageResponseDTOList);
    }

    private TouristicPackageResponseDTO getTouristicPackageResponseDTO(TouristicPackageEntity touristicPackageEntity) {
        List<Integer> bookingsId = new ArrayList<>();
        List<Integer> reservationsId = new ArrayList<>();

        for (TouristicPackageBookingEntity touristicPackageBookingEntity: touristicPackageEntity.getTouristicPackageBookings()) {
            bookingsId.add(touristicPackageBookingEntity.getId());
        }
        for (TouristicPackageReservationEntity touristicPackageReservationEntity: touristicPackageEntity.getTouristicPackageReservations()) {
            reservationsId.add(touristicPackageReservationEntity.getId());
        }
        TouristicPackageResponseDTO touristicPackageResponseDTO = new TouristicPackageResponseDTO();
        touristicPackageResponseDTO.setReservations(reservationsId);
        touristicPackageResponseDTO.setBookings(bookingsId);

        return touristicPackageResponseDTO;
    }

    public Integer deleteTouristicPackage(Integer packageNumber) {
        TouristicPackageEntity touristicPackageEntity = touristicPackageRepository.findByPackageNumber(packageNumber).orElseThrow();
        touristicPackageRepository.deleteById(touristicPackageEntity.getId());
        return packageNumber;
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
