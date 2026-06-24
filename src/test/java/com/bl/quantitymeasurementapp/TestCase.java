package com.bl.quantitymeasurementapp;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestCase {
    @Test
    public void testFeetEquality() {
        Length feet1 = new Length(1.0, Length.LengthUnit.FEET);
        Length feet2 = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue("Identical feet values should match", feet1.equals(feet2));
    }

    @Test
    public void testInchesEquality() {
        Length inches1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length inches2 = new Length(1.0, Length.LengthUnit.INCHES);
        assertTrue("Identical inch values should match", inches1.equals(inches2));
    }

    @Test
    public void testFeetInchesComparison() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(12.0, Length.LengthUnit.INCHES);
        assertTrue("1 Foot should equal 12 Inches", feet.equals(inches));
    }

    @Test
    public void testFeetInequality() {
        Length feet1 = new Length(1.0, Length.LengthUnit.FEET);
        Length feet2 = new Length(2.0, Length.LengthUnit.FEET);
        assertFalse("Different feet values should not match", feet1.equals(feet2));
    }

    @Test
    public void testInchesInequality() {
        Length inches1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length inches2 = new Length(2.0, Length.LengthUnit.INCHES);
        assertFalse("Different inch values should not match", inches1.equals(inches2));
    }

    @Test
    public void testCrossUnitInequality() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(1.0, Length.LengthUnit.INCHES);
        assertFalse("1 Foot should not match 1 Inch", feet.equals(inches));
    }

    @Test
    public void testMultipleFeetComparison() {
        Length feet = new Length(2.5, Length.LengthUnit.FEET);
        Length inches = new Length(30.0, Length.LengthUnit.INCHES);
        assertTrue("2.5 Feet should equal 30 Inches", feet.equals(inches));
    }

    @Test
    public void yardEquals36Inches() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);
        assertTrue("1 Yard should equal 36 Inches", yard.equals(inches));
    }

    @Test
    public void centimeterEquals39Point3701Inches() {
        Length cm = new Length(100.0, Length.LengthUnit.CENTIMETERS);
        Length inches = new Length(39.3701, Length.LengthUnit.INCHES);
        assertTrue("100 Centimeters should equal 39.3701 Inches via rounding factor", cm.equals(inches));
    }

    @Test
    public void threeFeetEqualsOneYard() {
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        assertTrue("3 Feet should equal 1 Yard", feet.equals(yard));
    }

    @Test
    public void thirtyPoint48CmEqualsOneFoot() {
        Length cm = new Length(30.48, Length.LengthUnit.CENTIMETERS);
        Length foot = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue("30.48 Centimeters should equal 1 Foot", cm.equals(foot));
    }

    @Test
    public void yardNotEqualToInches() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length inches = new Length(1.0, Length.LengthUnit.INCHES);
        assertFalse("1 Yard should not equal 1 Inch", yard.equals(inches));
    }

    @Test
    public void referenceEqualitySameObject() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        assertTrue("An object must equal itself", yard.equals(yard));
    }

    @Test
    public void equalsReturnsFalseForNull() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        assertFalse("An object compared to null must return false", yard.equals(null));
    }

    @Test
    public void reflexiveSymmetricAndTransitiveProperty() {
        Length a = new Length(1.0, Length.LengthUnit.YARDS);
        Length b = new Length(3.0, Length.LengthUnit.FEET);
        Length c = new Length(36.0, Length.LengthUnit.INCHES);

        // Symmetric check
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));

        // Transitive check
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    public void differentValuesSameUnitNotEqual() {
        Length yard1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length yard2 = new Length(2.0, Length.LengthUnit.YARDS);
        assertFalse("Different values of same unit must be unequal", yard1.equals(yard2));
    }

    @Test
    public void crossUnitEqualityDemonstrateMethod() {
        boolean result = QuantityMeasurementApp.demonstrateLengthComparison(1.0, Length.LengthUnit.YARDS, 3.0, Length.LengthUnit.FEET);
        assertTrue("Application runner comparison should execute cleanly", result);
    }
    @Test
    public void convertFeetToInches() {
        Length lengthInInches = QuantityMeasurementApp.demonstrateLengthConversion(3.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        Length expectedLength = new Length(36.0, Length.LengthUnit.INCHES);
        assertTrue("Converting 3 feet via API should yield exactly 36 inches.", QuantityMeasurementApp.demonstrateLengthEquality(lengthInInches, expectedLength));
    }



    // --- ADDITIONAL UC5 EXCEPTIONAL FLOW TESTS ---

    @Test(expected = IllegalArgumentException.class)
    public void testConversion_InvalidUnit_Throws() {
        Length input = new Length(5.0, Length.LengthUnit.FEET);
        input.convertTo(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConversion_NaNValue_Throws() {

        new Length(Double.NaN, Length.LengthUnit.FEET);
    }
    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
        Length feet1 = new Length(1.0, Length.LengthUnit.FEET);
        Length feet2 = new Length(2.0, Length.LengthUnit.FEET);
        Length result = feet1.add(feet2);
        Length expected = new Length(3.0, Length.LengthUnit.FEET);
        assertTrue("1.0 FT + 2.0 FT should equal 3.0 FT.", result.equals(expected));
    }

    @Test
    public void testAddition_SameUnit_InchPlusInch() {
        Length inch1 = new Length(6.0, Length.LengthUnit.INCHES);
        Length inch2 = new Length(6.0, Length.LengthUnit.INCHES);
        Length result = inch1.add(inch2);
        Length expected = new Length(12.0, Length.LengthUnit.INCHES);
        assertTrue("6.0 INCHES + 6.0 INCHES should equal 12.0 INCHES.", result.equals(expected));
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(12.0, Length.LengthUnit.INCHES);
        Length result = feet.add(inches);
        Length expected = new Length(2.0, Length.LengthUnit.FEET);
        assertTrue("1.0 FT + 12.0 INCHES should equal 2.0 FT.", result.equals(expected));
    }

    @Test
    public void testAddition_CrossUnit_InchPlusFeet() {
        Length inches = new Length(12.0, Length.LengthUnit.INCHES);
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length result = inches.add(feet);
        Length expected = new Length(24.0, Length.LengthUnit.INCHES);
        assertTrue("12.0 INCHES + 1.0 FT should equal 24.0 INCHES.", result.equals(expected));
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeet() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length result = yard.add(feet);
        Length expected = new Length(2.0, Length.LengthUnit.YARDS);
        assertTrue("1.0 YARD + 3.0 FT should equal 2.0 YARDS.", result.equals(expected));
    }

    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch() {
        Length cm = new Length(2.54, Length.LengthUnit.CENTIMETERS);
        Length inches = new Length(1.0, Length.LengthUnit.INCHES);
        Length result = cm.add(inches);
        Length expected = new Length(5.08, Length.LengthUnit.CENTIMETERS);
        assertTrue("2.54 CM + 1.0 INCH should equal approximately 5.08 CM.", result.equals(expected));
    }

    @Test
    public void testAddition_Commutativity() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(12.0, Length.LengthUnit.INCHES);

        Length sumAB = feet.add(inches); // Expressed in FEET
        Length sumBA = inches.add(feet); // Expressed in INCHES

        // To verify mathematical commutativity independent of target unit display, compare base unit equivalence
        assertTrue("Addition must be commutative: A + B == B + A.", sumAB.equals(sumBA));
    }

    @Test
    public void testAddition_WithZero() {
        Length feet = new Length(5.0, Length.LengthUnit.FEET);
        Length inches = new Length(0.0, Length.LengthUnit.INCHES);
        Length result = feet.add(inches);
        Length expected = new Length(5.0, Length.LengthUnit.FEET);
        assertTrue("Adding zero acts as an identity element leaving value unchanged.", result.equals(expected));
    }

    @Test
    public void testAddition_NegativeValues() {
        Length feet1 = new Length(5.0, Length.LengthUnit.FEET);
        Length feet2 = new Length(-2.0, Length.LengthUnit.FEET);
        Length result = feet1.add(feet2);
        Length expected = new Length(3.0, Length.LengthUnit.FEET);
        assertTrue("Addition with negative measurements should subtract appropriately.", result.equals(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddition_NullSecondOperand() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        feet.add(null);
    }

    @Test
    public void testAddition_LargeValues() {
        Length l1 = new Length(1e6, Length.LengthUnit.FEET);
        Length l2 = new Length(1e6, Length.LengthUnit.FEET);
        Length result = l1.add(l2);
        Length expected = new Length(2e6, Length.LengthUnit.FEET);
        assertTrue("Large magnitudes must add cleanly without precision breakdown.", result.equals(expected));
    }

    @Test
    public void testAddition_SmallValues() {
        Length l1 = new Length(0.001, Length.LengthUnit.FEET);
        Length l2 = new Length(0.002, Length.LengthUnit.FEET);
        Length result = l1.add(l2);
        Length expected = new Length(0.003, Length.LengthUnit.FEET);
        assertTrue("Small float values must compute accurately within defined tolerances.", result.equals(expected));
    }
    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        Length result = l1.add(l2, Length.LengthUnit.FEET);
        Length expected = new Length(2.0, Length.LengthUnit.FEET);
        assertTrue("1 Foot + 12 Inches explicitly targeting Feet should equal 2 Feet.", result.equals(expected));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        Length result = l1.add(l2, Length.LengthUnit.INCHES);
        Length expected = new Length(24.0, Length.LengthUnit.INCHES);
        assertTrue("1 Foot + 12 Inches explicitly targeting Inches should equal 24 Inches.", result.equals(expected));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_YARDS() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        Length result = l1.add(l2, Length.LengthUnit.YARDS);

        // 24 total inches / 36 inches per yard = 0.667 yards
        assertEquals("Numeric value check for fractional yards representation.", 0.667, result.getValue(), 0.001);
        assertEquals("Unit token validation check.", Length.LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Centimeters() {
        Length l1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(1.0, Length.LengthUnit.INCHES);
        Length result = l1.add(l2, Length.LengthUnit.CENTIMETERS);

        // 2 inches / 0.393701 conversion scale = 5.0802
        assertEquals("Explicit centimeter mapping validation.", 5.08, result.getValue(), 0.01);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        Length l1 = new Length(2.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(3.0, Length.LengthUnit.FEET);
        Length result = l1.add(l2, Length.LengthUnit.YARDS);
        Length expected = new Length(3.0, Length.LengthUnit.YARDS);
        assertTrue("2 Yards + 3 Feet targeting Yards should equal 3 Yards.", result.equals(expected));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        Length l1 = new Length(2.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(3.0, Length.LengthUnit.FEET);
        Length result = l1.add(l2, Length.LengthUnit.FEET);
        Length expected = new Length(9.0, Length.LengthUnit.FEET);
        assertTrue("2 Yards + 3 Feet targeting Feet should equal 9 Feet.", result.equals(expected));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Commutativity() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length sumAB = l1.add(l2, Length.LengthUnit.YARDS);
        Length sumBA = l2.add(l1, Length.LengthUnit.YARDS);

        assertTrue("Commutative equality must map evenly regardless of parameter insertion layout context.", sumAB.equals(sumBA));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_WithZero() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length l2 = new Length(0.0, Length.LengthUnit.INCHES);
        Length result = l1.add(l2, Length.LengthUnit.YARDS);

        // 60 inches / 36 = 1.667 yards
        assertEquals("Identity conversion mapping checks.", 1.667, result.getValue(), 0.001);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NegativeValues() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length l2 = new Length(-2.0, Length.LengthUnit.FEET);
        Length result = l1.add(l2, Length.LengthUnit.INCHES);
        Length expected = new Length(36.0, Length.LengthUnit.INCHES);
        assertTrue("Negative values compute arithmetic differences and express cleanly inside target bounds.", result.equals(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        l1.add(l2, null);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        Length l1 = new Length(1000.0, Length.LengthUnit.FEET);
        Length l2 = new Length(500.0, Length.LengthUnit.FEET);
        Length result = l1.add(l2, Length.LengthUnit.INCHES);
        Length expected = new Length(18000.0, Length.LengthUnit.INCHES);
        assertTrue("Up-scaling massive metrics should map structurally across bounds accurately.", result.equals(expected));
    }
}

