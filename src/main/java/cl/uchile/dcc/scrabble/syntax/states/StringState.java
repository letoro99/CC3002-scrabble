package cl.uchile.dcc.scrabble.syntax.states;

/**
 * A class that his state represents a TypeString
 * */
public class StringState extends AbstractState{

    /**
     * Create a String State.
     * */
    public StringState(){
        super();
    }

    @Override
    public boolean isTypeString() {
        return true;
    }
}
