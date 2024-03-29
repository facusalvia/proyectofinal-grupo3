package com.santander.proyectofinal.util;
import com.santander.proyectofinal.dto.request.TouristicPackageRequestDTO;
import com.santander.proyectofinal.dto.response.TouristicPackageInfoResponseDTO;
import com.santander.proyectofinal.dto.response.TouristicPackageResponseDTO;
import com.santander.proyectofinal.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TouristicPackageFactory {

    public static TouristicPackageEntity newTouristicPackageEntity() {
        ClientEntity client = new ClientEntity(1, "team", "juan", "carlos", null, null);
        return buildTouristicPackageEntity(client);

    }

    public static TouristicPackageResponseDTO newTouristicPackageResponseDTO() {
        return buildTouristicPackageDTO();
    }

    public static TouristicPackageRequestDTO newTouristicPackageRequestDTO() {
        return buildTouristicPackageDTORequest();
    }

    private static List<TouristicPackageBookingEntity> refillBookings(TouristicPackageEntity touristicPackageEntity) {
        List<TouristicPackageBookingEntity> touristicPackageBookings = new ArrayList<>();
        touristicPackageBookings.add(new TouristicPackageBookingEntity(1, touristicPackageEntity, HotelBookingEntityFactory.newHotelBookingEntity()));
        return touristicPackageBookings;
    }


    private static List<TouristicPackageReservationEntity> refillReservations(TouristicPackageEntity touristicPackageEntity) {
        List<TouristicPackageReservationEntity> touristicPackageReservationEntities = new ArrayList<>();
        touristicPackageReservationEntities.add(new TouristicPackageReservationEntity(1, touristicPackageEntity, FlightReservationFactory.newFlightReservationEntity()));
        return touristicPackageReservationEntities;
    }



    private static TouristicPackageEntity buildTouristicPackageEntity(ClientEntity client) {
        TouristicPackageEntity touristicPackageEntity = new TouristicPackageEntity();
        touristicPackageEntity.setPackageNumber(123);
        touristicPackageEntity.setClient(client);
        touristicPackageEntity.setTouristicPackageBookings(refillBookings(touristicPackageEntity));
        touristicPackageEntity.setTouristicPackageReservations(refillReservations(touristicPackageEntity));
        touristicPackageEntity.setId(1);
        return touristicPackageEntity;
    }


    private static TouristicPackageResponseDTO buildTouristicPackageDTO() {
        TouristicPackageResponseDTO touristicPackageResponseDTO = new TouristicPackageResponseDTO();
        touristicPackageResponseDTO.setTouristicPackageInfoResponseDTO(buildTouristicPackageDTOInfo());
        List<Integer> idBookings = new ArrayList<>();
        idBookings.add(1);
        List<Integer> idReservation = new ArrayList<>();
        idReservation.add(1);
        touristicPackageResponseDTO.setReservations(idReservation);
        touristicPackageResponseDTO.setBookings(idBookings);
        return touristicPackageResponseDTO;
    }


    private static TouristicPackageRequestDTO buildTouristicPackageDTORequest() {
        TouristicPackageRequestDTO touristicPackageInfoResponseDTO = new TouristicPackageRequestDTO();
        touristicPackageInfoResponseDTO.setPackageNumber(123);
        touristicPackageInfoResponseDTO.setName("paquete");
        touristicPackageInfoResponseDTO.setClientId(1);
        List<Integer> idBookings = new ArrayList<>();
        idBookings.add(1);
        List<Integer> idReservation = new ArrayList<>();
        idReservation.add(1);
        touristicPackageInfoResponseDTO.setCreationDate(LocalDate.of(2022,02,12));
        touristicPackageInfoResponseDTO.setBookings(idBookings);
        touristicPackageInfoResponseDTO.setReservations(idReservation);
        return touristicPackageInfoResponseDTO;
    }


    private static TouristicPackageInfoResponseDTO buildTouristicPackageDTOInfo() {
        TouristicPackageInfoResponseDTO touristicPackageInfoResponseDTO = new TouristicPackageInfoResponseDTO();
        touristicPackageInfoResponseDTO.setPackageNumber(123);
        touristicPackageInfoResponseDTO.setId(1);
        touristicPackageInfoResponseDTO.setName(null);
        touristicPackageInfoResponseDTO.setClientId(1);
        return touristicPackageInfoResponseDTO;
    }
}

