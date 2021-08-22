package cl.uchile.dcc.scrabble.memory;

import cl.uchile.dcc.scrabble.syntax.states.BooleanState;
import cl.uchile.dcc.scrabble.types.vBool.TypeBoolean;
import java.util.Hashtable;

/**
 * This is a class that uses the flyweight pattern.
 * This class saves TypeBoolean Objects in a Hashtable to optimize memory.
 * */
public class BooleanMemory implements IMemory{

    private Hashtable<Boolean, TypeBoolean> typeBooleanCache = new Hashtable<>();
    private final BooleanState state = new BooleanState();

    /**
     * Create a BooleanMemory object
     * */
    public BooleanMemory(){
    }

    /**
     * This method returns a TypeBoolean object with the given value.
     * This method creates and return a new TypeBoolean if the given parameter is not a typeBooleanCache's key,
     * or returns a TypeBoolean associated to the given key.
     * @param key the value of the binary.
     * @return a TypeBoolean with the given value.
     * */
    public TypeBoolean createType(boolean key) {
        if(typeBooleanCache.containsKey(key)){
            return typeBooleanCache.get(key);
        }
        else{
            TypeBoolean t = new TypeBoolean(key);
            t.setState(state);
            typeBooleanCache.put(key,t);
            return t;
        }
    }
}
