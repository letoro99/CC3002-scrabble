package cl.uchile.dcc.scrabble.memory;

import cl.uchile.dcc.scrabble.types.vString.TypeString;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

public class StringFactoryTest {

    private String value1;
    private String value2;
    private StringMemory factory = new StringMemory();

    @BeforeEach
    void setUp(){
        value1 = RandomStringUtils.randomAlphanumeric(1,100);
        value2 = RandomStringUtils.randomAlphanumeric(1,100);
    }

    @RepeatedTest(100)
    void createTest(){
        TypeString test1 = factory.createType(value1);
        TypeString test2 = factory.createType(value1);
        TypeString test3 = factory.createType(value2);
        assertEquals(test1,test2);
        assertSame(test1,test2);
        assertNotEquals(test1,test3);
        assertNotSame(test1,test3);
    }

}
