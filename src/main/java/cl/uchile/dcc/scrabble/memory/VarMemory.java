package cl.uchile.dcc.scrabble.memory;

import cl.uchile.dcc.scrabble.syntax.operations.IComponent;
import cl.uchile.dcc.scrabble.types.Var;
import java.util.Hashtable;

/**
 * This is a class that uses the flyweight pattern.
 * This class saves Var objects in a Hashtable to optimize memory.
 * */
public class VarMemory implements IMemory {
    private Hashtable<String, Var> memory = new Hashtable<>();

    /**
     * Create a VarMemory object
     * */
    public VarMemory(){
    }

    /**
     * This method returns a Var object with the given values.
     * This method creates and return a new Var if the given name is not a memory's key,
     * or returns a Var associated to the given key.
     * @param name the variable's name.
     * @param value the value of the variable.
     * @return a TypeBoolean with the given value.
     * */
    public Var createVar(String name, IComponent value){
        Var variable = new Var(name,value);
        if(memory.containsKey(name)){
            memory.replace(name,variable);
        }
        else{
            memory.put(name,variable);
        }
        return memory.get(name);
    }

    /**
     * This method returns a Var object if exist a Var object associated with the string key.
     * @param key a string with the key of a Var Object.
     * @return a Var object.
     * */
    public Var returnVar(String key){
        return memory.getOrDefault(key, null);
    }

}
