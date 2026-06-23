package com.bl.quantitymeasurementapp;

public class QuantityMeasurementApp {
    // Inner class representing the Feet measurement
    public static final class Feet {
        private final double value;

        // Constructor to initialize the final value
        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            // Step 1: Check if the object is the same reference (Reflexive)
            if (this == obj) {
                return true;
            }

            // Step 2: Check if the object is null or of a different class (Type Safety)
            if (obj == null || this.getClass() != obj.getClass()) {
                return false;
            }

            // Step 3: Safe cast to Feet type
            Feet other = (Feet) obj;

            // Step 4: Compare double values precisely using Double.compare()
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    // Main method for a quick manual verification run
    public static void main(String[] args) {
        Feet firstFeet = new Feet(1.0);
        Feet secondFeet = new Feet(1.0);

        boolean result = firstFeet.equals(secondFeet);
        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + result + ")");
    }
}
