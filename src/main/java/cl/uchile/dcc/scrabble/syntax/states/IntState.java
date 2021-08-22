package cl.uchile.dcc.scrabble.syntax.states;

/**
 * A class that his state represents a TypeInt
 * */
public class IntState extends AbstractState{

    /**
     * Create a Int State.
     * */
    public IntState(){
        super();
    }

    @Override
    public boolean isTypeInt() {
        return true;
    }
}
