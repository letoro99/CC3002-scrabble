package cl.uchile.dcc.scrabble.syntax.operations;

import cl.uchile.dcc.scrabble.syntax.IVisitor;
import cl.uchile.dcc.scrabble.syntax.states.IState;
import cl.uchile.dcc.scrabble.types.IVar;
import cl.uchile.dcc.scrabble.types.vBool.IBooleanOperators;
import cl.uchile.dcc.scrabble.types.vString.TypeString;
import java.util.ArrayList;

/**
 * This class represents the logical operator OR
 * */
public class Or implements IComponent {
    ArrayList<IComponent> components = new ArrayList<>();

    /**
     * Create a Or object with two component.
     * @param a a object that implements the IComponent interface.
     * @param b another object that implements the IComponent interface.
     * */
    public Or(IComponent a, IComponent b){
        this.addNode(a);
        this.addNode(b);
    }

    /**
     * This methods adds a new IComponent to the list of components.
     * */
    public void addNode(IComponent node){
        if(components.size() < 2){
            components.add(node);
        }
    }

    @Override
    public IState getState() {return null;}

    @Override
    public void setState(IState state) {}

    @Override
    public IVar execute(IVisitor visitor){
        return visitor.executeOr(this);
    }

    /**
     * This method returns the i-th IComponent from the component's list
     * @param i a integer.
     * @return the i-th value of components.
     * */
    public IComponent getChild(int i){
        return components.get(i);
    }

    /**
     * This method calls execute() and changes his value to a TypeString object.
     * */
    public TypeString toTypeString(IVisitor visitor){
        return this.execute(visitor).toTypeString();
    }
}
