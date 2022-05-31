package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.SuccessDTO;
import com.santander.proyectofinal.dto.request.BookingRequestDTO;
import com.santander.proyectofinal.dto.request.HotelBookingDTORequest;
import com.santander.proyectofinal.dto.response.ListHotelBookingResponseDTO;
import com.santander.proyectofinal.entity.GuestEntity;
import com.santander.proyectofinal.entity.HotelBookingEntity;
import com.santander.proyectofinal.entity.HotelEntity;
import com.santander.proyectofinal.exceptions.HotelBookingDoesNotExistException;
import com.santander.proyectofinal.exceptions.HotelDoesNotExistException;
import com.santander.proyectofinal.exceptions.RepositorySaveException;
import com.santander.proyectofinal.repository.IHotelBookingRepository;
import com.santander.proyectofinal.repository.IHotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelBookingService {

    @Autowired
    IHotelBookingRepository hotelBookingRepository;

    @Autowired
    IHotelRepository hotelRepository;

    ModelMapper modelMapper = new ModelMapper();

    public SuccessDTO addBooking(HotelBookingDTORequest hotelBookingDTORequest) {
        HotelEntity hotelEntity = hotelRepository.findByHotelCode(hotelBookingDTORequest.getBooking().getHotelCode()).orElseThrow(HotelDoesNotExistException::new);
        HotelBookingEntity hotelBookingEntity = modelMapper.map(hotelBookingDTORequest.getBooking(), HotelBookingEntity.class);

        // TODO: ver si dejamos el username como una columna o usamos una fk user_id a la tabla UserEntity, verificando que exista el username antes de hacer la reserva
        hotelBookingEntity.setUsername(hotelBookingDTORequest.getUsername());
        hotelBookingEntity.setHotel(hotelEntity);
        hotelBookingEntity.setActive(true);

        // TODO: setear el id a cada guest en caso de que ya exista en la tabla pq sino el cascade genera otra entrada
        List<GuestEntity> guests = hotelBookingEntity.getPeople();
        for (GuestEntity guest: guests) {
            guest.setHotelBookingEntity(List.of(hotelBookingEntity));
        }

        hotelBookingEntity = hotelBookingRepository.save(hotelBookingEntity);

        if(hotelBookingEntity.getId() == null){
            throw new RepositorySaveException();
        }

        return new SuccessDTO("Reserva de hotel dada de alta correctamente, con id " + hotelBookingEntity.getId(), HttpStatus.OK.value());
    }

    public ListHotelBookingResponseDTO getHotelBookings() {
        List<HotelBookingEntity> listHotelBookings = hotelBookingRepository.findByIsActiveTrue();
        return new ListHotelBookingResponseDTO(listHotelBookings.stream().map(hotelBookingEntity
                ->modelMapper.map(hotelBookingEntity, HotelBookingDTORequest.class)).collect(Collectors.toList()));
    }

    public SuccessDTO deleteHotelBooking(Integer idReservation) {
        HotelBookingEntity hotelBookingEntity = hotelBookingRepository.findById(idReservation).orElseThrow(HotelBookingDoesNotExistException::new);
        hotelBookingEntity.setActive(false);
        hotelBookingRepository.save(hotelBookingEntity);
        return new SuccessDTO("La reserva ha sido eliminada correctamente",200);
    }

    public SuccessDTO updateHotelBooking(Integer bookingId, HotelBookingDTORequest hotelBookingDTORequest) {
        // verifico que exista el hotelBooking
        HotelBookingEntity savedHotelBookingEntity = hotelBookingRepository.findById(bookingId).orElseThrow(HotelBookingDoesNotExistException::new);
        BookingRequestDTO bookingRequestDTO = hotelBookingDTORequest.getBooking();

        HotelBookingEntity updatedHotelBookingEntity = modelMapper.map(bookingRequestDTO, HotelBookingEntity.class);
        updatedHotelBookingEntity.setId(savedHotelBookingEntity.getId());
        updatedHotelBookingEntity.setUsername(hotelBookingDTORequest.getUsername());
        updatedHotelBookingEntity.setHotel(savedHotelBookingEntity.getHotel());
        updatedHotelBookingEntity.setActive(true);

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
        return new SuccessDTO("La reserva ha sido modificada correctamente", HttpStatus.OK.value());
    }
}
