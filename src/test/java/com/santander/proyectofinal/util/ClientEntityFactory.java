package com.santander.proyectofinal.util;

import com.santander.proyectofinal.entity.ClientEntity;

public class ClientEntityFactory {

    public static ClientEntity newClientEntity(){
        ClientEntity client = new ClientEntity(1, "team", "juan", "carlos", null, null);
        return client;
    }
}
