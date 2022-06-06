package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.request.FlightReservationRequestDTO;
import com.santander.proyectofinal.dto.request.HotelBookingDTORequest;
import com.santander.proyectofinal.dto.response.FlightReservationResponseDTO;
import com.santander.proyectofinal.dto.response.FlightReservationResponseListDTO;
import com.santander.proyectofinal.dto.response.ListHotelBookingResponseDTO;
import com.santander.proyectofinal.entity.*;
import com.santander.proyectofinal.exceptions.BookingPeriodOutsideHotelDisponibilityPeriodException;
import com.santander.proyectofinal.exceptions.RepositorySaveException;
import com.santander.proyectofinal.exceptions.ReservationDatesDoNotMatchFlightDatesException;
import com.santander.proyectofinal.exceptions.flightException.FlightDoesNotExistException;
import com.santander.proyectofinal.exceptions.flightException.FlightReservationCanNotDeleteException;
import com.santander.proyectofinal.exceptions.flightException.FlightReservationDoesNotExistException;
import com.santander.proyectofinal.exceptions.hotelException.HotelBookingCanNotDeleteException;
import com.santander.proyectofinal.repository.IClientRepository;
import com.santander.proyectofinal.repository.IFlightEntityRepository;
import com.santander.proyectofinal.repository.IFlightReservationRepository;
import com.santander.proyectofinal.repository.ITouristicPackageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightReservationService {
    @Autowired
    IFlightReservationRepository flightReservationRepository;
    @Autowired
    IFlightEntityRepository flightEntityRepository;
    @Autowired
    ITouristicPackageRepository touristicPackageRepository;
    @Autowired
    InterestService interestService;
    @Autowired
    IClientRepository clientRepository;


    ModelMapper modelMapper = new ModelMapper();

    public FlightReservationRequestDTO reservation(FlightReservationRequestDTO flightReservationRequestDTO) {
        FlightEntity flightEntity = flightEntityRepository.findByFlightNumberEquals(flightReservationRequestDTO.getFlightReservationDTO().getFlightNumber()).orElseThrow(FlightDoesNotExistException::new);
        FlightReservationEntity flightReservationEntity = modelMapper.map(flightReservationRequestDTO.getFlightReservationDTO(), FlightReservationEntity.class);

        validateDates(flightReservationRequestDTO, flightEntity);

        flightReservationEntity.setClient(clientRepository.findByUsernameEquals(flightReservationRequestDTO.getUsername()).orElseThrow());

        flightReservationEntity.setFlightEntity(flightEntity);
        flightReservationEntity.setUsername(flightReservationRequestDTO.getUsername());
        flightReservationEntity.setActive(true);

        List<PersonEntity> passengers = flightReservationEntity.getPeople().stream().map(
                        person -> modelMapper.map(person, PersonEntity.class)
                )
                .collect(Collectors.toList());
        for (PersonEntity person : passengers) {
            person.setFlightReservationEntities(List.of(flightReservationEntity));
        }

        flightReservationEntity.setCreatedAt(LocalDate.now());
        Double interest = interestService.interestCalculator(flightReservationRequestDTO.getFlightReservationDTO().getPaymentMethod());
        double amount = flightEntity.getPricePerPerson() * flightReservationRequestDTO.getFlightReservationDTO().getSeats();
        double total = interest * amount;
        flightReservationEntity.setTotalAmount((double) Math.round(total));
        flightReservationEntity = flightReservationRepository.save(flightReservationEntity);
        if (flightReservationEntity.getId() == null) {
            throw new RepositorySaveException();
        }

        return flightReservationRequestDTO;
    }


    public FlightReservationRequestDTO update(Integer id, FlightReservationRequestDTO flightReservationRequestDTO) {
        FlightReservationEntity flightReservationEntityRepo = flightReservationRepository.findById(id).orElseThrow(FlightReservationDoesNotExistException::new);

        validateDates(flightReservationRequestDTO, flightReservationEntityRepo.getFlightEntity());

        FlightReservationEntity flightReservationEntity = buildFlightReservationEntity(id, flightReservationRequestDTO, flightReservationEntityRepo);
        flightReservationEntity.setCreatedAt(flightReservationEntityRepo.getCreatedAt());
        flightReservationEntity.setTotalAmount(flightReservationEntityRepo.getTotalAmount());
        flightReservationEntity.setSeats(flightReservationEntityRepo.getSeats());
        flightReservationEntity.setClient(clientRepository.findByUsernameEquals(flightReservationRequestDTO.getUsername()).orElseThrow());

        flightReservationRepository.save(flightReservationEntity);
        return flightReservationRequestDTO;
    }


    //TODO: Verificar porque duplica en la tabla intermerdia

    private FlightReservationEntity buildFlightReservationEntity(Integer id, FlightReservationRequestDTO flightReservationRequestDTO, FlightReservationEntity flightReservationEntityRepo) {
        FlightReservationEntity flightReservationEntity = modelMapper.map(flightReservationRequestDTO.getFlightReservationDTO(), FlightReservationEntity.class);
        flightReservationEntity.setId(id);
        flightReservationEntity.getPaymentMethod().setId(flightReservationEntityRepo.getPaymentMethod().getId());
        flightReservationEntity.setFlightEntity(flightReservationEntityRepo.getFlightEntity());
        flightReservationEntity.setUsername(flightReservationRequestDTO.getUsername());
        for (int i = 0; i < flightReservationRequestDTO.getFlightReservationDTO().getPeople().size(); i++) {
            if (flightReservationEntityRepo.getPeople().size() > i) {
                flightReservationEntity.getPeople().get(i).setId(flightReservationEntityRepo.getPeople().get(i).getId());
            } else {
                flightReservationEntity.getPeople().add(flightReservationEntity.getPeople().get(i));
            }
        }
        return flightReservationEntity;
    }
    public FlightReservationResponseDTO deleteFlightReservation(Integer id) {
        FlightReservationEntity flightReservationEntity = flightReservationRepository.findById(id).orElseThrow(FlightReservationDoesNotExistException::new);

        // verifico que no existan paquetes con esta reserva
        List<TouristicPackageEntity> touristicPackageEntities = touristicPackageRepository.findPackagesByFlightReservation(flightReservationEntity.getId());
        if(!touristicPackageEntities.isEmpty()){
            throw new FlightReservationCanNotDeleteException();
        }

        flightReservationEntity.setActive(false);
        flightReservationRepository.save(flightReservationEntity);
        FlightReservationResponseDTO flightReservationResponseDTO = modelMapper.map(flightReservationEntity, FlightReservationResponseDTO.class);
        return flightReservationResponseDTO;
    }

    public FlightReservationResponseListDTO getReservations() {

        List<FlightReservationEntity> flightReservationEntityList = flightReservationRepository.findByIsActiveTrue();

        List<FlightReservationResponseDTO> flightReservationResponseDTOS = flightReservationEntityList.stream().map(
                reservation -> modelMapper.map(reservation, FlightReservationResponseDTO.class)
        ).collect(Collectors.toList());

        return new FlightReservationResponseListDTO(flightReservationResponseDTOS);

    }

    private void validateDates(FlightReservationRequestDTO flightReservationRequestDTO, FlightEntity flightEntity) {
        //verifico que coincidan als fechas de vuelo
        LocalDate flightFrom = flightEntity.getDateFrom();
        LocalDate flightTo = flightEntity.getDateTo();

        LocalDate reservationFrom = flightReservationRequestDTO.getFlightReservationDTO().getGoingDate();
        LocalDate reservationTo = flightReservationRequestDTO.getFlightReservationDTO().getReturnDate();

        boolean equalDeparture =  reservationFrom.isEqual(flightFrom);
        boolean equalReturn = reservationTo.isEqual(flightTo);
        if(!equalDeparture || !equalReturn){
            throw new ReservationDatesDoNotMatchFlightDatesException();
        }
    }
}
