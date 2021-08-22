package cl.uchile.dcc.scrabble.syntax.states;

/**
 * A class that his state represents a TypeBinary
 * */
public class BinaryState extends AbstractState{

    /**
     * Create a Binary State.
     * */
    public BinaryState(){
        super();
    }

    @Override
    public boolean isTypeBinary() {
        return true;
    }
}
