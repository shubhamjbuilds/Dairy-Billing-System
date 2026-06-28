package com.dairybilling.api.service;

import com.dairybilling.api.entity.Customer;
import com.dairybilling.api.entity.MilkCollection;
import com.dairybilling.api.repository.CustomerRepository;
import com.dairybilling.api.repository.MilkCollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MilkCollectionService {

    private final MilkCollectionRepository collectionRepo;
    private final CustomerRepository customerRepo;

    // This could eventually be moved to a database table so admins can change it!
    private static final Double RATE_PER_FAT_UNIT = 7.00;

    @Transactional
    public MilkCollection recordCollection(MilkCollection request, Long customerId) {
        
        // 1. Verify the customer actually exists and is approved
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

        if (!customer.isActive()) {
            throw new RuntimeException("Cannot collect milk for an inactive customer.");
        }

        // 2. The Dynamic Math Engine
        Double fatPercentage = request.getFatPercentage();
        Double quantity = request.getQuantityInLiters();
        
        // Calculate price per liter based on fat
        Double ratePerLiter = fatPercentage * RATE_PER_FAT_UNIT;
        
        // Calculate final payout
        Double totalPayout = ratePerLiter * quantity;

        // 3. Assemble the final record
        request.setCustomer(customer);
        request.setTotalPrice(totalPayout);
        
        // Automatically set the date to today if not provided
        if (request.getCollectionDate() == null) {
            request.setCollectionDate(LocalDate.now());
        }

        // 4. Save to database
        return collectionRepo.save(request);
    }
}