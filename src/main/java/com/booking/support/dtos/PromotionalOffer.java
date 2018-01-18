package com.booking.support.dtos;

import java.util.List;

public class PromotionalOffer {
    private List<PromotionalRule> promos;
    private String loyaltyCode;

    public List<PromotionalRule> getRules() {
        return promos;
    }

    public void setRules(List<PromotionalRule> rules) {
        this.promos = rules;
    }

    public String getLoyaltyCode() {
        return loyaltyCode;
    }

    public void setLoyaltyCode(String loyaltyCode) {
        this.loyaltyCode = loyaltyCode;
    }
}
