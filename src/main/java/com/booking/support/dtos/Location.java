package com.booking.support.dtos;

public class Location {
    private Double latitude;
    private Double longitude;

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

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Location) {
            /*Location loc = (Location)obj;
            return latitude.equals(loc.latitude) && longitude.equals(loc.longitude);*/return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Integer.MIN_VALUE;
    }
    
    
    
}
