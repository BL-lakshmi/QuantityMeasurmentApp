package com.bl.quantitymeasurementapp;

public enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return this.conversionFactor;
    }

    /**
     * Converts a given value in this unit to the base unit (FEET).
     *
     * @param value the numeric value to convert
     * @return value expressed in FEET
     */
    public double convertToBaseUnit(double value) {
        return value * this.conversionFactor;
    }

    /**
     * Converts a base unit value (FEET) into this specific unit destination.
     *
     * @param baseValue the value in FEET
     * @return value expressed in this unit
     */
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / this.conversionFactor;
    }
}
