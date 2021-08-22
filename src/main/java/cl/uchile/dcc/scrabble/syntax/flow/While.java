package cl.uchile.dcc.scrabble.syntax.flow;

import cl.uchile.dcc.scrabble.syntax.IVisitor;
import cl.uchile.dcc.scrabble.syntax.flow.compare.ICompare;
import cl.uchile.dcc.scrabble.syntax.operations.IComponent;
import cl.uchile.dcc.scrabble.syntax.states.IState;
import cl.uchile.dcc.scrabble.types.IVar;
import java.util.ArrayList;

/**
 * This class represents a while-clause for Scrabble.
 * */
public class While implements IComponent{
    private ICompare cond;
    private final ArrayList<IComponent> code;

    /**
     * Create a new If Object with a condition and a list of AST for execute when cond
     * is true.
     * @param cond a condition, it's usually a Compare Object.
     * @param whileTrue a list of AST to execute when cond is true.
     * */
    public While(ICompare cond, ArrayList<IComponent> whileTrue){
        this.cond = cond;
        this.code = whileTrue;
    }

    /**
     * This method evaluate the condition cond and execute the two list of AST. Similar as a while-clause.
     * */
    @Override
    public IVar execute(IVisitor visitor) {
        return visitor.executeWhile(this);
    }

    @Override
    public void setState(IState state) {
    }

    @Override
    public IState getState() {
        return null;
    }

    /**
     *
     * */
    public ICompare getCond() {
        return this.cond;
    }

    /**
     *
     * */
    public ArrayList<IComponent> getCode() {
        return this.code;
    }
}
