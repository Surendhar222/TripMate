package com.tripmate.data.dto;

public class RoomType {
    private String roomTypeId;
    private String hotelId;
    private String type;
    private Integer capacity;
    private Double pricePerNight;
    private Integer totalRooms;
    private Integer bookedRooms;

    public RoomType() {
    }

    public RoomType(String roomTypeId, String hotelId, String type, Integer capacity, Double pricePerNight, Integer totalRooms) {
        this.roomTypeId = roomTypeId;
        this.hotelId = hotelId;
        this.type = type;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
        this.totalRooms = totalRooms;
        this.bookedRooms = 0;
    }

    public String getRoomTypeId() { return roomTypeId; }
    public void setRoomTypeId(String roomTypeId) { this.roomTypeId = roomTypeId; }

    public String getHotelId() { return hotelId; }
    public void setHotelId(String hotelId) { this.hotelId = hotelId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public Double getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(Double pricePerNight) { this.pricePerNight = pricePerNight; }

    public Integer getTotalRooms() { return totalRooms; }
    public void setTotalRooms(Integer totalRooms) { this.totalRooms = totalRooms; }

    public Integer getBookedRooms() { return bookedRooms; }
    public void setBookedRooms(Integer bookedRooms) { this.bookedRooms = bookedRooms; }

    public Integer getAvailableRooms() { return totalRooms - bookedRooms; }
}
