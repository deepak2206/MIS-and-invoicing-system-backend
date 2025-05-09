package com.example.itvinternship.controller;

import com.example.itvinternship.model.Invoice;
import com.example.itvinternship.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/invoices")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/generate/{estimateId}")
    public Invoice generateInvoice(@PathVariable Long estimateId, @RequestBody Map<String, String> payload) {
        return invoiceService.generateInvoice(estimateId, payload.get("emailId"));
    }

    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @PutMapping("/{id}/update-email")
    public Invoice updateEmail(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        return invoiceService.updateEmail(id, payload.get("emailId"));
    }

    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
    }
}
