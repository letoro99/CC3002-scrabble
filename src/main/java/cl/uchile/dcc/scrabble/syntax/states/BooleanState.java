package cl.uchile.dcc.scrabble.syntax.states;

/**
 * A class that his state represents a TypeBoolean.
 * */
public class BooleanState extends AbstractState{

    /**
     * Create a Boolean State.
     * */
    public BooleanState(){
        super();
    }

    @Override
    public boolean isTypeBoolean() {
        return true;
    }
}
