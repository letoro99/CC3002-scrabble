package cl.uchile.dcc.scrabble.memory;

import cl.uchile.dcc.scrabble.types.TypeBinary;
import cl.uchile.dcc.scrabble.types.vBool.TypeBoolean;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeFloat;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeInt;
import cl.uchile.dcc.scrabble.types.vString.TypeString;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

public class VarTest {
    String binaryValue;
    String stringValue;
    int intValue;
    double doubleValue;
    boolean boolValue;
    VarMemory memory = new VarMemory();

    @BeforeEach
    void setUp(){
        int sign = ((Math.random() < 0.5) ? -1 : 1);
        binaryValue = RandomStringUtils.random(32,"01");
        stringValue = RandomStringUtils.randomAlphanumeric(1,100);
        intValue = (int) (Math.random() * (Math.pow(2,10)) * sign);
        doubleValue = Math.random() * (Math.pow(2,10) * sign);
        boolValue = new Random().nextBoolean();
    }

    @RepeatedTest(100)
    void TestMemory(){
        String key1 = RandomStringUtils.randomAlphabetic(1,10);
        TypeBinary expectedBinary = new TypeBinary(binaryValue);
        String key2 = RandomStringUtils.randomAlphabetic(1,10);
        TypeString expectedString = new TypeString(stringValue);
        String key3 = RandomStringUtils.randomAlphabetic(1,10);
        TypeInt expectedInt = new TypeInt(intValue);
        String key4 = RandomStringUtils.randomAlphabetic(1,10);
        TypeFloat expectedFloat = new TypeFloat(doubleValue);
        String key5 = RandomStringUtils.randomAlphabetic(1,10);
        TypeBoolean expectedBoolean = new TypeBoolean(boolValue);
        memory.createVar(key1,expectedBinary);
        assertSame(expectedBinary, memory.returnVar(key1).getValue());
        memory.createVar(key2,expectedString);
        assertSame(expectedString, memory.returnVar(key2).getValue());
        memory.createVar(key3,expectedInt);
        assertSame(expectedInt, memory.returnVar(key3).getValue());
        memory.createVar(key4,expectedFloat);
        assertSame(expectedFloat, memory.returnVar(key4).getValue());
        memory.createVar(key5,expectedBoolean);
        assertSame(expectedBoolean, memory.returnVar(key5).getValue());
    }
}
