package com.tripmate.data.dto;

public class Booking {
    private String bookingId;
    private String userId;

    private String packageId;     // nullable
    private String customTripId;  // nullable
    private String hotelId;       // nullable

    private BookingStatus status;
    private double totalAmount;
    private long createdAt;
    private BookingType bookingType;

    public Booking() {}

    public Booking(String bookingId, String userId, String packageId, String customTripId, String hotelId , BookingStatus status, double totalAmount, long createdAt , BookingType bookingType) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.packageId = packageId;
        this.customTripId = customTripId;
        this.hotelId = hotelId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.bookingType = bookingType;
    }

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getPackageId() { return packageId; }
    public void setPackageId(String packageId) { this.packageId = packageId; }

    public String getCustomTripId() { return customTripId; }
    public void setCustomTripId(String customTripId) { this.customTripId = customTripId; }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { this.status = status; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }

    public BookingType getBookingType() {
        return bookingType;
    }

    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
    }

    public enum BookingStatus {
        PENDING, CONFIRMED, CANCELLED
    }

    public enum BookingType {
        BUS,HOTEL,PACKAGE;
    }
}
