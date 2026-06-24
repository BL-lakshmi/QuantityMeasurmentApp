package com.bl.quantitymeasurementapp;

import org.junit.Test;
import com.bl.quantitymeasurementapp.LengthUnit; // Add this!
import static org.junit.Assert.*;

public class TestCase {

    @Test
    public void convertFeetToInches() {
        Length lengthInInches = QuantityMeasurementApp.demonstrateLengthConversion(3.0, LengthUnit.FEET, LengthUnit.INCHES);
        Length expectedLength = new Length(36.0, LengthUnit.INCHES);
        assertTrue("Converting 3 feet via API should yield exactly 36 inches.", QuantityMeasurementApp.demonstrateLengthEquality(lengthInInches, expectedLength));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConversion_InvalidUnit_Throws() {
        Length input = new Length(5.0, LengthUnit.FEET);
        input.convertTo(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConversion_NaNValue_Throws() {
        new Length(Double.NaN, LengthUnit.FEET);
    }

    // ==========================================
    //          UC6 - IMPLICIT ADDITION
    // ==========================================

    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length feet2 = new Length(2.0, LengthUnit.FEET);
        Length result = feet1.add(feet2);
        Length expected = new Length(3.0, LengthUnit.FEET);
        assertTrue("1.0 FT + 2.0 FT should equal 3.0 FT.", result.equals(expected));
    }

    @Test
    public void testAddition_SameUnit_InchPlusInch() {
        Length inch1 = new Length(6.0, LengthUnit.INCHES);
        Length inch2 = new Length(6.0, LengthUnit.INCHES);
        Length result = inch1.add(inch2);
        Length expected = new Length(12.0, LengthUnit.INCHES);
        assertTrue("6.0 INCHES + 6.0 INCHES should equal 12.0 INCHES.", result.equals(expected));
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);
        Length result = feet.add(inches);
        Length expected = new Length(2.0, LengthUnit.FEET);
        assertTrue("1.0 FT + 12.0 INCHES should equal 2.0 FT.", result.equals(expected));
    }

    @Test
    public void testAddition_CrossUnit_InchPlusFeet() {
        Length inches = new Length(12.0, LengthUnit.INCHES);
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length result = inches.add(feet);
        Length expected = new Length(24.0, LengthUnit.INCHES);
        assertTrue("12.0 INCHES + 1.0 FT should equal 24.0 INCHES.", result.equals(expected));
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeet() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length result = yard.add(feet);
        Length expected = new Length(2.0, LengthUnit.YARDS);
        assertTrue("1.0 YARD + 3.0 FT should equal 2.0 YARDS.", result.equals(expected));
    }

    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch() {
        Length cm = new Length(2.54, LengthUnit.CENTIMETERS);
        Length inches = new Length(1.0, LengthUnit.INCHES);
        Length result = cm.add(inches);
        Length expected = new Length(5.08, LengthUnit.CENTIMETERS);
        assertTrue("2.54 CM + 1.0 INCH should equal approximately 5.08 CM.", result.equals(expected));
    }

    @Test
    public void testAddition_Commutativity() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        Length sumAB = feet.add(inches);
        Length sumBA = inches.add(feet);

        assertTrue("Addition must be commutative: A + B == B + A.", sumAB.equals(sumBA));
    }

    @Test
    public void testAddition_WithZero() {
        Length feet = new Length(5.0, LengthUnit.FEET);
        Length inches = new Length(0.0, LengthUnit.INCHES);
        Length result = feet.add(inches);
        Length expected = new Length(5.0, LengthUnit.FEET);
        assertTrue("Adding zero acts as an identity element leaving value unchanged.", result.equals(expected));
    }

