package com.bl.quantitymeasurementapp;

public class QuantityMeasurementApp {
    // --- Length Utilities ---
    public static boolean demonstrateLengthComparison(double v1, LengthUnit u1, double v2, LengthUnit u2) {
        return new Length(v1, u1).equals(new Length(v2, u2));
    }

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        if (l1 == null) return false;
        return l1.equals(l2);
    }

    public static Length demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {
        return new Length(value, from).convertTo(to);
    }

    // --- Weight Utilities ---
    public static boolean demonstrateWeightEquality(Weight w1, Weight w2) {
        if (w1 == null) return false;
        return w1.equals(w2);
    }

    public static Weight demonstrateWeightConversion(double value, WeightUnit from, WeightUnit to) {
        return new Weight(value, from).convertTo(to);
    }

    public static Weight demonstrateWeightAddition(double v1, WeightUnit u1, double v2, WeightUnit u2, WeightUnit target) {
        return new Weight(v1, u1).add(new Weight(v2, u2), target);
    }


    public static void main(String[] args) {
        System.out.println("--- UC8 Standalone Architecture Validation ---");
        System.out.println("12 Inches to Base Unit (FEET): " + LengthUnit.INCHES.convertToBaseUnit(12.0));
        System.out.println("1 Foot converted to Inches: " + new Length(1.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES));
        System.out.println("Addition (1 FT + 12 IN) to YARDS: " + new Length(1.0, LengthUnit.FEET).add(new Length(12.0, LengthUnit.INCHES), LengthUnit.YARDS));
    }
}
