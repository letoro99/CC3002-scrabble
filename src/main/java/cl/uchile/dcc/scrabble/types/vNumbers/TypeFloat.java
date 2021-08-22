package cl.uchile.dcc.scrabble.types.vNumbers;

import cl.uchile.dcc.scrabble.syntax.IVisitor;
import cl.uchile.dcc.scrabble.syntax.operations.IComponent;
import cl.uchile.dcc.scrabble.syntax.states.BooleanState;
import cl.uchile.dcc.scrabble.syntax.states.FloatState;
import cl.uchile.dcc.scrabble.syntax.states.IState;
import cl.uchile.dcc.scrabble.types.IVar;
import cl.uchile.dcc.scrabble.types.TypeBinary;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A class that represent a float type of Scrabble.
 * @author Alvaro Toro Gonzalez.
 * */
public class TypeFloat extends AbstractNumbers implements IComponent, Comparable {
    private IState state = new FloatState();

    /**
     * Create a float type for Scrabble.
     * @param value The double value for the float type.
     * */
    public TypeFloat(double value){
        super(value);
    }

    /**
     * Change the value of the float type.
     * @param value The new value.
     * */
    public void setValue(double value){
        this.value = value;
    }

    @Override
    public IVar execute(IVisitor visitor) {
        return visitor.executeFloat(this);
    }

    /**
     *
     * */
    @Override
    public void setState(IState state){
        this.state = state;
    }

    /**
     *
     * */
    @Override
    public IState getState() {
        return this.state;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof TypeFloat){
            var obj = (TypeFloat) o;
            return this.getValue().equals(obj.getValue());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(TypeFloat.class);
    }

    @Override
    public TypeFloat toTypeFloat(){return new TypeFloat((double) this.getValue());}

    /**
     * This method returns null because it's impossible to transform a TypeFloat
     * to a TypeInt.
     * @return null
     * */
    @Override
    public TypeInt toTypeInt() {
        return null;
    }

    /**
     * This method returns null because it's impossible to transform a TypeFloat
     * to a TypeBinary.
     * @return null
     * */
    @Override
    public TypeBinary toTypeBinary() {
        return null;
    }

    // Operators methods

    @Override
    public INumbers add(INumbers second){
        return second.addFloat(this);
    }

    @Override
    public INumbers subtract(INumbers second){
        return second.subtractFloat(this);
    }

    @Override
    public INumbers multiply(INumbers second){
        return second.multiFloat(this);
    }

    @Override
    public INumbers divide(INumbers second){
        return second.divFloat(this);
    }

    @Override
    public int compareToInt(TypeInt o){
        if(this.getValue().doubleValue() > o.getValue().intValue()){
            return -1;
        }
        else if(this.getValue().doubleValue() == o.getValue().intValue()){
            return 0;
        }
        else{
            return 1;
        }
    }

    @Override
    public int compareToFloat(TypeFloat o){
        if(this.getValue().doubleValue() > o.getValue().doubleValue()){
            return -1;
        }
        else if(this.getValue().doubleValue() == o.getValue().doubleValue()){
            return 0;
        }
        else{
            return 1;
        }
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return ((INumbers) o).compareToFloat(this);
    }

    // END

}

