package com.bl.quantitymeasurementapp;

public class Length {
    // Instance variables
    private final double value;
    private final LengthUnit unit;

    // Enum to represent different length units and their conversion factors
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0);

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

    // Convert the length value to the base unit (inches)
    private double convertToBaseUnit() {
        return this.value * unit.getConversionFactor();
    }

    // Compare two Length objects for equality based on their values in the base unit
    public boolean compare(Length thatLength) {
        if (thatLength == null) {
            return false;
        }
        return Double.compare(this.convertToBaseUnit(), thatLength.convertToBaseUnit()) == 0;
    }

    // Equals method overridden for object identity, type-safety, and structural equality
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
        return Double.hashCode(convertToBaseUnit());
    }

    // Main method for standalone testing validation
    public static void main(String[] args) {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length1.equals(length2)); // Should print true
    }
}
