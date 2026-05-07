package com.tripmate.features.bus;

import com.tripmate.data.dto.*;
import com.tripmate.data.repository.TripMateDB;
import com.tripmate.features.payment.PaymentView;
import com.tripmate.util.ParseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class BusView {
    private final BusModel model = new BusModel();
    private final TripMateDB db = TripMateDB.getInstance();



    public void addBus() {

        System.out.println("\n--- Add Bus ---");

        String number = ParseHelper.readString("Bus Number: ");

        int seats = 0;
        while (true) {
            seats = ParseHelper.readInt("Total Seats: ");
            if (seats > 20 && seats <= 40) {
                break;
            }
            System.out.println("Seats should be between 20 and 40.");
        }

        String type = ParseHelper.readString("Type (Sleeper/Semi-Sleeper): ");

        String busId = UUID.randomUUID().toString();

        Bus bus = new Bus(busId, number, seats, type);

        db.getBuses().add(bus);


        System.out.println("\n--- Route Details ---");

        String source = ParseHelper.readString("Source Location ID [L1 to L10]: ");
        String destination = ParseHelper.readString("Destination Location ID [L1 to L10]: ");

        int distance = ParseHelper.readInt("Distance (KM): ");

        String routeId = UUID.randomUUID().toString();

        Route route = new Route(routeId, source, destination, distance);

        db.getRoutes().add(route);


        System.out.println("\n--- Trip Details ---");

        long departureTime = ParseHelper.readLong("Departure Time (milliseconds): ");

        long arrivalTime = ParseHelper.readLong("Arrival Time (milliseconds): ");

        String tripId = UUID.randomUUID().toString();

        BusTrip trip = new BusTrip(
                tripId,
                busId,
                routeId,
                departureTime,
                arrivalTime
        );

        db.getBusTrips().add(trip);

        System.out.println("\nBus, Route and Trip added successfully!");
    }

    public void busBooking(User user) {
        System.out.println("\n--- Bus Booking ---");
        List<Location> locations = model.getLocations();
        for (int i = 0; i < locations.size(); i++) {
            System.out.println((i + 1) + ". " + locations.get(i).getCity());
        }

        int srcIdx = ParseHelper.readInt("Select Source City (Number): ") - 1;
        int destIdx = ParseHelper.readInt("Select Destination City (Number): ") - 1;

        if (srcIdx < 0 || srcIdx >= locations.size() || destIdx < 0 || destIdx >= locations.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Location srcLoc = locations.get(srcIdx);
        Location destLoc = locations.get(destIdx);

        List<BusTrip> trips = model.searchTrips(srcLoc.getLocationId(), destLoc.getLocationId());

        if (trips.isEmpty()) {
            System.out.println("No buses available for this route.");
            return;
        }

        System.out.println("\nAvailable Trips:");
        for (int i = 0; i < trips.size(); i++) {
            BusTrip t = trips.get(i);
            Bus b = model.getBusById(t.getBusId());
            System.out.println((i + 1) + ". Bus: " + b.getBusNumber() + " (" + b.getType() + ")");
            System.out.println("   Departure: " + ParseHelper.formatDateTime(t.getDepartureTime()) + " | Arrival: " + ParseHelper.formatDateTime(t.getArrivalTime()));
        }

        int tripIdx = ParseHelper.readInt("Select Trip to book (Number): ") - 1;
        if (tripIdx < 0 || tripIdx >= trips.size()) return;

        BusTrip selectedTrip = trips.get(tripIdx);
        Bus selectedBus = model.getBusById(selectedTrip.getBusId());
        
        List<Integer> selectedSeats = showSeatUI(selectedBus);
        if (selectedSeats.isEmpty()) return;

        double price = (selectedBus.getType().equalsIgnoreCase("Sleeper") ? 1200 : 800) * selectedSeats.size();
        
        Booking booking = model.bookBus(user, selectedTrip, selectedSeats, price);
        if (new PaymentView().makePayment(booking.getBookingId(), booking.getTotalAmount())) {
            model.saveToDB(booking , selectedTrip , selectedSeats);
            printTicket(user, selectedTrip, selectedBus, selectedSeats, price, srcLoc, destLoc);
        }else {
            System.out.println("Booking Failed, Due to Payment Failure.");
        }
    }

    private List<Integer> showSeatUI(Bus bus) {
        Set<Integer> booked = bus.getBookedSeats();
        int total = bus.getTotalSeats();
        List<Integer> selected = new ArrayList<>();

        while (true) {
            System.out.println("\n--- Select Seats for " + bus.getBusNumber() + " ---");
            for (int i = 1; i <= total; i++) {
                String label = String.format("[%02d]", i);
                System.out.print(booked.contains(i) ? "[XX] " : label + " ");
                if (i % 4 == 0) System.out.println();
                else if (i % 2 == 0) System.out.print("  ");
            }
            System.out.println();

            String input = ParseHelper.readString("Enter seat numbers (e.g. 1,2) or '0' to cancel: ");
            if (input.equals("0")) return new ArrayList<>();

            String[] seatInputs = input.split(",");
            boolean allValid = true;
            List<Integer> tempSelection = new ArrayList<>();

            for (String s : seatInputs) {
                try {
                    int sn = Integer.parseInt(s.trim());
                    if (sn > 0 && sn <= total && !booked.contains(sn)) {
                        tempSelection.add(sn);
                    } else {
                        System.out.println("Error: Seat " + sn + " is invalid or already booked.");
                        allValid = false;
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: '" + s + "' is not a valid number.");
                    allValid = false;
                    break;
                }
            }

            if (allValid && !tempSelection.isEmpty()) {
                selected = tempSelection;
                break; // Exit the loop when all selected seats are valid
            } else if (tempSelection.isEmpty()) {
                System.out.println("Please select at least one seat.");
            }
        }
        return selected;
    }

    private void printTicket(User user, BusTrip trip, Bus bus, List<Integer> seats, double price, Location src, Location dest) {
        System.out.println("\n+------------------------------------------+");
        System.out.println("|             TRIPMATE E-TICKET            |");
        System.out.println("+------------------------------------------+");
        System.out.println(String.format("| Passenger: %-29s |", user.getName()));
        System.out.println(String.format("| Bus: %-10s Type: %-14s |", bus.getBusNumber(), bus.getType()));
        System.out.println(String.format("| Seats: %-33s |", seats.toString()));
        System.out.println(String.format("| From: %-12s To: %-15s |", src.getCity(), dest.getCity()));
        System.out.println(String.format("| Departure: %-29s |", ParseHelper.formatDateTime(trip.getDepartureTime())));
        System.out.println(String.format("| Arrival:   %-29s |", ParseHelper.formatDateTime(trip.getArrivalTime())));
        System.out.println(String.format("| Fare Paid: Rs. %-25.2f |", price));
        System.out.println("+------------------------------------------+");
    }

    public void viewBusBookings() {

        System.out.println("\n--- Bus Bookings ---");

        boolean found = false;

        for (Booking booking : db.getBookings()) {

            if (booking.getBookingType() == Booking.BookingType.BUS) {

                found = true;

                System.out.println("--------------------------------");

                System.out.println("Booking ID : " + booking.getBookingId());
                System.out.println("User ID    : " + booking.getUserId());
                System.out.println("Trip ID    : " + booking.getCustomTripId());
              //  System.out.println("Seats      : " + booking.get());
                System.out.println("Amount     : " + booking.getTotalAmount());
                System.out.println("Status     : " + booking.getStatus());
            }
        }

        if (!found) {
            System.out.println("No bus bookings found.");
        }
    }
}
