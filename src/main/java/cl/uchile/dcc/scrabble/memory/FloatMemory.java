package cl.uchile.dcc.scrabble.memory;

import cl.uchile.dcc.scrabble.syntax.states.FloatState;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeFloat;
import java.util.Hashtable;

/**
 * This is a class that use the flyweight pattern.
 * Save TypeFloat Objects in a Hashmap to optimize memory.
 * */
public class FloatMemory implements IMemory{

    private Hashtable<Double, TypeFloat> typeFloatCache = new Hashtable<>();
    private final FloatState state = new FloatState();

    /**
     * Create a BinaryMemory object
     * */
    public FloatMemory(){
    }

    /**
     * This method return a TypeFloat object with the given value.
     * This method create and return a new TypeFloat if the given parameter is not a typeFloatCache's key,
     * or return a TypeFloat associated to the given key.
     * @param key the value of the binary.
     * @return a TypeBinary with the given value.
     * */
    public TypeFloat createType(double key) {
        if(typeFloatCache.containsKey(key)){
            return typeFloatCache.get(key);
        }
        else{
            TypeFloat t = new TypeFloat(key);
            t.setState(state);
            typeFloatCache.put(key,t);
            return t;
        }
    }
}
