package cl.uchile.dcc.scrabble.types.vNumbers;

import cl.uchile.dcc.scrabble.types.TypeBinary;
import cl.uchile.dcc.scrabble.types.vString.TypeString;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.lang.Math;
import static org.junit.jupiter.api.Assertions.*;

public class TypeFloatTest {
    private double value;
    private double value2;
    private TypeFloat typeFloatTest;

    @BeforeEach
    void setUp(){
        double sign = ((Math.random() < 0.5) ? -1 : 1);
        value = Math.random() * (1.7 * Math.pow(10,100)) * sign;
        typeFloatTest = new TypeFloat(value);
        do value2 = Math.random() * (1.7 * Math.pow(10, 100)) * sign; while (value == value2);
    }

    @RepeatedTest(10)
    void constructorTest(){
        var expectedFloat = new TypeFloat(value);
        assertEquals(expectedFloat,typeFloatTest);
        assertEquals(expectedFloat.hashCode(),typeFloatTest.hashCode());
        var unexpectedFloat = new TypeFloat(value2);
        assertNotEquals(unexpectedFloat,typeFloatTest);
    }

    @RepeatedTest(10)
    void getters_settersTest() {
        var expectedFloat = new TypeFloat(value2);
        assertNotEquals(typeFloatTest, expectedFloat);
        expectedFloat.setValue(value);
        assertEquals(typeFloatTest, expectedFloat);
        assertNotEquals(typeFloatTest.getValue(), value2);
        assertEquals(typeFloatTest.getValue(), value);
    }

    @RepeatedTest(10)
    void toStringTest(){
        String value_string = String.valueOf(value);
        assertEquals(value_string,typeFloatTest.toString());
        value_string = String.valueOf(value2);
        assertNotEquals(value_string,typeFloatTest.toString());
    }

    @RepeatedTest(10)
    void toTypeStringTest(){
        TypeString testTypeString = new TypeString(String.valueOf(value));
        TypeString tesToTypeStringFloat = typeFloatTest.toTypeString();
        assertEquals(testTypeString, tesToTypeStringFloat);
        typeFloatTest.setValue(value2);
        assertNotEquals(testTypeString,typeFloatTest.toTypeString());
    }

    @RepeatedTest(10)
    void toTypeFloatTest(){
        TypeFloat testToTypeFloat = new TypeFloat(value);
        assertEquals(testToTypeFloat,typeFloatTest.toTypeFloat());
        testToTypeFloat.setValue(value2);
        assertNotEquals(testToTypeFloat,typeFloatTest.toTypeFloat());
    }

    @RepeatedTest(10)
    void toTypeInt(){
        assertNull(typeFloatTest.toTypeInt());
        typeFloatTest.setValue(value2);
        assertNull(typeFloatTest.toTypeInt());
    }

    @RepeatedTest(10)
    void toTypeBinary(){
        assertNull(typeFloatTest.toTypeBinary());
        typeFloatTest.setValue(value2);
        assertNull(typeFloatTest.toTypeBinary());
    }

    @RepeatedTest(100)
    void addTest(){
        // add with FloatType
        TypeFloat expected = new TypeFloat(value+value2);
        TypeFloat addTypeFloat = new TypeFloat(value2);
        assertEquals(expected, addTypeFloat.add(typeFloatTest));

        // add with TypeInt
        int sign = ((Math.random() < 0.5) ? -1: 1);
        int valueInt = (int) (Math.random() * (Math.pow(2,10)) * sign);
        TypeFloat expected_int = new TypeFloat(value+valueInt);
        TypeInt addTypeInt = new TypeInt(valueInt);
        assertEquals(expected_int, addTypeInt.add(typeFloatTest));

        // add with TypeBinary
        String valueBinary = RandomStringUtils.random(32,"01");
        TypeBinary testTypeBinary = new TypeBinary(valueBinary);
        TypeFloat expected_binary = new TypeFloat(typeFloatTest.getValue().doubleValue()+testTypeBinary.toInt());
        assertEquals(expected_binary, testTypeBinary.add(typeFloatTest));
    }

