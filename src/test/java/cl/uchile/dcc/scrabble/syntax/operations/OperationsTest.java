package cl.uchile.dcc.scrabble.syntax.operations;

import cl.uchile.dcc.scrabble.syntax.ExecuteVisitor;
import cl.uchile.dcc.scrabble.syntax.states.BooleanState;
import cl.uchile.dcc.scrabble.types.TypeBinary;
import cl.uchile.dcc.scrabble.types.vBool.TypeBoolean;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeFloat;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeInt;
import cl.uchile.dcc.scrabble.types.vString.TypeString;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class OperationsTest {
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
        // Float
        double sign = ((Math.random() < 0.5) ? -1 : 1);
        valueFloat1 = Math.random() * (1.7 * Math.pow(10,50)) * sign;
        tFloat1 = new TypeFloat(valueFloat1);

        sign = ((Math.random() < 0.5) ? -1 : 1);
        valueFloat2 = Math.random() * (1.7 * Math.pow(10,50)) * sign;
        tFloat2 = new TypeFloat(valueFloat2);

        // Int
        sign = ((Math.random() < 0.5) ? -1 : 1);
        valueInt1 = (int) (Math.random() * (Math.pow(2,8)) * sign);
        tInt1 = new TypeInt(valueInt1);

        sign = ((Math.random() < 0.5) ? -1 : 1);
        valueInt2 = (int) (Math.random() * (Math.pow(2,8)) * sign);
        tInt2 = new TypeInt(valueInt2);

        // Binary
        valueBinary1 = RandomStringUtils.random(32,"01");
        tBinary1 = new TypeBinary(valueBinary1);

        valueBinary2 = RandomStringUtils.random(32,"01");
        tBinary2 = new TypeBinary(valueBinary2);

        // String
        valueString1 = RandomStringUtils.randomAlphanumeric(1,25);
        tString1 = new TypeString(valueString1);

        valueString2 = RandomStringUtils.randomAlphanumeric(1,25);
        tString2 = new TypeString(valueString2);

        // Boolean
        valueBoolean1 = new Random().nextBoolean();
        tBoolean1 = new TypeBoolean(valueBoolean1);

        valueBoolean2 = new Random().nextBoolean();
        tBoolean2 = new TypeBoolean(valueBoolean2);
    }

    @RepeatedTest(50)
    void addTest(){
        // float + float
        TypeFloat expectedFloat = new TypeFloat(valueFloat1 + valueFloat2);
        Add test = new Add(tFloat1,tFloat2);
        assertEquals(expectedFloat,test.execute(visitor));

        // float + int
        expectedFloat = new TypeFloat(valueFloat1 + valueInt1);
        test = new Add(tFloat1,tInt1);
        assertEquals(expectedFloat,test.execute(visitor));

        // float + binary
        expectedFloat = new TypeFloat(valueFloat1 + tBinary1.toInt());
        test = new Add(tFloat1,tBinary1);
        assertEquals(expectedFloat,test.execute(visitor));

        // int + int
        TypeInt expectedInt = new TypeInt(valueInt1+valueInt2);
        test = new Add(tInt1,tInt2);
        assertEquals(expectedInt,test.execute(visitor));

        // int + float
        expectedFloat = new TypeFloat(valueInt1+valueFloat2);
        test = new Add(tInt1,tFloat2);
        assertEquals(expectedFloat,test.execute(visitor));
        assertEquals(expectedFloat,test.toTypeFloat(visitor));

        // int + binary
        expectedInt = new TypeInt(valueInt1+ tBinary2.toInt());
        test = new Add(tInt1,tBinary2);
        assertEquals(expectedInt,test.execute(visitor));
        assertEquals(expectedInt,test.toTypeInt(visitor));

        // binary + binary
        TypeBinary expectedBinary = new TypeInt(tBinary1.toInt()+ tBinary2.toInt()).toTypeBinary();
        test = new Add(tBinary1,tBinary2);
        assertEquals(expectedBinary,test.execute(visitor));

        // binary + int
        expectedBinary = new TypeInt(tBinary1.toInt()+ valueInt1).toTypeBinary();
        test = new Add(tBinary1,tInt1);
        assertEquals(expectedBinary,test.execute(visitor));
        assertEquals(expectedBinary,test.toTypeBinary(visitor));

        // string + string
        TypeString expectedString = new TypeString(valueString1+valueString2);
        test = new Add(tString1,tString2);
        assertEquals(expectedString,test.execute(visitor));
        assertEquals(expectedString,test.toTypeString(visitor));

        // string + bool
        expectedString = new TypeString(valueString1+valueBoolean1);
        test = new Add(tString1,tBoolean1);
        assertEquals(expectedString,test.execute(visitor));
        assertEquals(expectedString,test.toTypeString(visitor));


        // string + float
        expectedString = new TypeString(valueString1+valueFloat1);
        test = new Add(tString1,tFloat1);
        assertEquals(expectedString,test.execute(visitor));
        assertEquals(expectedString,test.toTypeString(visitor));


        // string + int
        expectedString = new TypeString(valueString1+valueInt1);
        test = new Add(tString1,tInt1);
        assertEquals(expectedString,test.execute(visitor));
        assertEquals(expectedString,test.toTypeString(visitor));


        // string + binary
        expectedString = new TypeString(valueString1+valueBinary1);
        test = new Add(tString1,tBinary1);
        assertEquals(expectedString,test.execute(visitor));
        assertEquals(expectedString,test.toTypeString(visitor));
    }

    @RepeatedTest(50)
    void subtractTest(){
        // float - float
        TypeFloat expectedFloat = new TypeFloat(valueFloat1 - valueFloat2);
        Subtract test = new Subtract(tFloat1,tFloat2);
        assertEquals(expectedFloat,test.execute(visitor));

        // float - int
        expectedFloat = new TypeFloat(valueFloat1 - valueInt1);
        test = new Subtract(tFloat1,tInt1);
        assertEquals(expectedFloat,test.execute(visitor));

        // float - binary
        expectedFloat = new TypeFloat(valueFloat1 - tBinary1.toInt());
        test = new Subtract(tFloat1,tBinary1);
        assertEquals(expectedFloat,test.execute(visitor));

        // int - int
        TypeInt expectedInt = new TypeInt(valueInt1-valueInt2);
        test = new Subtract(tInt1,tInt2);
        assertEquals(expectedInt,test.execute(visitor));

        // int - float
        expectedFloat = new TypeFloat(valueInt1-valueFloat2);
        test = new Subtract(tInt1,tFloat2);
        assertEquals(expectedFloat,test.execute(visitor));
        assertEquals(expectedFloat,test.toTypeFloat(visitor));

        // int - binary
        expectedInt = new TypeInt(valueInt1 - tBinary2.toInt());
        test = new Subtract(tInt1,tBinary2);
        assertEquals(expectedInt,test.execute(visitor));
        assertEquals(expectedInt,test.toTypeInt(visitor));

        // binary - binary
        TypeBinary expectedBinary = new TypeInt(tBinary1.toInt()- tBinary2.toInt()).toTypeBinary();
        test = new Subtract(tBinary1,tBinary2);
        assertEquals(expectedBinary,test.execute(visitor));

        // binary - int
        expectedBinary = new TypeInt(tBinary1.toInt()-valueInt2).toTypeBinary();
        test = new Subtract(tBinary1,tInt2);
        assertEquals(expectedBinary,test.execute(visitor));
        assertEquals(expectedBinary,test.toTypeBinary(visitor));
    }

    @RepeatedTest(50)
    void multiTest(){
        // float * float
        TypeFloat expectedFloat = new TypeFloat(valueFloat1*valueFloat2);
        Multi test = new Multi(tFloat1,tFloat2);
        assertEquals(expectedFloat, test.execute(visitor));

        // float * int
        expectedFloat = new TypeFloat(valueFloat1*valueInt1);
        test = new Multi(tFloat1,tInt1);
        assertEquals(expectedFloat, test.execute(visitor));

        // float * binary
        expectedFloat = new TypeFloat(valueFloat1 * tBinary1.toInt());
        test = new Multi(tFloat1,tBinary1);
        assertEquals(expectedFloat, test.execute(visitor));

        // int * int
        TypeInt expectedInt = new TypeInt(valueInt1*valueInt2);
        test = new Multi(tInt1,tInt2);
        assertEquals(expectedInt, test.execute(visitor));
        assertEquals(expectedInt, test.toTypeInt(visitor));

        // int * float
        expectedFloat = new TypeFloat(valueInt1*valueFloat2);
        test = new Multi(tInt1,tFloat2);
        assertEquals(expectedFloat, test.execute(visitor));
        assertEquals(expectedFloat, test.toTypeFloat(visitor));

        // binary * binary
        TypeBinary expectedBinary = new TypeInt(tBinary1.toInt()*tBinary2.toInt()).toTypeBinary();
        test = new Multi(tBinary1,tBinary2);
        assertEquals(expectedBinary, test.execute(visitor));

        // binary * int
        expectedBinary = new TypeInt(tBinary1.toInt()*valueInt2).toTypeBinary();
        test = new Multi(tBinary1,tInt2);
        assertEquals(expectedBinary, test.execute(visitor));
        assertEquals(expectedBinary, test.toTypeBinary(visitor));
    }

    @RepeatedTest(50)
    void divTest(){
        // float / float
        TypeFloat expectedFloat = new TypeFloat(valueFloat1/valueFloat2);
        Div test = new Div(tFloat1,tFloat2);
        assertEquals(expectedFloat, test.execute(visitor));

        // float / int
        expectedFloat = new TypeFloat(valueFloat1/valueInt1);
        test = new Div(tFloat1,tInt1);
        assertEquals(expectedFloat, test.execute(visitor));

        // float / binary
        expectedFloat = new TypeFloat(valueFloat1/tBinary1.toInt());
        test = new Div(tFloat1,tBinary1);
        assertEquals(expectedFloat, test.execute(visitor));

        // int / int
        TypeInt expectedInt = new TypeInt((int)Math.round((double)valueInt1/valueInt2));
        test = new Div(tInt1,tInt2);
        assertEquals(expectedInt, test.execute(visitor));

        // int / float
        expectedFloat = new TypeFloat(valueInt1/valueFloat2);
        test = new Div(tInt1,tFloat2);
        assertEquals(expectedFloat, test.execute(visitor));
        assertEquals(expectedFloat, test.toTypeFloat(visitor));

        // int / binary
        expectedInt = new TypeInt((int)Math.round((double)valueInt1/tBinary2.toInt()));
        test = new Div(tInt1,tBinary2);
        assertEquals(expectedInt, test.execute(visitor));
        assertEquals(expectedInt, test.toTypeInt(visitor));

        // binary / binary
        TypeBinary expectedBinary =  new TypeInt((int)Math.round((double)tBinary1.toInt()/tBinary2.toInt())).toTypeBinary();
        test = new Div(tBinary1,tBinary2);
        assertEquals(expectedBinary, test.execute(visitor));

        // binary / int
        expectedBinary =  new TypeInt((int)Math.round((double)tBinary1.toInt()/valueInt2)).toTypeBinary();
        test = new Div(tBinary1,tInt2);
        assertEquals(expectedBinary, test.execute(visitor));
        assertEquals(expectedBinary, test.toTypeBinary(visitor));
    }

    @RepeatedTest(50)
    void orTest(){
        // boolean || boolean
        TypeBoolean expectedBoolean = new TypeBoolean(valueBoolean1 || valueBoolean2);
        Or test = new Or(tBoolean1,tBoolean2);
        assertEquals(expectedBoolean,test.execute(visitor));

        // boolean || binary
        TypeBinary expectedBinary;
        if(valueBoolean1){
            expectedBinary = new TypeBinary("1".repeat(32));
        }
        else{
            expectedBinary = new TypeBinary(valueBinary1);
        }
        test = new Or(tBoolean1,tBinary1);
        assertEquals(expectedBinary,test.execute(visitor));

        // binary || boolean
        if(valueBoolean2){
            expectedBinary = new TypeBinary("1".repeat(32));
        }
        else{
            expectedBinary = new TypeBinary(valueBinary2);
        }
        test = new Or(tBinary2,tBoolean2);
        assertEquals(expectedBinary,test.execute(visitor));

        // binary || binary
        String expectedBinaryValue = "";
        for(int i = 0; i < 32; i++){
            if(valueBinary1.charAt(i) == '1' || valueBinary2.charAt(i) == '1') expectedBinaryValue = expectedBinaryValue.concat("1");
            else expectedBinaryValue = expectedBinaryValue.concat("0");
        }
        expectedBinary = new TypeBinary(expectedBinaryValue);
        test = new Or(tBinary1,tBinary2);
        assertEquals(expectedBinary,test.execute(visitor));
    }

    @RepeatedTest(50)
    void andTest(){
        // boolean && boolean
        TypeBoolean expectedBoolean = new TypeBoolean(valueBoolean1 && valueBoolean2);
        And test = new And(tBoolean1,tBoolean2);
        assertEquals(expectedBoolean,test.execute(visitor));

        // boolean && binary
        TypeBinary expectedBinary;
        if(valueBoolean1){
            expectedBinary = new TypeBinary(valueBinary1);
        }
        else{
            expectedBinary = new TypeBinary("0".repeat(32));
        }
        test = new And(tBoolean1,tBinary1);
        assertEquals(expectedBinary,test.execute(visitor));

        // binary && boolean
        if(valueBoolean2){
            expectedBinary = new TypeBinary(valueBinary2);
        }
        else{
            expectedBinary = new TypeBinary("0".repeat(32));
        }
        test = new And(tBinary2,tBoolean2);
        assertEquals(expectedBinary,test.execute(visitor));

        // binary && binary
        String expectedBinaryValue = "";
        for(int i = 0; i < 32; i++){
            if(valueBinary1.charAt(i) == '0' || valueBinary2.charAt(i) == '0') expectedBinaryValue = expectedBinaryValue.concat("0");
            else expectedBinaryValue = expectedBinaryValue.concat("1");
        }
        expectedBinary = new TypeBinary(expectedBinaryValue);
        test = new And(tBinary1,tBinary2);
        assertEquals(expectedBinary,test.execute(visitor));

    }

    /*@RepeatedTest(50)
    void negationTest(){
        // boolean
        TypeBoolean expectedBoolean = new TypeBoolean(!valueBoolean1);
        Negation test = new Negation(tBoolean1);
        assertEquals(expectedBoolean,test.execute(visitor));

        // binary
        TypeInt temp = tBinary1.toTypeInt();
        temp.setValue(~temp.getValue().intValue());
        TypeBinary expectedBinary = temp.toTypeBinary();
        test = new Negation(tBinary1);
        assertEquals(expectedBinary,test.execute(visitor));
    }*/

    @RepeatedTest(500)
    void variousOperations(){
        // string1 + (float1 + (int2 - int1).toInt).toString
        TypeString expectedString = new TypeString(valueString1+(valueFloat1+(valueInt2-valueInt1)));
        Add testAdd = new Add(tString1,new Add(tFloat1,new Add(tInt2,tInt1).toTypeFloat(visitor)).toTypeString(visitor));
        assertEquals(expectedString, testAdd.execute(visitor));

        // (string + bool) + (binary || (binary).toString)
        String temp = valueString2.concat(String.valueOf(valueBoolean1));
        temp = temp.concat(tBinary2.or(tBinary1).toString());
        expectedString = new TypeString(temp);
        testAdd = new Add(new Add(tString2,tBoolean1),new Or(tBinary1,tBinary2));
        assertEquals(expectedString,testAdd.execute(visitor));

        // (float1 / float2) * (int1 + (binary1 - binary2))).toFloat
        TypeFloat expectedFloat = new TypeFloat((valueFloat1/valueFloat2)*(valueInt1+(tBinary1.toInt()-tBinary2.toInt())));
        Multi testMulti = new Multi(new Div(tFloat1,tFloat2),new Add(tInt1,new Subtract(tBinary1,tBinary2)).toTypeFloat(visitor));
        assertEquals(expectedFloat,testMulti.execute(visitor));

        // ((binary + binary).toBinary + (int - int).toBinary) * ((binary)*(binary).binary).toBinary
       TypeBinary expectedBinary = new TypeInt(((tBinary1.toInt()+tBinary2.toInt())+(valueInt1-valueInt2))*(tBinary1.toInt())*tBinary2.toInt()).toTypeBinary();
        testMulti = new Multi(
                        new Add(
                            new Add(tBinary1,tBinary2).toTypeBinary(visitor),
                                new Subtract(tInt1,tInt2).toTypeBinary(visitor)),
                        new Multi(tBinary1,tBinary2).toTypeBinary(visitor));
        assertEquals(expectedBinary,testMulti.execute(visitor));

        // toTypeString
        // subtract
        expectedString = new TypeString(String.valueOf(valueInt1-valueInt2));
        Subtract testSubtract = new Subtract(tInt1,tInt2);
        assertEquals(expectedString,testSubtract.toTypeString(visitor));

        // multiply
        expectedString = new TypeString((String.valueOf(valueFloat1*valueInt2)));
        testMulti = new Multi(tFloat1,tInt2);
        assertEquals(expectedString,testMulti.toTypeString(visitor));

        // divide
        expectedString = new TypeString((String.valueOf(valueFloat1/valueFloat2)));
        Div testDiv = new Div(tFloat1,tFloat2);
        assertEquals(expectedString,testDiv.toTypeString(visitor));

        // or
        expectedString = tBinary1.orBinary(tBinary2).toTypeString();
        Or testOr = new Or(tBinary1,tBinary2);
        assertEquals(expectedString,testOr.toTypeString(visitor));

        // and
        expectedString = new TypeString(String.valueOf(valueBoolean1 && valueBoolean2));
        And testAnd = new And(tBoolean1,tBoolean2);
        assertEquals(expectedString,testAnd.toTypeString(visitor));

        // opearaciones invalidas
        testAdd = new Add(tInt1,tString1);
        assertNull(testAdd.execute(visitor));

        testSubtract = new Subtract(tFloat1,tBoolean1);
        assertNull(testSubtract.execute(visitor));

        testMulti = new Multi(tBinary1,tString1);
        assertNull(testMulti.execute(visitor));

        testDiv = new Div(tInt1,tString1);
        assertNull(testDiv.execute(visitor));

        testOr = new Or(tBoolean1,tInt1);
        assertNull(testOr.execute(visitor));

        testAnd = new And(tBoolean1,tString2);
        assertNull(testAnd.execute(visitor));

        testAnd = new And(new Or(tInt1,tInt2), new And(tBoolean1,tBoolean2));
        assertNull(testAnd.execute(visitor));

        testDiv = new Div(new Or(new Or(tFloat1,tBinary2),tInt2), new Subtract(new Or(tString1,tInt2), tBinary2));
        assertNull(testDiv.execute(visitor));
    }

    @Test
    void otherTest(){
        Add addTest = new Add(tFloat1,tFloat2);
        Subtract subtractTest = new Subtract(tInt1,tFloat2);
        Multi multiTest = new Multi(tFloat1,tInt1);
        Div divTest = new Div(tBinary1,tFloat2);
        Or orTest = new Or(tBinary1,tBinary2);
        And andTest = new And(tBoolean1,tBoolean2);
        assertNull(addTest.getState());
        assertNull(subtractTest.getState());
        assertNull(multiTest.getState());
        assertNull(divTest.getState());
        assertNull(orTest.getState());
        assertNull(andTest.getState());
        BooleanState state = new BooleanState();
        addTest.setState(state);
        subtractTest.setState(state);
        multiTest.setState(state);
        divTest.setState(state);
        orTest.setState(state);
        andTest.setState(state);
        assertNull(addTest.getState());
        assertNull(subtractTest.getState());
        assertNull(multiTest.getState());
        assertNull(divTest.getState());
        assertNull(orTest.getState());
        assertNull(andTest.getState());
    }
}
