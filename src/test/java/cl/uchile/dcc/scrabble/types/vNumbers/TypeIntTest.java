package cl.uchile.dcc.scrabble.types.vNumbers;

import cl.uchile.dcc.scrabble.types.TypeBinary;
import cl.uchile.dcc.scrabble.types.vString.TypeString;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.lang.Math;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TypeIntTest {
    private int value;
    private int value2;
    private TypeInt typeIntTest;

    @BeforeEach
    void setUp() {
        int sign = ((Math.random() < 0.5) ? -1 : 1);
        value = (int) (Math.random() * (Math.pow(2, 8)) * sign);
        typeIntTest = new TypeInt(value);
        do {
            value2 = (int) (Math.random() * (Math.pow(2, 8)) * sign);
        } while (value == value2);
    }

    @RepeatedTest(10)
    void constructorTest() {
        var expectedInt = new TypeInt(value);
        assertEquals(expectedInt, typeIntTest);
        assertEquals(expectedInt.hashCode(), typeIntTest.hashCode());
        var unexpectedInt = new TypeInt(value2);
        assertNotEquals(unexpectedInt, typeIntTest);
    }

    @RepeatedTest(10)
    void getters_settersTest() {
        var expectedInt = new TypeInt(value2);
        assertNotEquals(expectedInt, typeIntTest);
        expectedInt.setValue(value);
        assertEquals(expectedInt, typeIntTest);
        assertNotEquals(typeIntTest.getValue(), value2);
        assertEquals(typeIntTest.getValue(), value);
    }

    @RepeatedTest(10)
    void toStringTest() {
        String value_string = String.valueOf(value);
        assertEquals(value_string, typeIntTest.toString());
        value_string = String.valueOf(value2);
        assertNotEquals(value_string, typeIntTest.toString());
    }

    @RepeatedTest(10)
    void toTypeStringTest() {
        TypeString testTypeString = new TypeString(String.valueOf(value));
        assertEquals(testTypeString, typeIntTest.toTypeString());
        testTypeString.setValue(String.valueOf(value2));
        assertNotEquals(testTypeString, typeIntTest.toTypeString());
    }

    @RepeatedTest(10)
    void toTypeIntTest() {
        TypeInt testToTypeInt = new TypeInt(value);
        assertEquals(testToTypeInt, typeIntTest.toTypeInt());
        testToTypeInt.setValue(value2);
        assertNotEquals(testToTypeInt, typeIntTest.toTypeInt());
    }

    @RepeatedTest(10)
    void toTypeFloat() {
        TypeFloat testToTypeFloat = new TypeFloat(value);
        assertEquals(testToTypeFloat, typeIntTest.toTypeFloat());
        testToTypeFloat.setValue(value2);
        assertNotEquals(testToTypeFloat, typeIntTest.toTypeFloat());
    }

    @RepeatedTest(10)
    void toTypeBinary() {
        TypeBinary test = new TypeBinary(typeIntTest.intToBinary());
        System.out.println(typeIntTest);
        assertEquals(test, typeIntTest.toTypeBinary());
        test.setValue(new TypeInt(value2).intToBinary());
        assertNotEquals(test, typeIntTest.toTypeBinary());
    }

    @RepeatedTest(100)
    void addTest() {
        double sign = ((Math.random() < 0.5) ? -1 : 1);
        double valueFloat = Math.random() * (1.7 * Math.pow(10, 100)) * sign;
        TypeFloat expectedFloat = new TypeFloat(value + valueFloat);
        TypeFloat addTypeFloat = new TypeFloat(valueFloat);
        assertEquals(expectedFloat, typeIntTest.add(addTypeFloat));

        TypeInt expectedInt = new TypeInt(value + value2);
        TypeInt addTypeInt = new TypeInt(value2);
        assertEquals(expectedInt, typeIntTest.add(addTypeInt));

        String valueBinary = RandomStringUtils.random(32, "01");
        TypeBinary testTypeBinary = new TypeBinary(valueBinary);
        TypeInt expected = new TypeInt(typeIntTest.getValue().intValue() + testTypeBinary.toInt());
        assertEquals(expected, typeIntTest.addBinary(testTypeBinary));
    }

    @RepeatedTest(100)
    void subtractTest() {
        double sign = ((Math.random() < 0.5) ? -1 : 1);
        double valueFloat = Math.random() * (1.7 * Math.pow(10, 100)) * sign;
        TypeFloat expected = new TypeFloat(value - valueFloat);
        TypeFloat subtractTypeFloat = new TypeFloat(valueFloat);
        assertEquals(expected, subtractTypeFloat.subtract(typeIntTest));

        TypeInt expected_int = new TypeInt(value - value2);
        TypeInt subtractTypeInt = new TypeInt(value2);
        assertEquals(expected_int, subtractTypeInt.subtract(typeIntTest));

        String valueBinary = RandomStringUtils.random(32, "01");
        TypeBinary testTypeBinary = new TypeBinary(valueBinary);
        TypeInt expected_binary = new TypeInt(typeIntTest.getValue().intValue() - testTypeBinary.toInt());
        assertEquals(expected_binary, testTypeBinary.subtract(typeIntTest));
    }

    @RepeatedTest(100)
    void multiTest() {
        double sign = ((Math.random() < 0.5) ? -1 : 1);
        double valueFloat = Math.random() * (1.7 * Math.pow(10, 100)) * sign;
        TypeFloat expected = new TypeFloat(value * valueFloat);
        TypeFloat multiTypeFloat = new TypeFloat(valueFloat);
        assertEquals(expected, multiTypeFloat.multiply(typeIntTest));

        TypeInt expected_int = new TypeInt(value * value2);
        TypeInt multiTypeInt = new TypeInt(value2);
        assertEquals(expected_int, multiTypeInt.multiply(typeIntTest));

        String valueBinary = RandomStringUtils.random(32, "01");
        TypeBinary testTypeBinary = new TypeBinary(valueBinary);
        TypeInt expected_binary = new TypeInt(typeIntTest.getValue().intValue() * testTypeBinary.toInt());
        assertEquals(expected_binary, testTypeBinary.multiply(typeIntTest));
    }

    @RepeatedTest(100)
    void divTest() {
        double sign = ((Math.random() < 0.5) ? -1 : 1);
        double valueFloat = Math.random() * (1.7 * Math.pow(10, 100)) * sign;
        TypeFloat expected = new TypeFloat(value / valueFloat);
        TypeFloat divTypeFloat = new TypeFloat(valueFloat);
        assertEquals(expected, divTypeFloat.divide(typeIntTest));

        TypeInt expected_int = new TypeInt((int) Math.round(((double) value) / ((double) value2)));
        TypeInt divTypeInt = new TypeInt(value2);
        assertEquals(expected_int, divTypeInt.divide(typeIntTest));

        String valueBinary = RandomStringUtils.random(32, "01");
        TypeBinary testTypeBinary = new TypeBinary(valueBinary);
        TypeInt expected_binary = new TypeInt(typeIntTest.getValue().intValue() / testTypeBinary.toInt());
        assertEquals(expected_binary, testTypeBinary.divide(typeIntTest));
    }

    @Test
    void compareTest() {
        TypeFloat x1 = new TypeFloat(100);
        TypeFloat x2 = new TypeFloat(10);
        TypeInt x4 = new TypeInt(10);
        TypeInt x5 = new TypeInt(100);
        TypeInt x6 = new TypeInt(100);
        assertEquals(0, x5.compareToFloat(x1));
        assertEquals(1, x4.compareToFloat(x1));
        assertEquals(-1, x5.compareToFloat(x2));
        assertEquals(0, x5.compareToInt(x6));
        assertEquals(-1, x5.compareToInt(x4));
        assertEquals(1, x4.compareToInt(x5));

    }
}
