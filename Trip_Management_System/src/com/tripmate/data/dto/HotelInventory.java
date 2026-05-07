package com.tripmate.data.dto;

public class HotelInventory {
    private String inventoryId;
    private String roomTypeId;
    private long date;
    private int availableRooms;

    public HotelInventory() {}

    public HotelInventory(String inventoryId, String roomTypeId, long date, int availableRooms) {
        this.inventoryId = inventoryId;
        this.roomTypeId = roomTypeId;
        this.date = date;
        this.availableRooms = availableRooms;
    }

    public String getInventoryId() { return inventoryId; }
    public void setInventoryId(String inventoryId) { this.inventoryId = inventoryId; }

    public String getRoomTypeId() { return roomTypeId; }
    public void setRoomTypeId(String roomTypeId) { this.roomTypeId = roomTypeId; }

    public long getDate() { return date; }
    public void setDate(long date) { this.date = date; }

    public int getAvailableRooms() { return availableRooms; }
    public void setAvailableRooms(int availableRooms) { this.availableRooms = availableRooms; }
}