    @Test
    public void testAddition_NegativeValues() {
        Length feet1 = new Length(5.0, LengthUnit.FEET);
        Length feet2 = new Length(-2.0, LengthUnit.FEET);
        Length result = feet1.add(feet2);
        Length expected = new Length(3.0, LengthUnit.FEET);
        assertTrue("Addition with negative measurements should subtract appropriately.", result.equals(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddition_NullSecondOperand() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        feet.add(null);
    }

    @Test
    public void testAddition_LargeValues() {
        Length l1 = new Length(1e6, LengthUnit.FEET);
        Length l2 = new Length(1e6, LengthUnit.FEET);
        Length result = l1.add(l2);
        Length expected = new Length(2e6, LengthUnit.FEET);
        assertTrue("Large magnitudes must add cleanly without precision breakdown.", result.equals(expected));
    }

    @Test
    public void testAddition_SmallValues() {
        Length l1 = new Length(0.001, LengthUnit.FEET);
        Length l2 = new Length(0.002, LengthUnit.FEET);
        Length result = l1.add(l2);
        Length expected = new Length(0.003, LengthUnit.FEET);
        assertTrue("Small float values must compute accurately within defined tolerances.", result.equals(expected));
    }

    // ==========================================
    //          UC7 - EXPLICIT TARGET ADDITION
    // ==========================================

    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        Length result = l1.add(l2, LengthUnit.FEET);
        Length expected = new Length(2.0, LengthUnit.FEET);
        assertTrue("1 Foot + 12 Inches explicitly targeting Feet should equal 2 Feet.", result.equals(expected));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        Length result = l1.add(l2, LengthUnit.INCHES);
        Length expected = new Length(24.0, LengthUnit.INCHES);
        assertTrue("1 Foot + 12 Inches explicitly targeting Inches should equal 24 Inches.", result.equals(expected));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_YARDS() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        Length result = l1.add(l2, LengthUnit.YARDS);

        assertEquals("Numeric value check for fractional yards representation.", 0.667, result.getValue(), 0.001);
        assertEquals("Unit token validation check.", LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Centimeters() {
        Length l1 = new Length(1.0, LengthUnit.INCHES);
        Length l2 = new Length(1.0, LengthUnit.INCHES);
        Length result = l1.add(l2, LengthUnit.CENTIMETERS);

        assertEquals("Explicit centimeter mapping validation.", 5.08, result.getValue(), 0.01);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        Length l1 = new Length(2.0, LengthUnit.YARDS);
        Length l2 = new Length(3.0, LengthUnit.FEET);
        Length result = l1.add(l2, LengthUnit.YARDS);
        Length expected = new Length(3.0, LengthUnit.YARDS);
        assertTrue("2 Yards + 3 Feet targeting Yards should equal 3 Yards.", result.equals(expected));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        Length l1 = new Length(2.0, LengthUnit.YARDS);
        Length l2 = new Length(3.0, LengthUnit.FEET);
        Length result = l1.add(l2, LengthUnit.FEET);
        Length expected = new Length(9.0, LengthUnit.FEET);
        assertTrue("2 Yards + 3 Feet targeting Feet should equal 9 Feet.", result.equals(expected));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Commutativity() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length sumAB = l1.add(l2, LengthUnit.YARDS);
        Length sumBA = l2.add(l1, LengthUnit.YARDS);

        assertTrue("Commutative equality must map evenly regardless of parameter insertion layout context.", sumAB.equals(sumBA));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_WithZero() {
        Length l1 = new Length(5.0, LengthUnit.FEET);
        Length l2 = new Length(0.0, LengthUnit.INCHES);
        Length result = l1.add(l2, LengthUnit.YARDS);

        assertEquals("Identity conversion mapping checks.", 1.667, result.getValue(), 0.001);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NegativeValues() {
        Length l1 = new Length(5.0, LengthUnit.FEET);
        Length l2 = new Length(-2.0, LengthUnit.FEET);
        Length result = l1.add(l2, LengthUnit.INCHES);
        Length expected = new Length(36.0, LengthUnit.INCHES);
        assertTrue("Negative values compute arithmetic differences and express cleanly inside target bounds.", result.equals(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        l1.add(l2, null);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        Length l1 = new Length(1000.0, LengthUnit.FEET);
        Length l2 = new Length(500.0, LengthUnit.FEET);
        Length result = l1.add(l2, LengthUnit.INCHES);
        Length expected = new Length(18000.0, LengthUnit.INCHES);
        assertTrue("Up-scaling massive metrics should map structurally across bounds accurately.", result.equals(expected));
    }

    // ==========================================
    //       UC8 - STANDALONE DELEGATION TESTS
    // ==========================================

    @Test
    public void testLengthUnitEnum_FeetConstant() {
        assertEquals("FEET must be the baseline unit (1.0).", 1.0, LengthUnit.FEET.getConversionFactor(), 1e-6);
    }

    @Test
    public void testLengthUnitEnum_InchesConstant() {
        assertEquals("INCHES conversion factor must equal 1/12.", 1.0 / 12.0, LengthUnit.INCHES.getConversionFactor(), 1e-4);
    }

    @Test
    public void testLengthUnitEnum_YardsConstant() {
        assertEquals("YARDS conversion factor must equal 3.0.", 3.0, LengthUnit.YARDS.getConversionFactor(), 1e-6);
    }

    @Test
    public void testLengthUnitEnum_CentimetersConstant() {
        assertEquals("CENTIMETERS conversion factor must equal 1/30.48.", 1.0 / 30.48, LengthUnit.CENTIMETERS.getConversionFactor(), 1e-4);
    }

    @Test
    public void testConvertToBaseUnit_FeetToFeet() {
        assertEquals(5.0, LengthUnit.FEET.convertToBaseUnit(5.0), 1e-6);
    }

    @Test
    public void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0), 1e-6);
    }

    @Test
    public void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0), 1e-6);
    }

    @Test
    public void testConvertToBaseUnit_CentimetersToFeet() {
        assertEquals(1.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), 1e-3);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToFeet() {
        assertEquals(2.0, LengthUnit.FEET.convertFromBaseUnit(2.0), 1e-6);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0), 1e-6);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToYards() {
        assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3.0), 1e-6);
    }

    @Test
    public void testQuantityLengthRefactored_Equality() {
        Length standardFeet = new Length(1.0, LengthUnit.FEET);
        Length standardInches = new Length(12.0, LengthUnit.INCHES);
        assertTrue("Refactored conversion matching matrix equality checks.", standardFeet.equals(standardInches));
    }

    @Test
    public void testQuantityLengthRefactored_ConvertTo() {
        Length foot = new Length(1.0, LengthUnit.FEET);
        Length result = foot.convertTo(LengthUnit.INCHES);
        assertEquals("Conversion delegation outcome check.", 12.0, result.getValue(), 1e-6);
    }

    @Test
    public void testQuantityLengthRefactored_AddWithTargetUnit() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);
        Length result = feet.add(inches, LengthUnit.YARDS);
        assertEquals("1 FT + 12 IN must equal ~0.667 Yards.", 0.667, result.getValue(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuantityLengthRefactored_NullUnit() {
        new Length(10.0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuantityLengthRefactored_InvalidValue() {
        new Length(Double.NaN, LengthUnit.FEET);
    }

    @Test
    public void testRoundTripConversion_RefactoredDesign() {
        Length start = new Length(100.0, LengthUnit.CENTIMETERS);
        Length intermediate = start.convertTo(LengthUnit.INCHES);
        Length back = intermediate.convertTo(LengthUnit.CENTIMETERS);
        assertEquals("Round-trip conversions must retain structural fidelity.", start.getValue(), back.getValue(), 0.05);
    }

}

