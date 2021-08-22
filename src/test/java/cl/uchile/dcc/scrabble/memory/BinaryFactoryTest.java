package cl.uchile.dcc.scrabble.memory;

import cl.uchile.dcc.scrabble.types.TypeBinary;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class BinaryFactoryTest {

    private final BinaryMemory factory = new BinaryMemory();
    private String value1;
    private String value2;

    @BeforeEach
    void setUp(){
        int num = (int) (Math.random() * 32 + 1);
        value1 = RandomStringUtils.random(32,"01");
        value2 = RandomStringUtils.random(num,"01");
    }

    @RepeatedTest(100)
    void constructorFactory(){
        TypeBinary test1 = factory.createType(value1);
        TypeBinary test2 = factory.createType(value1);
        TypeBinary test3 = factory.createType(value2);
        assertEquals(test1,test2);
        assertSame(test1, test2);
        assertNotEquals(test1,test3);
        assertNotSame(test1, test3);
    }
}
