package cl.uchile.dcc.scrabble.types.vBool;

import cl.uchile.dcc.scrabble.types.TypeBinary;

/**
 * A interface that contains the method's signature for the logical operators.
 * */
public interface IBooleanOperators {

    /**
     * @return a similar Type object the negation of the called object's value
     * */
    IBooleanOperators negation();

    /**
     * Double dispatch method that evaluate the called object with
     * the TypeBoolean object "second" with the logical operator
     * conjunction.
     * @param second a TypeBoolean Object.
     * @return a TypeBoolean or TypeBinary object depending who call the method.
     * */
    IBooleanOperators andBoolean(TypeBoolean second);

    /**
     * Double dispatch method that evaluate the called object with
     * the TypeBoolean object "second" with the logical operator
     * disjunction.
     * @param second a TypeBoolean Object.
     * @return a TypeBoolean or TypeBinary object depending who call the method.
     * */
    IBooleanOperators orBoolean(TypeBoolean second);

    /**
     * Double dispatch method that evaluate the called object with
     * the TypeBoolean object "second" with the logical operator
     * conjunction.
     * @param second a TypeBoolean Object.
     * @return a TypeBoolean or TypeBinary object depending who call the method.
     * */
    IBooleanOperators andBinary(TypeBinary second);

    /**
     * Double dispatch method that evaluate the called object with
     * the TypeBinary object "second" with the logical operator
     * disjunction.
     * @param second a TypeBoolean Object.
     * @return TypeBinary object with a new value.
     * */
    IBooleanOperators orBinary(TypeBinary second);

    /**
     * This method create the OR value with itself and the given parameter.
     * @param second a object that implements the IBooleanOperators interface.
     * */
    IBooleanOperators or(IBooleanOperators second);

    /**
     * This method create the AND value with itself and the given parameter.
     * @param second a object that implements the IBooleanOperators interface.
     * */
    IBooleanOperators and(IBooleanOperators second);

}
