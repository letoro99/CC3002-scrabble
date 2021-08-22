package cl.uchile.dcc.scrabble.types;

import cl.uchile.dcc.scrabble.types.vBool.TypeBoolean;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeFloat;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeInt;
import cl.uchile.dcc.scrabble.types.vString.TypeString;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TypeBinaryTest {
    private String value;
    private String value2;
    private String not_value;
    private TypeBinary typeBinaryTest;
    private TypeBinary typeBinaryTest2;

    /**
     * Return a integer number of the binary string. It use the two's complement
     * @return integer number
     * */
    protected int toInt(String x) {
        if(bitToInt(x.charAt(0)) == 1 && x.length()%8 == 0){
            return negativeBinaryToInt(x);
        }
        else {
            return positiveBinToInt(x);
        }
    }

    /**
     * Return the negative number that represent the string class's value
     * @return the number that represent the binary string
     * */
    private int negativeBinaryToInt(String x) {
        int n = x.length() - 1;
        int w = -bitToInt(x.charAt(0)) * (int) Math.pow(2, n);
        for (int i = n, j = 0; i > 0; i--, j++) {
            w += (int) Math.pow(2, j) * bitToInt(x.charAt(i));
        }
        return w-1;
    }

    /**
     * Return the positive number that represent the string class's value
     * @return the number that represent the binary string
     * */
    private int positiveBinToInt(String x) {
        int w = 0;
        for (int i = x.length() - 1, j = 0; i > 0; i--, j++) {
            w += (int) Math.pow(2, j) * bitToInt(x.charAt(i));
        }
        return w;
    }

    /**
     * Return the number of the char bit
     * @param bit Represent a bit
     * @return 0 if bit is '0' or 1 if bit is '1'
     * */
    private int bitToInt(char bit){
        return (bit == '0' ? 0 : 1);
    }

    /**
     * It sets up the binary numbers for the tests
     * */
    @BeforeEach
    void setUp(){
        value = RandomStringUtils.random(32,"01");
        value2 = RandomStringUtils.random(32,"01");
        not_value = RandomStringUtils.randomAlphanumeric(32);
        typeBinaryTest = new TypeBinary(value);
        typeBinaryTest2 = new TypeBinary(value2);
    }

    @RepeatedTest(10)
    void constructor(){
        var expectedBinary = new TypeBinary(value);
        assertEquals(expectedBinary,typeBinaryTest);
        assertEquals(expectedBinary.hashCode(),typeBinaryTest.hashCode());
        var unexpectedBinary = new TypeBinary(value2);
        assertNotEquals(unexpectedBinary,typeBinaryTest);
        assertNotEquals(value,not_value);
        assertNotEquals(value2,not_value);
    }

    @RepeatedTest(10)
    void getters_settersTest(){
        assertEquals(value,typeBinaryTest.getValue());
        assertNotEquals(value2,typeBinaryTest.getValue());
        typeBinaryTest.setValue(value2);
        assertNotEquals(value,typeBinaryTest.getValue());
        assertEquals(value2,typeBinaryTest.getValue());
    }

    @RepeatedTest(10)
    void toStringTest(){
        assertEquals(value,typeBinaryTest.toString());
        assertNotEquals(value2,typeBinaryTest.toString());
    }

    @RepeatedTest(10)
    void toTypeStringTest(){
        TypeString test_TypeString = new TypeString(value);
        assertEquals(test_TypeString,typeBinaryTest.toTypeString());
        var unexpectedBinary = new TypeBinary(value2);
        assertNotEquals(test_TypeString,unexpectedBinary.toTypeString());
    }

    @RepeatedTest(10)
    void toTypeFloatTest(){
        TypeFloat testToTypeFloat = new TypeFloat(typeBinaryTest.toInt());
        assertEquals(testToTypeFloat,typeBinaryTest.toTypeFloat());
        testToTypeFloat.setValue(new TypeBinary(value2).toInt());
        assertNotEquals(testToTypeFloat,typeBinaryTest.toTypeFloat());
    }

    @RepeatedTest(100)
    void toTypeIntTest(){
        TypeInt testTypeInt = new TypeInt(typeBinaryTest.toInt());
        assertEquals(testTypeInt,typeBinaryTest.toTypeInt());
        testTypeInt.setValue(new TypeBinary(value2).toInt());
        assertNotEquals(testTypeInt,typeBinaryTest.toTypeInt());
    }

    @RepeatedTest(10)
    void toTypeBinary(){
        TypeBinary test = new TypeBinary(value);
        assertEquals(test,typeBinaryTest.toTypeBinary());
        test.setValue(value2);
        assertNotEquals(test,typeBinaryTest.toTypeBinary());
    }

    @RepeatedTest(100)
    void addTest(){
        // test with TypeInt
        int sign = ((Math.random() < 0.5) ? -1 : 1);
        int value_int = (int) (Math.random() * (Math.pow(2,30)) * sign);
        TypeInt second_typeint = new TypeInt(value_int);
        TypeBinary expected = new TypeInt(toInt(value)+value_int).toTypeBinary();
        assertEquals(expected,second_typeint.add(typeBinaryTest));
        assertNotEquals(second_typeint.toTypeBinary(),expected);

        // test with TypeBinary
        int expected_int = toInt(value) + toInt(value2);
        expected = new TypeBinary(new TypeInt(expected_int).intToBinary());
        assertEquals(expected, typeBinaryTest2.add(typeBinaryTest));

        // test with TypeFloat
        sign = ((Math.random() < 0.5) ? -1 : 1);
        double value_float = Math.random() * (1.7 * Math.pow(10,100)) * sign;
        TypeFloat second_typeFloat = new TypeFloat(value_float);
        assertNull(second_typeFloat.add(typeBinaryTest));
    }

    @RepeatedTest(100)
    void subtractTest(){
        // test with TypeInt
        int sign = ((Math.random() < 0.5) ? -1 : 1);
        int value_int = (int) (Math.random() * (Math.pow(2,30)) * sign);
        TypeInt second_typeint = new TypeInt(value_int);
        TypeBinary expected = new TypeInt(toInt(value)-value_int).toTypeBinary();
        assertEquals(expected,second_typeint.subtract(typeBinaryTest));
        assertNotEquals(second_typeint.toTypeBinary(),expected);

        // test with TypeBinary
        int expected_int = toInt(value) - toInt(value2);
        expected = new TypeBinary(new TypeInt(expected_int).intToBinary());
        assertEquals(expected, typeBinaryTest2.subtract(typeBinaryTest));

        // test with TypeFloat
        sign = ((Math.random() < 0.5) ? -1 : 1);
        double value_float = Math.random() * (1.7 * Math.pow(10,100)) * sign;
        TypeFloat second_typeFloat = new TypeFloat(value_float);
        assertNull(second_typeFloat.subtract(typeBinaryTest));
    }

    @RepeatedTest(100)
    void multiTest(){
        // test with TypeInt
        int sign = ((Math.random() < 0.5) ? -1 : 1);
        int value_int = (int) (Math.random() * (Math.pow(2,30)) * sign);
        TypeInt second_typeint = new TypeInt(value_int);
        TypeBinary expected = new TypeInt(toInt(value)*value_int).toTypeBinary();
        assertEquals(expected,second_typeint.multiply(typeBinaryTest));
        assertNotEquals(second_typeint.toTypeBinary(),expected);

        // test with TypeBinary
        int expected_int = toInt(value) * toInt(value2);
        expected = new TypeBinary(new TypeInt(expected_int).intToBinary());
        assertEquals(expected, typeBinaryTest2.multiply(typeBinaryTest));

        // test with TypeFloat
        sign = ((Math.random() < 0.5) ? -1 : 1);
        double value_float = Math.random() * (1.7 * Math.pow(10,100)) * sign;
        TypeFloat second_typeFloat = new TypeFloat(value_float);
        assertNull(second_typeFloat.multiply(typeBinaryTest));
    }

    @RepeatedTest(100)
    void divTest(){
        // test with TypeInt
        int sign = ((Math.random() < 0.5) ? -1 : 1);
        int value_int = (int) (Math.random() * (Math.pow(2,30)) * sign);
        TypeInt second_typeint = new TypeInt(value_int);
        TypeBinary expected = new TypeInt((int) Math.round((double) toInt(value) /value_int)).toTypeBinary();
        assertEquals(expected,second_typeint.divide(typeBinaryTest));
        assertNotEquals(expected,second_typeint.toTypeBinary());

        // test with TypeBinary
        int expected_int = (int) Math.round((double) toInt(value) / toInt(value2));
        expected = new TypeBinary(new TypeInt(expected_int).intToBinary());
        assertEquals(expected, typeBinaryTest2.divide(typeBinaryTest));

        // test with TypeFloat
        sign = ((Math.random() < 0.5) ? -1 : 1);
        double value_float = Math.random() * (1.7 * Math.pow(10,100)) * sign;
        TypeFloat second_typeFloat = new TypeFloat(value_float);
        assertNull(second_typeFloat.divide(typeBinaryTest));
    }

    @RepeatedTest(10)
    void orTest(){
        // test with TypeBinary
        String temp = "";
        for(int i = 0; i < 32; i++){
            if(value.charAt(i) == '1' || value2.charAt(i) == '1') temp = temp.concat("1");
            else temp = temp.concat("0");
        }
        assertEquals(new TypeBinary(temp), typeBinaryTest2.or(typeBinaryTest));

        // test with TypeBoolean
        TypeBoolean testBoolean = new TypeBoolean(new Random().nextBoolean());
        if(!testBoolean.getValue()) {
            assertEquals(typeBinaryTest, testBoolean.or(typeBinaryTest));
        }
        else {
            TypeBinary expected = new TypeBinary("1".repeat(32));
            assertEquals(expected, testBoolean.or(typeBinaryTest));
        }
    }

    @RepeatedTest(10)
    void andTest(){
        // test with TypeBinary
        String temp = "";
        for(int i = 0; i < 32; i++){
            if(value.charAt(i) == '0' || value2.charAt(i) == '0') temp = temp.concat("0");
            else temp = temp.concat("1");
        }
        assertEquals(new TypeBinary(temp), typeBinaryTest2.and(typeBinaryTest));

        // test with TypeBoolean
        TypeBoolean testBoolean = new TypeBoolean(new Random().nextBoolean());
        if(testBoolean.getValue()) {
            assertEquals(typeBinaryTest, testBoolean.and(typeBinaryTest));
        }
        else {
            TypeBinary expected = new TypeBinary("0".repeat(32));
            assertEquals(expected, testBoolean.and(typeBinaryTest));
        }
    }

    @RepeatedTest(10)
    void negationBinaryTest(){
        TypeInt temp = typeBinaryTest.toTypeInt();
        temp.setValue(~temp.getValue().intValue());
        TypeBinary expected = temp.toTypeBinary();
        assertEquals(expected,typeBinaryTest.negation());
    }

}