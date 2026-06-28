package com.dairybilling.api.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BillingReportResponse {
    private String customerName;
    private String contactNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalLiters;
    private Double averageFat;
    private Double totalAmountToPay;
}