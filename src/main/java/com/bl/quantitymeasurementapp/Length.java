package com.bl.quantitymeasurementapp;


import java.util.Objects;

public class Length {
    private final double value;
    private final LengthUnit unit;

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
     * Converts this length instance cleanly to any other target unit destination.
     */
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseValue = this.unit.convertToBaseUnit(this.value);
        double targetValue = targetUnit.convertFromBaseUnit(baseValue);
        double roundedValue = Math.round(targetValue * 1000.0) / 1000.0;
        return new Length(roundedValue, targetUnit);
    }

    /**
     * Adds another Length object to this instance, returning the result in the current unit.
     */
    public Length add(Length thatLength) {
        return add(thatLength, this.unit);
    }

    /**
     * Overloaded addition method allowing explicit target unit declaration.
     */
    public Length add(Length thatLength, LengthUnit targetUnit) {
        if (thatLength == null) {
            throw new IllegalArgumentException("Operand for addition cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseValue1 = this.unit.convertToBaseUnit(this.value);
        double baseValue2 = thatLength.unit.convertToBaseUnit(thatLength.value);

        double totalBase = baseValue1 + baseValue2;
        double targetValue = targetUnit.convertFromBaseUnit(totalBase);
        double roundedValue = Math.round(targetValue * 1000.0) / 1000.0;

        return new Length(roundedValue, targetUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Length length = (Length) o;

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double thatBase = length.unit.convertToBaseUnit(length.value);

        return Math.abs(thisBase - thatBase) < 1e-4;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(this.unit.convertToBaseUnit(this.value) * 100.0) / 100.0);
    }

    @Override
    public String toString() {
        return String.format("%.3f %s", this.value, this.unit);
    }
}


