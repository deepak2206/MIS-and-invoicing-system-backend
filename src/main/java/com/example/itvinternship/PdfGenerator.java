package com.example.itvinternship;

import com.example.itvinternship.model.Invoice;
import org.springframework.stereotype.Component;

@Component
public class PdfGenerator {
    public byte[] generateInvoicePdf(Invoice invoice) {
        // Replace this with actual PDF generation (e.g., iText or OpenPDF)
        return ("Invoice PDF for " + invoice.getInvoiceNo()).getBytes();
    }
}
