package com.tripmate.data.dto;

public class BusTrip {
    private String tripId;
    private String busId;
    private String routeId;
    private long departureTime;
    private long arrivalTime;

    public BusTrip() {}

    public BusTrip(String tripId, String busId, String routeId, long departureTime, long arrivalTime) {
        this.tripId = tripId;
        this.busId = busId;
        this.routeId = routeId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getTripId() { return tripId; }
    public void setTripId(String tripId) { this.tripId = tripId; }

    public String getBusId() { return busId; }
    public void setBusId(String busId) { this.busId = busId; }

    public String getRouteId() { return routeId; }
    public void setRouteId(String routeId) { this.routeId = routeId; }

    public long getDepartureTime() { return departureTime; }
    public void setDepartureTime(long departureTime) { this.departureTime = departureTime; }

    public long getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(long arrivalTime) { this.arrivalTime = arrivalTime; }
}
