package cl.uchile.dcc.scrabble.syntax.states;

/**
 * Abstract class for State Class
 * */
public class AbstractState implements IState {

    AbstractState(){
    }

    @Override
    public boolean isTypeInt() {
        return false;
    }

    @Override
    public boolean isTypeFloat() {
        return false;
    }

    @Override
    public boolean isTypeBinary() {
        return false;
    }

    @Override
    public boolean isTypeString() {
        return false;
    }

    @Override
    public boolean isTypeBoolean() {
        return false;
    }
}
