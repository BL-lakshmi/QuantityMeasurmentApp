package com.bl.quantitymeasurementapp;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestCase {
    @Test
    public void givenSameFeetValues_WhenCompared_ShouldReturnTrue() {
        // Given
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(1.0);
        // When
        boolean result = feet1.equals(feet2);
        // Then
        assertTrue("Two Feet objects with the same value should be equal.",result );
    }

    @Test
    public void givenDifferentFeetValues_WhenCompared_ShouldReturnFalse() {
        // Given
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(2.0);
        // When
        boolean result = feet1.equals(feet2);
        // Then
        assertFalse("Two Feet objects with different values should not be equal.",result);
    }

    @Test
    public void givenFeetValueAndNull_WhenCompared_ShouldReturnFalse() {
        // Given
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);
        // When
        boolean result = feet.equals(null);
        // Then
        assertFalse("Comparing a Feet object with null should return false.",result);
    }

    @Test
    public void givenFeetValueAndDifferentTypeObject_WhenCompared_ShouldReturnFalse() {
        // Given
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);
        String nonNumericInput = "1.0"; // Different data type
        // When
        boolean result = feet.equals(nonNumericInput);
        // Then
        assertFalse("Comparing a Feet object with a non-Feet object should return false.",result);
    }

    @Test
    public void givenSameFeetReference_WhenCompared_ShouldReturnTrue() {
        // Given
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);
        // When
        boolean result = feet.equals(feet);
        // Then
        assertTrue("A Feet object compared with its own reference should return true.",result);
    }
     //==========================================
    //          INCHES EQUALITY TEST CASES
    // ==========================================

    @Test
    public void givenSameInchesValues_WhenCompared_ShouldReturnTrue() {
        boolean result = QuantityMeasurementApp.checkInchesEquality(1.0, 1.0);
        assertTrue("Two Inches objects with the same value should be equal.", result);
    }

    @Test
    public void givenDifferentInchesValues_WhenCompared_ShouldReturnFalse() {
        boolean result = QuantityMeasurementApp.checkInchesEquality(1.0, 2.0);
        assertFalse("Two Inches objects with different values should not be equal.", result);
    }

    @Test
    public void givenInchesValueAndNull_WhenCompared_ShouldReturnFalse() {
        QuantityMeasurementApp.Inches inch = new QuantityMeasurementApp.Inches(1.0);
        assertFalse("Comparing an Inches object with null should return false.", inch.equals(null));
    }

    @Test
    public void givenInchesValueAndDifferentTypeObject_WhenCompared_ShouldReturnFalse() {
        QuantityMeasurementApp.Inches inch = new QuantityMeasurementApp.Inches(1.0);
        String nonNumericInput = "1.0";
        assertFalse("Comparing an Inches object with a non-Inches object should return false.", inch.equals(nonNumericInput));
    }

    @Test
    public void givenSameInchesReference_WhenCompared_ShouldReturnTrue() {
        QuantityMeasurementApp.Inches inch = new QuantityMeasurementApp.Inches(1.0);
        assertTrue("An Inches object compared with its own reference should return true.", inch.equals(inch));
    }

    // ==================================================
    // TYPE SAFETY CROSS-CHECK (Feet vs Inches Isolation)
    // ==================================================

    @Test
    public void givenFeetAndInchesWithSameValue_WhenCompared_ShouldReturnFalse() {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Inches inch = new QuantityMeasurementApp.Inches(1.0);

        assertFalse("Feet object and Inches object should not be equal even if values match.", feet.equals(inch));
    }
}

