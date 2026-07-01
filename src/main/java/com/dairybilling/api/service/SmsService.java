package com.dairybilling.api.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    @PostConstruct
    public void initTwilio() {
        Twilio.init(accountSid, authToken);
    }

    public void sendReceipt(String destinationPhoneNumber, Double liters, Double fat, Double amount) {
        try {
            // Standard SMS format
            String receiptText = String.format(
                "🥛 Dairy Receipt:\nMilk: %.1f Liters\nFat: %.1f%%\nTotal Credited: Rs. %.2f\nThank you!",
                liters, fat, amount
            );

            // Standard SMS payload (No 'whatsapp:' prefix)
            Message message = Message.creator(
                    new PhoneNumber(destinationPhoneNumber), // To
                    new PhoneNumber(twilioPhoneNumber),      // From
                    receiptText                              // Body
            ).create();

            System.out.println("✅ SMS Receipt Sent Successfully! Twilio Tracking SID: " + message.getSid());
            
        } catch (Exception e) {
            System.err.println("❌ Failed to send SMS: " + e.getMessage());
        }
    }
}