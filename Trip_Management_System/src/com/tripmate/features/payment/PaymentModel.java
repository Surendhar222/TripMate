package com.tripmate.features.payment;

import com.tripmate.data.dto.Payments;
import com.tripmate.data.repository.TripMateDB;

public class PaymentModel {
    private final TripMateDB db = TripMateDB.getInstance();

    public boolean processPayment(Payments payment) {
        // Simple logic: success if amount > 0
        if (payment.getAmount() > 0 && payment.getMethod() == Payments.PaymentMethod.CREDIT_CARD || payment.getMethod() == Payments.PaymentMethod.DEBIT_CARD || payment.getMethod() == Payments.PaymentMethod.UPI) {
            payment.setStatus(Payments.PaymentStatus.SUCCESS);
            db.getPayments().add(payment);
            return true;
        }
        payment.setStatus(Payments.PaymentStatus.FAILED);
        return false;
    }
}
