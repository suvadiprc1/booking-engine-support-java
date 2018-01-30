package com.booking.support.dtos;

public class PointOfInterest {
    private String name;
    private String specialInstructions;
    private DistanceDetails driveDistance;
    private PromotionalOffer promoOffers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
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
