package com.santander.proyectofinal.service;

import com.santander.proyectofinal.repository.ITouristicPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TouristicPackageService {

    @Autowired
    ITouristicPackageRepository touristicPackageRepository;


}
