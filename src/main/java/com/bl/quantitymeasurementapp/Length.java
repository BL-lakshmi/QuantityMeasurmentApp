package com.bl.quantitymeasurementapp;


import java.util.Objects;

public class Length {
    // Instance variables
    private final double value;
    private final LengthUnit unit;

    /**
     * Nested enumeration representing different length units and their conversion factors.
     * The base unit for conversion is inches. Thus, each unit's conversion factor is defined
     * relative to inches.
     * <p>Example: 1 FOOT = 12.0 inches, 1 YARD = 36.0 inches, 1 CENTIMETER = 0.393701 inches.
     */
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

    /**
     * Constructor to initialize length value and unit.
     * Validates that the input value is finite and the unit type is not null.
     */
    public Length(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be a valid finite number");
        }
        this.value = value;
        this.unit = unit;
    }

    /**
     * Converts this length value to the base unit (inches) with rounding.
     * <p><b>Private Utility Method:</b> This method is used internally for all conversions
     * and comparisons. It ensures consistent rounding to two decimal places across all operations.
     *
     * @return the length value in inches, rounded to two decimal places
     */
    private double convertToBaseUnit() {
        double rawValue = this.value * unit.getConversionFactor();
        return Math.round(rawValue * 100.0) / 100.0;
    }

    /**
     * Compares two {@code Length} objects for equality based on their base unit values.
     * <p><b>Private Helper Method:</b> Encapsulates the core comparison logic by converting
     * both lengths to the base unit and performing a numerical comparison.
     *
     * @param thatLength the {@code Length} object to compare with
     * @return {@code true} if both lengths represent the same physical distance in inches, {@code false} otherwise
     */
    private boolean compare(Length thatLength) {
        if (thatLength == null) {
            return false;
        }
        return Double.compare(this.convertToBaseUnit(), thatLength.convertToBaseUnit()) == 0;
    }

    /**
     * Checks equality between this {@code Length} and another object.
     * <p><b>Overridden Method:</b> Implements the {@link Object#equals(Object)} contract.
     * Performs reference equality check first, then type validation, and finally delegates
     * to the {@link #compare(Length)} method for value-based comparison.
     */
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

    /**
     * Convert this length to the specified target unit.
     * <p><b>Public API Method:</b> Provides the primary interface for unit conversion.
     * This method implements the conversion pipeline: base unit conversion, target unit conversion,
     * and rounding to maintain precision consistency.
     * <p><b>Immutability Guarantee:</b> This method never modifies the receiver; it always returns
     * a new {@code Length} instance, ensuring that the original object remains unchanged.
     *
     * @param targetUnit the unit to convert this length into; must not be null
     * @return a new {@code Length} representing the same physical length in {@code targetUnit},
     * with the numeric value rounded to two decimal places
     * @throws IllegalArgumentException if {@code targetUnit} is null
     */
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        // Pipeline: 1. Convert to inches, 2. Convert to target unit, 3. Round off
        double valueInInches = this.value * this.unit.getConversionFactor();
        double convertedValue = valueInInches / targetUnit.getConversionFactor();
        double roundedValue = Math.round(convertedValue * 100.0) / 100.0;
        return new Length(roundedValue, targetUnit);
    }

    /**
     * Returns a string representation of this {@code Length}.
     * <p><b>Overridden Method:</b> Provides a human-readable format for logging and debugging.
     * The format is "{value} {unit}" where the value is formatted to two decimal places.
     */
    @Override
    public String toString() {
        return String.format("%.2f %s", this.value, this.unit);
    }

    // Main method for standalone validation testing matching image_7c3105.jpg
    public static void main(String[] args) {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length1.equals(length2));

        Length length3 = new Length(1.0, LengthUnit.YARDS);
        Length length4 = new Length(36.0, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length3.equals(length4));

        Length length5 = new Length(100.0, LengthUnit.CENTIMETERS);
        Length length6 = new Length(39.3701, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length5.equals(length6));
    }
}


