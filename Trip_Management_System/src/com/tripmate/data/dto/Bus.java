package com.tripmate.data.dto;

import java.util.HashSet;
import java.util.Set;

public class Bus {
    private String busId;
    private String busNumber;
    private Integer totalSeats;
    private String type; // Sleeper, Semi-Sleeper
    private Set<Integer> bookedSeats = new HashSet<>();

    public Bus() {}

    public Bus(String busId, String busNumber, Integer totalSeats, String type) {
        this.busId = busId;
        this.busNumber = busNumber;
        this.totalSeats = totalSeats;
        this.type = type;
    }

    public String getBusId() { return busId; }
    public void setBusId(String busId) { this.busId = busId; }

    public String getBusNumber() { return busNumber; }
    public void setBusNumber(String busNumber) { this.busNumber = busNumber; }

    public Integer getTotalSeats() { return totalSeats; }
    public void setTotalSeats(Integer totalSeats) { this.totalSeats = totalSeats; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Set<Integer> getBookedSeats() { return bookedSeats; }
    public void setBookedSeats(Set<Integer> bookedSeats) { this.bookedSeats = bookedSeats; }
}
