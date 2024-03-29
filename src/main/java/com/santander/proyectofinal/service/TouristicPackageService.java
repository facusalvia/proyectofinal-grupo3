package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.request.TouristicPackageRequestDTO;
import com.santander.proyectofinal.dto.response.ListTouristicPackageResponseDTO;
import com.santander.proyectofinal.dto.response.TouristicPackageInfoResponseDTO;
import com.santander.proyectofinal.dto.response.TouristicPackageResponseDTO;
import com.santander.proyectofinal.entity.*;
import com.santander.proyectofinal.exceptions.*;
import com.santander.proyectofinal.exceptions.flightException.FlightReservationDoesNotExistException;
import com.santander.proyectofinal.exceptions.hotelException.HotelBookingDoesNotExistException;
import com.santander.proyectofinal.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TouristicPackageService {

    private final int DEFAULT_DISCOUNT_TYPE = 1;

    @Autowired
    ITouristicPackageRepository touristicPackageRepository;

    @Autowired
    IHotelBookingRepository hotelBookingRepository;

    @Autowired
    IFlightReservationRepository flightReservationRepository;

    @Autowired
    IUserEntityRepository userEntityRepository;

    @Autowired
    ITouristicPackageDiscountTypeRepository touristicPackageDiscountTypeRepository;

    @Autowired
    IClientRepository clientRepository;

    ModelMapper mapper = new ModelMapper();

    public TouristicPackageRequestDTO addTouristicPackage(TouristicPackageRequestDTO touristicPackageRequestDTO) {
        //verifica que no exista otro paquete con el mismo packagenumber
        if(touristicPackageRepository.findByPackageNumberEquals(touristicPackageRequestDTO.getPackageNumber()).isPresent()){
            throw new TouristicPackageAlreadyExistsException();
        }

        isEqualsTwo(touristicPackageRequestDTO.getBookings(), touristicPackageRequestDTO.getReservations());
        List<HotelBookingEntity> bookings = fillListBookings(touristicPackageRequestDTO);
        List<FlightReservationEntity> reservations = fillListReservation(touristicPackageRequestDTO);

        TouristicPackageEntity touristicPackage = mapper.map(touristicPackageRequestDTO, TouristicPackageEntity.class);
        //TODO por alguna razon toma el client_id como id y se lo setea, por esa razon le seteo a null el id
        touristicPackage.setId(null);

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
        touristicPackage.setClient(clientRepository.findById(touristicPackageRequestDTO.getClientId()).orElseThrow(UserDoesNotExistException::new));

        // seteo relacion con descuento
        touristicPackage.setTouristicPackageDiscountType(touristicPackageDiscountTypeRepository.findById(DEFAULT_DISCOUNT_TYPE).orElseThrow());

        touristicPackage = touristicPackageRepository.save(touristicPackage);

        if (touristicPackage.getId() == null) {
            throw new RepositorySaveException();
        }

        return touristicPackageRequestDTO;
    }

    public ListTouristicPackageResponseDTO getTouristicPackages() {
        List<TouristicPackageEntity> touristicPackageEntityList = touristicPackageRepository.findAll();

        List<TouristicPackageResponseDTO> touristicPackageResponseDTOList = new ArrayList<>();

        for (TouristicPackageEntity touristicPackageEntity : touristicPackageEntityList) {
            TouristicPackageResponseDTO touristicPackageResponseDTO = getTouristicPackageResponseDTO(touristicPackageEntity);
            TouristicPackageInfoResponseDTO touristicPackageInfoResponseDTO = mapper.map(touristicPackageEntity, TouristicPackageInfoResponseDTO.class);
            touristicPackageInfoResponseDTO.setClientId(touristicPackageEntity.getClient().getId());
            touristicPackageResponseDTO.setTouristicPackageInfoResponseDTO(touristicPackageInfoResponseDTO);
            touristicPackageResponseDTOList.add(touristicPackageResponseDTO);
        }
        return new ListTouristicPackageResponseDTO(touristicPackageResponseDTOList);
    }

    public Integer deleteTouristicPackage(Integer packageNumber) {
        if (touristicPackageRepository.findByPackageNumberEquals(packageNumber).isEmpty()){
            throw new PackageDoesNotExistException();
        }
        TouristicPackageEntity touristicPackageEntity = touristicPackageRepository.findByPackageNumberEquals(packageNumber).orElseThrow(PackageDoesNotExistException::new);
        touristicPackageRepository.deleteById(touristicPackageEntity.getId());
        return packageNumber;
    }

    public TouristicPackageRequestDTO update(Integer packageNumber, TouristicPackageRequestDTO touristicPackageRequestDTO) {
        TouristicPackageEntity touristicPackage = touristicPackageRepository.findByPackageNumberEquals(packageNumber).orElseThrow(PackageCanNotModifyException::new);
        TouristicPackageEntity touristicPackageEntity = buildTouristicPackageEntity(packageNumber, touristicPackageRequestDTO, touristicPackage);
        touristicPackageEntity.setClient(clientRepository.findById(touristicPackageRequestDTO.getClientId()).orElseThrow(UserDoesNotExistException::new));
        touristicPackageEntity.setTouristicPackageDiscountType(touristicPackageDiscountTypeRepository.findById(DEFAULT_DISCOUNT_TYPE).orElseThrow());

        touristicPackageRepository.save(touristicPackageEntity);
        return touristicPackageRequestDTO;
    }

    private TouristicPackageEntity buildTouristicPackageEntity(Integer packageNumber, TouristicPackageRequestDTO touristicPackageRequestDTO, TouristicPackageEntity touristicPackage) {
        TouristicPackageEntity touristicPackageEntity = mapper.map(touristicPackageRequestDTO, TouristicPackageEntity.class);
        Integer id = touristicPackage.getId();
        touristicPackageEntity.setId(id);
        touristicPackageEntity.setPackageNumber(packageNumber);
        for (int i = 0; i < touristicPackageRequestDTO.getBookings().size(); i++) {
            HotelBookingEntity booking = hotelBookingRepository.findById(touristicPackageRequestDTO.getBookings().get(i)).orElseThrow(HotelBookingDoesNotExistException::new);
            touristicPackageEntity.getTouristicPackageBookings().add(new TouristicPackageBookingEntity(touristicPackage.getTouristicPackageBookings().get(i).getId(), touristicPackage, booking));
        }
        for (int i = 0; i < touristicPackageRequestDTO.getReservations().size(); i++) {
            FlightReservationEntity flightReservationEntity = flightReservationRepository.findById(touristicPackageRequestDTO.getReservations().get(i)).orElseThrow(PackageCanNotModifyException::new);
            touristicPackageEntity.getTouristicPackageReservations().add(new TouristicPackageReservationEntity(touristicPackage.getTouristicPackageReservations().get(i).getId(), touristicPackage, flightReservationEntity));
        }

        return touristicPackageEntity;
    }

    private List<HotelBookingEntity> fillListBookings(TouristicPackageRequestDTO touristicPackageRequestDTO) {
        List<HotelBookingEntity> bookings = new ArrayList<>();
        for (Integer bookingId : touristicPackageRequestDTO.getBookings()) {
            bookings.add(hotelBookingRepository.findById(bookingId).orElseThrow(HotelBookingDoesNotExistException::new));
        }
        return bookings;
    }

    private List<FlightReservationEntity> fillListReservation(TouristicPackageRequestDTO touristicPackageRequestDTO) {
        List<FlightReservationEntity> reservations = new ArrayList<>();
        for (Integer reservationFlightId : touristicPackageRequestDTO.getReservations()) {
            reservations.add(flightReservationRepository.findById(reservationFlightId).orElseThrow(FlightReservationDoesNotExistException::new));
        }
        return reservations;
    }

    private void isEqualsTwo(List<Integer> bookings, List<Integer> reservations) {
        if (bookings.size() + reservations.size() != 2) {
            throw new CountPackageDistintTwoException();
        }
    }

    private TouristicPackageResponseDTO getTouristicPackageResponseDTO(TouristicPackageEntity touristicPackageEntity) {
        List<Integer> bookingsId = new ArrayList<>();
        List<Integer> reservationsId = new ArrayList<>();

        for (TouristicPackageBookingEntity touristicPackageBookingEntity : touristicPackageEntity.getTouristicPackageBookings()) {
            bookingsId.add(touristicPackageBookingEntity.getId());
        }
        for (TouristicPackageReservationEntity touristicPackageReservationEntity : touristicPackageEntity.getTouristicPackageReservations()) {
            reservationsId.add(touristicPackageReservationEntity.getId());
        }

        TouristicPackageResponseDTO touristicPackageResponseDTO = new TouristicPackageResponseDTO();
        touristicPackageResponseDTO.setReservations(reservationsId);
        touristicPackageResponseDTO.setBookings(bookingsId);

        return touristicPackageResponseDTO;
    }

}
