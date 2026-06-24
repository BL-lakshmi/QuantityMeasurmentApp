package com.bl.quantitymeasurementapp;


import java.util.Objects;

public class Length {
    // Instance variables
    private final double value;
    private final LengthUnit unit;

    // Enum to represent different length units and their conversion factors in terms of inches
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // Constructor to initialize length value and unit
    public Length(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    // Convert the length value to the base unit (inches) and round off to two decimal places
    private double convertToBaseUnit() {
        double rawValue = this.value * unit.getConversionFactor();
        return Math.round(rawValue * 100.0) / 100.0;
    }

    // Compare two Length objects for equality based on their values in the base unit
    public boolean compare(Length thatLength) {
        if (thatLength == null) {
            return false;
        }
        return Double.compare(this.convertToBaseUnit(), thatLength.convertToBaseUnit()) == 0;
    }

    // Equals method overridden for validation checking and object comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Length length = (Length) o;
        return this.compare(length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(convertToBaseUnit());
    }

    // Main method matching image_7bbc08.jpg
    public static void main(String[] args) {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length1.equals(length2)); // Should print true

        Length length3 = new Length(1.0, LengthUnit.YARDS);
        Length length4 = new Length(36.0, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length3.equals(length4)); // Should print true

        Length length5 = new Length(100.0, LengthUnit.CENTIMETERS);
        Length length6 = new Length(39.3701, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length5.equals(length6)); // Should print true
    }
}
