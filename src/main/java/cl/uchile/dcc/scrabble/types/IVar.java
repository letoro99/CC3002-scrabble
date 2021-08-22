package cl.uchile.dcc.scrabble.types;

import cl.uchile.dcc.scrabble.syntax.states.IState;
import cl.uchile.dcc.scrabble.types.vString.TypeString;

/**
 * A interface that contains the commons methods of all types
 * @author Alvaro Toro Gonzalez
 */
public interface IVar {

    /**
     * Return a TypeString object with the value of called object
     *
     * @return TypeString object
     */
    TypeString toTypeString();

    /**
     * Return a string with the parameter value of called Object
     *
     * @return String of value
     */
    String toString();

    /**
     * Hash function of TypeBoolean
     */
    int hashCode();

    /**
     * Compare a object with a TypeBinary
     *
     * @param o Object to compare
     * @return true if both are equal
     */
    boolean equals(Object o);

    /**
     * This method adds the itself value with the second's value
     * */
    TypeString addTypeString(TypeString second);

    /**
     * This method return the parameter state.
     * */
    IState getState();
}
