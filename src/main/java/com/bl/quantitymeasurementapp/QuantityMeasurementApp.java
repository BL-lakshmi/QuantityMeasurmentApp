package com.bl.quantitymeasurementapp;

public class QuantityMeasurementApp {
    // Create a generic method to demonstrate Length equality check
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        if (length1 == null) return false;
        return length1.equals(length2);
    }

    // Create a static method to demonstrate Feet equality check
    public static void demonstrateFeetEquality() {
        Length feet1 = new Length(1.0, Length.LengthUnit.FEET);
        Length feet2 = new Length(1.0, Length.LengthUnit.FEET);
        boolean result = demonstrateLengthEquality(feet1, feet2);
        System.out.println("Input: 1.0 ft and 1.0 ft -> Output: Equal (" + result + ")");
    }

    // Create a static method to demonstrate Inches equality check
    public static void demonstrateInchesEquality() {
        Length inches1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length inches2 = new Length(1.0, Length.LengthUnit.INCHES);
        boolean result = demonstrateLengthEquality(inches1, inches2);
        System.out.println("Input: 1.0 inch and 1.0 inch -> Output: Equal (" + result + ")");
    }

    // Create a static method to demonstrate Feet and Inches cross-comparison
    public static void demonstrateFeetInchesComparison() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(12.0, Length.LengthUnit.INCHES);
        boolean result = demonstrateLengthEquality(feet, inches);
        System.out.println("Input: 1.0 ft and 12.0 inches -> Output: Equal (" + result + ")");
    }

    // Main method matching image_7adecb.jpg
    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();
    }
}
