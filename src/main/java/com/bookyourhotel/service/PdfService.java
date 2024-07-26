package com.bookyourhotel.service;


import com.bookyourhotel.dto.BookingDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

@Service
public class PdfService
{
    private static final String PDF_DIRECTORY = "G://PSA//Intellij//WorkingDirectory//Pdf//";

    public boolean generatePdf(String fileName , BookingDto dto)
    {

        try
        {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(PDF_DIRECTORY+fileName+".pdf"));

            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk bookingConfirmation = new Chunk("BookingConfirmation", font);
            Chunk guestName = new Chunk("Guest Name : " + dto.getName(), font);
            Chunk totalNights = new Chunk("Total Nights : " + dto.getTotalNights(), font);
            Chunk totalPrice = new Chunk("Total Price : " + dto.getPrice(), font);

            document.add(bookingConfirmation);
            document.add(new Paragraph("\n"));
            document.add(guestName);
            document.add(new Paragraph("\n"));
            document.add(totalNights);
            document.add(new Paragraph("\n"));
            document.add(totalPrice);
            document.close();
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
