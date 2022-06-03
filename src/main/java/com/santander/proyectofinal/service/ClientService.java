package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.response.TopClientsResponseDTO;
import com.santander.proyectofinal.entity.ClientEntity;
import com.santander.proyectofinal.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClientService {

    @Autowired
    IClientRepository clientRepository;

    public TopClientsResponseDTO getTopClients(Integer year, int limit) {
        List<Map<ClientEntity,Integer>> clients =  clientRepository.findCLientsTop3();
        TopClientsResponseDTO topClientsResponseDTO = new TopClientsResponseDTO();

        return new TopClientsResponseDTO();
    }
}
