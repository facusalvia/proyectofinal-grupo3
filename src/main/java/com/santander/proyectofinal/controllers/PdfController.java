package com.santander.proyectofinal.controllers;

import com.santander.proyectofinal.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/api/v1")
public class PdfController {
    @Autowired
    PdfService pdfService;

    @GetMapping(value = "/tickets",params = {"flightReservationId"})
    public void generateTicketPDF(HttpServletResponse response,@RequestParam(value = "flightReservationId") Integer flightReservationId)throws IOException{
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss:dd-MM-yyyy");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;  filename = pdf_ "+currentDateTime+".pdf";
        response.setHeader(headerKey,headerValue);
        this.pdfService.exportFlightTicket(flightReservationId);
    }
}
