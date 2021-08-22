package cl.uchile.dcc.scrabble.types.vString;

import cl.uchile.dcc.scrabble.syntax.IVisitor;
import cl.uchile.dcc.scrabble.syntax.operations.IComponent;
import cl.uchile.dcc.scrabble.syntax.states.BooleanState;
import cl.uchile.dcc.scrabble.syntax.states.IState;
import cl.uchile.dcc.scrabble.syntax.states.StringState;
import cl.uchile.dcc.scrabble.types.AbstractVariable;
import cl.uchile.dcc.scrabble.types.IVar;

import java.util.Objects;

/**
 * A class that represent the string type for Scrabble
 * @author Alvaro Toro Gonzalez
 * */
public class TypeString extends AbstractVariable implements IComponent {

    private String value;
    private IState state = new StringState();

    /**
     * Create a object with a String as his value
     * @param s string for the object
     * */
    public TypeString(String s){
        this.value = s;
    }

    /**
     * Change the value of the object to another string
     * @param value The new string
     * */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Return the value
     * @return the value of the object
     * */
    public String getValue() {
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
    public String toString() {
        return String.valueOf(this.getValue());
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof TypeString){
            var obj = (TypeString) o;
            return obj.getValue().equals(this.getValue());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(TypeString.class);
    }

    // Double Dispatch methods

    public TypeString add(IVar second){
        return second.addTypeString(this);
    }

    @Override
    public TypeString execute(IVisitor visitor) {
        return visitor.executeString(this);
    }
}
