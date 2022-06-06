package com.santander.proyectofinal.util;

import com.santander.proyectofinal.dto.request.MailRequestDTO;
import org.hibernate.jdbc.Expectations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class MailFactory {
    @Autowired
    private static Environment env;

    public static MailRequestDTO newMailRequestDTO(){
        return new MailRequestDTO("Your Ticket test","brian.contrera@hotmail.com","Congratulations","contrerabrian@gmail.com");
    }


}
