package cl.uchile.dcc.scrabble.memory;

import cl.uchile.dcc.scrabble.syntax.states.IntState;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeInt;
import java.util.Hashtable;

/**
 * This is a class that uses the flyweight pattern.
 * This class saves TypeInt objects in a Hashtable to optimize memory.
 * */
public class IntMemory implements IMemory {

    private Hashtable<Integer,TypeInt> typeIntCache = new Hashtable<>();
    private final IntState state = new IntState();

    /**
     * Create a IntMemory object
     * */
    public IntMemory(){
    }

    /**
     * This method returns a TypeInt object with the given value.
     * This method creates and return a new TypeInt if the given parameter is not a typeIntCache's key,
     * or returns a TypeInt associated to the given key.
     * @param key the value of the binary.
     * @return a TypeInt with the given value.
     * */
    public TypeInt createType(Integer key) {
        if(typeIntCache.containsKey(key)){
            return typeIntCache.get(key);
        }
        else {
            TypeInt t = new TypeInt(key);
            t.setState(state);
            typeIntCache.put(key, t);
            return t;
        }

    }

}
