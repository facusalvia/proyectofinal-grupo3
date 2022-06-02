package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.response.TopClientsResponseDTO;
import com.santander.proyectofinal.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    IClientRepository clientRepository;

    public TopClientsResponseDTO getTopClients(Integer year, int limit) {
        return new TopClientsResponseDTO();
    }
}
