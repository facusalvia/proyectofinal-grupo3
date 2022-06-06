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
    public void generateTicketPDF(HttpServletResponse response,@RequestParam(value = "flightReservationId") Integer flightReservationId) throws IOException{
        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;  filename = FlightReservation.pdf";
        response.setHeader(headerKey,headerValue);
        this.pdfService.exportFlightTicket(response,flightReservationId);
    }

    @GetMapping(value = "/booking",params = {"bookingReservationId"})
    public void generateBookingPDF(HttpServletResponse response,@RequestParam(value = "bookingReservationId") Integer bookingReservationId) throws IOException{
        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;  filename = HotelReservation.pdf";
        response.setHeader(headerKey,headerValue);
        this.pdfService.exportHotelBooking(response,bookingReservationId);
    }
    @GetMapping(value = "/touristicPackage",params = {"touristicPackageId"})
    public void exportTouristPackage(HttpServletResponse response,@RequestParam(value = "touristicPackageId") Integer touristicPackageId) throws IOException{
        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;  filename = TouristicPackageReservation.pdf";
        response.setHeader(headerKey,headerValue);
        this.pdfService.exportTouristPackage(response,touristicPackageId);
    }
}
