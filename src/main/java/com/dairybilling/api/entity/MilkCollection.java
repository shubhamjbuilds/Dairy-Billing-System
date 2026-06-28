package com.dairybilling.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "milk_collection")
@Data
public class MilkCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Links this specific milk drop-off to a registered customer
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private LocalDate collectionDate;
    
    @Column(nullable = false)
    private String shift; // e.g., "MORNING" or "EVENING"

    @Column(nullable = false)
    private Double quantityInLiters;
    
    @Column(nullable = false)
    private Double fatPercentage;
    
    private Double snfPercentage; // Solid Not Fat (Optional depending on the dairy)
    
    private Double totalPrice; // We will calculate this automatically!
}