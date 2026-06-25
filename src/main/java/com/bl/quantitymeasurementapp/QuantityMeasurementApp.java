package com.bl.quantitymeasurementapp;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        System.out.println("--- UC12 QUANTITY ARITHMETIC DEMONSTRATION ---\n");

        // 1. Subtraction with Implicit Target Unit
        Quantity<LengthUnit> length1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(6.0, LengthUnit.INCHES);
        Quantity<LengthUnit> subImplicitLength = length1.subtract(length2);
        System.out.println("Implicit Subtraction (10 FT - 6 IN): " + subImplicitLength);

        // 2. Subtraction with Explicit Target Unit
        Quantity<LengthUnit> subExplicitLength = length1.subtract(length2, LengthUnit.INCHES);
        System.out.println("Explicit Target Subtraction (10 FT - 6 IN to Inches): " + subExplicitLength);

        // 3. Subtraction resulting in Negative Value
        Quantity<WeightUnit> w1 = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        System.out.println("Negative Result Subtraction (2kg - 5kg): " + w1.subtract(w2));

        // 4. Division Operation (Dimensionless Scalar Ratio Result)
        Quantity<LengthUnit> lenDiv1 = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> lenDiv2 = new Quantity<>(2.0, LengthUnit.FEET);
        double dimensionMultiplier = lenDiv1.divide(lenDiv2);
        System.out.println("Division Ratio Scalar (24 IN / 2 FT): " + dimensionMultiplier);
    }
}
