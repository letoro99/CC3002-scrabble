package cl.uchile.dcc.scrabble.types;

import cl.uchile.dcc.scrabble.syntax.states.IState;
import cl.uchile.dcc.scrabble.types.vString.TypeString;
import org.jetbrains.annotations.NotNull;

/**
 * Abstract class with the minimums methods for a Scrabble type
 * @author Alvaro Toro Gonzalez
 * */
public abstract class AbstractVariable implements IVar {

    @Override
    public TypeString toTypeString(){
        return new TypeString(this.toString());
    }

    @Override
    public TypeString addTypeString(TypeString second){
        return new TypeString(second.getValue()+this);
    }

    @Override
    public abstract IState getState();


}
