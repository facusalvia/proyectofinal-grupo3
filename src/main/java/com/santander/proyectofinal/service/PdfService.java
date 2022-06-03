package com.santander.proyectofinal.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.santander.proyectofinal.entity.FlightReservationEntity;
import com.santander.proyectofinal.repository.IFlightReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class PdfService {

    @Autowired
    IFlightReservationRepository flightReservationRepository;

    public Document exportFlightTicket(HttpServletResponse response, Integer flightReservationId) throws IOException{
        FlightReservationEntity myFlight = (flightReservationRepository.findById(flightReservationId)).get();

        Document ticketPDF = new Document(PageSize.A5);
        PdfWriter.getInstance(ticketPDF,response.getOutputStream());
        Paragraph superR = new Paragraph(myFlight.getDestination());
        Table tableHeader = new Table(3,1);
        Image logo = Image.getInstance("src/main/resources/static/img/logo.png");
        Cell cellLogo = new Cell();
        cellLogo.add(logo);


        tableHeader.addCell(cellLogo);
        ticketPDF.open();
        ticketPDF.add(tableHeader);
        ticketPDF.close();

        return ticketPDF;
    }
}
