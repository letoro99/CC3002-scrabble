package cl.uchile.dcc.scrabble.syntax.operations;

import cl.uchile.dcc.scrabble.syntax.IVisitor;
import cl.uchile.dcc.scrabble.syntax.states.*;
import cl.uchile.dcc.scrabble.types.IVar;
import cl.uchile.dcc.scrabble.types.TypeBinary;
import cl.uchile.dcc.scrabble.types.vNumbers.INumbers;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeFloat;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeInt;
import cl.uchile.dcc.scrabble.types.vString.TypeString;

import java.util.ArrayList;

/**
 * This class represents the arithmetic operation division.
 * */
public class Div implements IComponent {
    ArrayList<IComponent> components = new ArrayList<>();

    /**
     * Create a Div object with two component.
     * @param a a object that implements the IComponent interface.
     * @param b another object that implements the IComponent interface.
     * */
    public Div(IComponent a, IComponent b){
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
        return visitor.executeDiv(this);
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
     * This method calls execute() and changes his value to a TypeInt object.
     * */
    public TypeInt toTypeInt(IVisitor visitor){
        this.setState(new IntState());
        return ((INumbers) this.execute(visitor)).toTypeInt();
    }

    /**
     * This method calls execute() and changes his value to a TypeFloat object.
     * */
    public TypeFloat toTypeFloat(IVisitor visitor){
        this.setState(new FloatState());
        return ((INumbers) this.execute(visitor)).toTypeFloat();
    }

    /**
     * This method calls execute() and changes his value to a TypeBinary object.
     * */
    public TypeBinary toTypeBinary(IVisitor visitor){
        this.setState(new BinaryState());
        return ((INumbers) this.execute(visitor)).toTypeBinary();
    }

    /**
     * This method calls execute() and changes his value to a TypeString object.
     * */
    public TypeString toTypeString(IVisitor visitor){
        this.setState(new StringState());
        return this.execute(visitor).toTypeString();
    }

}
