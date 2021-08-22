package cl.uchile.dcc.scrabble.memory;

import cl.uchile.dcc.scrabble.types.vNumbers.TypeInt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.*;

public class IntFactoryTest {

    private IntMemory factory = new IntMemory();
    private int value1;
    private int value2;

    @BeforeEach
    void setUp(){
        int sign = ((Math.random() < 0.5) ? -1 : 1);
        value1 = (int) (Math.random() * (Math.pow(2,10)) * sign);
        do{
            value2 = (int) (Math.random() * (Math.pow(2,10)) * sign);
        } while(value2 == value1);
    }

    @RepeatedTest(100)
    void constructorFactory(){
        TypeInt test1 = factory.createType(value1);
        TypeInt test2 = factory.createType(value1);
        TypeInt test3 = factory.createType(value2);
        assertEquals(test1,test2);
        assertSame(test1, test2);
        assertNotEquals(test1,test3);
        assertNotSame(test1, test3);
    }

}
