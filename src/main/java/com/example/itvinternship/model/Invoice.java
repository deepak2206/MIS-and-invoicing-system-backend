package com.example.itvinternship.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique 4-digit Invoice Number
    @Column(nullable = false, unique = true)
    private Integer invoiceNo;

    // Foreign Key Reference to Estimate
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estimate_id", nullable = false)
    private Estimate estimate;

    // Foreign Key Reference to Chain (Company)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chain_id", nullable = false)
    private Chain chain;

    @Column(nullable = false, length = 100)
    private String serviceDetails;

    private Integer qty;

    private Float costPerQty;

    private Float amountPayable;

    private Float amountPaid;

    private Float balance;

    private LocalDate deliveryDate;

    @Column(length = 100)
    private String deliveryDetails;

    @Column(length = 50)
    private String emailId;

    private LocalDateTime dateOfPayment;

    @PrePersist
    protected void onCreate() {
        this.dateOfPayment = LocalDateTime.now();
    }
}
