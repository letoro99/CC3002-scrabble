package cl.uchile.dcc.scrabble.syntax.operations;

import cl.uchile.dcc.scrabble.syntax.IVisitor;
import cl.uchile.dcc.scrabble.syntax.states.IState;
import cl.uchile.dcc.scrabble.types.IVar;
import cl.uchile.dcc.scrabble.types.vString.TypeString;

/**
 * This interface has the commons method for every object for the AST.
 * */
public interface IComponent {

    /**
     * This method returns a Object that implements IVar.
     * Returns itself if it's a Scrabble's types, or returns a new Scrabble's types from the
     * evaluation of a Operator object (like Add, Subtract, Multi, ...).
     * @return  a Object that implements IVar.
     * */
    IVar execute(IVisitor visitor);

    /**
     * This method sets a state into the variable state of the object.
     * */
    void setState(IState state);

    /**
     * This methods return the type of the object.
     * @return a object that implements the interface IState.
     * */
    IState getState();


}
