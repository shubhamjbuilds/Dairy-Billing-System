package com.dairybilling.api.service;

import com.dairybilling.api.dto.BillingReportResponse;
import com.dairybilling.api.entity.Customer;
import com.dairybilling.api.entity.MilkCollection;
import com.dairybilling.api.repository.CustomerRepository;
import com.dairybilling.api.repository.MilkCollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillingService {

    private final MilkCollectionRepository collectionRepo;
    private final CustomerRepository customerRepo;

    public BillingReportResponse generateBill(Long customerId, LocalDate startDate, LocalDate endDate) {
        
        // 1. Find the customer
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // 2. Fetch all milk collections in that date range
        List<MilkCollection> collections = collectionRepo
                .findByCustomerIdAndCollectionDateBetween(customerId, startDate, endDate);

        if (collections.isEmpty()) {
            throw new RuntimeException("No milk collections found for this period.");
        }

        // 3. Calculate the Totals
        double totalLiters = 0.0;
        double totalPayout = 0.0;
        double totalFatSum = 0.0;

        for (MilkCollection record : collections) {
            totalLiters += record.getQuantityInLiters();
            totalPayout += record.getTotalPrice();
            totalFatSum += record.getFatPercentage();
        }

        double averageFat = totalFatSum / collections.size();

        // 4. Build the Receipt (DTO)
        BillingReportResponse report = new BillingReportResponse();
        report.setCustomerName(customer.getName());
        report.setContactNumber(customer.getContactNumber());
        report.setStartDate(startDate);
        report.setEndDate(endDate);
        
        // Math.round is used to keep it to 2 decimal places
        report.setTotalLiters(Math.round(totalLiters * 100.0) / 100.0);
        report.setAverageFat(Math.round(averageFat * 100.0) / 100.0);
        report.setTotalAmountToPay(Math.round(totalPayout * 100.0) / 100.0);

        return report;
    }
}