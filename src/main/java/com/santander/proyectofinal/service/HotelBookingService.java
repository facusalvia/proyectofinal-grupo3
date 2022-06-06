package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.request.BookingRequestDTO;
import com.santander.proyectofinal.dto.request.HotelBookingDTORequest;
import com.santander.proyectofinal.dto.response.ListHotelBookingResponseDTO;
import com.santander.proyectofinal.entity.HotelBookingEntity;
import com.santander.proyectofinal.entity.HotelEntity;
import com.santander.proyectofinal.entity.PersonEntity;
import com.santander.proyectofinal.entity.TouristicPackageEntity;
import com.santander.proyectofinal.exceptions.BookingPeriodOutsideHotelDisponibilityPeriodException;
import com.santander.proyectofinal.exceptions.hotelException.HotelBookingCanNotDeleteException;
import com.santander.proyectofinal.exceptions.hotelException.HotelBookingDoesNotExistException;
import com.santander.proyectofinal.exceptions.hotelException.HotelDoesNotExistException;
import com.santander.proyectofinal.exceptions.RepositorySaveException;
import com.santander.proyectofinal.repository.IClientRepository;
import com.santander.proyectofinal.repository.IHotelBookingRepository;
import com.santander.proyectofinal.repository.IHotelRepository;
import com.santander.proyectofinal.repository.ITouristicPackageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelBookingService {

    @Autowired
    IHotelBookingRepository hotelBookingRepository;

    @Autowired
    IHotelRepository hotelRepository;

    @Autowired
    InterestService interestService;

    @Autowired
    ITouristicPackageRepository touristicPackageRepository;

    @Autowired
    IClientRepository clientRepository;


    ModelMapper modelMapper = new ModelMapper();

    public HotelBookingDTORequest addBooking(HotelBookingDTORequest hotelBookingDTORequest) {
        HotelEntity hotelEntity = hotelRepository.findByHotelCode(hotelBookingDTORequest.getBooking().getHotelCode()).orElseThrow(HotelDoesNotExistException::new);

        // valido que la fecha del request este dentro del periodo del hotel
        LocalDate hotelFrom = hotelEntity.getDisponibilityDateFrom();
        LocalDate hotelTo = hotelEntity.getDisponibilityDateTo();

        LocalDate bookingFrom = hotelBookingDTORequest.getBooking().getDateFrom();
        LocalDate bookingTo = hotelBookingDTORequest.getBooking().getDateTo();

        boolean low = bookingFrom.isAfter(hotelFrom) || bookingFrom.isEqual(hotelFrom);
        boolean high = bookingTo.isBefore(hotelTo) || bookingTo.isEqual(hotelTo);
        if(!low || !high){
            throw new BookingPeriodOutsideHotelDisponibilityPeriodException();
        }

        HotelBookingEntity hotelBookingEntity = modelMapper.map(hotelBookingDTORequest.getBooking(), HotelBookingEntity.class);

        //valido que exista cliente
        hotelBookingEntity.setClient(clientRepository.findByUsernameEquals(hotelBookingDTORequest.getUsername()).orElseThrow());

        hotelBookingEntity.setUsername(hotelBookingDTORequest.getUsername());
        hotelBookingEntity.setHotel(hotelEntity);
        hotelBookingEntity.setActive(true);

        // TODO: setear el id a cada guest en caso de que ya exista en la tabla pq sino el cascade genera otra entrada
        List<PersonEntity> guests = hotelBookingEntity.getPeople();
        for (PersonEntity guest: guests) {
            guest.setHotelBookingEntity(List.of(hotelBookingEntity));
        }
        hotelBookingEntity.setCreatedAt(LocalDate.now());
        Double interest = interestService.interestCalculator(hotelBookingDTORequest.getBooking().getPaymentMethod());
        double amount = hotelEntity.getRoomPrice();
        double day = ChronoUnit.DAYS.between(hotelBookingDTORequest.getBooking().getDateTo(), hotelBookingDTORequest.getBooking().getDateFrom());
        double total = interest * amount * day;
        hotelBookingEntity.setTotalAmount((double) Math.round(total));
        hotelBookingEntity = hotelBookingRepository.save(hotelBookingEntity);
        if(hotelBookingEntity.getId() == null){
            throw new RepositorySaveException();
        }
        return hotelBookingDTORequest;

    }

    public ListHotelBookingResponseDTO getHotelBookings() {
        List<HotelBookingEntity> listHotelBookings = hotelBookingRepository.findByIsActiveTrue();
        return new ListHotelBookingResponseDTO(listHotelBookings.stream().map(hotelBookingEntity
                ->modelMapper.map(hotelBookingEntity, HotelBookingDTORequest.class)).collect(Collectors.toList()));
    }

    public HotelBookingDTORequest deleteHotelBooking(Integer idReservation) {
        HotelBookingEntity hotelBookingEntity = hotelBookingRepository.findById(idReservation).orElseThrow(HotelBookingDoesNotExistException::new);

        // verifico que no existan paquetes con esta reserva
        List<TouristicPackageEntity> touristicPackageEntities = touristicPackageRepository.findPackagesByHotelBooking(hotelBookingEntity.getId());
        if(!touristicPackageEntities.isEmpty()){
            throw new HotelBookingCanNotDeleteException();
        }

        hotelBookingEntity.setActive(false);
        hotelBookingRepository.save(hotelBookingEntity);
        HotelBookingDTORequest hotelBookingDTORequest = modelMapper.map(hotelBookingEntity,HotelBookingDTORequest.class);
        return hotelBookingDTORequest;
    }

    // TODO: fix created_at y total_amount quedan en null
    public HotelBookingDTORequest updateHotelBooking(Integer bookingId, HotelBookingDTORequest hotelBookingDTORequest) {
        // verifico que exista el hotelBooking
        HotelBookingEntity savedHotelBookingEntity = hotelBookingRepository.findById(bookingId).orElseThrow(HotelBookingDoesNotExistException::new);
        BookingRequestDTO bookingRequestDTO = hotelBookingDTORequest.getBooking();

        HotelBookingEntity updatedHotelBookingEntity = modelMapper.map(bookingRequestDTO, HotelBookingEntity.class);
        updatedHotelBookingEntity.setId(savedHotelBookingEntity.getId());
        updatedHotelBookingEntity.setUsername(hotelBookingDTORequest.getUsername());
        updatedHotelBookingEntity.setHotel(savedHotelBookingEntity.getHotel());
        updatedHotelBookingEntity.setActive(true);
        updatedHotelBookingEntity.setCreatedAt(savedHotelBookingEntity.getCreatedAt());
        updatedHotelBookingEntity.setTotalAmount(savedHotelBookingEntity.getTotalAmount());

        //valido que exista cliente
        updatedHotelBookingEntity.setClient(clientRepository.findByUsernameEquals(hotelBookingDTORequest.getUsername()).orElseThrow());

        updatedHotelBookingEntity.getPaymentMethod().setId(savedHotelBookingEntity.getPaymentMethod().getId());

        // modifica todas las personas hasta la cantidad que habia en la BD
        int i = 0;
        for (i = 0; i < savedHotelBookingEntity.getPeople().size(); i++) {
            updatedHotelBookingEntity.getPeople().get(i).setId(savedHotelBookingEntity.getPeople().get(i).getId());
            updatedHotelBookingEntity.getPeople().get(i).setHotelBookingEntity(List.of(savedHotelBookingEntity));
        }

        // en el caso de que sean mas personas que la BD, las agrega
        for (; i < updatedHotelBookingEntity.getPeople().size(); i++) {
            updatedHotelBookingEntity.getPeople().get(i).setHotelBookingEntity(List.of(savedHotelBookingEntity));
        }

        hotelBookingRepository.save(updatedHotelBookingEntity);
        return hotelBookingDTORequest;

    }


}
