package cl.uchile.dcc.scrabble.syntax;

import cl.uchile.dcc.scrabble.syntax.flow.If;
import cl.uchile.dcc.scrabble.syntax.flow.While;
import cl.uchile.dcc.scrabble.syntax.operations.IComponent;
import cl.uchile.dcc.scrabble.types.TypeBinary;
import cl.uchile.dcc.scrabble.types.Var;
import cl.uchile.dcc.scrabble.types.VarSyntax;
import cl.uchile.dcc.scrabble.types.vBool.TypeBoolean;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeFloat;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeInt;
import cl.uchile.dcc.scrabble.types.vString.TypeString;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class ATSControllerTest {
    private final ATSController controller = new ATSController();
    private double valueFloat1;
    private double valueFloat2;
    private int valueInt1;
    private int valueInt2;
    private String valueBinary1;
    private String valueBinary2;
    private String valueString1;
    private String valueString2;
    private boolean valueBoolean1;
    private boolean valueBoolean2;
    private TypeFloat tFloat1;
    private TypeFloat tFloat2;
    private TypeInt tInt1;
    private TypeInt tInt2;
    private TypeBinary tBinary1;
    private TypeBinary tBinary2;
    private TypeString tString1;
    private TypeString tString2;
    private TypeBoolean tBoolean1;
    private TypeBoolean tBoolean2;
    private ExecuteVisitor visitor = new ExecuteVisitor();

    @BeforeEach
    void setUp(){
        double sign = ((Math.random() < 0.5) ? -1 : 1);
        valueFloat1 = Math.random() * (1.7 * Math.pow(10,50)) * sign;
        tFloat1 = controller.createFloat(valueFloat1);

        sign = ((Math.random() < 0.5) ? -1 : 1);
        do{
            valueFloat2 = Math.random() * (1.7 * Math.pow(10,50)) * sign;
        } while (valueFloat1 == valueFloat2);
        tFloat2 = controller.createFloat(valueFloat2);

        // Int
        sign = ((Math.random() < 0.5) ? -1 : 1);
        valueInt1 = (int) (Math.random() * (Math.pow(2,10)) * sign);
        tInt1 = controller.createInt(valueInt1);

        sign = ((Math.random() < 0.5) ? -1 : 1);
        do{
            valueInt2 = (int) (Math.random() * (Math.pow(2,10)) * sign);
        } while(valueInt1 == valueInt2);
        tInt2 = controller.createInt(valueInt2);

        // Binary
        valueBinary1 = RandomStringUtils.random(32,"01");
        tBinary1 = controller.createBinary(valueBinary1);

        valueBinary2 = RandomStringUtils.random(32,"01");
        tBinary2 = controller.createBinary(valueBinary2);

        // String
        valueString1 = RandomStringUtils.randomAlphanumeric(1,25);
        tString1 = controller.createString(valueString1);

        valueString2 = RandomStringUtils.randomAlphanumeric(1,25);
        tString2 = controller.createString(valueString2);

        // Boolean
        valueBoolean1 = new Random().nextBoolean();
        valueBoolean2 = new Random().nextBoolean();
        tBoolean1 = controller.createBoolean(valueBoolean1);
        tBoolean2 = controller.createBoolean(valueBoolean2);
    }

    @RepeatedTest(1)
    void TestControllerOperations(){
        // creation of different types test
        assertEquals(tFloat1, controller.createFloat(valueFloat1));
        assertSame(tFloat1, controller.createFloat(valueFloat1));
        assertNotEquals(tFloat1, controller.createFloat(valueFloat2));
        assertNotSame(tFloat1, controller.createFloat(valueFloat2));

        assertEquals(tInt1, controller.createInt(valueInt1));
        assertSame(tInt1, controller.createInt(valueInt1));
        assertNotEquals(tInt1, controller.createInt(valueInt2));
        assertNotSame(tInt1, controller.createInt(valueInt2));

        assertEquals(tBinary1, controller.createBinary(valueBinary1));
        assertSame(tBinary1, controller.createBinary(valueBinary1));
        assertNotEquals(tBinary1, controller.createBinary(valueBinary2));
        assertNotSame(tBinary1, controller.createBinary(valueBinary2));

        assertEquals(tString1, controller.createString(valueString1));
        assertSame(tString1, controller.createString(valueString1));
        assertNotEquals(tString1, controller.createString(valueString2));
        assertNotSame(tString1, controller.createString(valueString2));

        // add
        // float + float
        controller.addOperation(controller.addOp(tFloat1,tFloat2));
        TypeFloat expectedFloat = controller.createFloat(valueFloat1+valueFloat2);
        assertEquals(expectedFloat,controller.executeAST(0));
        assertSame(expectedFloat,controller.executeAST(0));
        controller.deleteOperation(0);

        // float + int
        controller.addOperation(controller.addOp(tFloat1,tInt1));
        expectedFloat = controller.createFloat(valueFloat1+valueInt1);
        assertEquals(expectedFloat,controller.executeAST(0));
        assertSame(expectedFloat,controller.executeAST(0));
        controller.deleteOperation(0);

        // float + binary
        controller.addOperation(controller.addOp(tFloat1,tBinary1));
        expectedFloat = controller.createFloat(valueFloat1+tBinary1.toInt());
        assertEquals(expectedFloat,controller.executeAST(0));
        assertSame(expectedFloat,controller.executeAST(0));
        controller.deleteOperation(0);

        // int + int
        controller.addOperation(controller.addOp(tInt1,tInt2));
        TypeInt expectedInt = controller.createInt(valueInt1+valueInt2);
        assertEquals(expectedInt,controller.executeAST(0));
        assertSame(expectedInt,controller.executeAST(0));
        controller.deleteOperation(0);

        // int + binary
        controller.addOperation(controller.addOp(tInt1,tBinary1));
        expectedInt = controller.createInt(valueInt1+tBinary1.toInt());
        assertEquals(expectedInt,controller.executeAST(0));
        assertSame(expectedInt,controller.executeAST(0));
        controller.deleteOperation(0);

        // binary + binary
        controller.addOperation(controller.addOp(tBinary1,tBinary2));
        TypeBinary expectedBinary = controller.createBinary(new TypeInt(tBinary1.toInt()+tBinary2.toInt()).toTypeBinary().toString());
        assertEquals(expectedBinary,controller.executeAST(0));
        assertSame(expectedBinary,controller.executeAST(0));
        controller.deleteOperation(0);

        // binary + int
        controller.addOperation(controller.addOp(tBinary1,tInt1));
        TypeBinary temp = new TypeInt(tBinary1.toInt()+valueInt1).toTypeBinary();
        expectedBinary = controller.createBinary(temp.getValue());
        assertEquals(expectedBinary,controller.executeAST(0));
        assertSame(expectedBinary,controller.executeAST(0));
        controller.deleteOperation(0);

        // string + string
        controller.addOperation(controller.addOp(tString1,tString2));
        TypeString expectedString = controller.createString(valueString1 + valueString2);
        assertEquals(expectedString,controller.executeAST(0));
        assertSame(expectedString,controller.executeAST(0));
        controller.deleteOperation(0);

        // string + bool
        controller.addOperation(controller.addOp(tString1,tBoolean1));
        expectedString = controller.createString(valueString1+valueBoolean1);
        assertEquals(expectedString,controller.executeAST(0));
        assertSame(expectedString,controller.executeAST(0));
        controller.deleteOperation(0);

        // string + float
        controller.addOperation(controller.addOp(tString1,tFloat1));
        expectedString = controller.createString(valueString1+valueFloat1);
        assertEquals(expectedString,controller.executeAST(0));
        assertSame(expectedString,controller.executeAST(0));
        controller.deleteOperation(0);

        // string + int
        controller.addOperation(controller.addOp(tString1,tInt1));
        expectedString = controller.createString(valueString1+valueInt1);
        assertEquals(expectedString,controller.executeAST(0));
        assertSame(expectedString,controller.executeAST(0));
        controller.deleteOperation(0);

        // string + binary
        controller.addOperation(controller.addOp(tString1,tBinary1));
        expectedString = controller.createString(valueString1+valueBinary1);
        assertEquals(expectedString,controller.executeAST(0));
        assertSame(expectedString,controller.executeAST(0));
        controller.deleteOperation(0);

        // subtract
        // float - float
        controller.addOperation(controller.subtractOp(tFloat1,tFloat2));
        expectedFloat = controller.createFloat(valueFloat1-valueFloat2);
        assertEquals(expectedFloat,controller.executeAST(0));
        assertSame(expectedFloat,controller.executeAST(0));
        controller.deleteOperation(0);

        // float - int
        controller.addOperation(controller.subtractOp(tFloat1,tInt1));
        expectedFloat = controller.createFloat(valueFloat1-valueInt1);
        assertEquals(expectedFloat,controller.executeAST(0));
        assertSame(expectedFloat,controller.executeAST(0));
        controller.deleteOperation(0);

        // float - binary
        controller.addOperation(controller.subtractOp(tFloat1,tBinary1));
        expectedFloat = controller.createFloat(valueFloat1-tBinary1.toInt());
        assertEquals(expectedFloat,controller.executeAST(0));
        assertSame(expectedFloat,controller.executeAST(0));
        controller.deleteOperation(0);

        // int - int
        controller.addOperation(controller.subtractOp(tInt1,tInt2));
        expectedInt = controller.createInt(valueInt1-valueInt2);
        assertEquals(expectedInt,controller.executeAST(0));
        assertSame(expectedInt,controller.executeAST(0));
        controller.deleteOperation(0);

        // int - binary
        controller.addOperation(controller.subtractOp(tInt1,tBinary1));
        expectedInt = controller.createInt(valueInt1-tBinary1.toInt());
        assertEquals(expectedInt,controller.executeAST(0));
        assertSame(expectedInt,controller.executeAST(0));
        controller.deleteOperation(0);

        // binary - binary
        controller.addOperation(controller.subtractOp(tBinary1,tBinary2));
        expectedBinary = controller.createBinary(new TypeInt(tBinary1.toInt()-tBinary2.toInt()).toTypeBinary().getValue());
        assertEquals(expectedBinary,controller.executeAST(0));
        assertSame(expectedBinary,controller.executeAST(0));
        controller.deleteOperation(0);

        // binary - int
        controller.addOperation(controller.subtractOp(tBinary1,tInt1));
        temp = new TypeInt(tBinary1.toInt()-valueInt1).toTypeBinary();
        expectedBinary = controller.createBinary(temp.getValue());
        assertEquals(expectedBinary,controller.executeAST(0));
        assertSame(expectedBinary,controller.executeAST(0));
        controller.deleteOperation(0);

        // multiply
        // float * float
        controller.addOperation(controller.multiOp(tFloat1,tFloat2));
        expectedFloat = controller.createFloat(valueFloat1*valueFloat2);
        assertEquals(expectedFloat,controller.executeAST(0));
        assertSame(expectedFloat,controller.executeAST(0));
        controller.deleteOperation(0);

        // float * int
        controller.addOperation(controller.multiOp(tFloat1,tInt1));
        expectedFloat = controller.createFloat(valueFloat1*valueInt1);
        assertEquals(expectedFloat,controller.executeAST(0));
        assertSame(expectedFloat,controller.executeAST(0));
        controller.deleteOperation(0);

        // float * binary
        controller.addOperation(controller.multiOp(tFloat1,tBinary1));
        expectedFloat = controller.createFloat(valueFloat1*tBinary1.toInt());
        assertEquals(expectedFloat,controller.executeAST(0));
        assertSame(expectedFloat,controller.executeAST(0));
        controller.deleteOperation(0);

        // int * int
        controller.addOperation(controller.multiOp(tInt1,tInt2));
        expectedInt = controller.createInt(valueInt1*valueInt2);
        assertEquals(expectedInt,controller.executeAST(0));
        assertSame(expectedInt,controller.executeAST(0));
        controller.deleteOperation(0);

        // int * binary
        controller.addOperation(controller.multiOp(tInt1,tBinary1));
        expectedInt = controller.createInt(valueInt1*tBinary1.toInt());
        assertEquals(expectedInt,controller.executeAST(0));
        assertSame(expectedInt,controller.executeAST(0));
        controller.deleteOperation(0);

        // binary * binary
        controller.addOperation(controller.multiOp(tBinary1,tBinary2));
        expectedBinary = controller.createBinary(new TypeInt(tBinary1.toInt()*tBinary2.toInt()).toTypeBinary().getValue());
        assertEquals(expectedBinary,controller.executeAST(0));
        assertSame(expectedBinary,controller.executeAST(0));
        controller.deleteOperation(0);

        // binary * int
        controller.addOperation(controller.multiOp(tBinary1,tInt1));
        temp = new TypeInt(tBinary1.toInt()*valueInt1).toTypeBinary();
        expectedBinary = controller.createBinary(temp.getValue());
        assertEquals(expectedBinary,controller.executeAST(0));
        assertSame(expectedBinary,controller.executeAST(0));
        controller.deleteOperation(0);

        // divide
        // float / float
        controller.addOperation(controller.divOp(tFloat1,tFloat2));
        expectedFloat = controller.createFloat(valueFloat1/valueFloat2);
        assertEquals(expectedFloat,controller.executeAST(0));
        assertSame(expectedFloat,controller.executeAST(0));
        controller.deleteOperation(0);

        // float / int
        controller.addOperation(controller.divOp(tFloat1,tInt1));
        expectedFloat = controller.createFloat(valueFloat1/valueInt1);
        assertEquals(expectedFloat,controller.executeAST(0));
        assertSame(expectedFloat,controller.executeAST(0));
        controller.deleteOperation(0);

        // float / binary
        controller.addOperation(controller.divOp(tFloat1,tBinary1));
        expectedFloat = controller.createFloat(valueFloat1/tBinary1.toInt());
        assertEquals(expectedFloat,controller.executeAST(0));
        assertSame(expectedFloat,controller.executeAST(0));
        controller.deleteOperation(0);

        // int / int
        controller.addOperation(controller.divOp(tInt1,tInt2));
        expectedInt = controller.createInt((int)Math.round((double)valueInt1/valueInt2));
        assertEquals(expectedInt,controller.executeAST(0));
        assertSame(expectedInt,controller.executeAST(0));
        controller.deleteOperation(0);

        // int / binary
        controller.addOperation(controller.divOp(tInt1,tBinary1));
        expectedInt = controller.createInt((int)Math.round((double)valueInt1/tBinary2.toInt()));
        assertEquals(expectedInt,controller.executeAST(0));
        assertSame(expectedInt,controller.executeAST(0));
        controller.deleteOperation(0);

        // binary / binary
        controller.addOperation(controller.divOp(tBinary1,tBinary2));
        expectedBinary = controller.createBinary(new TypeInt((int)Math.round((double)tBinary1.toInt()/tBinary2.toInt())).toTypeBinary().getValue());
        assertEquals(expectedBinary,controller.executeAST(0));
        assertSame(expectedBinary,controller.executeAST(0));
        controller.deleteOperation(0);

        // binary / int
        controller.addOperation(controller.divOp(tBinary1,tInt1));
        temp = new TypeInt((int)Math.round((double)tBinary1.toInt()/valueInt1)).toTypeBinary();
        expectedBinary = controller.createBinary(temp.getValue());
        assertEquals(expectedBinary,controller.executeAST(0));
        assertSame(expectedBinary,controller.executeAST(0));
        controller.deleteOperation(0);

        // or
        // boolean || boolean
        controller.addOperation(controller.orOp(tBoolean1,tBoolean2));
        TypeBoolean expectedBoolean = controller.createBoolean(tBoolean1.getValue() || tBoolean2.getValue());
        assertEquals(expectedBoolean,controller.executeAST(0));
        assertSame(expectedBoolean,controller.executeAST(0));
        controller.deleteOperation(0);

        // boolean || binary
        controller.addOperation(controller.orOp(tBoolean1,tBinary1));
        if(valueBoolean1){
            expectedBinary = controller.createBinary("1".repeat(32));
        }
        else{
            expectedBinary = controller.createBinary(valueBinary1);
        }
        assertEquals(expectedBinary,controller.executeAST(0));
        assertSame(expectedBinary,controller.executeAST(0));
        controller.deleteOperation(0);

        // binary || boolean
        controller.addOperation(controller.orOp(tBinary2,tBoolean2));
        if(valueBoolean2){
            expectedBinary = controller.createBinary("1".repeat(32));
        }
        else{
            expectedBinary = controller.createBinary(valueBinary2);
        }
        assertEquals(expectedBinary,controller.executeAST(0));
        assertSame(expectedBinary,controller.executeAST(0));
        controller.deleteOperation(0);

        // binary || binary
        controller.addOperation(controller.orOp(tBinary1,tBinary2));
        String expectedBinaryValue = "";
        for(int i = 0; i < 32; i++){
            if(valueBinary1.charAt(i) == '1' || valueBinary2.charAt(i) == '1') expectedBinaryValue = expectedBinaryValue.concat("1");
            else expectedBinaryValue = expectedBinaryValue.concat("0");
        }
        expectedBinary = controller.createBinary(expectedBinaryValue);
        assertEquals(expectedBinary,controller.executeAST(0));
        assertSame(expectedBinary,controller.executeAST(0));
        controller.deleteOperation(0);

        // and
        // boolean && boolean
        controller.addOperation(controller.andOp(tBoolean1,tBoolean2));
        expectedBoolean = controller.createBoolean(tBoolean1.getValue() && tBoolean2.getValue());
        assertEquals(expectedBoolean,controller.executeAST(0));
        assertSame(expectedBoolean,controller.executeAST(0));
        controller.deleteOperation(0);

        // boolean && binary
        controller.addOperation(controller.andOp(tBoolean1,tBinary1));
        if(valueBoolean1){
            expectedBinary = controller.createBinary(valueBinary1);
        }
        else{
            expectedBinary = controller.createBinary("0".repeat(32));
        }
        assertEquals(expectedBinary,controller.executeAST(0));
        assertSame(expectedBinary,controller.executeAST(0));
        controller.deleteOperation(0);

        // binary && boolean
        controller.addOperation(controller.andOp(tBinary2,tBoolean2));
        if(valueBoolean2){
            expectedBinary = controller.createBinary(valueBinary2);
        }
        else{
            expectedBinary = controller.createBinary("0".repeat(32));
        }
        assertEquals(expectedBinary,controller.executeAST(0));
        assertSame(expectedBinary,controller.executeAST(0));
        controller.deleteOperation(0);

        // binary && binary
        controller.addOperation(controller.andOp(tBinary1,tBinary2));
        expectedBinaryValue = "";
        for(int i = 0; i < 32; i++){
            if(valueBinary1.charAt(i) == '0' || valueBinary2.charAt(i) == '0') expectedBinaryValue = expectedBinaryValue.concat("0");
            else expectedBinaryValue = expectedBinaryValue.concat("1");
        }
        expectedBinary = controller.createBinary(expectedBinaryValue);
        assertEquals(expectedBinary,controller.executeAST(0));
        assertSame(expectedBinary,controller.executeAST(0));
        controller.deleteOperation(0);

        // negation
        // bool
        /*controller.addOperation(controller.negationOp(tBoolean1));
        expectedBoolean  = controller.createBoolean(!tBoolean1.getValue());
        assertEquals(expectedBoolean,controller.executeAST(0));
        assertSame(expectedBoolean,controller.executeAST(0));
        controller.deleteOperation(0);

        // binary
        expectedBinaryValue = "";
        for(int i = 0; i < 32; i++){
            if(tBinary1.getValue().charAt(i) == '0') expectedBinaryValue = expectedBinaryValue.concat("1");
            else expectedBinaryValue = expectedBinaryValue.concat("0");
        }
        controller.addOperation(controller.negationOp(tBinary1));
        expectedBinary = controller.createBinary(expectedBinaryValue);
        assertEquals(expectedBinary,controller.executeAST(0));
        assertSame(expectedBinary,controller.executeAST(0));
        controller.deleteOperation(0);

        // more operations
        // string1 + (float1 + (int2 - int1).toInt).toString
        expectedString = controller.createString(valueString1 + (valueFloat1 + (valueInt2 - valueInt1)));
        controller.addOperation(controller.addOp(tString1, controller.addOp(tFloat1, controller.addOp(tInt2,tInt1)).toTypeString()));
        assertEquals(expectedString, controller.executeAST(0));
        assertSame(expectedString,controller.executeAST(0));
        controller.deleteOperation(0);*/

        // (float1 / float2) * (int1 + (binary1 - binary2))).toFloat
        expectedFloat = controller.createFloat((valueFloat1/valueFloat2)*(valueInt1+(tBinary1.toInt()-tBinary2.toInt())));
        controller.addOperation(controller.multiOp(controller.divOp(tFloat1,tFloat2), controller.addOp(tInt1, controller.subtractOp(tBinary1,tBinary2))));
        assertEquals(expectedFloat, controller.executeAST(0));
        assertSame(expectedFloat,controller.executeAST(0));
        controller.deleteOperation(0);

        // ((binary + binary) + (int - int)) * ((binary) * (binary))
        expectedBinary = controller.createBinary(new TypeInt(((tBinary1.toInt()+tBinary2.toInt())+(valueInt1-valueInt2))*(tBinary1.toInt())*tBinary2.toInt()).toTypeBinary().getValue());
        controller.addOperation(controller.multiOp(controller.addOp(controller.addOp(tBinary1,tBinary2), controller.subtractOp(tInt1,tInt2)), controller.multiOp(tBinary1,tBinary2)));
        assertEquals(expectedBinary, controller.executeAST(0));
        assertSame(expectedBinary,controller.executeAST(0));
        controller.deleteOperation(0);

        // (float + int) + (int + float)
        expectedFloat = controller.createFloat((valueFloat1+valueInt1)+(valueInt2+valueFloat2));
        controller.addOperation(controller.addOp(controller.addOp(tFloat1,tInt1),controller.addOp(tInt1,tFloat2)));
        assertEquals(expectedFloat,controller.executeAST(0));
        assertSame(expectedFloat,controller.executeAST(0));
        controller.deleteOperation(0);

        // (binary && binary) - (int * (int + int))
        expectedBinary = controller.createBinary(new TypeInt(tBinary2.andBinary(tBinary1).toInt() - (valueInt1 * (valueInt2+valueInt1))).toTypeBinary().getValue());
        controller.addOperation(controller.subtractOp(controller.andOp(tBinary1,tBinary2), controller.multiOp(tInt1,controller.addOp(tInt2,tInt1))));
        assertEquals(expectedBinary,controller.executeAST(0));
        assertEquals(expectedBinary,controller.executeAST(0));
        controller.deleteOperation(0);
    }

    @RepeatedTest(20)
    void FlowControlTest(){
        // change value of a Var with if clause
        Var x = controller.createVariableFloat("x", tFloat1);
        Var y = controller.createVariableInt("y", tInt2);
        Var b1 = controller.createVariableFloat("b1", tFloat2);
        Var b2 = controller.createVariableInt("b2", tInt1);
        Var z = controller.createVariableString("str",tString1);
        Var z1 = controller.createVariableBoolean("z1", tBoolean1);
        Var z2 = controller.createVariableBinary("z2", tBinary2);
        ArrayList<IComponent> codeTrue = controller.createCodeBlock();
        controller.addCodeLine(codeTrue, controller.createVariableSyntax("x", controller.addOp(x, y)));
        controller.addCodeLine(codeTrue, controller.createVariableSyntax("b1", controller.subtractOp(b1, b2)));
        controller.addCodeLine(codeTrue, controller.createVariableSyntax("str", controller.addOp(z, z1)));
        ArrayList<IComponent> codeFalse = controller.createCodeBlock();
        controller.addCodeLine(codeFalse, controller.createVariableSyntax("x", controller.subtractOp(x, y)));
        controller.addCodeLine(codeFalse, controller.createVariableSyntax("b1", controller.addOp(b1, b2)));
        controller.addCodeLine(codeFalse, controller.createVariableSyntax("str", controller.addOp(z, z2)));
        If testIf = controller.createIf(controller.createCompareMinor(x,y),codeTrue, codeFalse);
        controller.addOperation(testIf);
        controller.executeAllCode();
        if(valueFloat1 < valueInt2){
            assertEquals(controller.addOp(tFloat1,tInt2).execute(controller.getVisitor()),controller.returnVariable("x").getValue());
            assertEquals(controller.subtractOp(tFloat2,tInt1).execute(controller.getVisitor()),controller.returnVariable("b1").getValue());
            assertEquals(controller.addOp(tString1,tBoolean1).execute(controller.getVisitor()), controller.returnVariable("str").getValue());
        }
        else{
            assertEquals(controller.subtractOp(tFloat1,tInt2).execute(controller.getVisitor()),controller.returnVariable("x").getValue());
            assertEquals(controller.addOp(tFloat2,tInt1).execute(controller.getVisitor()),controller.returnVariable("b1").getValue());
            assertEquals(controller.addOp(tString1,tBinary2).execute(controller.getVisitor()), controller.returnVariable("str").getValue());
        }

        // a program with while (subtract a var named b2 until b2 < 0)
        controller.clear();
        TypeInt zero = controller.createInt(0);
        TypeInt mil = controller.createInt(1000);
        ArrayList<IComponent> codeWhile = controller.createCodeBlock();
        controller.addCodeLine(codeWhile, controller.createVariableSyntax("b2",controller.subtractOp(controller.returnVariable("b2"),mil)));
        While whileTest = controller.createWhile(controller.createCompareMinor(zero,controller.returnVariable("b2")),codeWhile);
        controller.addOperation(whileTest);
        controller.executeAllCode();
        assertTrue(controller.createCompareMinor(controller.returnVariable("b2"),zero).execute(controller.getVisitor()));

        // while with if (duplicate the minor variable between a and b until the c (initial 0, then a + b) is above 1000)
        controller.clear();
        int valueA = (int) (Math.random() * 100 + 1);
        int valueB = (int) (Math.random() * 100 + 1);
        controller.createVariableInt("a", controller.createInt(valueA));
        controller.createVariableInt("b", controller.createInt(valueB));
        controller.createVariableInt("c", controller.createInt(0));
        codeWhile = controller.createCodeBlock();
        codeTrue = controller.createCodeBlock();
        codeFalse = controller.createCodeBlock();
        controller.addCodeLine(codeTrue, controller.createVariableSyntax("a",controller.multiOp(controller.returnVariable("a"),controller.createInt(2))));
        controller.addCodeLine(codeFalse, controller.createVariableSyntax("b",controller.multiOp(controller.returnVariable("b"),controller.createInt(2))));
        testIf = controller.createIf(controller.createCompareMinor(controller.returnVariable("a"),controller.returnVariable("b")), codeTrue, codeFalse);
        controller.addCodeLine(codeWhile,testIf);
        controller.addCodeLine(codeWhile,controller.createVariableSyntax("c",controller.addOp(controller.returnVariable("a"),controller.returnVariable("b"))));
        whileTest = controller.createWhile(controller.createCompareMinor(controller.returnVariable("c"),controller.createInt(1000)),codeWhile);
        controller.addOperation(whileTest);
        controller.executeAllCode();
        assertTrue(controller.createCompareMinor(controller.createInt(1000),controller.returnVariable("c")).execute(controller.getVisitor()));

    }
}
