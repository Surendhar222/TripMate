package com.tripmate.features.payment;

import com.tripmate.data.dto.Payments;
import com.tripmate.util.ParseHelper;

import java.util.UUID;

public class PaymentView {
    private final PaymentModel model = new PaymentModel();

    public boolean makePayment(String bookingId, double amount) {
        System.out.println("\n--- Payment ---");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Total Amount: " + amount);

        System.out.println("Select Payment Method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit Card");
        System.out.println("3. UPI");
        int choice = ParseHelper.readInt("Choice: ");

        Payments.PaymentMethod method = null;

        if(choice == 1)method = Payments.PaymentMethod.CREDIT_CARD;
        else if (choice == 2) method = Payments.PaymentMethod.DEBIT_CARD;
        else if (choice == 3) method = Payments.PaymentMethod.UPI;
        else System.out.println("Invalid Option, Please Check");

        Payments payment = new Payments(UUID.randomUUID().toString(), bookingId, amount, null, method, System.currentTimeMillis());

        if (model.processPayment(payment)) {
            System.out.println("Payment Successful!");
            return true;
        } else {
            System.out.println("Payment Failed. Please try again.");
            return false;
        }
    }
}
