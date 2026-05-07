package com.tripmate.data.repository;

import com.tripmate.data.dto.*;

import java.util.*;

public class TripMateDB {
    private static TripMateDB instance;

    private List<User> users = new ArrayList<>();
    private List<Credential> credentials = new ArrayList<>();
    private List<Hotel> hotels = new ArrayList<>();
    private List<RoomType> roomTypes = new ArrayList<>();
    private List<HotelInventory> hotelInventories = new ArrayList<>();
    private List<Bus> buses = new ArrayList<>();
    private List<BusTrip> busTrips = new ArrayList<>();
    private List<BusInventory> busInventories = new ArrayList<>();
    private List<Location> locations = new ArrayList<>();
    private List<TravelPackage> travelPackages = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();
    private List<Payments> payments = new ArrayList<>();
    private List<Route> routes = new ArrayList<>();
    private List<Role> roles = new ArrayList<>();
    private List<UserRole> userRoles = new ArrayList<>();
    private Map<String , String> map = new HashMap<>();

    private TripMateDB() {
        seedData();
    }

    public static synchronized TripMateDB getInstance() {
        if (instance == null) {
            instance = new TripMateDB();
        }
        return instance;
    }

    private void seedData() {
        // Seed Roles
        roles.add(new Role("R_ADMIN", "ADMIN"));
        roles.add(new Role("R_CUSTOMER", "CUSTOMER"));

        // Locations in Tamil Nadu
        locations.add(new Location("L1", "Chennai", "Tamil Nadu", "India"));
        locations.add(new Location("L2", "Coimbatore", "Tamil Nadu", "India"));
        locations.add(new Location("L3", "Madurai", "Tamil Nadu", "India"));
        locations.add(new Location("L4", "Ooty", "Tamil Nadu", "India"));
        locations.add(new Location("L5", "Kanyakumari", "Tamil Nadu", "India"));
        locations.add(new Location("L6", "Salem", "Tamil Nadu", "India"));
        locations.add(new Location("L7", "Trichy", "Tamil Nadu", "India"));
        locations.add(new Location("L8", "Pondicherry", "Puducherry", "India"));
        locations.add(new Location("L9", "Rameswaram", "Tamil Nadu", "India"));
        locations.add(new Location("L10", "Kodaikanal", "Tamil Nadu", "India"));
        
        // Hotels
        hotels.add(new Hotel("H1", "ITC Grand Chola", "Luxury stay in Chennai", "L1", 4.8));
        hotels.add(new Hotel("H2", "Taj Coromandel", "Iconic hotel in Chennai", "L1", 4.7));
        map.put("H1" , "ITC Grand Chola");
        map.put("H2" , "Taj Coromandel");


        // Room Types (Corrected constructor usage)
        roomTypes.add(new RoomType("RT1", "H1", "Deluxe", 2, 8000.0, 10));
        roomTypes.add(new RoomType("RT2", "H1", "Suite", 2, 15000.0, 5));

        // Buses
        buses.add(new Bus("B1", "TN-01-AB-1234", 40, "Semi-Sleeper"));
        buses.add(new Bus("B2", "TN-37-XY-5678", 36, "Sleeper"));

        // Routes
        routes.add(new Route("R_CHN_CBE", "L1", "L2", 500));
        routes.add(new Route("R_CHN_MDU", "L1", "L3", 450));

        // Bus Trips
        long now = System.currentTimeMillis();
        long oneHour = 60 * 60 * 1000;
        long oneDay = 24 * oneHour;
        busTrips.add(new BusTrip("BT1", "B1", "R_CHN_CBE", now + oneDay + 6 * oneHour, now + oneDay + 10 * oneHour));

        // Travel Packages
        travelPackages.add(new TravelPackage("P1", "Ooty Summer Bliss", "3 days in Ooty from Chennai", "L1", "L4", 3, 12000));
    }

    public List<User> getUsers() { return users; }
    public List<Credential> getCredentials() { return credentials; }
    public List<Hotel> getHotels() { return hotels; }
    public List<RoomType> getRoomTypes() { return roomTypes; }
    public List<HotelInventory> getHotelInventories() { return hotelInventories; }
    public List<Bus> getBuses() { return buses; }
    public List<BusTrip> getBusTrips() { return busTrips; }
    public List<BusInventory> getBusInventories() { return busInventories; }
    public List<Location> getLocations() { return locations; }
    public List<TravelPackage> getTravelPackages() { return travelPackages; }
    public List<Booking> getBookings() { return bookings; }
    public List<Payments> getPayments() { return payments; }
    public List<Route> getRoutes() { return routes; }
    public List<Role> getRoles() { return roles; }
    public List<UserRole> getUserRoles() { return userRoles; }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
