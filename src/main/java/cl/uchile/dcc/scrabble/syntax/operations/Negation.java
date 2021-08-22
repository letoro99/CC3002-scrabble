package cl.uchile.dcc.scrabble.syntax.operations;

import cl.uchile.dcc.scrabble.syntax.IVisitor;
import cl.uchile.dcc.scrabble.syntax.states.IState;
import cl.uchile.dcc.scrabble.syntax.states.StringState;
import cl.uchile.dcc.scrabble.types.IVar;
import cl.uchile.dcc.scrabble.types.vBool.IBooleanOperators;
import cl.uchile.dcc.scrabble.types.vString.TypeString;

/**
 * This method represents the negation from logical operators.
 * */
public class Negation implements IComponent{
    private IComponent Node;

    /**
     * Create a Add object with a component.
     * @param a a object that implements the IComponent interface.
     * */
    public Negation(IComponent a) {
        this.Node = a;
    }

    @Override
    public IVar execute(IVisitor visitor) {
        return visitor.executeNegation(this);
    }

    @Override
    public void setState(IState state) {}

    @Override
    public IState getState() {
        return null;
    }

    /**
     * This methods set a new value to Node.
     * @param node a object that implements the IComponent interface.
     * */
    public void setNode(IComponent node) {
        this.Node = node;
    }

    /**
     * This method returns the value of Node.
     * @return the i-th value of components.
     * */
    public IComponent getChild() {
        return this.Node;
    }

    /**
     * This method calls execute() and changes his value to a TypeString object.
     * */
    public TypeString toTypeString(IVisitor visitor){
        this.setState(new StringState());
        return this.execute(visitor).toTypeString();
    }
}
