package com.tripmate.features.bus;

import com.tripmate.data.dto.*;
import com.tripmate.data.repository.TripMateDB;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class BusModel {
    private final TripMateDB db = TripMateDB.getInstance();

    public List<Location> getLocations() {
        return db.getLocations();
    }

    public List<BusTrip> searchTrips(String srcId, String destId) {
        List<Route> allRoutes = db.getRoutes();
        List<String> foundRouteIds = new ArrayList<>();

        for (Route r : allRoutes) {
            if (r.getSourceLocationId().equals(srcId) && r.getDestinationLocationId().equals(destId)) {
                foundRouteIds.add(r.getRouteId());
            }
        }

        List<BusTrip> allTrips = db.getBusTrips();
        List<BusTrip> matchingTrips = new ArrayList<>();

        for (BusTrip t : allTrips) {
            for (String foundRouteId : foundRouteIds) {
                if (t.getRouteId().equals(foundRouteId)) {
                    matchingTrips.add(t);
                }
            }
        }
        return matchingTrips;
    }

    public Bus getBusById(String busId) {
        List<Bus> allBuses = db.getBuses();
        for (Bus allBus : allBuses) {
            if (allBus.getBusId().equals(busId)) {
                return allBus;
            }
        }
        return null;
    }

    public Route getRouteById(String routeId) {
        List<Route> allRoutes = db.getRoutes();
        for (Route allRoute : allRoutes) {
            if (allRoute.getRouteId().equals(routeId)) {
                return allRoute;
            }
        }
        return null;
    }

    public Booking bookBus(User user, BusTrip trip, List<Integer> seats, double price) {
//        Bus bus = getBusById(trip.getBusId());
//        if (bus != null) {
//            for (Integer seat : seats) {
//                bus.getBookedSeats().add(seat);
//            }
//        }

        Booking booking = new Booking(UUID.randomUUID().toString(), user.getUserId(), null, trip.getTripId(), trip.getBusId(), Booking.BookingStatus.PENDING, price, System.currentTimeMillis() , Booking.BookingType.BUS);
       // db.getBookings().add(booking);
        return booking;
    }

    public void saveToDB(Booking booking , BusTrip trip, List<Integer> seats){
        db.getBookings().add(booking);

        Bus bus = getBusById(trip.getBusId());
        if (bus != null) {
            for (Integer seat : seats) {
                bus.getBookedSeats().add(seat);
            }
        }
    }

    public Location getLocationById(String locationId) {
        List<Location> allLocations = db.getLocations();
        for (Location allLocation : allLocations) {
            if (allLocation.getLocationId().equals(locationId)) {
                return allLocation;
            }
        }
        return null;
    }
}
