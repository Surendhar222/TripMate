package com.tripmate.data.dto;

public class BusInventory {
    private String inventoryId;
    private String tripId;
    private int availableSeats;

    public BusInventory() {}

    public BusInventory(String inventoryId, String tripId, int availableSeats) {
        this.inventoryId = inventoryId;
        this.tripId = tripId;
        this.availableSeats = availableSeats;
    }

    public String getInventoryId() { return inventoryId; }
    public void setInventoryId(String inventoryId) { this.inventoryId = inventoryId; }

    public String getTripId() { return tripId; }
    public void setTripId(String tripId) { this.tripId = tripId; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }
}
