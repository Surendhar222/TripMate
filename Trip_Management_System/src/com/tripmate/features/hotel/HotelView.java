package com.tripmate.features.hotel;

import com.tripmate.data.dto.*;
import com.tripmate.data.repository.TripMateDB;
import com.tripmate.features.payment.PaymentView;
import com.tripmate.util.ParseHelper;
import com.tripmate.util.Validator;

import java.util.List;
import java.util.UUID;

public class HotelView {
    private final HotelModel model = new HotelModel();
    private final TripMateDB db = TripMateDB.getInstance();


    public void addHotel() {
        System.out.println("\n--- Add Hotel ---");
        String name;
        while (true) {
            name = ParseHelper.readString("Hotel Name: ");
            if (Validator.isValidHotelName(name)) {
                break;
            }
            System.out.println("Invalid hotel name.");
        }

        String desc;

        while (true) {
            desc = ParseHelper.readString("Description: ");
            if (Validator.isValidDescription(desc)) {
                break;
            }

            System.out.println("Invalid description.");
        }

        List<Location> list = db.getLocations();

        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getCity());
        }

        int index = ParseHelper.readInt("Select Location: ") - 1;

        if (index < 0 || index >= list.size()) {
            System.out.println("Invalid location selected.");
            return;
        }

        String locationId = list.get(index).getLocationId();

        double rating;
        while (true) {
            rating = ParseHelper.readDouble("Rating (0-5): ");
            if (Validator.isValidRating(rating)) {
                break;
            }
            System.out.println("Invalid rating.");
        }

        String hotelId = UUID.randomUUID().toString();
        Hotel h = new Hotel(hotelId, name, desc, locationId, rating);

        db.getHotels().add(h);
        System.out.println("\nAdd Room Types for " + name);

        int deluxeCount;
        while (true) {
            deluxeCount = ParseHelper.readInt("How many Deluxe rooms: ");
            if (Validator.isValidRoomCount(deluxeCount)) {
                break;
            }
            System.out.println("Invalid room count.");
        }

        double deluxePrice;
        while (true) {
            deluxePrice = ParseHelper.readDouble("Price for Deluxe: ");
            if (Validator.isValidPrice(deluxePrice)) {
                break;
            }
            System.out.println("Invalid deluxe price.");
        }

        db.getRoomTypes().add(new RoomType(UUID.randomUUID().toString(), hotelId, "Deluxe", 2, deluxePrice, deluxeCount));

        int suiteCount;
        while (true) {
            suiteCount = ParseHelper.readInt("How many Suite rooms: ");
            if (Validator.isValidRoomCount(suiteCount)) {
                break;
            }
            System.out.println("Invalid room count.");
        }

        double suitePrice;
        while (true) {
            suitePrice = ParseHelper.readDouble("Price for Suite: ");
            if (Validator.isValidPrice(suitePrice)) {
                break;
            }
            System.out.println("Invalid suite price.");
        }

        db.getRoomTypes().add(new RoomType(UUID.randomUUID().toString(), hotelId, "Suite", 2, suitePrice, suiteCount));
        db.getMap().put(hotelId, name);

        System.out.println("Hotel and Rooms added successfully!");
    }

    public void hotelBooking(User user) {
        System.out.println("\n--- Hotel Booking ---");
        List<Location> locations = model.getLocations();
        for (int i = 0; i < locations.size(); i++) {
            System.out.println((i + 1) + ". " + locations.get(i).getCity());
        }

        int locIdx = ParseHelper.readInt("Select City: ") - 1;
        if (locIdx < 0 || locIdx >= locations.size()) return;

        List<Hotel> hotels = model.getHotelsByLocation(locations.get(locIdx).getLocationId());
        if (hotels.isEmpty()) {
            System.out.println("No hotels found.");
            return;
        }

        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).getName());
        }

        int hotelIdx = ParseHelper.readInt("Select Hotel: ") - 1;
        if (hotelIdx < 0 || hotelIdx >= hotels.size()) return;

        Hotel selectedHotel = hotels.get(hotelIdx);
        List<RoomType> rooms = model.getRoomsForHotel(selectedHotel.getHotelId());
        
        System.out.println("\nAvailable Room Types:");
        for (int i = 0; i < rooms.size(); i++) {
            RoomType r = rooms.get(i);
            System.out.println((i + 1) + ". " + r.getType() + " | Price: Rs." + r.getPricePerNight() + " | Available: " + r.getAvailableRooms());
        }

        int roomIdx = ParseHelper.readInt("Select Room Type: ") - 1;
        if (roomIdx < 0 || roomIdx >= rooms.size()) return;

        RoomType selectedRoom = rooms.get(roomIdx);
        if (selectedRoom.getAvailableRooms() <= 0) {
            System.out.println("Sorry, no rooms available in this category.");
            return;
        }

        int nights = ParseHelper.readInt("Number of nights: ");
        
        // Update room count
        selectedRoom.setBookedRooms(selectedRoom.getBookedRooms() + 1);

        Booking booking = model.bookHotel(user, selectedRoom, nights);
        if(new PaymentView().makePayment(booking.getBookingId(), booking.getTotalAmount())) {
            model.saveToDB(booking);
            printHotelReceipt(user, selectedHotel, selectedRoom, nights);
        }
        else
            System.out.println("Booking Failed, Due to Payment Failure.");
    }

    private void printHotelReceipt(User user, Hotel hotel, RoomType room, int nights) {
        System.out.println("\n+------------------------------------------+");
        System.out.println("|             TRIPMATE HOTEL RECEIPT       |");
        System.out.println("+------------------------------------------+");
        System.out.println(String.format("| Guest: %-33s |", user.getName()));
        System.out.println(String.format("| Hotel: %-33s |", hotel.getName()));
        System.out.println(String.format("| Room Type: %-29s |", room.getType()));
        System.out.println(String.format("| Duration:  %-29s |", nights + " Nights"));
        System.out.println(String.format("| Total Paid: Rs. %-24.2f |", (room.getPricePerNight() * nights)));
        System.out.println("+------------------------------------------+");
    }

    public void viewHotelBookings() {

        System.out.println("\n--- Hotel Bookings ---");

        boolean found = false;

        for (Booking booking : db.getBookings()) {

            if (booking.getBookingType() == Booking.BookingType.HOTEL) {

                found = true;

                System.out.println("--------------------------------");

                System.out.println("Booking ID : " + booking.getBookingId());
                System.out.println("User ID    : " + booking.getUserId());
                System.out.println("Hotel      : " + db.getMap().get(booking.getHotelId()));
//                System.out.println("Rooms      : " + booking.getQuantity());
                System.out.println("Amount     : " + booking.getTotalAmount());
                System.out.println("Status     : " + booking.getStatus());
            }
        }

        if (!found) {
            System.out.println("No hotel bookings found.");
        }
    }
}
