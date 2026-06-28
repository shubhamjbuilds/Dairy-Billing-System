package com.dairybilling.api.entity;

import com.dairybilling.api.enums.ApprovalStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customer_staging")
@Data
public class CustomerStaging {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String contactNumber;

    // The ID of the Branch (Sub-parent) they claim to belong to
    @Column(nullable = false)
    private Long branchId; 

    @Enumerated(EnumType.STRING)
    private ApprovalStatus status = ApprovalStatus.PENDING;
}