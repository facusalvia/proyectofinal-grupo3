package com.santander.proyectofinal.unitTest;
import com.santander.proyectofinal.repository.ITouristicPackageRepository;
import com.santander.proyectofinal.service.TouristicPackageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class PackageTouristicTest {
    @Mock
    ITouristicPackageRepository touristicPackageRepository;

    @InjectMocks
    TouristicPackageService touristicPackageService;

    @Test
    void shouldReturnAnAddePackageTouristic(){



    }

}
