package cl.uchile.dcc.scrabble.memory;

import cl.uchile.dcc.scrabble.syntax.states.StringState;
import cl.uchile.dcc.scrabble.types.vString.TypeString;
import java.util.Hashtable;

/**
 * This is a class that uses the flyweight pattern.
 * This class saves TypeString Objects in a Hashtable to optimize memory.
 * */
public class StringMemory implements IMemory {

    private Hashtable<String, TypeString> typeStringCache = new Hashtable<>();
    private final StringState state = new StringState();

    /**
     * Create a StringMemory object
     * */
    public StringMemory(){

    }

    /**
     * This method returns a TypeString object with the given value.
     * This method creates and return a new TypeString if the given parameter is not a typeStringCache's key,
     * or returns a TypeString associated to the given key.
     * @param key the value of the binary.
     * @return a TypeString with the given value.
     * */
    public TypeString createType(String key) {
        if(typeStringCache.containsKey(key)){
            return typeStringCache.get(key);
        }
        else{
            TypeString t = new TypeString(key);
            t.setState(state);
            typeStringCache.put(key,t);
            return t;

        }
    }
}
