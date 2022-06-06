package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.request.MailRequestDTO;
import com.santander.proyectofinal.entity.MailEntity;
import com.santander.proyectofinal.exceptions.FileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class SendMailService {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    PdfService pdfService;


    public MailRequestDTO sendEmail(Integer flightReservationId, MailRequestDTO mailRequestDTO) {
    MailEntity mail = buildMailEntity(mailRequestDTO);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            FileSystemResource file = pdfService.exportFlightTicket(flightReservationId);
            if (file == null) {
                throw new FileNotFoundException();
            }
            MimeMessageHelper mimeMessageHelper = createMimeHelper(mimeMessage, mail, file);
            messageBodyPart.setFileName(file.getFilename());
            mailSender.send(mimeMessageHelper.getMimeMessage());
            pdfService.deleteFile(file);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mailRequestDTO;
    }


    private MailEntity buildMailEntity(MailRequestDTO mailRequestDTO) {
        MailEntity mail = new MailEntity();
        mail.setMailSubject(mailRequestDTO.getSubject());
        mail.setMailFrom(mailRequestDTO.getFrom());
        mail.setMailTo(mailRequestDTO.getTo());
        mail.setMailContent(mailRequestDTO.getContent());
        return mail;
    }

    private MimeMessageHelper createMimeHelper(MimeMessage mimeMessage, MailEntity mail, FileSystemResource file) throws MessagingException {

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject(mail.getMailSubject());
        mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom()));
        mimeMessageHelper.setTo(mail.getMailTo());
        mimeMessageHelper.setText(mail.getMailContent());
        mimeMessageHelper.addAttachment("ticket.pdf", file);
        return mimeMessageHelper;
    }
}
