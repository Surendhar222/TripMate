package com.tripmate.data.dto;

public class TravelPackage {
    private String packageId;
    private String name;
    private String description;
    private String sourceLocationId;
    private String destinationLocationId;
    private int durationDays;
    private double basePrice;

    public TravelPackage() {}

    public TravelPackage(String packageId, String name, String description, String sourceLocationId, String destinationLocationId, int durationDays, double basePrice) {
        this.packageId = packageId;
        this.name = name;
        this.description = description;
        this.sourceLocationId = sourceLocationId;
        this.destinationLocationId = destinationLocationId;
        this.durationDays = durationDays;
        this.basePrice = basePrice;
    }

    public String getPackageId() { return packageId; }
    public void setPackageId(String packageId) { this.packageId = packageId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSourceLocationId() { return sourceLocationId; }
    public void setSourceLocationId(String sourceLocationId) { this.sourceLocationId = sourceLocationId; }

    public String getDestinationLocationId() { return destinationLocationId; }
    public void setDestinationLocationId(String destinationLocationId) { this.destinationLocationId = destinationLocationId; }

    public int getDurationDays() { return durationDays; }
    public void setDurationDays(int durationDays) { this.durationDays = durationDays; }

    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }

}
