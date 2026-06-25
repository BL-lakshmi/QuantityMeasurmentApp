package com.bl.quantitymeasurementapp;

import org.junit.Test;
import com.bl.quantitymeasurementapp.LengthUnit; // Add this!
import static org.junit.Assert.*;
import com.bl.quantitymeasurementapp.VolumeUnit;
public class TestCase {
    // Defined epsilon constant for floating-point comparisons
    private static final double EPSILON = 1e-4;

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

    @Test(expected = IllegalArgumentException.class)
    public void testAddition_NullSecondOperand() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        feet.add(null);
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
    //        UC8 - STANDALONE DELEGATION TESTS
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

    @Test
    public void testEquality_KilogramToKilogram_SameValue() {
        Weight kg1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight kg2 = new Weight(1.0, WeightUnit.KILOGRAM);
        assertTrue("Identical kilogram instances must evaluate to true.", kg1.equals(kg2));
    }

    @Test
    public void testEquality_KilogramToKilogram_DifferentValue() {
        Weight kg1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight kg2 = new Weight(2.0, WeightUnit.KILOGRAM);
        assertFalse("Different values within same unit should not match.", kg1.equals(kg2));
    }

    @Test
    public void testEquality_KilogramToGram_EquivalentValue() {
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight gm = new Weight(1000.0, WeightUnit.GRAM);
        assertTrue("1 Kilogram must be equal to 1000 Grams.", kg.equals(gm));
    }

    @Test
    public void testEquality_GramToKilogram_EquivalentValue() {
        Weight gm = new Weight(1000.0, WeightUnit.GRAM);
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        assertTrue("Symmetric verification: 1000g must equal 1kg.", gm.equals(kg));
    }

    @Test
    public void testEquality_PoundToKilogram_EquivalentValue() {
        Weight lb = new Weight(1.0, WeightUnit.POUND);
        Weight kg = new Weight(0.453592, WeightUnit.KILOGRAM);
        assertTrue("1 Pound should match 0.453592 Kilograms.", lb.equals(kg));
    }

    @Test
    public void testEquality_NegativeWeight() {
        Weight kgNeg = new Weight(-1.5, WeightUnit.KILOGRAM);
        Weight gmNeg = new Weight(-1500.0, WeightUnit.GRAM);
        assertTrue("Negative values must compute equivalent balance matrices cleanly.", kgNeg.equals(gmNeg));
    }

    @Test
    public void testEquality_WeightVsLength_Incompatible() {
        Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
        Length length = new Length(1.0, LengthUnit.FEET);
        assertFalse("Cross-category comparison must return false.", weight.equals(length));
    }

    @Test
    public void testConversion_KilogramToPound() {
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight converted = kg.convertTo(WeightUnit.POUND);
        assertEquals("1 kg should be approx 2.20462 lb.", 2.20462, converted.getValue(), 0.001);
    }

    @Test
    public void testConversion_PoundToKilogram() {
        Weight lb = new Weight(2.20462, WeightUnit.POUND);
        Weight converted = lb.convertTo(WeightUnit.KILOGRAM);
        assertEquals("2.20462 lb should revert back to 1 kg.", 1.0, converted.getValue(), 0.001);
    }

