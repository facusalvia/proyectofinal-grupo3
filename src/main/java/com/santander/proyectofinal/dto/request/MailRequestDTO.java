package com.santander.proyectofinal.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailRequestDTO {
    private String mailSubject;
    private String mailTo;
    private String mailContent;

}
