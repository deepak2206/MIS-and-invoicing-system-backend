package com.example.itvinternship.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.itvinternship.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
