package com.tripmate.features.travel_package;

import com.tripmate.data.dto.*;
import com.tripmate.data.repository.TripMateDB;

import java.util.List;
import java.util.UUID;

public class TravelPackageModel {
    private final TripMateDB db = TripMateDB.getInstance();

    public List<TravelPackage> getAllPackages() {
        return db.getTravelPackages();
    }

    public Booking bookPackage(User user, TravelPackage pkg) {
        Booking booking = new Booking(UUID.randomUUID().toString(), user.getUserId(), pkg.getPackageId(), null, pkg.getPackageId() , Booking.BookingStatus.PENDING, pkg.getBasePrice(), System.currentTimeMillis() , Booking.BookingType.PACKAGE);
//        db.getBookings().add(booking);
        return booking;
    }

    public void saveToDB(Booking booking){
        db.getBookings().add(booking);
    }
}
