package com.tripmate.data.dto;

public class Route {
    private String routeId;
    private String sourceLocationId;
    private String destinationLocationId;
    private double distance;

    public Route() {}

    public Route(String routeId, String sourceLocationId, String destinationLocationId, double distance) {
        this.routeId = routeId;
        this.sourceLocationId = sourceLocationId;
        this.destinationLocationId = destinationLocationId;
        this.distance = distance;
    }

    public String getRouteId() { return routeId; }
    public void setRouteId(String routeId) { this.routeId = routeId; }

    public String getSourceLocationId() { return sourceLocationId; }
    public void setSourceLocationId(String sourceLocationId) { this.sourceLocationId = sourceLocationId; }

    public String getDestinationLocationId() { return destinationLocationId; }
    public void setDestinationLocationId(String destinationLocationId) { this.destinationLocationId = destinationLocationId; }

    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }
}
