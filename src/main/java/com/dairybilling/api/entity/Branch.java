package com.dairybilling.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "branch")
@Data
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String branchName;

    // This links the Branch back to its Parent Dairy Group
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dairy_group_id", nullable = false)
    private DairyGroup dairyGroup;
}
