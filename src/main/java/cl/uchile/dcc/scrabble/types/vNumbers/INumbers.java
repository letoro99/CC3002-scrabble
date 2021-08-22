package cl.uchile.dcc.scrabble.types.vNumbers;

import cl.uchile.dcc.scrabble.types.TypeBinary;

/**
 * A interface that contains arithmetic operators methods and the trnasform's methods for
 * the different numbers types in Scrabble.
 * */
public interface INumbers {

    /**
     * Method that add the called object's value with the TypeInt second's value.
     * @param second TypeInt object.
     * @return a similar type like the called object with the new value.
     * */
    INumbers addInt(TypeInt second);

    /**
     * Method that subtract the called object's value with the TypeInt second's value.
     * @param second TypeInt object.
     * @return a similar type like the called object with the new value.
     * */
    INumbers subtractInt(TypeInt second);

    /**
     * Method that multiply the called object's value with the TypeInt second's value.
     * @param second TypeInt object.
     * @return a similar type like the called object with the new value.
     * */
    INumbers multiInt(TypeInt second);

    /**
     * Method that divide the called object's value with the TypeInt second's value.
     * @param second TypeInt object.
     * @return a similar type like the called object with the new value.
     * */
    INumbers divInt(TypeInt second);

    /**
     * Method that add the called object's value with the TypeFloat second's value.
     * @param second TypeFloat object.
     * @return Type float with the new value.
     * */
    INumbers addFloat(TypeFloat second);

    /**
     * Method that subtract the called object's value with the TypeFloat second's value.
     * @param second TypeFloat object.
     * @return a Type float with the new value.
     * */
    INumbers subtractFloat(TypeFloat second);

    /**
     * Method that multiply the called object's value with the TypeFloat second's value.
     * @param second TypeFloat object.
     * @return a Type float with the new value.
     * */
    INumbers multiFloat(TypeFloat second);

    /**
     * Method that divide the called object's value with the TypeFloat second's value.
     * @param second TypeFloat object.
     * @return a Type float with the new value.
     * */
    INumbers divFloat(TypeFloat second);

    /**
     * Method that add the called object's value with the TypeFloat second's value.
     * @param second TypeFloat object.
     * @return a similar type like the called object with the new value.
     * */
    INumbers addBinary(TypeBinary second);

    /**
     * Method that subtract the called object's value with the TypeFloat second's value.
     * @param second TypeFloat object.
     * @return a similar type like the called object with the new value.
     * */
    INumbers subtractBinary(TypeBinary second);

    /**
     * Method that multiply the called object's value with the TypeFloat second's value.
     * @param second TypeFloat object.
     * @return a similar type like the called object with the new value.
     * */
    INumbers multiBinary(TypeBinary second);

    /**
     * Method that divide the called object's value with the TypeFloat second's value.
     * @param second TypeFloat object.
     * @return a similar type like the called object with the new value.
     * */
    INumbers divBinary(TypeBinary second);

    /**
     * Return a TypeFloat Object with the value of called object.
     * @return TypeFloat Object.
     */
    TypeFloat toTypeFloat();

    /**
     * Return a TypeInt object with the value of called object.
     * @return TypeInt object.
     */
    TypeInt toTypeInt();

    /**
     * Return a TypeBinary object with the value of called object.
     * @return TypeBinary object.
     */
    TypeBinary toTypeBinary();

    /**
     * This method calls the method add from second with his parameter as itself.
     * */
    INumbers add(INumbers second);


    /**
     * This method calls the method subtract from second with his parameter as itself.
     * */
    INumbers subtract(INumbers second);

    /**
     * This method calls the method multi from second with his parameter as itself.
     * */
    INumbers multiply(INumbers second);

    /**
     * This method calls the method divide from second with his parameter as itself.
     * */
    INumbers divide(INumbers second);

    /**
     * This method compare the value of itself with the value of the TypeInt b
     * */
    int compareToInt(TypeInt b);

    /**
     * This method compare the value of itself with the value of the TypeFloat b
     * */
    int compareToFloat(TypeFloat b);

}
