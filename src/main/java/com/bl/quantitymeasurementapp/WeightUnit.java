package com.bl.quantitymeasurementapp;

public enum WeightUnit {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return this.conversionFactor;
    }

    /**
     * Converts a value in this unit to the base unit (KILOGRAM).
     */
    public double convertToBaseUnit(double value) {
        return value * this.conversionFactor;
    }

    /**
     * Converts a base unit value (KILOGRAM) into this unit destination.
     */
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / this.conversionFactor;
    }
}
