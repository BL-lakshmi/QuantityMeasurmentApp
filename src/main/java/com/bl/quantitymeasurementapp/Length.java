package com.bl.quantitymeasurementapp;


import java.util.Objects;

public class Length {
    private final double value;
    private final LengthUnit unit;

    /**
     * Enumeration representing length units and their conversion factors relative to inches.
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

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    /**
     * Private utility method to convert value to base unit (inches) with rounding.
     */
    private double convertToBaseUnit() {
        double rawValue = this.value * unit.getConversionFactor();
        return Math.round(rawValue * 100.0) / 100.0;
    }

    private boolean compare(Length thatLength) {
        if (thatLength == null) {
            return false;
        }
        return Double.compare(this.convertToBaseUnit(), thatLength.convertToBaseUnit()) == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Length length = (Length) o;
        return this.compare(length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(convertToBaseUnit());
    }

    /**
     * Convert this length to the specified target unit.
     */
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double valueInInches = this.value * this.unit.getConversionFactor();
        double convertedValue = valueInInches / targetUnit.getConversionFactor();
        double roundedValue = Math.round(convertedValue * 1000.0) / 1000.0; // 3 decimal places for fractional yards
        return new Length(roundedValue, targetUnit);
    }

    /**
     * Private utility addition method.
     * Consolidates arithmetic pipeline to avoid code duplication across overloaded methods.
     */
    private Length performAddition(Length thatLength, LengthUnit targetUnit) {
        if (thatLength == null) {
            throw new IllegalArgumentException("Operand for addition cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double firstValueInInches = this.value * this.unit.getConversionFactor();
        double secondValueInInches = thatLength.value * thatLength.unit.getConversionFactor();

        double totalInches = firstValueInInches + secondValueInInches;
        double finalValue = totalInches / targetUnit.getConversionFactor();
        double roundedValue = Math.round(finalValue * 1000.0) / 1000.0; // 3 decimal precision for scales like yards (~0.667)

        return new Length(roundedValue, targetUnit);
    }

    /**
     * UC6: Implicit addition using the unit of the first operand (this instance's unit).
     */
    public Length add(Length thatLength) {
        return performAddition(thatLength, this.unit);
    }

    /**
     * UC7: Overloaded explicit addition method specifying an independent target destination unit.
     */
    public Length add(Length thatLength, LengthUnit targetUnit) {
        return performAddition(thatLength, targetUnit);
    }

    @Override
    public String toString() {
        return String.format("%.3f %s", this.value, this.unit);
    }

    // Standalone main check matching image_8b3905.jpg
    public static void main(String[] args) {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println("Explicit addition to FEET: " + l1.add(l2, LengthUnit.FEET));
        System.out.println("Explicit addition to INCHES: " + l1.add(l2, LengthUnit.INCHES));
        System.out.println("Explicit addition to YARDS: " + l1.add(l2, LengthUnit.YARDS));
    }
}


