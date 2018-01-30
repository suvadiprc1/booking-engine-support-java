package com.booking.support.dtos;

public class DistanceDetails {
    private Double latitude;
    private Double longitude;
    private String driveDistance;
    private String landMarkInstructions;
    private String description;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDriveDistance() {
        return driveDistance;
    }

    public void setDriveDistance(String driveDistance) {
        this.driveDistance = driveDistance;
    }

    public String getLandMarkInstructions() {
        return landMarkInstructions;
    }

    public void setLandMarkInstructions(String landMarkInstructions) {
        this.landMarkInstructions = landMarkInstructions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
