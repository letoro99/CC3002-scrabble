package cl.uchile.dcc.scrabble.memory;

import cl.uchile.dcc.scrabble.types.vNumbers.TypeFloat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class FloatFactoryTest {

    private final FloatMemory factory = new FloatMemory();
    private double value1;
    private double value2;

    @BeforeEach
    void setUp(){
        double sign = ((Math.random() < 0.5) ? -1 : 1);
        value1 = Math.random() * (1.7 * Math.pow(10,100)) * sign;
        do{
            value2 = Math.random() * (1.7 * Math.pow(10,100)) * sign;
        } while(value1 == value2);
    }

    @RepeatedTest(100)
    void constructorFactory(){
        TypeFloat test1 = factory.createType(value1);
        TypeFloat test2 = factory.createType(value1);
        TypeFloat test3 = factory.createType(value2);
        assertEquals(test1,test2);
        assertSame(test1, test2);
        assertNotEquals(test1,test3);
        assertNotSame(test1, test3);
    }
}
