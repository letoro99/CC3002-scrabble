package cl.uchile.dcc.scrabble.memory;

import cl.uchile.dcc.scrabble.types.vBool.TypeBoolean;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BooleanFactoryTest {

    private boolean value1;
    private boolean value2;
    private final BooleanMemory factory = new BooleanMemory();

    @BeforeEach
    void setUp(){
        value1 = true;
        value2 = false;
    }

    @Test
    void createTest(){
        TypeBoolean test1 = factory.createType(value1);
        TypeBoolean test2 = factory.createType(value1);
        TypeBoolean test3 = factory.createType(value2);
        TypeBoolean test4 = factory.createType(value2);
        assertEquals(test1,test2);
        assertSame(test1,test2);
        assertNotEquals(test1,test3);
        assertNotSame(test1,test3);
        assertEquals(test3,test4);
        assertSame(test3,test4);

    }

}
