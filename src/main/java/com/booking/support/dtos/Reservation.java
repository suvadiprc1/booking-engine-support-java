package com.booking.support.dtos;

public class Reservation {
    private String reservationId;
    private Double startDateTime;
    private Double endDateTime;
    private String  category;
    private String companyName;
    private String companyWebsite;
    private String resourceDescription;
    private String bookingPrice;
    private String specialInstructions;
    private String vehicleNo;
    private DistanceDetails driveDistance;
    private PromotionalOffer promoOffers;
    public String getReservationId() {
        return reservationId;
    }
    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }
    public Double getStartDateTime() {
        return startDateTime;
    }
    public void setStartDateTime(Double startDateTime) {
        this.startDateTime = startDateTime;
    }
    public Double getEndDateTime() {
        return endDateTime;
    }
    public void setEndDateTime(Double endDateTime) {
        this.endDateTime = endDateTime;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyWebsite() {
        return companyWebsite;
    }
    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }
    public String getBookingPrice() {
        return bookingPrice;
    }
    public void setBookingPrice(String bookingPrice) {
        this.bookingPrice = bookingPrice;
    }
    public String getSpecialInstructions() {
        return specialInstructions;
    }
    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }
    public String getResourceDescription() {
        return resourceDescription;
    }
    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public DistanceDetails getDriveDistance() {
        return driveDistance;
    }

    public void setDriveDistance(DistanceDetails driveDistance) {
        this.driveDistance = driveDistance;
    }

    public PromotionalOffer getPromoOffers() {
        return promoOffers;
    }

    public void setPromoOffers(PromotionalOffer promoOffers) {
        this.promoOffers = promoOffers;
    }
}
