package cl.uchile.dcc.scrabble.types;

import cl.uchile.dcc.scrabble.syntax.IVisitor;
import cl.uchile.dcc.scrabble.syntax.operations.IComponent;
import cl.uchile.dcc.scrabble.syntax.states.IState;
import cl.uchile.dcc.scrabble.types.vString.TypeString;

/**
 * This class represents a variable, with his name as a string and his value as a type Object
 * (ex. x = 2 -> Var("x",TypeInt(2)).
 * */
public class Var implements IComponent, IVar{
    private String name;
    private IComponent value;
    private IState state;

    /**
     * Create a new Object with the given values.
     * @param name the name of the variable.
     * @param value the Type Object.
     * */
    public Var(String name, IComponent value){
        this.name = name;
        this.value = value;
    }

    /**
     * This method set a new value to the value parameter.
     * @param value the new Var's value.
     * */
    public void setValue(IComponent value){
        this.value = value;
    }


    /**
     * This method return the value parameter.
     * @return a IComponent
     * */
    public IComponent getValue(){
        return this.value;
    }

    /**
     * This method calls the parameter value's execute method, returning a IVar that represent the value
     * of the parameter value.
     * */
    public IVar execute(IVisitor visitor){
        return visitor.executeVar(this);
    }

    @Override
    public void setState(IState state) {
        this.state = state;
    }

    @Override
    public TypeString toTypeString() {
        return null;
    }

    @Override
    public TypeString addTypeString(TypeString second) {
        return null;
    }

    @Override
    public IState getState() {
        return this.state;
    }

}
