package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.request.MailRequestDTO;
import com.santander.proyectofinal.entity.MailEntity;
import com.santander.proyectofinal.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class SendMailService {
    @Autowired
    JavaMailSender mailSender;


    public void sendEmail(MailRequestDTO mailRequestDTO) {
        MailEntity mail = new MailEntity();
        mail.setMailSubject(mailRequestDTO.getMailSubject());
        //mail.setMailFrom("contrerabrian@gmail.com");
        if(mailRequestDTO.getMailTo().isBlank()){

        }
        mail.setMailTo("facu_salvia@hotmail.com");
        mail.setMailContent("hola");
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom()));
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(mail.getMailContent());
            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
