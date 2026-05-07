package com.tripmate.data.dto;

public class Hotel {
    private String hotelId;
    private String name;
    private String description;
    private String locationId;
    private Double rating;

    public Hotel() {}

    public Hotel(String hotelId, String name, String description, String locationId, Double rating) {
        this.hotelId = hotelId;
        this.name = name;
        this.description = description;
        this.locationId = locationId;
        this.rating = rating;
    }

    public String getHotelId() { return hotelId; }
    public void setHotelId(String hotelId) { this.hotelId = hotelId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocationId() { return locationId; }
    public void setLocationId(String locationId) { this.locationId = locationId; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
}
