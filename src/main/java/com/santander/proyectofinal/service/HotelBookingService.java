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
        // verifico que exista el hotel
        HotelEntity hotelEntity = hotelRepository.findByHotelCode(hotelBookingDTORequest.getBooking().getHotelCode()).orElseThrow(HotelDoesNotExistException::new);

        HotelBookingEntity hotelBookingEntity = modelMapper.map(hotelBookingDTORequest.getBooking(), HotelBookingEntity.class);
        // TODO: ver si dejamos el username como una columna o usamos una fk user_id a la tabla UserEntity, verificando que exista el username antes de hacer la reserva
        //seteo el username
        hotelBookingEntity.setUsername(hotelBookingDTORequest.getUsername());

        hotelBookingEntity.setHotel(hotelEntity);

        // TODO: setear el id a cada guest en caso de que ya exista en la tabla pq sino el cascade genera otra entrada
        // a cada guest le seteo el hotelEntity
        List<GuestEntity> guests = hotelBookingEntity.getPeople();
        for (GuestEntity guest: guests) {
            guest.setHotelBookingEntity(List.of(hotelBookingEntity));
        }
        hotelBookingEntity.setActive(true);
        hotelBookingEntity = hotelBookingRepository.save(hotelBookingEntity);

        if(hotelBookingEntity.getId() == null){
            throw new RepositorySaveException();
        }

        // TODO: devolver el id de la nueva reserva para luego saber cual es al modificarla/deletearla
        return new SuccessDTO("Reserva de hotel dada de alta correctamente", HttpStatus.OK.value());

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
        HotelBookingEntity hotelBookingEntity = hotelBookingRepository.findById(bookingId).orElseThrow(HotelBookingDoesNotExistException::new);
        BookingRequestDTO bookingRequestDTO = hotelBookingDTORequest.getBooking();

        // verifico que exista el hotel
        HotelEntity hotelEntity = hotelRepository.findByHotelCode(hotelBookingDTORequest.getBooking().getHotelCode()).orElseThrow();
        hotelBookingEntity.setHotel(hotelEntity);

        // actualizo datos de reserva
        // TODO: ver si usando un modelmapper puedo setear esto sin perder la referencia a la entidad almacenada
        hotelBookingEntity.setUsername(hotelBookingDTORequest.getUsername());
        hotelBookingEntity.setDateFrom(bookingRequestDTO.getDateFrom());
        hotelBookingEntity.setDateTo(bookingRequestDTO.getDateTo());
        hotelBookingEntity.setDestination(bookingRequestDTO.getDestination());
        hotelBookingEntity.setRoomType(bookingRequestDTO.getRoomType());
        hotelBookingEntity.setPeopleAmount(bookingRequestDTO.getPeopleAmount());
        hotelBookingEntity.setActive(true);

        // actualizo metodo de pago
        hotelBookingEntity.getPaymentMethod().setDues(bookingRequestDTO.getPaymentMethod().getDues());
        hotelBookingEntity.getPaymentMethod().setNumber(bookingRequestDTO.getPaymentMethod().getNumber());
        hotelBookingEntity.getPaymentMethod().setType(bookingRequestDTO.getPaymentMethod().getType());

        // actualizo lista de personas
        List<GuestEntity> savedGuests = hotelBookingEntity.getPeople();
        List<GuestEntity> newGuests = bookingRequestDTO.getPeople().stream().map(guestDTO -> modelMapper.map(guestDTO, GuestEntity.class)).collect(Collectors.toList());

        // TODO: debe coincidir la cantidad de personas, no se pueden agregar o quitar
        GuestEntity savedGuest;
        GuestEntity updatedGuest;

        for (int i = 0; i < savedGuests.size(); i++) {
            savedGuest = savedGuests.get(i);
            updatedGuest = newGuests.get(i);

            savedGuest.setDni(updatedGuest.getDni());
            savedGuest.setBirthDate(updatedGuest.getBirthDate());
            savedGuest.setLastname(updatedGuest.getLastname());
            savedGuest.setMail(updatedGuest.getMail());
            savedGuest.setName(updatedGuest.getName());
        }

        hotelBookingRepository.save(hotelBookingEntity);
        return new SuccessDTO("La reserva ha sido modificada correctamente", HttpStatus.OK.value());
    }
}
