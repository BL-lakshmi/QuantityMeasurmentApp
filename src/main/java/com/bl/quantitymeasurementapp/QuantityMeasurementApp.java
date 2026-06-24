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

    public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit) {
        if (length == null) {
            throw new IllegalArgumentException("Source length instance cannot be null");
        }
        return length.convertTo(toUnit);
    }

    /**
     * Overloaded Demonstration Method 1: Takes raw components and computes sum in targetUnit.
     */
    public static Length demonstrateLengthAddition(double value1, Length.LengthUnit unit1, double value2, Length.LengthUnit unit2, Length.LengthUnit targetUnit) {
        Length l1 = new Length(value1, unit1);
        Length l2 = new Length(value2, unit2);
        Length sum = l1.add(l2);
        return sum.convertTo(targetUnit);
    }

    /**
     * Overloaded Demonstration Method 2: Takes existing object instances and computes sum.
     */
    public static Length demonstrateLengthAddition(Length length1, Length length2) {
        if (length1 == null || length2 == null) {
            throw new IllegalArgumentException("Operands cannot be null");
        }
        return length1.add(length2);
    }

    // Main execution method demonstrating required examples from specification
    public static void main(String[] args) {
        System.out.println("--- UC6 Demonstration of Length Addition ---");

        Length sum1 = demonstrateLengthAddition(1.0, Length.LengthUnit.FEET, 2.0, Length.LengthUnit.FEET, Length.LengthUnit.FEET);
        System.out.println("add(1.0 FT, 2.0 FT) -> " + sum1);

        Length sum2 = demonstrateLengthAddition(1.0, Length.LengthUnit.FEET, 12.0, Length.LengthUnit.INCHES, Length.LengthUnit.FEET);
        System.out.println("add(1.0 FT, 12.0 INCHES) -> " + sum2);

        Length sum3 = demonstrateLengthAddition(12.0, Length.LengthUnit.INCHES, 1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        System.out.println("add(12.0 INCHES, 1.0 FT) -> " + sum3);

        Length sum4 = demonstrateLengthAddition(1.0, Length.LengthUnit.YARDS, 3.0, Length.LengthUnit.FEET, Length.LengthUnit.YARDS);
        System.out.println("add(1.0 YARDS, 3.0 FT) -> " + sum4);

        Length sum5 = demonstrateLengthAddition(2.54, Length.LengthUnit.CENTIMETERS, 1.0, Length.LengthUnit.INCHES, Length.LengthUnit.CENTIMETERS);
        System.out.println("add(2.54 CM, 1.0 INCHES) -> " + sum5);
    }
}
