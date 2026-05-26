package com.ra.ptit_cntt2_it211_ex1;

public class ShippingFeeCalculator {

    public double calculateFee(double weightKg, double distanceKm) {
        if (weightKg <= 0 || distanceKm <= 0) {
            throw new IllegalArgumentException("Weight and distance must be positive");
        }

        double weightFee = 0;
        if (weightKg <= 1) {
            weightFee = 50000;
        } else {
            weightFee = 50000 + (Math.ceil(weightKg - 1) * 10000);
        }

        double distanceFee = 0;
        if (distanceKm < 10) {
            distanceFee = 0;
        } else if (distanceKm < 50) {
            distanceFee = distanceKm * 5000;
        } else {
            distanceFee = distanceKm * 4000;
        }

        return weightFee + distanceFee;
    }
}