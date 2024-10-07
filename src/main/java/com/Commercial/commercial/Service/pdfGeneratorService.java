package com.Commercial.commercial.Service;

import com.Commercial.commercial.DAO.devis;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class pdfGeneratorService {

public ByteArrayInputStream generatePdf(devis devi) {
    ByteArrayOutputStream out = new ByteArrayOutputStream();


        // Create a PDF writer
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Add title
        document.add(new Paragraph("Devis Details")
                .setFontSize(20)
                .setBold()
                .setMarginBottom(20));

        // Create a table with 2 columns
        Table table = new Table(2);
        table.setWidth(100);

        // Add table headers
        table.addHeaderCell(new Cell().add(new Paragraph("Field")).setBold());
        table.addHeaderCell(new Cell().add(new Paragraph("Value")).setBold());

        // Add data to the table
        table.addCell(new Cell().add(new Paragraph("Devis ID")));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(devi.getId()))));

        table.addCell(new Cell().add(new Paragraph("Client")));
        table.addCell(new Cell().add(new Paragraph(devi.getClient().getName())));

        table.addCell(new Cell().add(new Paragraph("Category")));
        table.addCell(new Cell().add(new Paragraph(devi.getCategory().getName())));

        table.addCell(new Cell().add(new Paragraph("Product")));

        table.addCell(new Cell().add(new Paragraph("Total Sum")));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(devi.getSum()))));

        table.addCell(new Cell().add(new Paragraph("Creation Date")));
        table.addCell(new Cell().add(new Paragraph(devi.getCreationDate().toString())));

        table.addCell(new Cell().add(new Paragraph("Offer End Date")));
        table.addCell(new Cell().add(new Paragraph(devi.getOfferEndDate().toString())));

        table.addCell(new Cell().add(new Paragraph("Currency")));
        table.addCell(new Cell().add(new Paragraph(devi.getCurrency())));

        table.addCell(new Cell().add(new Paragraph("Discount")));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(devi.getDiscount()))));

        document.add(table);

        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}