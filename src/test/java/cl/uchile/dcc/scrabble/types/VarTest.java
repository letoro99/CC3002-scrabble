package cl.uchile.dcc.scrabble.types;

import cl.uchile.dcc.scrabble.types.vNumbers.TypeInt;
import cl.uchile.dcc.scrabble.types.vString.TypeString;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;

public class VarTest {

    @Test
    void setUp(){
        Var x = new Var("x", new TypeInt(10));
        assertNull(x.addTypeString(new TypeString("hola")));
        assertNull(x.toTypeString());
    }
}
