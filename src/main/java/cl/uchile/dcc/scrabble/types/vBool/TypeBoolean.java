package cl.uchile.dcc.scrabble.types.vBool;

import cl.uchile.dcc.scrabble.syntax.IVisitor;
import cl.uchile.dcc.scrabble.syntax.operations.IComponent;
import cl.uchile.dcc.scrabble.syntax.states.BooleanState;
import cl.uchile.dcc.scrabble.syntax.states.IState;
import cl.uchile.dcc.scrabble.types.AbstractVariable;
import cl.uchile.dcc.scrabble.types.TypeBinary;

import java.util.Objects;

/**
 * This class represents the boolean type from Scrabble.
 * @author Alvaro Toro Gonzalez
 * */

public class TypeBoolean extends AbstractVariable implements IBooleanOperators, IComponent {
    private boolean value;
    private IState state = new BooleanState();

    /**
     * Create a new boolean type for Scrabble.
     * @param value the boolean value for this object.
     * */
    public TypeBoolean(boolean value) {
        this.value = value;
    }

    /***
     * Set the value of the object.
     * @param value a boolean.
     */
    public void setValue(boolean value) {
        this.value = value;
    }

    /**
     * Returns the value of the object.
     * @return The encapsulated value of the TypeBoolean.
     * */
    public boolean getValue() {
        return value;
    }

    @Override
    public void setState(IState state){
        this.state = state;
    }

    @Override
    public IState getState() {
        return this.state;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof TypeBoolean){
            var obj = (TypeBoolean) o;
            return obj.getValue() == this.getValue();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(TypeBoolean.class);
    }

    /**
     * Returns a TypeBoolean with the value of the called object.
     * @return TypeBoolean object.
     * */
    public TypeBoolean toTypeBoolean(){ return new TypeBoolean(this.getValue());}

    @Override
    public String toString() {
        return String.valueOf(this.getValue());
    }

    // Operators methods

    @Override
    public IBooleanOperators or(IBooleanOperators second){
        return second.orBoolean(this);
    }

    @Override
    public IBooleanOperators and(IBooleanOperators second){
        return second.andBoolean(this);
    }

    // END

    // Double Dispatch


    @Override
    public TypeBoolean andBoolean(TypeBoolean second){
        boolean new_value = this.getValue() && second.getValue();
        return new TypeBoolean(new_value);
    }

    @Override
    public TypeBoolean orBoolean(TypeBoolean second){
        boolean new_value = this.getValue() || second.getValue();
        return new TypeBoolean(new_value);
    }

    @Override
    public TypeBinary andBinary(TypeBinary second){
        if(this.getValue()) return new TypeBinary(second.getValue());
        else return new TypeBinary("0".repeat(32));
    }

    @Override
    public TypeBinary orBinary(TypeBinary second){
        if(this.getValue()) return new TypeBinary("1".repeat(32));
        else return new TypeBinary(second.getValue());
    }

    // END

    @Override
    public TypeBoolean negation(){
        return new TypeBoolean(!this.getValue());
    }

    @Override
    public TypeBoolean execute(IVisitor visitor) {
        return visitor.executeBoolean(this);
    }
}
