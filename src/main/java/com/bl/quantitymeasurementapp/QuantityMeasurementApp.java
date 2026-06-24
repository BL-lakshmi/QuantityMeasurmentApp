package com.bl.quantitymeasurementapp;

public class QuantityMeasurementApp {
    /**
     * Demonstrate length equality between two QuantityLength instances.
     *
     * @param length1 the first QuantityLength instance
     * @param length2 the second QuantityLength instance
     * @return true if the two lengths are equal, false otherwise
     */
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        if (length1 == null) return false;
        return length1.equals(length2);
    }

    /**
     * Demonstrate length comparison between raw values and units.
     */
    public static boolean demonstrateLengthComparison(double value1, Length.LengthUnit unit1, double value2, Length.LengthUnit unit2) {
        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);
        return demonstrateLengthEquality(length1, length2);
    }

    /**
     * Demonstrate length conversion from one unit to another.
     * <p><b>Method Overloading Used:</b> Takes a numeric value and two units (from and to).
     * Used when you have raw values to convert.
     *
     * @param value the length value to convert
     * @param fromUnit the unit of the length value
     * @param toUnit the target unit to convert to
     * @return a new Length instance representing the converted length
     */
    public static Length demonstrateLengthConversion(double value, Length.LengthUnit fromUnit, Length.LengthUnit toUnit) {
        Length sourceLength = new Length(value, fromUnit);
        return sourceLength.convertTo(toUnit);
    }

    /**
     * Demonstrate length conversion from one QuantityLength instance to another unit.
     * <p><b>Method Overloading Used:</b> Takes an existing Length object and a target unit.
     * Used when you already have a Length instance.
     *
     * @param length the QuantityLength instance to convert
     * @param toUnit the target unit to convert to
     * @return a new Length instance representing the converted length
     */
    public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit) {
        if (length == null) {
            throw new IllegalArgumentException("Source length instance cannot be null");
        }
        return length.convertTo(toUnit);
    }

    // Main method matching image_7c315e.jpg
    public static void main(String[] args) {
        System.out.println("--- Demonstration of Length Equality Checks ---");
        System.out.println("1.0 FT and 12.0 INCHES -> Equal: " + demonstrateLengthComparison(1.0, Length.LengthUnit.FEET, 12.0, Length.LengthUnit.INCHES));
        System.out.println("1.0 YARDS and 36.0 INCHES -> Equal: " + demonstrateLengthComparison(1.0, Length.LengthUnit.YARDS, 36.0, Length.LengthUnit.INCHES));
        System.out.println("100.0 CM and 39.3701 INCHES -> Equal: " + demonstrateLengthComparison(100.0, Length.LengthUnit.CENTIMETERS, 39.3701, Length.LengthUnit.INCHES));

        System.out.println("\n--- Demonstration of Unit Conversions ---");
        Length converted1 = demonstrateLengthConversion(3.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        System.out.println("Convert 3 feet to inches => " + converted1);

        Length yardInstance = new Length(2.0, Length.LengthUnit.YARDS);
        Length converted2 = demonstrateLengthConversion(yardInstance, Length.LengthUnit.INCHES);
        System.out.println("Convert 2 yards to inches (Overloaded) => " + converted2);
    }
}
