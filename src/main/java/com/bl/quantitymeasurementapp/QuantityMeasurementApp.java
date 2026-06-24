package com.bl.quantitymeasurementapp;

public class QuantityMeasurementApp {
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        if (length1 == null) return false;
        return length1.equals(length2);
    }

    public static boolean demonstrateLengthComparison(double value1, Length.LengthUnit unit1, double value2, Length.LengthUnit unit2) {
        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);
        return demonstrateLengthEquality(length1, length2);
    }

    public static Length demonstrateLengthConversion(double value, Length.LengthUnit fromUnit, Length.LengthUnit toUnit) {
        Length sourceLength = new Length(value, fromUnit);
        return sourceLength.convertTo(toUnit);
    }

    /**
     * Overloaded Addition Demo Pattern 1: Implicit execution targeting first operand unit layout.
     */
    public static Length demonstrateLengthAddition(Length length1, Length length2) {
        if (length1 == null) {
            throw new IllegalArgumentException("Operands cannot be null");
        }
        return length1.add(length2);
    }

    /**
     * Overloaded Addition Demo Pattern 2: Explicit application with user-defined target unit tracking.
     */
    public static Length demonstrateLengthAddition(double value1, Length.LengthUnit unit1, double value2, Length.LengthUnit unit2, Length.LengthUnit targetUnit) {
        Length l1 = new Length(value1, unit1);
        Length l2 = new Length(value2, unit2);
        return l1.add(l2, targetUnit);
    }

    // Main deployment application block matching image_8b3922.jpg
    public static void main(String[] args) {
        System.out.println("--- UC7 API Specification Validation Runs ---");

        Length res1 = demonstrateLengthAddition(1.0, Length.LengthUnit.FEET, 12.0, Length.LengthUnit.INCHES, Length.LengthUnit.FEET);
        System.out.println("Result (Target FEET) -> " + res1);

        Length res2 = demonstrateLengthAddition(1.0, Length.LengthUnit.FEET, 12.0, Length.LengthUnit.INCHES, Length.LengthUnit.INCHES);
        System.out.println("Result (Target INCHES) -> " + res2);

        Length res3 = demonstrateLengthAddition(1.0, Length.LengthUnit.FEET, 12.0, Length.LengthUnit.INCHES, Length.LengthUnit.YARDS);
        System.out.println("Result (Target YARDS) -> " + res3);

        Length res4 = demonstrateLengthAddition(2.54, Length.LengthUnit.CENTIMETERS, 1.0, Length.LengthUnit.INCHES, Length.LengthUnit.CENTIMETERS);
        System.out.println("Result (Target CM) -> " + res4);
    }
}
