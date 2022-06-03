package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.request.MailRequestDTO;
import com.santander.proyectofinal.entity.MailEntity;
import com.santander.proyectofinal.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Service
public class SendMailService {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    PdfService pdfService;
    private MailEntity buildMailEntity(MailRequestDTO mailRequestDTO){
        MailEntity mail = new MailEntity();
        mail.setMailSubject(mailRequestDTO.getMailSubject());
        mail.setMailFrom("contrerabrian@gmail.com");
        mail.setMailTo(mailRequestDTO.getMailTo());
        mail.setMailContent(mailRequestDTO.getMailContent());
        return mail;
    }

    public void sendEmail(HttpServletResponse response, Integer flightReservationId,MailRequestDTO mailRequestDTO) {
      MailEntity mail = buildMailEntity(mailRequestDTO);
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom()));
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(mail.getMailContent());
            String filename = pdfService.exportFlightTicket(response,flightReservationId);//change accordingly
            DataSource source = new FileDataSource(filename);
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
