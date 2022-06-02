package com.santander.proyectofinal.util;
import com.santander.proyectofinal.dto.response.TouristicPackageInfoResponseDTO;
import com.santander.proyectofinal.dto.response.TouristicPackageResponseDTO;
import com.santander.proyectofinal.entity.*;
import java.util.ArrayList;
import java.util.List;

public class TouristicPackageFactory {

    public static TouristicPackageEntity newTouristicPackageEntity() {
        UserEntity user = new UserEntity(1, "team", "123", "admin");
        return buildTouristicPackageEntity(user);

    }

    public static TouristicPackageResponseDTO newTouristicPackageDTO() {
        return buildTouristicPackageDTO();
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



    private static TouristicPackageEntity buildTouristicPackageEntity(UserEntity user) {
        TouristicPackageEntity touristicPackageEntity = new TouristicPackageEntity();
        touristicPackageEntity.setPackageNumber(123);
        touristicPackageEntity.setUser(user);
        touristicPackageEntity.setTouristicPackageBookings(refillBookings(touristicPackageEntity));
        touristicPackageEntity.setTouristicPackageReservations(refillReservations(touristicPackageEntity));
        touristicPackageEntity.setId(1);
        return touristicPackageEntity;
    }

    private static TouristicPackageResponseDTO buildTouristicPackageDTO() {
        TouristicPackageResponseDTO touristicPackageResponseDTO = new TouristicPackageResponseDTO();
        touristicPackageResponseDTO.setTouristicPackageInfoResponseDTO(new TouristicPackageInfoResponseDTO());
        return touristicPackageResponseDTO;
    }

    private static TouristicPackageInfoResponseDTO buildTouristicPackageDTOInfo() {
        TouristicPackageInfoResponseDTO touristicPackageInfoResponseDTO = new TouristicPackageInfoResponseDTO();
        touristicPackageInfoResponseDTO.setPackageNumber(123);
        touristicPackageInfoResponseDTO.setId(1);
        touristicPackageInfoResponseDTO.setName("paquete");
        touristicPackageInfoResponseDTO.setClientId(1);
        return touristicPackageInfoResponseDTO;
    }
}

