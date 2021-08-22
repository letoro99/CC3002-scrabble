package cl.uchile.dcc.scrabble.syntax.flow;
import cl.uchile.dcc.scrabble.syntax.IVisitor;
import cl.uchile.dcc.scrabble.syntax.flow.compare.ICompare;
import cl.uchile.dcc.scrabble.syntax.operations.IComponent;
import cl.uchile.dcc.scrabble.syntax.states.IState;
import cl.uchile.dcc.scrabble.types.IVar;
import java.util.ArrayList;

/**
 * This class represents a if-clause for Scrabble.
 * */
public class If implements IComponent{
    private final ICompare cond;
    private final ArrayList<IComponent> codeTrue;
    private final ArrayList<IComponent> codeFalse;

    /**
     * Create a new If Object with a condition and two list of AST for execute when cond
     * is true or not.
     * @param cond a condition, it's usually a Compare Object.
     * @param ifTrue a list of AST to execute when cond is true.
     * @param ifFalse a list of AST to execute when cond is false.
     * */
    public If(ICompare cond, ArrayList<IComponent> ifTrue, ArrayList<IComponent> ifFalse){
        this.cond = cond;
        this.codeFalse = ifFalse;
        this.codeTrue = ifTrue;
    }

    /**
     * This method evaluate the condition cond and execute one of the two list of AST.
     * */
    @Override
    public IVar execute(IVisitor visitor) {
        return visitor.executeIf(this);
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
    public ArrayList<IComponent> getCodeTrue() {
        return this.codeTrue;
    }

    /**
     *
     * */
    public ArrayList<IComponent> getCodeFalse() {
        return this.codeFalse;
    }
}
