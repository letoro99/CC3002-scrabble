package cl.uchile.dcc.scrabble.types;

import cl.uchile.dcc.scrabble.syntax.IVisitor;
import cl.uchile.dcc.scrabble.syntax.operations.IComponent;
import cl.uchile.dcc.scrabble.syntax.states.BinaryState;
import cl.uchile.dcc.scrabble.syntax.states.BooleanState;
import cl.uchile.dcc.scrabble.syntax.states.IState;
import cl.uchile.dcc.scrabble.types.vBool.IBooleanOperators;
import cl.uchile.dcc.scrabble.types.vBool.TypeBoolean;
import cl.uchile.dcc.scrabble.types.vNumbers.INumbers;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeFloat;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeInt;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 *  This class represents the binary type from Scrabble
 *  @author Alvaro Toro Gonzalez
 * */
public class TypeBinary extends AbstractVariable implements INumbers, IBooleanOperators, IComponent {

    private String value;
    private IState state = new BinaryState();

    /**
     * Create a new binary type
     * @param value String that represent a binary number
     * */
    public TypeBinary(String value){
        String byteLeft = String.valueOf(value.charAt(0));
        int len = value.length();
        if(len < 32){
            String ladoIzq = "";
            while(len < 32){
                len++;
                ladoIzq = ladoIzq.concat(byteLeft);
            }
            this.value = ladoIzq.concat(value);
        }
        else this.value = value;
    }

    /**
     * This method return the binary number that contain this object
     * @return object's value
     * */
    public String getValue() {
        return this.value;
    }


    /**
     * This method set a new value to the object
     * @param value a string that represents a binary
     * */
    public void setValue(String value) {
        if(value.length() == 32) {
            this.value = value;
        }
    }

    /**
     * Set a State Object on variable state
     * @param state a State object
     * */
    @Override
    public void setState(IState state){
        this.state = state;
    }

    /**
     * return the state object of this object
     * @return state
     * */
    @Override
    public IState getState() {
        return this.state;
    }

    /**
     * Return a integer number of the binary string. It use the two's complement
     * @return integer number
     * */
    public int toInt() {
        if(bitToInt(this.getValue().charAt(0)) == 1 && this.getValue().length()%8 == 0){
            return negativeBinaryToInt();
        }
        else {
            return positiveBinToInt();
        }
    }

    /**
     * Return the negative number that represent the string class's value
     * @return the number that represent the binary string
     * */
    private int negativeBinaryToInt() {
        int n = this.getValue().length() - 1;
        int w = -bitToInt(this.getValue().charAt(0)) * (int) Math.pow(2, n);
        for (int i = n, j = 0; i > 0; i--, j++) {
            w += (int) Math.pow(2, j) * bitToInt(this.getValue().charAt(i));
        }
        return w-1;
    }

    /**
     * Return the positive number that represent the string class's value
     * @return the number that represent the binary string
     * */
    private int positiveBinToInt() {
        int w = 0;
        for (int i = this.getValue().length() - 1, j = 0; i > 0; i--, j++) {
            w += (int) Math.pow(2, j) * bitToInt(this.getValue().charAt(i));
        }
        return w;
    }

    /**
     * Return the number of the char bit
     * @param bit Represent a bit
     * @return 0 if bit is '0' or 1 if bit is '1'
     * */
    private int bitToInt(char bit){
        return (bit == '0' ? 0 : 1);
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof TypeBinary){
            var obj = (TypeBinary) o;
            return this.getValue().equals(obj.getValue());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(TypeBinary.class);
    }

    // to different type methods
    @Override
    public TypeFloat toTypeFloat() {
        return new TypeFloat(this.toInt());
    }

    @Override
    public TypeInt toTypeInt(){return new TypeInt(this.toInt());}

    @Override
    public TypeBinary toTypeBinary() {
        return new TypeBinary(this.getValue());
    }

    @Override
    public String toString() {
        return String.valueOf(this.getValue());
    }

    // END

    // Operator methods with this object

    @Override
    public INumbers add(INumbers second){
        return second.addBinary(this);
    }

    @Override
    public INumbers subtract(INumbers second){
        return second.subtractBinary(this);
    }

    @Override
    public INumbers multiply(INumbers second){
        return second.multiBinary(this);
    }

