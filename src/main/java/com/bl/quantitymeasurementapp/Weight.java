package com.bl.quantitymeasurementapp;

import java.util.Objects;

public class Weight {
    private final double value;
    private final WeightUnit unit;

    public Weight(double value, WeightUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Weight unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Weight value must be a valid finite number");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    /**
     * Converts this Weight instance cleanly to another target unit destination.
     */
    public Weight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseValue = this.unit.convertToBaseUnit(this.value);
        double targetValue = targetUnit.convertFromBaseUnit(baseValue);
        double roundedValue = Math.round(targetValue * 1000.0) / 1000.0;
        return new Weight(roundedValue, targetUnit);
    }

    /**
     * Adds another Weight to this instance, returning the result in the current unit.
     */
    public Weight add(Weight thatWeight) {
        return add(thatWeight, this.unit);
    }

    /**
     * Overloaded addition method allowing explicit target unit declaration.
     */
    public Weight add(Weight thatWeight, WeightUnit targetUnit) {
        if (thatWeight == null) {
            throw new IllegalArgumentException("Operand for addition cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseValue1 = this.unit.convertToBaseUnit(this.value);
        double baseValue2 = thatWeight.unit.convertToBaseUnit(thatWeight.value);

        double totalBase = baseValue1 + baseValue2;
        double targetValue = targetUnit.convertFromBaseUnit(totalBase);
        double roundedValue = Math.round(targetValue * 1000.0) / 1000.0;

        return new Weight(roundedValue, targetUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false; // Enforces Category Type Safety
        Weight weight = (Weight) o;

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double thatBase = weight.unit.convertToBaseUnit(weight.value);

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
