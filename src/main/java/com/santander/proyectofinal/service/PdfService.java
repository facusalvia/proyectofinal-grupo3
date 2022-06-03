package com.santander.proyectofinal.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.alignment.VerticalAlignment;
import com.lowagie.text.pdf.PdfWriter;
import com.santander.proyectofinal.entity.FlightReservationEntity;
import com.santander.proyectofinal.entity.HotelBookingEntity;
import com.santander.proyectofinal.repository.IFlightReservationRepository;
import com.santander.proyectofinal.repository.IHotelBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Service
public class PdfService {

    @Autowired
    IFlightReservationRepository flightReservationRepository;

    @Autowired
    IHotelBookingRepository hotelBookingRepository;

    public Document exportFlightTicket(HttpServletResponse response, Integer flightReservationId) throws IOException{
        FlightReservationEntity myFlight = (flightReservationRepository.findById(flightReservationId)).get();

        Document ticketPDF = new Document(PageSize.A4);
        PdfWriter.getInstance(ticketPDF,response.getOutputStream());

        //Fonts
        Font font25 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font25.setSize(25);
        font25.setColor(Color.getHSBColor(97,86,95));
        Font font20 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font20.setSize(20);
        Font font15 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font15.setSize(15);
        Font font10 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font10.setSize(15);
        //Header del PDF
        Table tableHeader = new Table(2,1);
        Image logo = Image.getInstance("src/main/resources/static/img/logo.png");
        Paragraph reservationConfirmation = new Paragraph("Reserva de Vuelo Confirmada",font25);
        tableHeader.setBorder(0);
        Cell cellLogo = new Cell(logo);
        cellLogo.setBorder(0);
        cellLogo.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cellLogo.setVerticalAlignment(VerticalAlignment.CENTER);
        Cell cellReservationConfirmation = new Cell(reservationConfirmation);
        cellReservationConfirmation.setBorder(0);
        cellReservationConfirmation.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cellReservationConfirmation.setVerticalAlignment(VerticalAlignment.CENTER);
        tableHeader.addCell(cellLogo);
        tableHeader.addCell(cellReservationConfirmation);
        tableHeader.setPadding(5);

        //Banner de reserva
        Table tableBanner = new Table(1,1);
        Paragraph reservationCode = new Paragraph("RESERVATION CODE: "+myFlight.getId()+"-"+myFlight.getFlightEntity().getFlightNumber(),font15);
        Cell cellBanner = new Cell(reservationCode);
        tableBanner.setPadding(2);
        cellBanner.setBorder(0);
        cellBanner.setBackgroundColor(Color.getHSBColor(97,86,95));
        cellBanner.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cellBanner.setVerticalAlignment(VerticalAlignment.CENTER);
        tableBanner.addCell(cellBanner);
        tableBanner.setSpacing(1);
        tableBanner.setWidth(100);

        //Tabla de titulos del area cliente
        Table clientTable = new Table(2,1);
        clientTable.setBorder(0);
        Cell cellClient = new Cell(new Paragraph("Cliente",font15));
        cellClient.setBorder(0);
        Cell infoCell = new Cell(new Paragraph("Información",font15));
        infoCell.setBorder(0);
        clientTable.addCell(cellClient);
        clientTable.addCell(infoCell);
        clientTable.setWidth(90);

        //Tabla de datos del cliente
        Table clientDataTable = new Table(2,1);
        clientDataTable.setBorder(0);
        Cell cellDataClient1 = new Cell(new Paragraph("Nombre: "+myFlight.getClient().getName()));
        cellDataClient1.setBorder(0);
        Cell infoContent = new Cell("Reserva de Vuelo realizada con éxito");
        infoContent.setBorder(0);
        clientDataTable.addCell(cellDataClient1);
        clientDataTable.addCell(infoContent);
        clientDataTable.setWidth(90);

        Cell cellDataClient2 = new Cell(new Paragraph("Apellido: "+myFlight.getClient().getLastname()));
        cellDataClient2.setBorder(0);
        Cell infoContent2 = new Cell(" ");
        infoContent2.setBorder(0);
        clientDataTable.addCell(cellDataClient2);
        clientDataTable.addCell(infoContent2);

        Cell cellDataClient3 = new Cell(new Paragraph("Nombre de Usuario: "+myFlight.getClient().getUsername()));
        cellDataClient3.setBorder(0);
        Cell infoContent3 = new Cell(" ");
        infoContent3.setBorder(0);
        clientDataTable.addCell(cellDataClient3);
        clientDataTable.addCell(infoContent3);

        Table tableSpace = new Table(1,1);
        Cell vacia = new Cell(new Paragraph("\n"));
        vacia.setBorder(0);
        tableSpace.addCell(vacia);
        tableSpace.setBorder(0);

        Table flightTable = new Table(2,1);
        flightTable.setBorder(0);
        Cell cellFlight = new Cell(new Paragraph("Datos de la Ida",font15));
        cellFlight.setBorder(0);
        Cell cellFlightDer = new Cell(new Paragraph("Datos de la Vuelta",font15));
        cellFlightDer.setBorder(0);
        flightTable.addCell(cellFlight);
        flightTable.addCell(cellFlightDer);
        flightTable.setWidth(90);

        Cell cellDataFlight = new Cell(new Paragraph("Aeropuerto: "+myFlight.getOrigin()));
        cellDataFlight.setBorder(0);
        Cell cellDataFlightDer = new Cell("Aeropuerto: "+myFlight.getDestination());
        cellDataFlightDer.setBorder(0);
        flightTable.addCell(cellDataFlight);
        flightTable.addCell(cellDataFlightDer);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Cell cellDataFlight2 = new Cell(new Paragraph("Fecha: "+myFlight.getGoingDate().format(formatter)));
        cellDataFlight2.setBorder(0);
        Cell cellDataFlightDer2 = new Cell("Fecha: "+myFlight.getReturnDate().format(formatter));
        cellDataFlightDer2.setBorder(0);
        flightTable.addCell(cellDataFlight2);
        flightTable.addCell(cellDataFlightDer2);

        //Banner de metodo de pago
        Table tablePaymentBanner = new Table(1,1);
        Paragraph payment = new Paragraph("Datos de Pago",font10);
        Cell cellPaymentBanner = new Cell(payment);
        tablePaymentBanner.setPadding(2);
        cellPaymentBanner.setBorder(0);
        cellPaymentBanner.setBackgroundColor(Color.getHSBColor(97,86,95));
        cellPaymentBanner.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cellPaymentBanner.setVerticalAlignment(VerticalAlignment.CENTER);
        tablePaymentBanner.addCell(cellPaymentBanner);
        tablePaymentBanner.setSpacing(1);
        tablePaymentBanner.setWidth(100);

        Table paymentTable = new Table(2,1);
        paymentTable.setBorder(0);
        Cell cellPayment = new Cell(new Paragraph("Método de Pago ",font15));
        cellPayment.setBorder(0);
        Cell cellPaymentDer = new Cell(new Paragraph("Cuotas",font15));
        cellPaymentDer.setBorder(0);
        paymentTable.addCell(cellPayment);
        paymentTable.addCell(cellPaymentDer);
        paymentTable.setWidth(90);

        Cell paymentTable2 = new Cell(new Paragraph("Tarjeta de "+myFlight.getPaymentMethod().getType()));
        paymentTable2.setBorder(0);
        Cell paymentTableDer2 = new Cell("Cantidad: "+myFlight.getPaymentMethod().getDues());
        paymentTableDer2.setBorder(0);
        paymentTable.addCell(paymentTable2);
        paymentTable.addCell(paymentTableDer2);

        Cell paymentTable3 = new Cell(new Paragraph("Numero de Tarjeta: "+myFlight.getPaymentMethod().getNumber()));
        paymentTable3.setBorder(0);
        Cell paymentTableDer3 = new Cell("Monto total abonado: $"+myFlight.getTotalAmount());
        paymentTableDer3.setBorder(0);
        paymentTable.addCell(paymentTable3);
        paymentTable.addCell(paymentTableDer3);

        //Tabla footer
        Table footerTable = new Table(1,1);
        Cell footer = new Cell(new Paragraph("No olvides realizar tu web check in a tiempo. Este trámite estará disponible como máximo 3 días " +
                "antes y como mínimo 3 horas antes del vuelo."));
        footer.setBorder(0);
        footer.setHorizontalAlignment(HorizontalAlignment.CENTER);
        footerTable.addCell(footer);
        Cell emptyFooter = new Cell(new Paragraph("\n"));
        emptyFooter.setBorder(0);
        footerTable.addCell(emptyFooter);
        footerTable.setWidth(100);
        flightTable.setSpacing(1);
        flightTable.setPadding(2);

        //Incorporación de partes al PDF
        ticketPDF.open();
        ticketPDF.add(tableHeader);
        ticketPDF.add(tableBanner);
        ticketPDF.add(tableSpace);
        ticketPDF.add(clientTable);
        ticketPDF.add(clientDataTable);
        ticketPDF.add(tableSpace);
        ticketPDF.add(flightTable);
        ticketPDF.add(tableSpace);
        ticketPDF.add(tableSpace);
        ticketPDF.add(tablePaymentBanner);
        ticketPDF.add(tableSpace);
        ticketPDF.add(paymentTable);
        ticketPDF.add(paymentTable2);
        ticketPDF.add(paymentTable3);
        ticketPDF.add(tableSpace);
        ticketPDF.add(tableSpace);
        ticketPDF.add(tableSpace);
        ticketPDF.add(tableSpace);
        ticketPDF.add(tableSpace);
        ticketPDF.add(tableSpace);
        ticketPDF.add(tableSpace);
        ticketPDF.add(new Paragraph("Observaciones:"));
        ticketPDF.add(footerTable);
        ticketPDF.close();

        return ticketPDF;
    }
    public Document exportHotelBooking(HttpServletResponse response, Integer hotelBookingId) throws IOException{
        HotelBookingEntity myBooking = (hotelBookingRepository.findById(hotelBookingId)).get();

        Document ticketPDF = new Document(PageSize.A4);
        PdfWriter.getInstance(ticketPDF,response.getOutputStream());

        //Fonts
        Font font25 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font25.setSize(25);
        font25.setColor(Color.getHSBColor(97,86,95));
        Font font20 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font20.setSize(20);
        Font font15 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font15.setSize(15);
        Font font10 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font10.setSize(15);
        //Header del PDF
        Table tableHeader = new Table(2,1);
        Image logo = Image.getInstance("src/main/resources/static/img/logo.png");
        Paragraph reservationConfirmation = new Paragraph("Reserva de Hotel Confirmada",font25);
        tableHeader.setBorder(0);
        Cell cellLogo = new Cell(logo);
        cellLogo.setBorder(0);
        cellLogo.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cellLogo.setVerticalAlignment(VerticalAlignment.CENTER);
        Cell cellReservationConfirmation = new Cell(reservationConfirmation);
        cellReservationConfirmation.setBorder(0);
        cellReservationConfirmation.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cellReservationConfirmation.setVerticalAlignment(VerticalAlignment.CENTER);
        tableHeader.addCell(cellLogo);
        tableHeader.addCell(cellReservationConfirmation);
        tableHeader.setPadding(5);

        //Banner de reserva
        Table tableBanner = new Table(1,1);
        Paragraph reservationCode = new Paragraph("RESERVATION CODE: "+myBooking.getId()+"-"+myBooking.getHotel().getHotelCode(),font15);
        Cell cellBanner = new Cell(reservationCode);
        tableBanner.setPadding(2);
        cellBanner.setBorder(0);
        cellBanner.setBackgroundColor(Color.getHSBColor(97,86,95));
        cellBanner.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cellBanner.setVerticalAlignment(VerticalAlignment.CENTER);
        tableBanner.addCell(cellBanner);
        tableBanner.setSpacing(1);
        tableBanner.setWidth(100);

        //Tabla de titulos del area cliente
        Table clientTable = new Table(2,1);
        clientTable.setBorder(0);
        Cell cellClient = new Cell(new Paragraph("Cliente",font15));
        cellClient.setBorder(0);
        Cell infoCell = new Cell(new Paragraph("Información",font15));
        infoCell.setBorder(0);
        clientTable.addCell(cellClient);
        clientTable.addCell(infoCell);
        clientTable.setWidth(90);

        //Tabla de datos del cliente
        Table clientDataTable = new Table(2,1);
        clientDataTable.setBorder(0);
        Cell cellDataClient1 = new Cell(new Paragraph("Nombre: "+myBooking.getClient().getName()));
        cellDataClient1.setBorder(0);
        Cell infoContent = new Cell("Reserva de Hotel realizada con éxito");
        infoContent.setBorder(0);
        clientDataTable.addCell(cellDataClient1);
        clientDataTable.addCell(infoContent);
        clientDataTable.setWidth(90);

        Cell cellDataClient2 = new Cell(new Paragraph("Apellido: "+myBooking.getClient().getLastname()));
        cellDataClient2.setBorder(0);
        Cell infoContent2 = new Cell(" ");
        infoContent2.setBorder(0);
        clientDataTable.addCell(cellDataClient2);
        clientDataTable.addCell(infoContent2);

        Cell cellDataClient3 = new Cell(new Paragraph("Nombre de Usuario: "+myBooking.getClient().getUsername()));
        cellDataClient3.setBorder(0);
        Cell infoContent3 = new Cell(" ");
        infoContent3.setBorder(0);
        clientDataTable.addCell(cellDataClient3);
        clientDataTable.addCell(infoContent3);

        Table tableSpace = new Table(1,1);
        Cell vacia = new Cell(new Paragraph("\n"));
        vacia.setBorder(0);
        tableSpace.addCell(vacia);
        tableSpace.setBorder(0);

        Table hotelTable = new Table(2,1);
        hotelTable.setBorder(0);
        Cell cellFlight = new Cell(new Paragraph("Datos del Hotel",font15));
        cellFlight.setBorder(0);
        Cell cellFlightDer = new Cell(new Paragraph("Detalles de la Reserva",font15));
        cellFlightDer.setBorder(0);
        hotelTable.addCell(cellFlight);
        hotelTable.addCell(cellFlightDer);
        hotelTable.setWidth(90);


        Table hotelTable2 = new Table(2,1);
        hotelTable2.setWidth(90);
        hotelTable2.setBorder(0);
        Cell cellDataFlight = new Cell(new Paragraph("Nombre del Hotel: "+myBooking.getHotel().getName()));
        cellDataFlight.setBorder(0);
        Cell cellDataFlightDer = new Cell("Cantidad de Personas: "+myBooking.getPeopleAmount());
        cellDataFlightDer.setBorder(0);
        hotelTable2.addCell(cellDataFlight);
        hotelTable2.addCell(cellDataFlightDer);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Cell cellDataFlight2 = new Cell(new Paragraph("Ubicación: "+myBooking.getHotel().getPlace()));
        cellDataFlight2.setBorder(0);
        Cell cellDataFlightDer2 = new Cell("Fecha de Ingreso: "+myBooking.getDateFrom().format(formatter));
        cellDataFlightDer2.setBorder(0);
        hotelTable2.addCell(cellDataFlight2);
        hotelTable2.addCell(cellDataFlightDer2);

        Cell cellDataFlight3 = new Cell(new Paragraph(""));
        cellDataFlight3.setBorder(0);
        Cell cellDataFlightDer3 = new Cell("Fecha de Egreso: "+myBooking.getDateTo().format(formatter));
        cellDataFlightDer3.setBorder(0);
        hotelTable2.addCell(cellDataFlight3);
        hotelTable2.addCell(cellDataFlightDer3);

        Cell cellDataFlight4 = new Cell(new Paragraph(""));
        cellDataFlight4.setBorder(0);
        Cell cellDataFlightDer4 = new Cell("Habitación Tipo: "+myBooking.getRoomType());
        cellDataFlightDer4.setBorder(0);
        hotelTable2.addCell(cellDataFlight4);
        hotelTable2.addCell(cellDataFlightDer4);

        //Banner de metodo de pago
        Table tablePaymentBanner = new Table(1,1);
        Paragraph payment = new Paragraph("Datos de Pago",font10);
        Cell cellPaymentBanner = new Cell(payment);
        tablePaymentBanner.setPadding(2);
        cellPaymentBanner.setBorder(0);
        cellPaymentBanner.setBackgroundColor(Color.getHSBColor(97,86,95));
        cellPaymentBanner.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cellPaymentBanner.setVerticalAlignment(VerticalAlignment.CENTER);
        tablePaymentBanner.addCell(cellPaymentBanner);
        tablePaymentBanner.setSpacing(1);
        tablePaymentBanner.setWidth(100);

        Table paymentTable = new Table(2,1);
        paymentTable.setBorder(0);
        Cell cellPayment = new Cell(new Paragraph("Método de Pago ",font15));
        cellPayment.setBorder(0);
        Cell cellPaymentDer = new Cell(new Paragraph("Cuotas",font15));
        cellPaymentDer.setBorder(0);
        paymentTable.addCell(cellPayment);
        paymentTable.addCell(cellPaymentDer);
        paymentTable.setWidth(90);

        Cell paymentTable2 = new Cell(new Paragraph("Tarjeta de "+myBooking.getPaymentMethod().getType()));
        paymentTable2.setBorder(0);
        Cell paymentTableDer2 = new Cell("Cantidad: "+myBooking.getPaymentMethod().getDues());
        paymentTableDer2.setBorder(0);
        paymentTable.addCell(paymentTable2);
        paymentTable.addCell(paymentTableDer2);

        Cell paymentTable3 = new Cell(new Paragraph("Numero de Tarjeta: "+myBooking.getPaymentMethod().getNumber()));
        paymentTable3.setBorder(0);
        Cell paymentTableDer3 = new Cell("Monto total abonado: $"+myBooking.getTotalAmount());
        paymentTableDer3.setBorder(0);
        paymentTable.addCell(paymentTable3);
        paymentTable.addCell(paymentTableDer3);

        //Tabla footer
        Table footerTable = new Table(1,1);
        Cell footer = new Cell(new Paragraph("El check-in se realiza a partir de las 11:00AM del dia de entrada de la reserva. Fuera de ese horario, unicamente " +
                "se podra guardar el equipaje de las personas de la reserva."));
        footer.setBorder(0);
        footer.setHorizontalAlignment(HorizontalAlignment.CENTER);
        footerTable.addCell(footer);
        Cell emptyFooter = new Cell(new Paragraph("\n"));
        emptyFooter.setBorder(0);
        footerTable.addCell(emptyFooter);
        footerTable.setWidth(100);


        //Incorporación de partes al PDF
        ticketPDF.open();
        ticketPDF.add(tableHeader);
        ticketPDF.add(tableBanner);
        ticketPDF.add(tableSpace);
        ticketPDF.add(clientTable);
        ticketPDF.add(clientDataTable);
        ticketPDF.add(tableSpace);
        ticketPDF.add(hotelTable);
        ticketPDF.add(hotelTable2);
        ticketPDF.add(tableSpace);
        ticketPDF.add(tableSpace);
        ticketPDF.add(tablePaymentBanner);
        ticketPDF.add(tableSpace);
        ticketPDF.add(paymentTable);
        ticketPDF.add(paymentTable2);
        ticketPDF.add(paymentTable3);
        ticketPDF.add(tableSpace);
        ticketPDF.add(tableSpace);
        ticketPDF.add(tableSpace);
        ticketPDF.add(tableSpace);
        ticketPDF.add(tableSpace);
        ticketPDF.add(tableSpace);
        ticketPDF.add(new Paragraph("Observaciones:"));
        ticketPDF.add(footerTable);
        ticketPDF.close();

        return ticketPDF;
    }
}