    @Test
    public void testAddition_SameUnit_KilogramPlusKilogram() {
        Weight w1 = new Weight(10.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(5.5, WeightUnit.KILOGRAM);
        Weight result = w1.add(w2);
        assertEquals(15.5, result.getValue(), 1e-6);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_KilogramPlusGram() {
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight gm = new Weight(1000.0, WeightUnit.GRAM);
        Weight result = kg.add(gm);
        assertEquals(2.0, result.getValue(), 1e-6);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Gram() {
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight gm = new Weight(500.0, WeightUnit.GRAM);
        Weight result = kg.add(gm, WeightUnit.GRAM);
        assertEquals(1500.0, result.getValue(), 1e-6);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_NullUnit_Throws() {
        new Weight(5.0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_NaNValue_Throws() {
        new Weight(Double.NaN, WeightUnit.KILOGRAM);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddition_NullTargetUnit_Throws() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1.0, WeightUnit.KILOGRAM);
        w1.add(w2, null);
    }

    @Test
    public void testIMeasurableInterface_LengthUnitImplementation() {
        IMeasurable feet = LengthUnit.FEET;
        assertEquals("FEET", feet.getUnitName());
        assertEquals(1.0, feet.getConversionFactor(), 1e-6);
    }

    @Test
    public void testIMeasurableInterface_WeightUnitImplementation() {
        IMeasurable kg = WeightUnit.KILOGRAM;
        assertEquals("KILOGRAM", kg.getUnitName());
        assertEquals(1.0, kg.getConversionFactor(), 1e-6);
    }

    @Test
    public void testIMeasurableInterface_ConsistentBehavior() {
        assertTrue(LengthUnit.INCHES.convertToBaseUnit(12) == 1.0);
        assertTrue(WeightUnit.GRAM.convertToBaseUnit(1000) == 1.0);
    }

    @Test
    public void testGenericQuantity_LengthOperations_Equality() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testGenericQuantity_WeightOperations_Equality() {
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertTrue(w1.equals(w2));
    }

    @Test
    public void testGenericQuantity_LengthOperations_Conversion() {
        Quantity<LengthUnit> start = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> converted = start.convertTo(LengthUnit.INCHES);
        assertEquals(12.0, converted.getValue(), 1e-2);
        assertEquals(LengthUnit.INCHES, converted.getUnit());
    }

    @Test
    public void testGenericQuantity_WeightOperations_Conversion() {
        Quantity<WeightUnit> start = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> converted = start.convertTo(WeightUnit.GRAM);
        assertEquals(1000.0, converted.getValue(), 1e-2);
    }

    @Test
    public void testGenericQuantity_LengthOperations_Addition() {
        Quantity<LengthUnit> f1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> i1 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = f1.add(i1, LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), 1e-6);
    }

    @Test
    public void testGenericQuantity_WeightOperations_Addition() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> gm = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> result = kg.add(gm, WeightUnit.KILOGRAM);
        assertEquals(2.0, result.getValue(), 1e-6);
    }

    @Test
    public void testCrossCategoryPrevention_LengthVsWeight() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Object targetComparison = weight;
        assertFalse("Cross-category domains should safely return false on equals comparison", length.equals(targetComparison));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenericQuantity_ConstructorValidation_NullUnit() {
        new Quantity<LengthUnit>(1.0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenericQuantity_ConstructorValidation_InvalidValue() {
        new Quantity<>(Double.NaN, LengthUnit.FEET);
    }

    @Test
    public void testHashCode_GenericQuantity_Consistency() {
        Quantity<LengthUnit> item1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> item2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(item1.hashCode(), item2.hashCode());
    }

    @Test
    public void testEquals_GenericQuantity_ContractPreservation() {
        Quantity<LengthUnit> prime = new Quantity<>(3.0, LengthUnit.FEET);
        assertTrue(prime.equals(prime));
        assertFalse(prime.equals(null));
    }

    @Test
    public void testImmutability_GenericQuantity() {
        Quantity<LengthUnit> base = new Quantity<>(5.0, LengthUnit.FEET);
        base.convertTo(LengthUnit.INCHES);
        assertEquals(5.0, base.getValue(), 1e-6);
    }

    // ==========================================
    //       SCALABILITY VERIFICATION MOCK
    // ==========================================
    private enum VolumeUnit implements IMeasurable {
        LITRE(1.0),
        MILLILITRE(0.001),
        GALLON(3.78541);

        private final double conversionFactor;
        VolumeUnit(double factor) { this.conversionFactor = factor; }
        @Override public double getConversionFactor() { return conversionFactor; }
        @Override public double convertToBaseUnit(double value) { return value * conversionFactor; }
        @Override public double convertFromBaseUnit(double baseValue) { return baseValue / conversionFactor; }
        @Override public String getUnitName() { return this.name(); }
    }

    @Test
    public void testScalability_NewUnitEnumIntegration() {
        Quantity<VolumeUnit> vol1 = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> vol2 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        assertTrue("New categories are plug-and-play extensions under the unified interface matrix.", vol1.equals(vol2));
    }

    @Test
    public void testVolumeUnitEnum_LitreConstant() {
        assertEquals(1.0, VolumeUnit.LITRE.getConversionFactor(), EPSILON);
        assertEquals("LITRE", VolumeUnit.LITRE.getUnitName());
    }

    @Test
    public void testVolumeUnitEnum_MillilitreConstant() {
        assertEquals(0.001, VolumeUnit.MILLILITRE.getConversionFactor(), EPSILON);
    }

    @Test
    public void testVolumeUnitEnum_GallonConstant() {
        assertEquals(3.78541, VolumeUnit.GALLON.getConversionFactor(), EPSILON);
    }

    @Test
    public void testConvertToBaseUnit_LitreToLitre() {
        assertEquals(5.5, VolumeUnit.LITRE.convertToBaseUnit(5.5), EPSILON);
    }

    @Test
    public void testConvertToBaseUnit_MillilitreToLitre() {
        assertEquals(1.0, VolumeUnit.MILLILITRE.convertToBaseUnit(1000.0), EPSILON);
    }

    @Test
    public void testConvertToBaseUnit_GallonToLitre() {
        assertEquals(3.78541, VolumeUnit.GALLON.convertToBaseUnit(1.0), EPSILON);
    }

    @Test
    public void testConvertFromBaseUnit_LitreToLitre() {
        assertEquals(2.5, VolumeUnit.LITRE.convertFromBaseUnit(2.5), EPSILON);
    }

    @Test
    public void testConvertFromBaseUnit_LitreToMillilitre() {
        assertEquals(1000.0, VolumeUnit.MILLILITRE.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    public void testConvertFromBaseUnit_LitreToGallon() {
        assertEquals(1.0, VolumeUnit.GALLON.convertFromBaseUnit(3.78541), EPSILON);
    }

    // ==========================================
    //          EQUALITY VERIFICATIONS
    // ==========================================

    @Test
    public void testEquality_LitreToLitre_SameValue() {
        Quantity<VolumeUnit> l1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> l2 = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_LitreToLitre_DifferentValue() {
        Quantity<VolumeUnit> l1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> l2 = new Quantity<>(2.0, VolumeUnit.LITRE);
        assertFalse(l1.equals(l2));
    }

    @Test
    public void testEquality_LitreToMillilitre_EquivalentValue() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertTrue(litre.equals(ml));
    }

    @Test
    public void testEquality_MillilitreToLitre_EquivalentValue() {
        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(ml.equals(litre));
    }

    @Test
    public void testEquality_LitreToGallon_EquivalentValue() {
        Quantity<VolumeUnit> litre = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        assertTrue(litre.equals(gallon));
    }

    @Test
    public void testEquality_GallonToLitre_EquivalentValue() {
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> litre = new Quantity<>(3.78541, VolumeUnit.LITRE);
        assertTrue(gallon.equals(litre));
    }

    @Test
    public void testEquality_SameReference() {
        Quantity<VolumeUnit> volume = new Quantity<>(5.0, VolumeUnit.LITRE);
        assertTrue(volume.equals(volume));
    }

    @Test
    public void testEquality_NullComparison() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertFalse(volume.equals(null));
    }

    @Test
    public void testEquality_ZeroValue() {
        Quantity<VolumeUnit> zeroL = new Quantity<>(0.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> zeroMl = new Quantity<>(0.0, VolumeUnit.MILLILITRE);
        assertTrue(zeroL.equals(zeroMl));
    }

    @Test
    public void testEquality_NegativeVolume() {
        Quantity<VolumeUnit> negL = new Quantity<>(-1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> negMl = new Quantity<>(-1000.0, VolumeUnit.MILLILITRE);
        assertTrue(negL.equals(negMl));
    }

    @Test
    public void testEquality_LargeVolumeValue() {
        Quantity<VolumeUnit> largeL = new Quantity<>(1000.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> largeMl = new Quantity<>(1000000.0, VolumeUnit.MILLILITRE);
        assertTrue(largeL.equals(largeMl));
    }

    @Test
    public void testEquality_SmallVolumeValue() {
        Quantity<VolumeUnit> smallL = new Quantity<>(0.001, VolumeUnit.LITRE);
        Quantity<VolumeUnit> smallMl = new Quantity<>(1.0, VolumeUnit.MILLILITRE);
        assertTrue(smallL.equals(smallMl));
    }

    @Test
    public void testEquality_TransitiveProperty() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> c = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(a.equals(b) && b.equals(c) && a.equals(c));
    }

    // ==========================================
    //          CONVERSION UNIT TESTS
    // ==========================================

    @Test
    public void testConversion_LitreToMillilitre() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_MillilitreToLitre() {
        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = ml.convertTo(VolumeUnit.LITRE);
        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_GallonToLitre() {
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> result = gallon.convertTo(VolumeUnit.LITRE);
        assertEquals(3.78541, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_LitreToGallon() {
        Quantity<VolumeUnit> litre = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.GALLON);
        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_MillilitreToGallon() {
        Quantity<VolumeUnit> ml = new Quantity<>(3785.41, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = ml.convertTo(VolumeUnit.GALLON);
        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_SameUnit() {
        Quantity<VolumeUnit> volume = new Quantity<>(42.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = volume.convertTo(VolumeUnit.LITRE);
        assertEquals(42.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_ZeroValue() {
        Quantity<VolumeUnit> source = new Quantity<>(0.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> result = source.convertTo(VolumeUnit.LITRE);
        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_NegativeValue() {
        Quantity<VolumeUnit> source = new Quantity<>(-1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> result = source.convertTo(VolumeUnit.LITRE);
        assertEquals(-3.78541, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_RoundTrip() {
        Quantity<VolumeUnit> source = new Quantity<>(2.5, VolumeUnit.LITRE);
        Quantity<VolumeUnit> roundTrip = source.convertTo(VolumeUnit.MILLILITRE).convertTo(VolumeUnit.LITRE);
        assertEquals(2.5, roundTrip.getValue(), EPSILON);
    }

    // ==========================================
    //          ARITHMETIC ADDITIONS
    // ==========================================

    @Test
    public void testAddition_SameUnit_LitrePlusLitre() {
        Quantity<VolumeUnit> l1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> l2 = new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> sum = l1.add(l2);
        assertEquals(3.0, sum.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, sum.getUnit());
    }

    @Test
    public void testAddition_SameUnit_MillilitrePlusMillilitre() {
        Quantity<VolumeUnit> m1 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> m2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> sum = m1.add(m2);
        assertEquals(1000.0, sum.getValue(), EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_LitrePlusMillilitre() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> sum = litre.add(ml);
        assertEquals(2.0, sum.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, sum.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_MillilitrePlusLitre() {
        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> sum = ml.add(litre);
        assertEquals(2000.0, sum.getValue(), EPSILON);
        assertEquals(VolumeUnit.MILLILITRE, sum.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_GallonPlusLitre() {
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> litre = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> sum = gallon.add(litre);
        assertEquals(2.0, sum.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Litre() {
        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> sum = ml.add(gallon, VolumeUnit.LITRE);
        assertEquals(4.78541, sum.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> sum = litre.add(gallon, VolumeUnit.MILLILITRE);
        assertEquals(4785.41, sum.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Gallon() {
        Quantity<VolumeUnit> l1 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> l2 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> sum = l1.add(l2, VolumeUnit.GALLON);
        assertEquals(2.0, sum.getValue(), EPSILON);
    }

    @Test
    public void testAddition_Commutativity() {
        Quantity<VolumeUnit> l = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertEquals(l.add(ml, VolumeUnit.LITRE), ml.add(l, VolumeUnit.LITRE));
    }

    // ==========================================
    //          UC12 - SUBTRACTION TESTS
    // ==========================================

    @Test
    public void testSubtraction_SameUnit_FeetMinusFeet() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q1.subtract(q2);
        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result);
    }

    @Test
    public void testSubtraction_SameUnit_LitreMinusLitre() {
        Quantity<VolumeUnit> v1 = new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(3.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = v1.subtract(v2);
        assertEquals(new Quantity<>(7.0, VolumeUnit.LITRE), result);
    }

    @Test
    public void testSubtraction_CrossUnit_FeetMinusInches() {
        Quantity<LengthUnit> feet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(6.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = feet.subtract(inches);
        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result);
    }

    @Test
    public void testSubtraction_CrossUnit_InchesMinusFeet() {
        Quantity<LengthUnit> inches = new Quantity<>(120.0, LengthUnit.INCHES);
        Quantity<LengthUnit> feet = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = inches.subtract(feet);
        assertEquals(new Quantity<>(60.0, LengthUnit.INCHES), result);
    }

    @Test
    public void testSubtraction_ExplicitTargetUnit_Feet() {
        Quantity<LengthUnit> feet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(6.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = feet.subtract(inches, LengthUnit.FEET);
        assertEquals(LengthUnit.FEET, result.getUnit());
        assertEquals(9.5, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_ExplicitTargetUnit_Inches() {
        Quantity<LengthUnit> feet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(6.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = feet.subtract(inches, LengthUnit.INCHES);
        assertEquals(new Quantity<>(114.0, LengthUnit.INCHES), result);
    }

    @Test
    public void testSubtraction_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> litre = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> minusLitre = new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = litre.subtract(minusLitre, VolumeUnit.MILLILITRE);
        assertEquals(new Quantity<>(3000.0, VolumeUnit.MILLILITRE), result);
    }

    @Test
    public void testSubtraction_ResultingInNegative() {
        Quantity<LengthUnit> f1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> f2 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = f1.subtract(f2);
        assertEquals(-5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_ResultingInZero() {
        Quantity<LengthUnit> feet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(120.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = feet.subtract(inches);
        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_WithZeroOperand() {
        Quantity<LengthUnit> feet = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(0.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = feet.subtract(inches);
        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result);
    }

    @Test
    public void testSubtraction_WithNegativeValues() {
        Quantity<LengthUnit> feet = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> negativeFeet = new Quantity<>(-2.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = feet.subtract(negativeFeet);
        assertEquals(new Quantity<>(7.0, LengthUnit.FEET), result);
    }

    @Test
    public void testSubtraction_NonCommutative() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> subAB = a.subtract(b);
        Quantity<LengthUnit> subBA = b.subtract(a);
        assertNotEquals(subAB.getValue(), subBA.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_ChainedOperations() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> c = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = a.subtract(b).subtract(c);
        assertEquals(new Quantity<>(7.0, LengthUnit.FEET), result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubtraction_NullOperand() {
        Quantity<LengthUnit> feet = new Quantity<>(10.0, LengthUnit.FEET);
        feet.subtract(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubtraction_NullTargetUnit() {
        Quantity<LengthUnit> f1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> f2 = new Quantity<>(5.0, LengthUnit.FEET);
        f1.subtract(f2, null);
    }

    // ==========================================
    //            UC12 - DIVISION TESTS
    // ==========================================

    @Test
    public void testDivision_SameUnit_FeetDividedByFeet() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
        double ratio = q1.divide(q2);
        assertEquals(5.0, ratio, EPSILON);
    }

    @Test
    public void testDivision_SameUnit_LitreDividedByLitre() {
        Quantity<VolumeUnit> v1 = new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(5.0, VolumeUnit.LITRE);
        double ratio = v1.divide(v2);
        assertEquals(2.0, ratio, EPSILON);
    }

    @Test
    public void testDivision_CrossUnit_FeetDividedByInches() {
        Quantity<LengthUnit> inches = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> feet = new Quantity<>(2.0, LengthUnit.FEET);
        double ratio = inches.divide(feet);
        assertEquals(1.0, ratio, EPSILON);
    }

    @Test
    public void testDivision_CrossUnit_KilogramDividedByGram() {
        Quantity<WeightUnit> kg = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> grams = new Quantity<>(2000.0, WeightUnit.GRAM);
        double ratio = kg.divide(grams);
        assertEquals(1.0, ratio, EPSILON);
    }

    @Test
    public void testDivision_RatioLessThanOne() {
        Quantity<LengthUnit> f1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> f2 = new Quantity<>(10.0, LengthUnit.FEET);
        double ratio = f1.divide(f2);
        assertEquals(0.5, ratio, EPSILON);
    }

    @Test
    public void testDivision_NonCommutative() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);
        double divAB = a.divide(b); // 2.0
        double divBA = b.divide(a); // 0.5
        assertNotEquals(divAB, divBA, EPSILON);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivision_ByZero() {
        Quantity<LengthUnit> feet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> zeroFeet = new Quantity<>(0.0, LengthUnit.FEET);
        feet.divide(zeroFeet);
    }

    @Test
    public void testSubtractionAndDivision_Integration() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> c = new Quantity<>(4.0, LengthUnit.FEET);

        // (10ft - 2ft) / 4ft = 8ft / 4ft = 2.0
        double result = a.subtract(b).divide(c);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    public void testSubtractionAddition_Inverse() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> roundTrip = a.add(b).subtract(b);
        assertEquals(a.getValue(), roundTrip.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_Immutability() {
        Quantity<LengthUnit> start = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> modifier = new Quantity<>(4.0, LengthUnit.FEET);
        start.subtract(modifier);
        assertEquals(10.0, start.getValue(), EPSILON);
    }
}

