package com.tripmate.data.dto;

import java.util.Objects;

public class Payments {
    private String paymentId;
    private String bookingId;
    private double amount;
    private PaymentStatus status;
    private PaymentMethod method;
    private long createdAt;

    public Payments() {}

    public Payments(String paymentId, String bookingId, double amount, PaymentStatus status, PaymentMethod method, long createdAt) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.status = status;
        this.method = method;
        this.createdAt = createdAt;
    }

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }

    public PaymentMethod getMethod() { return method; }
    public void setMethod(PaymentMethod method) { this.method = method; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payments payments = (Payments) o;
        return Double.compare(payments.amount, amount) == 0 && createdAt == payments.createdAt && Objects.equals(paymentId, payments.paymentId) && Objects.equals(bookingId, payments.bookingId) && status == payments.status && method == payments.method;
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, bookingId, amount, status, method, createdAt);
    }

    public enum PaymentMethod {
        UPI, CREDIT_CARD, DEBIT_CARD, NET_BANKING
    }

    public enum PaymentStatus {
        INITIATED, SUCCESS, FAILED, REFUNDED
    }
}
