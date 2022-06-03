package com.santander.proyectofinal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Valid
public class MonthBenefitsResponseDTO {
    @Max(value = 12)
    @Min(value = 1)
    private Integer month;
    private Integer year;
    private double total_income;
}
