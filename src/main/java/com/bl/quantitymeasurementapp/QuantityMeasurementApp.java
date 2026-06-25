package com.bl.quantitymeasurementapp;

public class QuantityMeasurementApp {
    public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
        if (q1 == null) return false;
        return q1.equals(q2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> source, U targetUnit) {
        if (source == null) return null;
        return source.convertTo(targetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        if (q1 == null) return null;
        return q1.add(q2, targetUnit);
    }

    public static void main(String[] args) {
        System.out.println("=== UC10 Unified Framework Execution ===");

        Quantity<LengthUnit> lengthsMatch = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> targetInches = new Quantity<>(12.0, LengthUnit.INCHES);
        System.out.println("Length Equivalency Match: " + demonstrateEquality(lengthsMatch, targetInches));

        Quantity<WeightUnit> kgWeight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> gWeight = new Quantity<>(1000.0, WeightUnit.GRAM);
        System.out.println("Weight Equivalency Match: " + demonstrateEquality(kgWeight, gWeight));
    }
}
