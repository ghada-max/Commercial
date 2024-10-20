package com.Commercial.commercial.Service;

import com.Commercial.commercial.DAO.*;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class pdfGeneratorService {
    public List<product> prods;

    public ByteArrayInputStream generatePdf(devisDTO dev, company comp) throws MalformedURLException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();


        // Create a PDF writer
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);


        // Add title
        document.add(new Paragraph("Devis")
                .setFontSize(25)
                .setBold()
                .setMarginBottom(20).setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER));// Aligner au centre si souhaité


        document.add(new Paragraph("Company Details")
                .setFontSize(20)
                .setBold()
                .setMarginBottom(20));


        // Add company details (Name, Address, etc.)
        Paragraph companyDetails = new Paragraph("Company Name"+comp.getDenomination()+"\nAddress:"+ comp.getAddress()+"\nFax "+comp.getFax()+"\nPhone:+"+comp.getPhone()+"\nEmail:"+comp.getEmail())
                .setBold()
                .setFontSize(12)
                .setMarginBottom(20);

        String imgPath=comp.getImageURL();
        ImageData imageData=ImageDataFactory.create(comp.getImageURL());
        Image img=new Image(imageData);
        img.setWidth(200);
        img.setHeight(150);
        img.setPaddingLeft(5);
        document.add(companyDetails);
        img.setFixedPosition(400, 670);  // Adjust X and Y coordinates for positioning the image

        document.add(img);
        // Create a table with 2 columns
        Paragraph parag1=new Paragraph("");
        Paragraph parag2=new Paragraph("Client: ").setFontSize(20)
                .setBold()
                .setMarginBottom(20);
        Paragraph parag3 = new Paragraph("Name: " + dev.getClient().getName() +
                "\nAddress: " + dev.getClient().getAddress() +
                "\nEmail: " + dev.getClient().getEmail() +

                "\nPhone: " + dev.getClient().getPhone())
                .setBold()
                .setFontSize(12)
                .setMarginBottom(20);

// Add each paragraph individually to the document
        document.add(parag1);
        document.add(parag2);
        document.add(parag3);

        Table table = new Table(4);  // 4 columns
        table.setWidth(UnitValue.createPercentValue(100));  // Set the table width to 100% of the page width

// Add column headers (Products, Quantity, Price, Total)
        table.addHeaderCell(new Cell().add(new Paragraph("Products").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Quantity").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Price").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Total").setBold()));

        for (ProductDTO prods : dev.getProductsDto()) {

            table.addCell(new Paragraph(prods.getName() != null ? prods.getQuantity().toString() : "N/A"));
            table.addCell(new Paragraph(prods.getOrderedQuantity() != null ? prods.getOrderedQuantity().toString() : "N/A"));
            table.addCell(new Paragraph(prods.getPrice() != null ? prods.getPrice().toString() : "N/A"));
            if(prods.getPrice() != null && prods.getOrderedQuantity() != null) {
                table.addCell(new Paragraph(String.valueOf(prods.getPrice() * prods.getOrderedQuantity())));

            }
            else {
                table.addCell(new Paragraph("N/A"));
            }}
        table.addCell(new Paragraph(String.valueOf("Total:"+dev.getSum())+dev.getCurrency()).setUnderline());
        document.add(table);
        Paragraph parag4=new Paragraph("");



        Paragraph parag6 = new Paragraph("Signature")
                .setPaddingRight(1).setBold().setPaddingBottom(20) // Ajuster l'espacement à gauche si nécessaire
                .setTextAlignment(TextAlignment.RIGHT);  // Alignement à gauche


        document.add(parag4);
        document.add(parag6);

        Paragraph validityParagraph = new Paragraph()
                .add(new Text("This document is valid until: ")
                        .setBold()
                        .setFontColor(ColorConstants.RED)) // Set the font color to red
                .add(new Text(dev.getOfferEndDate().toString()) // Add the actual date
                        .setItalic()
                        .setFontSize(12)).setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER);
        document.add(validityParagraph);



        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }}
