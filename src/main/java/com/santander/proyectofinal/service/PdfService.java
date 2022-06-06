package com.santander.proyectofinal.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.alignment.VerticalAlignment;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfWriter;
import com.santander.proyectofinal.entity.FlightReservationEntity;
import com.santander.proyectofinal.repository.IFlightReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Service
public class PdfService {

    @Autowired
    IFlightReservationRepository flightReservationRepository;





    public FileSystemResource exportFlightTicket(Integer flightReservationId) throws IOException {
        String path = "src/main/resources/static/img/";
        FlightReservationEntity myFlight = (flightReservationRepository.findById(flightReservationId)).get();
        Document ticketPDF = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(ticketPDF, new FileOutputStream(path + "ticket.pdf"));

        //Fonts
        Font font30 = createFontHelvetica(30,Color.black);
        Font font15 = createFontHelvetica(15,Color.black);

        //Header del PDF
        Table tableHeader = configHeader(font30);


        //Banner de reserva
        Table tableBanner = new Table(1, 1);
        Paragraph reservationCode = new Paragraph("RESERVATION CODE: " + myFlight.getId() + "-" + myFlight.getFlightEntity().getFlightNumber(), font15);
        Cell cellBanner = new Cell(reservationCode);
        tableBanner.setPadding(2);
        cellBanner.setBorder(0);
        cellBanner.setBackgroundColor(Color.getHSBColor(97, 86, 95));
        cellBanner.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cellBanner.setVerticalAlignment(VerticalAlignment.CENTER);
        tableBanner.addCell(cellBanner);
        tableBanner.setSpacing(1);

        //Tabla de titulos del area cliente
        Table clientTable = new Table(2, 1);
        clientTable.setBorder(0);
        Cell cellClient = new Cell(new Paragraph("Cliente", font15));
        cellClient.setBorder(0);
        Cell infoCell = new Cell(new Paragraph("Información", font15));
        infoCell.setBorder(0);
        clientTable.addCell(cellClient);
        clientTable.addCell(infoCell);

        //Tabla de datos del cliente
        Table clientDataTable = new Table(2, 1);
        clientDataTable.setBorder(0);
        Cell cellDataClient1 = new Cell(new Paragraph("Nombre: " + myFlight.getClient().getName()));
        cellDataClient1.setBorder(0);
        Cell infoContent = new Cell("Reserva realizada con éxito");
        infoContent.setBorder(0);
        clientDataTable.addCell(cellDataClient1);
        clientDataTable.addCell(infoContent);

        Cell cellDataClient2 = new Cell(new Paragraph("Apellido: " + myFlight.getClient().getLastname()));
        cellDataClient2.setBorder(0);
        Cell infoContent2 = new Cell(" ");
        infoContent2.setBorder(0);
        clientDataTable.addCell(cellDataClient2);
        clientDataTable.addCell(infoContent2);

        Cell cellDataClient3 = new Cell(new Paragraph("Nombre de Usuario: " + myFlight.getClient().getUsername()));
        cellDataClient3.setBorder(0);
        Cell infoContent3 = new Cell(" ");
        infoContent3.setBorder(0);
        clientDataTable.addCell(cellDataClient3);
        clientDataTable.addCell(infoContent3);

        Table tableSpace = new Table(1, 1);
        Cell vacia = new Cell(new Paragraph("\n"));
        vacia.setBorder(0);
        tableSpace.addCell(vacia);
        tableSpace.setBorder(0);

        Table flightTable = new Table(2, 1);
        flightTable.setBorder(0);
        Cell cellFlight = new Cell(new Paragraph("Datos de la Ida", font15));
        cellFlight.setBorder(0);
        Cell cellFlightDer = new Cell(new Paragraph("Datos de la Vuelta", font15));
        cellFlightDer.setBorder(0);
        flightTable.addCell(cellFlight);
        flightTable.addCell(cellFlightDer);

        Cell cellDataFlight = new Cell(new Paragraph("Aeropuerto: " + myFlight.getOrigin()));
        cellDataFlight.setBorder(0);
        Cell cellDataFlightDer = new Cell("Aeropuerto: " + myFlight.getDestination());
        cellDataFlightDer.setBorder(0);
        flightTable.addCell(cellDataFlight);
        flightTable.addCell(cellDataFlightDer);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Cell cellDataFlight2 = new Cell(new Paragraph("Fecha: " + myFlight.getGoingDate().format(formatter)));
        cellDataFlight2.setBorder(0);
        Cell cellDataFlightDer2 = new Cell("Fecha: " + myFlight.getReturnDate().format(formatter));
        cellDataFlightDer2.setBorder(0);
        flightTable.addCell(cellDataFlight2);
        flightTable.addCell(cellDataFlightDer2);

        //Banner de metodo de pago
        Table tablePaymentBanner = new Table(1, 1);
        Paragraph payment = new Paragraph("Datos de pago", font15);
        Cell cellPaymentBanner = new Cell(payment);
        tablePaymentBanner.setPadding(2);
        cellPaymentBanner.setBorder(0);
        cellPaymentBanner.setBackgroundColor(Color.getHSBColor(97, 86, 95));
        cellPaymentBanner.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cellPaymentBanner.setVerticalAlignment(VerticalAlignment.CENTER);
        tablePaymentBanner.addCell(cellPaymentBanner);
        tablePaymentBanner.setSpacing(1);

        Table paymentTable = new Table(2, 1);
        paymentTable.setBorder(0);
        Cell cellPayment = new Cell(new Paragraph("Método de Pago ", font15));
        cellPayment.setBorder(0);
        Cell cellPaymentDer = new Cell(new Paragraph("Cuotas", font15));
        cellPaymentDer.setBorder(0);
        paymentTable.addCell(cellPayment);
        paymentTable.addCell(cellPaymentDer);

        //Incorporación de partes al PDF
        ticketPDF.open();
        ticketPDF.add(tableHeader);
        ticketPDF.add(tableBanner);
        ticketPDF.add(clientTable);
        ticketPDF.add(clientDataTable);
        ticketPDF.add(tableSpace);
        ticketPDF.add(flightTable);
        ticketPDF.add(tableSpace);
        ticketPDF.add(tableSpace);
        ticketPDF.add(tablePaymentBanner);
        ticketPDF.add(paymentTable);
        ticketPDF.close();

        writer.close();

        return new FileSystemResource(new File( path+"ticket.pdf"));
    }
    public boolean deleteFile(FileSystemResource file){
        File fileDelete = new File(file.getPath());
        if (fileDelete.delete())
            return true;
        else
            return false;
    }

    private Table configHeader(Font fontTitle) throws IOException {
        Table tableHeader = new Table(2, 1);
        Image logo = Image.getInstance("src/main/resources/static/img/logo.png");
        Paragraph reservationConfirmation = new Paragraph("Reserva Confirmada", fontTitle);
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
        return tableHeader;
    }

    private Font createFontHelvetica(int size,Color color){
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(size);
        font.setColor(color);
        return font;
    }

}
