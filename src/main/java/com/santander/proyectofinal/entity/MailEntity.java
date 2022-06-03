package com.santander.proyectofinal.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class MailEntity {

    private String mailFrom;

    private String mailTo;

    private String mailCc;

    private String mailBcc;

    private String mailSubject;

    private String mailContent;

    private String contentType;

    private List < Object > attachments;

    public MailEntity() {
        contentType = "text/plain";
    }

}
