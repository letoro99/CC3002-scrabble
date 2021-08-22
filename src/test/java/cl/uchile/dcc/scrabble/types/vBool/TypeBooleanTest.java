package cl.uchile.dcc.scrabble.types.vBool;

import cl.uchile.dcc.scrabble.types.TypeBinary;
import cl.uchile.dcc.scrabble.types.vBool.TypeBoolean;
import cl.uchile.dcc.scrabble.types.vString.TypeString;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class TypeBooleanTest {
    private boolean value;
    private boolean value2;
    private TypeBoolean test;

    @BeforeEach
    void setUp() {
        value = new Random().nextBoolean();
        value2 = !value;
        test = new TypeBoolean(value);
    }

    @RepeatedTest(10)
    void constructorTest() {
        var expectedBoolean = new TypeBoolean(value);
        assertEquals(test, expectedBoolean);
        assertEquals(expectedBoolean.hashCode(), test.hashCode());
        var unexpectedBoolean = new TypeBoolean(value2);
        assertNotEquals(test, unexpectedBoolean);
    }

    @RepeatedTest(10)
    void setters_gettersTest() {
        var expectedBoolean = new TypeBoolean(value2);
        assertNotEquals(test, expectedBoolean);
        assertNotEquals(test.getValue(), expectedBoolean.getValue());
        expectedBoolean.setValue(value);
        assertEquals(test, expectedBoolean);
        assertEquals(test.getValue(), expectedBoolean.getValue());
    }

    @RepeatedTest(10)
    void toStringTest() {
        String value_string = String.valueOf(value);
        assertEquals(value_string, test.toString());
    }

    @RepeatedTest(10)
    void toTypeStringTest() {
        TypeString test_typeString = new TypeString(String.valueOf(value));
        assertEquals(test_typeString, test.toTypeString());
        assertNotEquals(new TypeString(String.valueOf(value2)), test.toTypeString());
    }

    @RepeatedTest(10)
    void toTypeBooleanTest() {
        TypeBoolean newTypeBoolean = new TypeBoolean(value);
        assertEquals(newTypeBoolean, test.toTypeBoolean());
        newTypeBoolean.setValue(value2);
        assertNotEquals(newTypeBoolean, test.toTypeBoolean());
    }

    @RepeatedTest(10)
    void andTest() {
        TypeBinary testBinary = new TypeBinary(RandomStringUtils.random(32, "01"));
        if (!test.getValue()) {
            TypeBinary expected = new TypeBinary("0".repeat(32));
            assertEquals(expected, testBinary.and(test));
        } else {
            assertEquals(testBinary, testBinary.and(test));
        }
        TypeBoolean trueValue = new TypeBoolean(true);
        TypeBoolean falseValue = new TypeBoolean(false);
        assertEquals(trueValue, trueValue.and(trueValue));
        assertEquals(falseValue, falseValue.and(trueValue));
        assertEquals(falseValue, trueValue.and(falseValue));
        assertEquals(falseValue, falseValue.and(falseValue));
    }

    @RepeatedTest(10)
    void orTest() {
        TypeBinary testBinary = new TypeBinary(RandomStringUtils.random(32, "01"));
        if (test.getValue()) {
            TypeBinary expected = new TypeBinary("1".repeat(32));
            assertEquals(expected, testBinary.or(test));
        } else {
            assertEquals(testBinary, testBinary.or(test));
        }
        TypeBoolean trueValue = new TypeBoolean(true);
        TypeBoolean falseValue = new TypeBoolean(false);
        assertEquals(trueValue, trueValue.or(trueValue));
        assertEquals(trueValue, falseValue.or(trueValue));
        assertEquals(trueValue, trueValue.or(falseValue));
        assertEquals(falseValue, falseValue.or(falseValue));
    }

    @RepeatedTest(10)
    void negationTest() {
        TypeBoolean negation = new TypeBoolean(value2);
        assertEquals(negation, test.negation());
    }
}