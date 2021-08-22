package cl.uchile.dcc.scrabble.memory;

import cl.uchile.dcc.scrabble.syntax.states.BinaryState;
import cl.uchile.dcc.scrabble.types.TypeBinary;
import java.util.Hashtable;

/**
 * This is a class that uses the flyweight pattern.
 * This class saves TypeBinary objects in a Hashtable to optimize memory.
 * */
public class BinaryMemory implements IMemory{

    private Hashtable<String, TypeBinary> typeBinaryCache = new Hashtable<>();
    private final BinaryState state = new BinaryState();

    /**
     * Create a BinaryMemory object
     * */
    public BinaryMemory(){
    }

    /**
     * This method returns a TypeBinary object with the given value.
     * This method creates and return a new TypeBinary if the given parameter is not a typeBinaryCache's key,
     * or returns a TypeBinary associated to the given key.
     * @param binary the value of the binary.
     * @return a TypeBinary with the given value.
     * */
    public TypeBinary createType(String binary) {
        String key;
        String byteLeft = String.valueOf(binary.charAt(0));
        int len = binary.length();
        if(len < 32){
            String ladoIzq = "";
            while(len < 32){
                len++;
                ladoIzq = ladoIzq.concat(byteLeft);
            }
            key = ladoIzq.concat(binary);
        }
        else key = binary;
        if(typeBinaryCache.containsKey(key)){
            return typeBinaryCache.get(key);
        }
        else{
            TypeBinary t = new TypeBinary(key);
            t.setState(state);
            typeBinaryCache.put(key,t);
            return t;
        }
    }
}
