package com.example.itvinternship.repo;

import com.example.itvinternship.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findByInvoiceNo(Integer invoiceNo);
    boolean existsByInvoiceNo(Integer invoiceNo);
}
