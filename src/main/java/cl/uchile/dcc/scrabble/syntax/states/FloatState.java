package cl.uchile.dcc.scrabble.syntax.states;

/**
 * A class that his state represents a TypeFloat
 * */
public class FloatState extends AbstractState{

    /**
     * Create a Float State.
     * */
    public FloatState(){
        super();
    }

    @Override
    public boolean isTypeFloat() {
        return true;
    }
    
}
