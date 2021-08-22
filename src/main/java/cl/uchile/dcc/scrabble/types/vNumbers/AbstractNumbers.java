package cl.uchile.dcc.scrabble.types.vNumbers;

import cl.uchile.dcc.scrabble.syntax.IVisitor;
import cl.uchile.dcc.scrabble.syntax.operations.IComponent;
import cl.uchile.dcc.scrabble.types.AbstractVariable;
import cl.uchile.dcc.scrabble.types.IVar;
import cl.uchile.dcc.scrabble.types.TypeBinary;
import cl.uchile.dcc.scrabble.types.vString.TypeString;

/**
 * Abstract class that encapsulates a number (integer or double).
 * @author Alvaro Toro Gonzalez
 * */
public abstract class AbstractNumbers extends AbstractVariable implements INumbers, IComponent {

    protected Number value;

    /**
     * Create a object where the type of his value is a integer.
     * @param value Integer number
     * */
    public AbstractNumbers(int value) {
        this.value = value;
    }

    /**
     * Create a object where the type of his value is a double.
     * @param value Double number
     * */
    public AbstractNumbers(double value){
        this.value = value;
    }

    /**
     * Return the value of the object.
     * @return the encapsulated value of the object.
     * */
    public Number getValue(){
        return this.value;
    }

    @Override
    public TypeString toTypeString(){
        return new TypeString(this.toString());
    }

    @Override
    public TypeFloat toTypeFloat() {
        return new TypeFloat(this.getValue().doubleValue());
    }

    @Override
    public TypeInt toTypeInt() {
        return new TypeInt((int) this.getValue());
    }

    @Override
    public String toString(){
        return String.valueOf(this.getValue());
    }

    @Override
    public INumbers addFloat(TypeFloat second){
        double new_value = this.getValue().doubleValue() + second.getValue().doubleValue();
        return new TypeFloat(new_value);
    }

    @Override
    public INumbers subtractFloat(TypeFloat second){
        double new_value = (this.getValue().doubleValue() - second.getValue().doubleValue());
        return new TypeFloat(new_value);
    }

    @Override
    public INumbers multiFloat(TypeFloat second){
        double new_value = (this.getValue().doubleValue() * second.getValue().doubleValue());
        return new TypeFloat(new_value);
    }

    @Override
    public INumbers divFloat(TypeFloat second){
        double new_value = this.getValue().doubleValue() / second.getValue().doubleValue();
        return new TypeFloat(new_value);
    }

    @Override
    public INumbers addInt(TypeInt second){
        double new_value = this.getValue().doubleValue() + second.getValue().intValue();
        return new TypeFloat(new_value);
    }

    @Override
    public INumbers subtractInt(TypeInt second){
        double new_value = this.getValue().doubleValue() - second.getValue().intValue();
        return new TypeFloat(new_value);
    }

    @Override
    public INumbers multiInt(TypeInt second){
        double new_value = this.getValue().doubleValue() * second.getValue().intValue();
        return new TypeFloat(new_value);
    }

    @Override
    public INumbers divInt(TypeInt second){
        double new_value = this.getValue().doubleValue() / second.getValue().intValue();
        return new TypeFloat(new_value);
    }

    @Override
    public INumbers addBinary(TypeBinary second){
        double new_value = this.getValue().doubleValue() + second.toTypeInt().getValue().intValue();
        return new TypeFloat(new_value);
    }

    @Override
    public INumbers subtractBinary(TypeBinary second){
        double new_value = this.getValue().doubleValue() - second.toTypeInt().getValue().intValue();
        return new TypeFloat(new_value);
    }

    @Override
    public INumbers multiBinary(TypeBinary second){
        double new_value = this.getValue().doubleValue() * second.toTypeInt().getValue().intValue();
        return new TypeFloat(new_value);
    }

    @Override
    public INumbers divBinary(TypeBinary second){
        double new_value = this.getValue().doubleValue() / second.toTypeInt().getValue().intValue();
        return new TypeFloat(new_value);
    }

}

