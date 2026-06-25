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
        System.out.println("=== UC11 Multi-Category Operational Demonstration ===");

        // Preservation of Legacy Dimensions (Length/Weight)
        Quantity<LengthUnit> oneFoot = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> twelveInches = new Quantity<>(12.0, LengthUnit.INCHES);
        System.out.println("Length Equivalency Match: " + demonstrateEquality(oneFoot, twelveInches));

        // Validation of New Volume Dimensions
        Quantity<VolumeUnit> oneLitre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> thousandMl = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        System.out.println("Volume Equivalency Match: " + demonstrateEquality(oneLitre, thousandMl));

        Quantity<VolumeUnit> oneGallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> addedVolume = demonstrateAddition(oneLitre, oneGallon, VolumeUnit.LITRE);
        System.out.println("1 L + 1 Gal converted to Litres: " + addedVolume);
    }
}
