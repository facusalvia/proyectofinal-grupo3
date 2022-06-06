package com.santander.proyectofinal.unitTest;

import com.santander.proyectofinal.dto.response.ClientRespDTO;
import com.santander.proyectofinal.entity.ClientEntity;
import com.santander.proyectofinal.repository.IClientRepository;
import com.santander.proyectofinal.service.ClientService;
import com.santander.proyectofinal.util.ClientEntityFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    IClientRepository clientRepository;
    @InjectMocks
    ClientService clientService;

    @Test
    void shouldReturnClientRespDTO(){
        //Arrange
        ClientEntity clientEntity = ClientEntityFactory.newClientEntity();
        ClientRespDTO clientRespDTOExpected = ClientEntityFactory.newClientRespDTO();
        when(clientRepository.findClientWithMoreFlightReservation()).thenReturn(clientEntity);
        //Act
        ClientRespDTO clientRespDTOObteined = clientService.getUserWithMoreFlightReservation();
        //Assert
        assertEquals(clientRespDTOExpected,clientRespDTOObteined);
    }

    @Test
    void shoulWhenExecutingReturnClientRespDTO(){
        //Arrange
        ClientEntity clientEntity = ClientEntityFactory.newClientEntity();
        ClientRespDTO clientRespDTOExpected = ClientEntityFactory.newClientRespDTO();
        when(clientRepository.findClientWithMoreFlightBookings()).thenReturn(clientEntity);
        //Act
        ClientRespDTO clientRespDTOObteined = clientService.getUserWithMoreBookings();
        //Assert
        assertEquals(clientRespDTOExpected,clientRespDTOObteined);
    }

}
