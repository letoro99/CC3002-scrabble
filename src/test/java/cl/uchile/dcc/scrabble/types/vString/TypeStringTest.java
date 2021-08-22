package cl.uchile.dcc.scrabble.types.vString;

import cl.uchile.dcc.scrabble.types.TypeBinary;
import cl.uchile.dcc.scrabble.types.vBool.TypeBoolean;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeFloat;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeInt;
import cl.uchile.dcc.scrabble.types.vString.TypeString;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class TypeStringTest {
    private String value;
    private String value2;
    private TypeString typeStringTest;

    @BeforeEach
    void setUp(){
        value = RandomStringUtils.randomAlphanumeric(2,100);
        value2 = RandomStringUtils.randomAlphanumeric(2,100);
        typeStringTest = new TypeString(value);
    }

    @RepeatedTest(10)
    void constructorTest(){
        var expectedString = new TypeString(value);
        assertEquals(expectedString,typeStringTest);
        assertEquals(expectedString.hashCode(),typeStringTest.hashCode());
        var unexpectedString = new TypeString(value2);
        assertNotEquals(unexpectedString,typeStringTest);
    }

    @RepeatedTest(10)
    void settersgettersTest(){
        var expectedString = new TypeString(value2);
        assertNotEquals(expectedString.getValue(),typeStringTest.getValue());
        assertNotEquals(typeStringTest,expectedString);
        expectedString.setValue(value);
        assertEquals(expectedString.getValue(),typeStringTest.getValue());
        assertEquals(typeStringTest,expectedString);
    }

    @RepeatedTest(10)
    void toStringTest(){
        assertEquals(value,typeStringTest.toString());
        assertNotEquals(value2,typeStringTest.toString());
    }

    @RepeatedTest(10)
    void toTypeStringTest(){
        TypeString testToTypeString = new TypeString(value);
        assertEquals(testToTypeString,typeStringTest.toTypeString());
        testToTypeString.setValue(value2);
        assertNotEquals(testToTypeString,typeStringTest.toTypeString());
    }

    @RepeatedTest(100)
    void addTest(){
        TypeString second = new TypeString(value2);
        TypeString expected = new TypeString(value+value2);
        assertEquals(expected, second.addTypeString(typeStringTest));

        boolean value_bool = new Random().nextBoolean();
        TypeBoolean second_bool = new TypeBoolean(value_bool);
        TypeString expected2 = new TypeString(value+value_bool);
        assertEquals(expected2, second_bool.addTypeString(typeStringTest));

        double sign = ((Math.random() < 0.5) ? -1 : 1);
        double value_float = Math.random() * (1.7 * Math.pow(10,100)) * sign;
        TypeFloat second_float = new TypeFloat(value_float);
        TypeString expected3 = new TypeString(value+value_float);
        assertEquals(expected3, second_float.addTypeString(typeStringTest));

        int value_int = (int) (Math.random() * (Math.pow(2,10)) * sign);
        TypeInt second_int = new TypeInt(value_int);
        TypeString expected4 = new TypeString(value+value_int);
        assertEquals(expected4, second_int.addTypeString(typeStringTest));

        String valueSecond = RandomStringUtils.random(32,"01");
        TypeBinary second_binary = new TypeBinary(valueSecond);
        TypeString expected5 = new TypeString(value+valueSecond);
        assertEquals(expected5, second_binary.addTypeString(typeStringTest));
    }
}