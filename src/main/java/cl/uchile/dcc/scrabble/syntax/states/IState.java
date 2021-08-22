package cl.uchile.dcc.scrabble.syntax.states;

/**
 * A interface with commons methods for State classes.
 * */
public interface IState {

    /**
     * This method returns a boolean value, depending if its a IntState or not.
     * */
    boolean isTypeInt();

    /**
     * This method returns a boolean value, depending if its a FloatState or not.
     * */
    boolean isTypeFloat();

    /**
     * This method returns a boolean value, depending if its a BinaryState or not.
     * */
    boolean isTypeBinary();

    /**
     * This method returns a boolean value, depending if its a StringState or not.
     * */
    boolean isTypeString();

    /**
     * This method returns a boolean value, depending if its a BooleanState or not.
     * */
    boolean isTypeBoolean();

}