    @Override
    public INumbers divide(INumbers second){
        return second.divBinary(this);
    }

    @Override
    public int compareToInt(TypeInt b) {
        return 2;
    }

    @Override
    public int compareToFloat(TypeFloat b) {
        return 2;
    }

    @Override
    public IBooleanOperators or(IBooleanOperators second){
        return second.orBinary(this);
    }

    @Override
    public IBooleanOperators and(IBooleanOperators second){
        return second.andBinary(this);
    }

    // END

    // Double dispatch methods

    @Override
    public TypeBinary addBinary(TypeBinary second){
        int x = this.toInt(); int y = second.toInt();
        TypeInt temporal = new TypeInt(x+y);
        return temporal.toTypeBinary();
    }

    @Override
    public TypeBinary subtractBinary(TypeBinary second){
        int x = this.toInt(); int y = second.toInt();
        TypeInt temporal = new TypeInt(x-y);
        return temporal.toTypeBinary();
    }

    @Override
    public TypeBinary multiBinary(TypeBinary second){
        int x = this.toInt(); int y = second.toInt();
        TypeInt temporal = new TypeInt(x*y);
        return temporal.toTypeBinary();
    }

    @Override
    public TypeBinary divBinary(TypeBinary second){
        int x = this.toInt(); int y = second.toInt();
        TypeInt temporal = new TypeInt((int) Math.round((double) x/y));
        return temporal.toTypeBinary();
    }

    @Override
    public TypeBinary addInt(TypeInt second){
        return this.addBinary(second.toTypeBinary());
    }

    @Override
    public TypeBinary subtractInt(TypeInt second){
        return this.subtractBinary(second.toTypeBinary());
    }

    @Override
    public TypeBinary multiInt(TypeInt second){
        return this.multiBinary(second.toTypeBinary());
    }

    @Override
    public TypeBinary divInt(TypeInt second){
        return this.divBinary(second.toTypeBinary());
    }

    /**
     * This method return null because it's impossible to add a TypeBinary
     * with a TypeFloat object
     * */
    @Override
    public INumbers addFloat(TypeFloat second) {
        return null;
    }

    /**
     * This method return null because it's impossible to subtract a TypeBinary
     * with a TypeFloat object
     * */
    @Override
    public INumbers subtractFloat(TypeFloat second) {
        return null;
    }

    /**
     * This method return null because it's impossible to multiply a TypeBinary
     * with a TypeFloat object
     * */
    @Override
    public INumbers multiFloat(TypeFloat second) {
        return null;
    }

    /**
     * This method return null because it's impossible to divide a TypeBinary
     * with a TypeFloat object
     * */
    @Override
    public INumbers divFloat(TypeFloat second) {
        return null;
    }

    @Override
    public TypeBinary andBoolean(TypeBoolean second){
        if(second.getValue()) return new TypeBinary(this.getValue());
        else return new TypeBinary("0".repeat(32));
    }

    @Override
    public TypeBinary orBoolean(TypeBoolean second){
        if(second.getValue()) return new TypeBinary("1".repeat(32));
        else return new TypeBinary(this.getValue());
    }

    @Override
    public TypeBinary andBinary(TypeBinary second) {
        String result = "";
        for(int i = 0; i < 32; i++){
            if(this.getValue().charAt(i) == '0' || second.getValue().charAt(i) == '0') result = result.concat("0");
            else result = result.concat("1");
        }
        return new TypeBinary(result);
    }

    @Override
    public TypeBinary orBinary(TypeBinary second) {
        String result = "";
        for(int i = 0; i < 32; i++){
            if(this.getValue().charAt(i) == '1' || second.getValue().charAt(i) == '1') result = result.concat("1");
            else result = result.concat("0");
        }
        return new TypeBinary(result);
    }

    // END

    // Self Methods
    @Override
    public TypeBinary negation(){
        String new_binary = "";
        for(int i = 0; i < 32; i++){
            new_binary = ((value.charAt(i) == '0') ? new_binary.concat("1") : new_binary.concat("0"));
        }
        return new TypeBinary(new_binary);
    }

    @Override
    public TypeBinary execute(IVisitor visitor) {
        return visitor.executeBinary(this);
    }

    // END
}
