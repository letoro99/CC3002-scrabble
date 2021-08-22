package cl.uchile.dcc.scrabble.syntax.flow;

import cl.uchile.dcc.scrabble.syntax.ExecuteVisitor;
import cl.uchile.dcc.scrabble.syntax.flow.compare.CompareEquals;
import cl.uchile.dcc.scrabble.syntax.flow.compare.CompareMinor;
import cl.uchile.dcc.scrabble.syntax.operations.Add;
import cl.uchile.dcc.scrabble.syntax.operations.IComponent;
import cl.uchile.dcc.scrabble.syntax.operations.Multi;
import cl.uchile.dcc.scrabble.syntax.operations.Subtract;
import cl.uchile.dcc.scrabble.types.Var;
import cl.uchile.dcc.scrabble.types.VarSyntax;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeFloat;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeInt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FlowTest {
    private int intValue1;
    private int intValue2;
    private double doubleValue1;
    private double doubleValue2;
    private TypeInt tInt1;
    private TypeInt tInt2;
    private TypeFloat tFloat1;
    private TypeFloat tFloat2;
    private boolean cond;
    private ExecuteVisitor visitor = new ExecuteVisitor();


    @BeforeEach
    void setUp(){
        cond = (Math.random() < 0.5);
        intValue1  = (int) (Math.random() * (Math.pow(2,10)));
        intValue2  = (int) (Math.random() * (Math.pow(2,10)));
        doubleValue1 =  (Math.random() * (Math.pow(2,10)));
        doubleValue2 =  (Math.random() * (Math.pow(2,10)));
        tInt1 = new TypeInt(intValue1);
        tInt2 = new TypeInt(intValue2);
        tFloat1 = new TypeFloat(doubleValue1);
        tFloat2 = new TypeFloat(doubleValue2);
    }

    @RepeatedTest(20)
    void ifTest(){
        double expectedValue1, expectedValue2;
        Var x = new Var("x",tFloat1);
        Var y = new Var("y",tFloat2);
        VarSyntax trueCode1 = new VarSyntax(x, new Add(x,tInt2));
        VarSyntax trueCode2 = new VarSyntax(y, new Multi(y,tInt1));
        ArrayList<IComponent> trueCode = new ArrayList<>();
        trueCode.add(trueCode1);
        trueCode.add(trueCode2);
        ArrayList<IComponent> falseCode = new ArrayList<>();
        VarSyntax falseCode1 = new VarSyntax(x, new Subtract(x,tInt1));
        VarSyntax falseCode2 = new VarSyntax(y, new Subtract(y,tFloat1));
        falseCode.add(falseCode1);
        falseCode.add(falseCode2);
        CompareMinor condition = new CompareMinor(tInt1,tInt2);
        If if_test = new If(condition, trueCode, falseCode);
        if_test.execute(visitor);
        if(condition.execute(visitor)){
            expectedValue1 = doubleValue1 + intValue2;
            expectedValue2 = doubleValue2 * intValue1;
        }
        else{
            expectedValue1 = doubleValue1 - intValue1;
            expectedValue2 = doubleValue2 - doubleValue1;
        }
        assertEquals(new TypeFloat(expectedValue1), x.getValue());
        assertEquals(new TypeFloat(expectedValue2), y.getValue());
    }

    @RepeatedTest(20)
    void whileTest(){
        int rand = (int) (Math.random()) * (10);
        Var i = new Var("i",new TypeInt(rand));
        TypeInt uno = new TypeInt(1);
        Var x = new Var("x", tFloat1);
        double expectedValue = doubleValue1 + (rand*intValue1);
        ArrayList<IComponent> code = new ArrayList<>();
        VarSyntax code1 = new VarSyntax(x, new Add(x,tInt1));
        VarSyntax code2 = new VarSyntax(i, new Subtract(i,uno));
        code.add(code1);
        code.add(code2);
        CompareMinor condition = new CompareMinor(new TypeInt(0), i);
        While while_test = new While(condition, code);
        while_test.execute(visitor);
        assertEquals(new TypeFloat(expectedValue), x.getValue());
    }

    @RepeatedTest(10)
    void equalsTest(){
        assertTrue(new CompareEquals(tInt1,tInt1).execute(visitor));
        assertFalse(new CompareEquals(tInt1,tFloat1).execute(visitor));
    }
}
