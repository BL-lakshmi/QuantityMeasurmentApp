package com.bl.quantitymeasurementapp;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    /**
     * Constructs a Quantity instance with validation checks.
     *
     * @param value The numerical scalar measurement value
     * @param unit  The associated measurement unit token
     * @throws IllegalArgumentException if unit is null or value is infinite/NaN
     */
    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be a finite numeric entity.");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    /**
     * Converts this quantity instance over to a new unit target layout.
     *
     * @param targetUnit The intended destination unit configuration
     * @return A newly initialized Quantity structural clone mapped to target parameters
     */
    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target conversion unit cannot be null.");
        }
        double baseValue = this.unit.convertToBaseUnit(this.value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return new Quantity<>(round(convertedValue), targetUnit);
    }

    /**
     * Adds another quantity to this instance, returning the result in this instance's unit.
     */
    public Quantity<U> add(Quantity<U> other) {
        return this.add(other, this.unit);
    }

    /**
     * Adds another quantity to this instance, returning the result in an explicitly defined target unit.
     */
    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateOperationOperands(other, targetUnit);
        double baseSum = this.unit.convertToBaseUnit(this.value) + other.unit.convertToBaseUnit(other.value);
        double targetValue = targetUnit.convertFromBaseUnit(baseSum);
        return new Quantity<>(round(targetValue), targetUnit);
    }

    /**
     * Subtracts another quantity from this instance, returning the result in this instance's unit.
     *
     * @param other The subtrahend quantity instance
     * @return A new Quantity containing the computed difference in this instance's unit layout
     */
    public Quantity<U> subtract(Quantity<U> other) {
        return this.subtract(other, this.unit);
    }

    /**
     * Subtracts another quantity from this instance, returning the result in an explicit target unit.
     *
     * @param other      The subtrahend quantity instance
     * @param targetUnit The explicit unit layout intended to contain the output calculation
     * @return A new Quantity object containing the rounded computed difference
     */
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateOperationOperands(other, targetUnit);
        double baseDifference = this.unit.convertToBaseUnit(this.value) - other.unit.convertToBaseUnit(other.value);
        double targetValue = targetUnit.convertFromBaseUnit(baseDifference);
        return new Quantity<>(round(targetValue), targetUnit);
    }

    /**
     * Divides this quantity by another quantity of the same domain category.
     * Returns a pure dimensionless ratio multiplier value as a primitive double.
     *
     * @param other The divisor quantity instance
     * @return Pure scalar dimension multiplier value representation
     * @throws ArithmeticException if divisor value measures up to absolute zero
     */
    public double divide(Quantity<U> other) {
        if (other == null) {
            throw new IllegalArgumentException("Divisor operand cannot be null.");
        }
        if (this.unit.getClass() != other.unit.getClass()) {
            throw new IllegalArgumentException("Cross-category division operations are forbidden.");
        }
        if (Math.abs(other.value) < 1e-9) {
            throw new ArithmeticException("Division by zero configuration layout error detected.");
        }

        double baseDividend = this.unit.convertToBaseUnit(this.value);
        double baseDivisor = other.unit.convertToBaseUnit(other.value);
        return baseDividend / baseDivisor;
    }

    /**
     * Private central validations utility verifying structural arithmetic parity markers.
     */
    private void validateOperationOperands(Quantity<U> other, U targetUnit) {
        if (other == null) {
            throw new IllegalArgumentException("Operand cannot be null.");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit layout marker cannot be null.");
        }
        if (this.unit.getClass() != other.unit.getClass() || this.unit.getClass() != targetUnit.getClass()) {
            throw new IllegalArgumentException("Cross-category mathematical combinations are not allowed.");
        }
    }

    /**
     * Consistent utility method formatting calculations to 2 decimal points.
     */
    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Quantity<?> other = (Quantity<?>) obj;

        if (this.unit.getClass() != other.unit.getClass()) return false;

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double otherBase = ((IMeasurable) other.unit).convertToBaseUnit(other.value);
        return Math.abs(thisBase - otherBase) < 1e-4;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.unit.convertToBaseUnit(this.value), this.unit.getClass());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}
