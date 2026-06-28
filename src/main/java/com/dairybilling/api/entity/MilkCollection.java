package com.dairybilling.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

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

    @NotNull(message = "Quantity cannot be empty")
    @Positive(message = "Quantity must be greater than zero")
    @Column(nullable = false)
    private Double quantityInLiters;
    
    @NotNull(message = "Fat percentage cannot be empty")
    @Positive(message = "Fat must be greater than zero")
    @Column(nullable = false)
    private Double fatPercentage;
    
    private Double snfPercentage; // Solid Not Fat (Optional depending on the dairy)
    
    private Double totalPrice; // We will calculate this automatically!
}