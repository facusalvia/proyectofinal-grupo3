package com.santander.proyectofinal.util;

import com.santander.proyectofinal.dto.response.ClientRespDTO;
import com.santander.proyectofinal.entity.ClientEntity;

public class ClientEntityFactory {

    public static ClientEntity newClientEntity(){
        ClientEntity client = new ClientEntity(1, "nor", "norberto", "bernardo", null, null);
        return client;
    }

    public static ClientRespDTO newClientRespDTO(){
        ClientRespDTO client = new ClientRespDTO("norberto", "bernardo");
        return client;
    }
}
