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
}
