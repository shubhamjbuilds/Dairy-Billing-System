package com.dairybilling.api.entity;

import com.dairybilling.api.enums.ApprovalStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "branch_staging")
@Data
public class BranchStaging {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String branchName;

    // The ID of the DairyGroup (Parent) they claim to belong to
    @Column(nullable = false)
    private Long dairyGroupId; 

    @Enumerated(EnumType.STRING)
    private ApprovalStatus status = ApprovalStatus.PENDING;
}
