package com.tripmate.features.hotel;

import com.tripmate.data.dto.*;
import com.tripmate.data.repository.TripMateDB;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HotelModel {
    private final TripMateDB db = TripMateDB.getInstance();

    public List<Location> getLocations() {
        return db.getLocations();
    }

    public List<Hotel> getHotelsByLocation(String locationId) {
        List<Hotel> allHotels = db.getHotels();
        List<Hotel> filteredHotels = new ArrayList<>();

        for (Hotel allHotel : allHotels) {
            if (allHotel.getLocationId().equals(locationId)) {
                filteredHotels.add(allHotel);
            }
        }

        return filteredHotels;
    }

    public List<RoomType> getRoomsForHotel(String hotelId) {
        List<RoomType> allRooms = db.getRoomTypes();
        List<RoomType> filteredRooms = new ArrayList<>();

        for (RoomType allRoom : allRooms) {
            if (allRoom.getHotelId().equals(hotelId)) {
                filteredRooms.add(allRoom);
            }
        }

        return filteredRooms;
    }

    public Booking bookHotel(User user, RoomType room, int nights) {
        double total = room.getPricePerNight() * nights;
        Booking booking = new Booking(UUID.randomUUID().toString(), user.getUserId(), null, room.getRoomTypeId(), room.getHotelId() , Booking.BookingStatus.PENDING, total, System.currentTimeMillis() , Booking.BookingType.HOTEL);
       // db.getBookings().add(booking);
        return booking;
    }

    public void saveToDB(Booking booking){
        db.getBookings().add(booking);
    }
}
