package cl.uchile.dcc.scrabble.types.vNumbers;

import cl.uchile.dcc.scrabble.syntax.IVisitor;
import cl.uchile.dcc.scrabble.syntax.operations.IComponent;
import cl.uchile.dcc.scrabble.syntax.states.BooleanState;
import cl.uchile.dcc.scrabble.syntax.states.IState;
import cl.uchile.dcc.scrabble.syntax.states.IntState;
import cl.uchile.dcc.scrabble.types.IVar;
import cl.uchile.dcc.scrabble.types.TypeBinary;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A class that represent a int type from Scrabble
 * @author Alvaro Toro Gonzalez
 * */
public class TypeInt extends AbstractNumbers implements IComponent, Comparable {
    private IState state = new IntState();

    /**
     * Create a new int type for Scrabble
     * @param number  value of the int type
     * */
    public TypeInt(int number){
        super(number);
    }

    /**
     * Change the value of the int type
     * @param value the new value
     * */
    public void setValue(int value){
        this.value = value;
    }

    @Override
    public IVar execute(IVisitor visitor) {
        return visitor.executeInt(this);
    }

    @Override
    public void setState(IState state){
        this.state = state;
    }

    @Override
    public IState getState() {
        return this.state;
    }

    /**
     * Return a string that represents the number in binary
     * @return String with binary number of value
     * */
    public String intToBinary(){
        int abs = Math.abs((int) this.getValue());
        String byteLeft = "0";
        String result = positiveIntToBinary(abs);
        if(this.getValue().intValue() < 0) return twoComplement(abs);
        int len = result.length();
        if(len < 32){
            len++;
            while(len < 32){
                len++;
                byteLeft = byteLeft.concat("0");
            }
            result = byteLeft.concat(result);
        }
        return result;
    }

    /**
     * Create a string that represent this positive number in binary
     * @return String with binary number of value
     * */
    private String positiveIntToBinary(int value){
        StringBuilder result = new StringBuilder();
        while(value != 0){
            result.append(value % 2);
            value/=2;
        }
        return result.reverse().toString();
    }

    /**
     * Create a string that represent this negative number in binary
     * using two's complement
     * */
    private String twoComplement(int value){
        value = ~value+1;
        return Integer.toBinaryString(value);
    }

    @Override
    public TypeBinary toTypeBinary() {
        return new TypeBinary(this.intToBinary());
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof TypeInt){
            var obj = (TypeInt) o;
            return obj.getValue().equals(this.getValue());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(TypeInt.class);
    }

    // Operations methods

    @Override
    public INumbers add(INumbers second){
        return second.addInt(this);
    }

    @Override
    public INumbers subtract(INumbers second){
        return second.subtractInt(this);
    }

    @Override
    public INumbers multiply(INumbers second){
        return second.multiInt(this);
    }

    @Override
    public INumbers divide(INumbers second){
        return second.divInt(this);
    }

    @Override
    public int compareTo(Object o){
        return ((INumbers) o).compareToInt(this);
    }

    //

    @Override
    public TypeInt addInt(TypeInt second){
        int new_value = this.getValue().intValue() + second.getValue().intValue();
        return new TypeInt(new_value);
    }

    @Override
    public TypeInt subtractInt(TypeInt second){
        int new_value = this.getValue().intValue() - second.getValue().intValue();
        return new TypeInt(new_value);
    }

    @Override
    public TypeInt multiInt(TypeInt second){
        int new_value = this.getValue().intValue() * second.getValue().intValue();
        return new TypeInt(new_value);
    }

    @Override
    public TypeInt divInt(TypeInt second){
        int new_value = (int) Math.round(this.getValue().doubleValue() / second.getValue().doubleValue());
        return new TypeInt(new_value);
    }

    @Override
    public TypeInt addBinary(TypeBinary second){
        int new_value = this.getValue().intValue() + second.toTypeInt().getValue().intValue();
        return new TypeInt(new_value);
    }

    @Override
    public TypeInt subtractBinary(TypeBinary second){
        int new_value = this.getValue().intValue() - second.toTypeInt().getValue().intValue();
        return new TypeInt(new_value);
    }

    @Override
    public TypeInt multiBinary(TypeBinary second){
        int new_value = this.getValue().intValue() * second.toTypeInt().getValue().intValue();
        return new TypeInt(new_value);
    }

    @Override
    public TypeInt divBinary(TypeBinary second){
        int new_value = this.getValue().intValue() / second.toTypeInt().getValue().intValue();
        return new TypeInt(new_value);
    }

    @Override
    public int compareToInt(TypeInt o){
        if(this.getValue().intValue() > o.getValue().intValue()){
            return -1;
        }
        else if(this.getValue().intValue() == o.getValue().intValue()){
            return 0;
        }
        else{
            return 1;
        }
    }

    @Override
    public int compareToFloat(TypeFloat o){
        if(this.getValue().intValue() > o.getValue().doubleValue()){
            return -1;
        }
        else if(this.getValue().intValue() == o.getValue().doubleValue()){
            return 0;
        }
        else{
            return 1;
        }
    }

}


