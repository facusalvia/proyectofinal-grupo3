package com.santander.proyectofinal.controllers;

import com.santander.proyectofinal.dto.SuccessDTO;
import com.santander.proyectofinal.dto.TaskMessage;
import com.santander.proyectofinal.dto.response.DayBenefitsResponseDTO;
import com.santander.proyectofinal.dto.response.MonthBenefitsResponseDTO;
import com.santander.proyectofinal.service.CashSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Validated
@RestController
@RequestMapping("/api/v1")
public class CashSystemController {
    @Autowired
    CashSystemService cashSystemService;

    @GetMapping("/dailyIncome")
    public ResponseEntity<DayBenefitsResponseDTO> dayBenefits(@Param(value = "date") String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parcedDate = LocalDate.parse(date, formatter);
        return ResponseEntity.ok().body(cashSystemService.dayBenefits(parcedDate));
    }
   @GetMapping("/monthlyIncome")
   public ResponseEntity<MonthBenefitsResponseDTO> monthBenefits(@Param(value = "month") @Max(value = 12,message = "El mes ingresado deberá estar entre 1 y 12") @Min(value = 1,message = "El mes ingresado deberá estar entre 1 y 12") Integer month,
                                                                 @Param(value = "year") Integer year){
       return ResponseEntity.ok().body(cashSystemService.monthBenefits(month,year));
   }


}
