package com.bl.quantitymeasurementapp;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestCase {
    @Test
    public void testFeetEquality() {
        Length feet1 = new Length(1.0, Length.LengthUnit.FEET);
        Length feet2 = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue("Two identical Feet measurements should be structurally equal.", feet1.equals(feet2));
    }

    @Test
    public void testInchesEquality() {
        Length inches1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length inches2 = new Length(1.0, Length.LengthUnit.INCHES);
        assertTrue("Two identical Inch measurements should be structurally equal.", inches1.equals(inches2));
    }

    @Test
    public void testFeetInchesComparison() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(12.0, Length.LengthUnit.INCHES);
        assertTrue("1.0 Foot should scale perfectly to 12.0 Inches cross-unit.", feet.equals(inches));
    }

    @Test
    public void testFeetInequality() {
        Length feet1 = new Length(1.0, Length.LengthUnit.FEET);
        Length feet2 = new Length(2.0, Length.LengthUnit.FEET);
        assertFalse("Different values of same unit (Feet) must evaluate to false.", feet1.equals(feet2));
    }

    @Test
    public void testInchesInequality() {
        Length inches1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length inches2 = new Length(2.0, Length.LengthUnit.INCHES);
        assertFalse("Different values of same unit (Inches) must evaluate to false.", inches1.equals(inches2));
    }

    @Test
    public void testCrossUnitInequality() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(1.0, Length.LengthUnit.INCHES);
        assertFalse("1.0 Foot should not match 1.0 Inch after conversion evaluation.", feet.equals(inches));
    }

    @Test
    public void testMultipleFeetComparison() {
        Length feet1 = new Length(2.5, Length.LengthUnit.FEET);
        Length inches = new Length(30.0, Length.LengthUnit.INCHES);
        assertTrue("Fractional conversion checks (2.5 ft == 30.0 inches) should match accurately.", feet1.equals(inches));
    }

    @Test
    public void testEquality_NullComparison() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        assertFalse("Comparison against a null literal object must reliably gracefully return false.", feet.equals(null));
    }

    @Test
    public void testEquality_SameReference() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue("Reflexivity property demands an object equals itself.", feet.equals(feet));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEquality_NullUnit() {
        // Validation constraint ensuring instantiation crashes immediately if unit type is missing
        new Length(1.0, null);
    }
}

