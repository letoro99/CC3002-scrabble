package cl.uchile.dcc.scrabble.types;

import cl.uchile.dcc.scrabble.syntax.IVisitor;
import cl.uchile.dcc.scrabble.syntax.operations.IComponent;
import cl.uchile.dcc.scrabble.syntax.states.IState;

/**
 * This class is a Object to change the value of a Var object with the result of the AST syntax.
 * */
public class VarSyntax implements IComponent {
    private Var variable;
    private IComponent syntax;
    private IState state;

    /**
     * Create a new VarSyntax Object.
     * @param variable a Var Object.
     * @param syntax a AST.
     * */
    public VarSyntax(Var variable, IComponent syntax){
        this.variable = variable;
        this.syntax = syntax;
    }

    /**
     * This method call the method execute on the AST syntax and change the value of the Var
     * Object with the one that return in the execute of the AST.
     * */
    @Override
    public IVar execute(IVisitor visitor) {
        return visitor.executeVarSyntax(this);
    }

    @Override
    public void setState(IState state) {
        this.state = state;
    }

    @Override
    public IState getState() {
        return this.state;
    }

    /**
     *
     * */
    public IComponent getSyntax() {
        return this.syntax;
    }

    /**
     *
     * */
    public Var getVariable() {
        return this.variable;
    }
}
