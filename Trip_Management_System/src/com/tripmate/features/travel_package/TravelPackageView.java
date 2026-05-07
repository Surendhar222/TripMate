package com.tripmate.features.travel_package;

import com.tripmate.data.dto.Booking;
import com.tripmate.data.dto.TravelPackage;
import com.tripmate.data.dto.User;
import com.tripmate.data.repository.TripMateDB;
import com.tripmate.features.payment.PaymentView;
import com.tripmate.util.ParseHelper;

import java.util.List;
import java.util.UUID;

public class TravelPackageView {
    private final TravelPackageModel model = new TravelPackageModel();
    private final TripMateDB db = TripMateDB.getInstance();

    public void addPackage() {
        System.out.println("\n--- Add Travel Package ---");
        String name = ParseHelper.readString("Package Name: ");
        String desc = ParseHelper.readString("Description: ");
        double price = ParseHelper.readDouble("Base Price: ");
        int days = ParseHelper.readInt("Duration (Days): ");

        TravelPackage p = new TravelPackage(UUID.randomUUID().toString(), name, desc, "L1", "L4", days, price);
        db.getTravelPackages().add(p);
        System.out.println("Package added successfully!");
    }

    public void displayPackages(User user) {
        System.out.println("\n--- Travel Packages (Tamil Nadu) ---");
        List<TravelPackage> packages = model.getAllPackages();

        for (int i = 0; i < packages.size(); i++) {
            TravelPackage p = packages.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " | " + p.getDurationDays() + " Days | Rs." + p.getBasePrice());
            System.out.println("   " + p.getDescription());
        }

        int choice = ParseHelper.readInt("Select Package to book (0 to cancel): ") - 1;
        if (choice < 0 || choice >= packages.size()) return;

        Booking booking = model.bookPackage(user, packages.get(choice));
        if(new PaymentView().makePayment(booking.getBookingId(), booking.getTotalAmount())){
            model.saveToDB(booking);
        }else {
            System.out.println("Booking Failure, Due to Payment failed");
        }
    }

    public void viewPackageBookings() {

        System.out.println("\n--- Package Bookings ---");

        boolean found = false;

        for (Booking booking : db.getBookings()) {

            if (booking.getBookingType() == Booking.BookingType.PACKAGE) {

                found = true;

                System.out.println("--------------------------------");

                System.out.println("Booking ID : " + booking.getBookingId());
                System.out.println("User ID    : " + booking.getUserId());
                System.out.println("Package ID : " + booking.getPackageId());
               // System.out.println("Travellers : " + booking.getQuantity());
                System.out.println("Amount     : " + booking.getTotalAmount());
                System.out.println("Status     : " + booking.getStatus());
            }
        }

        if (!found) {
            System.out.println("No package bookings found.");
        }
    }
}