    @RepeatedTest(100)
    void subtractTest(){
        // test with TypeFloat
        TypeFloat expected = new TypeFloat(value-value2);
        TypeFloat subtractTypeFloat = new TypeFloat(value2);
        assertEquals(expected, subtractTypeFloat.subtract(typeFloatTest));

        // test with TypeInt
        int sign = ((Math.random() < 0.5) ? -1: 1);
        int valueInt = (int) (Math.random() * (Math.pow(2,10)) * sign);
        TypeFloat expected_int = new TypeFloat(value-valueInt);
        TypeInt subtractTypeInt = new TypeInt(valueInt);
        assertEquals(expected_int, subtractTypeInt.subtract(typeFloatTest));

        // test with TypeBinary
        String valueBinary = RandomStringUtils.random(32,"01");
        TypeBinary testTypeBinary = new TypeBinary(valueBinary);
        TypeFloat expected_binary = new TypeFloat(typeFloatTest.getValue().doubleValue()-testTypeBinary.toInt());
        assertEquals(expected_binary, testTypeBinary.subtract(typeFloatTest));
    }

    @RepeatedTest(100)
    void multiplyTest(){
        // test with TypeFloat
        TypeFloat expected = new TypeFloat(value*value2);
        TypeFloat multiTypeFloat = new TypeFloat(value2);
        assertEquals(expected, multiTypeFloat.multiply(typeFloatTest));

        // test with TypeInt
        int sign = ((Math.random() < 0.5) ? -1: 1);
        int valueInt = (int) (Math.random() * (Math.pow(2,10)) * sign);
        TypeFloat expected_int = new TypeFloat(value*valueInt);
        TypeInt multiTypeInt = new TypeInt(valueInt);
        assertEquals(expected_int, multiTypeInt.multiply(typeFloatTest));

        // test with TypeBinary
        String valueBinary = RandomStringUtils.random(32,"01");
        TypeBinary testTypeBinary = new TypeBinary(valueBinary);
        TypeFloat expected_binary = new TypeFloat(typeFloatTest.getValue().doubleValue()*testTypeBinary.toInt());
        assertEquals(expected_binary, testTypeBinary.multiply(typeFloatTest));
    }

    @RepeatedTest(100)
    void divideTest(){
        // test with TypeFloat
        TypeFloat expected = new TypeFloat(value/value2);
        TypeFloat divTypeFloat = new TypeFloat(value2);
        assertEquals(expected, divTypeFloat.divide(typeFloatTest));

        // test with TypeInt
        int sign = ((Math.random() < 0.5) ? -1: 1);
        int valueInt = (int) (Math.random() * (Math.pow(2,10)) * sign);
        TypeFloat expected_int = new TypeFloat(value/valueInt);
        TypeInt divTypeInt = new TypeInt(valueInt);
        assertEquals(expected_int, divTypeInt.divide(typeFloatTest));

        // test with TypeBinary
        String valueBinary = RandomStringUtils.random(32,"01");
        TypeBinary testTypeBinary = new TypeBinary(valueBinary);
        TypeFloat expected_binary = new TypeFloat(typeFloatTest.getValue().doubleValue()/testTypeBinary.toInt());
        assertEquals(expected_binary, testTypeBinary.divide(typeFloatTest));
    }

    @Test
    void compareTest(){
        TypeFloat x1 = new TypeFloat(100);
        TypeFloat x2 = new TypeFloat(10);
        TypeFloat x3 = new TypeFloat(100);
        TypeInt x4 = new TypeInt(12);
        TypeInt x5 = new TypeInt(100);
        assertEquals(0,x1.compareToFloat(x3));
        assertEquals(1,x2.compareToFloat(x1));
        assertEquals(-1,x1.compareToFloat(x2));
        assertEquals(0,x1.compareToInt(x5));
        assertEquals(-1,x1.compareToInt(x4));
        assertEquals(1,x2.compareToInt(x4));

    }

}