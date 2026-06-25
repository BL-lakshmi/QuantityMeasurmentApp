package com.bl.quantitymeasurementapp;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit type context cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be a valid finite number");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return this.value;
    }

    public U getUnit() {
        return this.unit;
    }

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseValue = this.unit.convertToBaseUnit(this.value);
        double targetValue = targetUnit.convertFromBaseUnit(baseValue);
        // Explicitly format to 2 decimal places as dictated by UC10 specifications
        targetValue = Math.round(targetValue * 100.0) / 100.0;
        return new Quantity<>(targetValue, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        if (other == null) {
            throw new IllegalArgumentException("Addition operand cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double sumInBase = this.unit.convertToBaseUnit(this.value) + other.unit.convertToBaseUnit(other.value);
        double targetValue = targetUnit.convertFromBaseUnit(sumInBase);
        return new Quantity<>(targetValue, targetUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity<?> quantity = (Quantity<?>) o;

        // Runtime Type Safety Check ensuring category boundaries are clean
        if (this.unit.getClass() != quantity.unit.getClass()) {
            return false;
        }

        double precisionTolerance = 1e-4;
        double thisBase = this.unit.convertToBaseUnit(this.value);
        double thatBase = ((IMeasurable) quantity.unit).convertToBaseUnit(quantity.value);

        return Math.abs(thisBase - thatBase) < precisionTolerance;
    }

    @Override
    public int hashCode() {
        double normalizedBase = Math.round(this.unit.convertToBaseUnit(this.value) * 1000.0) / 1000.0;
        return Objects.hash(this.unit.getClass(), normalizedBase);
    }

    @Override
    public String toString() {
        return String.format("Quantity(%s, %s)", this.value, this.unit.getUnitName());
    }
}
