package com.bl.quantitymeasurementapp;

public class QuantityMeasurementApp {

    // Inner class representing the Feet measurement
    public static class Feet {
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
    // --- INNER CLASS: INCHES ---
    public static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;
            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }


    // Static helper method for Feet comparison
    public static boolean checkFeetEquality(double value1, double value2) {
        Feet feet1 = new Feet(value1);
        Feet feet2 = new Feet(value2);
        return feet1.equals(feet2);
    }

    // Static helper method for Inches comparison
    public static boolean checkInchesEquality(double value1, double value2) {
        Inches inch1 = new Inches(value1);
        Inches inch2 = new Inches(value2);
        return inch1.equals(inch2);
    }

    // Main method for a quick manual verification run
    public static void main(String[] args) {
        // Checking Inches equality via static flow
        boolean inchResult = checkInchesEquality(1.0, 1.0);
        System.out.println("Input: 1.0 inch and 1.0 inch");
        System.out.println("Output: Equal (" + inchResult + ")");

        // Checking Feet equality via static flow
        boolean feetResult = checkFeetEquality(1.0, 1.0);
        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + feetResult + ")");

    }
}
