package com.santander.proyectofinal.unitTest;

import com.santander.proyectofinal.dto.request.MailRequestDTO;
import com.santander.proyectofinal.service.PdfService;
import com.santander.proyectofinal.service.SendMailService;
import com.santander.proyectofinal.util.MailFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SendMailServiceTest {
    @Mock
    PdfService pdfService;

    @InjectMocks
    SendMailService sendMailService;

    @Mock
    JavaMailSender mailSender;


    @Test
    void shouldSendMail() throws IOException {
        //Arrange
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("mail.host.com");
        String path = "src/main/resources/static/img/";
        int flightReservationId = 1;
        MailRequestDTO expectedMailRequestDTO = MailFactory.newMailRequestDTO();
        FileSystemResource file = new FileSystemResource(new File(path + "ticket.pdf"));

        //act
        when(pdfService.exportFlightTicket(flightReservationId)).thenReturn(file);
        when(mailSender.createMimeMessage()).thenReturn(sender.createMimeMessage());
        MailRequestDTO obtained = sendMailService.sendEmail(flightReservationId, expectedMailRequestDTO);

        //Assert
        assertAll(() -> assertEquals(expectedMailRequestDTO, obtained));

    }

}
