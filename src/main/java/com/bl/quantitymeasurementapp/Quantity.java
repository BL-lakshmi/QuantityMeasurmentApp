package com.bl.quantitymeasurementapp;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    /**
     * Internal enum to represent types of arithmetic operations using lambda expressions.
     */
    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0.0) {
                throw new ArithmeticException("Divide by zero");
            }
            return a / b;
        });

        private final DoubleBinaryOperator operation;

        ArithmeticOperation(DoubleBinaryOperator operation) {
            this.operation = operation;
        }

        public double compute(double a, double b) {
            return operation.applyAsDouble(a, b);
        }
    }

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

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target conversion unit cannot be null.");
        }
        double baseValue = this.unit.convertToBaseUnit(this.value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return new Quantity<>(round(convertedValue), targetUnit);
    }

    // =========================================================================
    //                      REFACTORED PUBLIC API METHODS
    // =========================================================================

    public Quantity<U> add(Quantity<U> other) {
        return this.add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double baseResult = performArithmetic(other, targetUnit, ArithmeticOperation.ADD);
        double targetValue = targetUnit.convertFromBaseUnit(baseResult);
        return new Quantity<>(round(targetValue), targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return this.subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double baseResult = performArithmetic(other, targetUnit, ArithmeticOperation.SUBTRACT);
        double targetValue = targetUnit.convertFromBaseUnit(baseResult);
        return new Quantity<>(round(targetValue), targetUnit);
    }

    public double divide(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        return performArithmetic(other, null, ArithmeticOperation.DIVIDE);
    }

    // =========================================================================
    //                 CENTRALIZED PRIVATE HELPER METHODS (DRY)
    // =========================================================================

    /**
     * Validates input parameters universally across all mathematical combinations.
     */
    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired) {
        if (other == null) {
            throw new IllegalArgumentException("Operand cannot be null.");
        }
        if (targetUnitRequired && targetUnit == null) {
            throw new IllegalArgumentException("Target unit layout marker cannot be null.");
        }
        if (this.unit.getClass() != other.unit.getClass() ||
                (targetUnitRequired && this.unit.getClass() != targetUnit.getClass())) {
            throw new IllegalArgumentException("Cross-category mathematical combinations are not allowed.");
        }
    }

    /**
     * Executes the centralized base normalization and invokes the targeted math operation dispatcher.
     */
    private double performArithmetic(Quantity<U> other, U targetUnit, ArithmeticOperation operation) {
        double baseLeft = this.unit.convertToBaseUnit(this.value);
        double baseRight = other.unit.convertToBaseUnit(other.value);
        return operation.compute(baseLeft, baseRight);
    }

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