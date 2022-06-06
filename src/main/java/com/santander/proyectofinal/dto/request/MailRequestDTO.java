package com.santander.proyectofinal.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Valid
@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailRequestDTO {
    private String subject;
    @NotBlank(message = "The field to cannot be empty")
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 100)
    @Email(message = "Insert correct mail to")
    private String to;
    private String content;
    @NotBlank(message = "The field from cannot be empty")
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 100)
    @Email(message = "Insert correct mail from")
    private String from;
}
