package cl.uchile.dcc.scrabble.syntax;

import cl.uchile.dcc.scrabble.memory.*;
import cl.uchile.dcc.scrabble.syntax.flow.compare.AbstractCompare;
import cl.uchile.dcc.scrabble.syntax.flow.If;
import cl.uchile.dcc.scrabble.syntax.flow.While;
import cl.uchile.dcc.scrabble.syntax.flow.compare.CompareEquals;
import cl.uchile.dcc.scrabble.syntax.flow.compare.CompareMinor;
import cl.uchile.dcc.scrabble.syntax.flow.compare.ICompare;
import cl.uchile.dcc.scrabble.syntax.operations.*;
import cl.uchile.dcc.scrabble.types.IVar;
import cl.uchile.dcc.scrabble.types.TypeBinary;
import cl.uchile.dcc.scrabble.types.Var;
import cl.uchile.dcc.scrabble.types.VarSyntax;
import cl.uchile.dcc.scrabble.types.vBool.TypeBoolean;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeFloat;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeInt;
import cl.uchile.dcc.scrabble.types.vString.TypeString;
import java.util.ArrayList;
/**
 * This class is a controller who has different AST to use and evaluate.
 * */
public class ATSController {
    private ArrayList<IComponent> syntax = new ArrayList<>();
    private BinaryMemory binaryFly = new BinaryMemory();
    private IntMemory intFly = new IntMemory();
    private FloatMemory floatFly = new FloatMemory();
    private StringMemory stringFly = new StringMemory();
    private BooleanMemory booleanFly = new BooleanMemory();
    private VarMemory varMemory = new VarMemory();
    private ExecuteVisitor visitor = new ExecuteVisitor();

    /**
     * Create the ASTController with empty values in the memory and the list of AST.
     * */
    public ATSController(){ }

    /**
     * Add an operation to the list of AST
     * @param operation an AST
     * */
    public void addOperation(IComponent operation){
        syntax.add(operation);
    }

    /**
     * Delete the i operation of the list of AST
     * @param i a integer
     * */
    public void deleteOperation(int i){
        if(i < syntax.size()){
            syntax.remove(i);
        }
    }

    /**
     *
     * */
    public ExecuteVisitor getVisitor() {
        return this.visitor;
    }

    /**
     * Clear the ArrayList syntax
     * */
    public void clear(){
        this.syntax.clear();
    }

    /**
     * This method evaluates and returns the value of the i AST from the list of AST
     * */
    public IVar executeAST(int i){
        if(i < syntax.size()){
            IVar temp = this.syntax.get(i).execute(this.visitor);
            if(temp == null) return null;
            if(temp.getState().isTypeBinary()){
                String value = ((TypeBinary) temp).getValue();
                return binaryFly.createType(value);
            }
            else if(temp.getState().isTypeInt()){
                int value = (int) ((TypeInt) temp).getValue();
                return intFly.createType(value);
            }
            else if(temp.getState().isTypeFloat()){
                double value = (double) ((TypeFloat) temp).getValue();
                return floatFly.createType(value);
            }
            else if(temp.getState().isTypeString()){
                String value = ((TypeString) temp).getValue();
                return stringFly.createType(value);
            }
            else if(temp.getState().isTypeBoolean()){
                boolean value = ((TypeBoolean) temp).getValue();
                return booleanFly.createType(value);
            }
            else{
                return null;
            }
        }
        else {
            return null;
        }
    }

    /**
     * This method run the method execute from the interface IComponent on syntax's elements.
     * */
    public void executeAllCode(){
        for(int i = 0; i < this.syntax.size(); i++){
            this.executeAST(i);
        }
    }

    /**
     * Returns a TypeBinary object with the given value.
     * This method uses the object binaryFly to create a new TypeBinary.
     * */
    public TypeBinary createBinary(String value) {
        return binaryFly.createType(value);
    }

    /**
     * Returns a TypeBoolean object with the given value
     * This method uses the object binaryFly to create a new TypeBinary.
     * */
    public TypeBoolean createBoolean(boolean value) {
        return booleanFly.createType(value);
    }

    /**
     * Returns a TypeBoolean object with the given value
     * This method uses the object floatFly to create a new TypeFloat.
     * */
    public TypeFloat createFloat(double value) {
        return floatFly.createType(value);
    }

    /**
     * Returns a TypeBoolean object with the given value
     * This method uses the object intFly to create a new TypeInt.
     * */
    public TypeInt createInt(int value) {
        return intFly.createType(value);
    }

    /**
     * Returns a TypeBoolean object with the given value
     * this method uses the object stringFly to create a new TypeString.
     * */
    public TypeString createString(String value) {
        return stringFly.createType(value);
    }

    /**
     * This methods return sa Var object with a TypeInt object with the given key, creating and saving it in the Var Memory object
     * @param key name of the variable.
     * @param value the value of the variable.
     * @return a Var object.
     * */
    public Var createVariableInt(String key, TypeInt value){
        return varMemory.createVar(key,value);
    }

    /**
     * This methods returns a Var object with a TypeFloat object with the given key, creating and saving it in the Var Memory object
     * @param key name of the variable.
     * @param value the value of the variable.
     * @return a Var object.
     * */
    public Var createVariableFloat(String key, TypeFloat value){
        return varMemory.createVar(key,value);
    }

    /**
     * This methods returns a Var object with a TypeString object with the given key, creating and saving it in the Var Memory object
     * @param key name of the variable.
     * @param value the value of the variable.
     * @return a Var object.
     * */
    public Var createVariableString(String key, TypeString value){
        return varMemory.createVar(key,value);
    }

    /**
     * This methods returns a Var object with a TypeBinary object with the given key, creating and saving it in the Var Memory object
     * @param key name of the variable.
     * @param value the value of the variable.
     * @return a Var object.
     * */
    public Var createVariableBinary(String key, TypeBinary value){
        return varMemory.createVar(key,value);
    }

    /**
     * This methods returns a Var object with a TypeBoolean object with the given key, creating and saving it in the Var Memory object
     * @param key name of the variable.
     * @param value the value of the variable.
     * @return a Var object.
     * */
    public Var createVariableBoolean(String key, TypeBoolean value){
        return varMemory.createVar(key,value);
    }


    /**
     * This methods creates a VariableSyntax object with the given key and AST.
     * @param key the name of the variable to change his value.
     * @param value a AST with a new value for the key to execute.
     * @return a VarSyntax object.
     * */
    public VarSyntax createVariableSyntax(String key, IComponent value){
        return new VarSyntax(varMemory.returnVar(key), value);
    }


    /**
     * This methods returns a Var object with the given key.
     * @param key the name of the var.
     * @return a Var.
     * */
    public Var returnVariable(String key){
        return varMemory.returnVar(key);
    }

    /**
     * This methods creates a ArrayList with different AST to execute in a If or While Object.
     * @return a empty ArrayList.
     * */
    public ArrayList<IComponent> createCodeBlock(){
        return new ArrayList<>();
    }

    /**
     * This method adds a AST into the given ArrayList.
     * */
    public void addCodeLine(ArrayList<IComponent> Block, IComponent line){
        Block.add(line);
    }

    /**
     * This methods creates a new If object with the given values.
     * @param cond the condition for the if condition.
     * @param trueBlock a list of AST to execute when cond is true.
     * @param falseBlock a list of AST to execute when cond is false.
     * */
    public If createIf(ICompare cond, ArrayList<IComponent> trueBlock, ArrayList<IComponent> falseBlock){
        return new If(cond, trueBlock, falseBlock);
    }

    /**
     * This method creates a new While Object with the given values.
     * @param cond the condition for the while condition.
     * @param codeBlock a list of AST to execute when the condition is true.
     * */
    public While createWhile(ICompare cond, ArrayList<IComponent> codeBlock){
        return new While(cond, codeBlock);
    }

    /**
     * This method creates a new CompareMinor Object with the given values.
     * @param a a AST
     * @param b a ATS
     * @return a CompareMinor object.
     * */
    public CompareMinor createCompareMinor(IComponent a, IComponent b){
        return new CompareMinor(a,b);
    }

    /**
     * This method creates a new CompareEquals Object with the given values.
     * @param a a AST
     * @param b a ATS
     * @return a CompareEquals object.
     * */
    public CompareEquals createCompareEquals(IComponent a, IComponent b){
        return new CompareEquals(a,b);
    }

    /**
     * This method returns a new Add object.
     * @param a a object that implements IComponent interface.
     * @param b another object that implements IComponent interface.
     * */
    protected Add addOp(IComponent a, IComponent b){
        return new Add(a, b);
    }

    /**
     * This method returns a new Subtract object.
     * @param a a object that implements IComponent interface.
     * @param b another object that implements IComponent interface.
     * */
    protected Subtract subtractOp(IComponent a, IComponent b) {
        return new Subtract(a,b);
    }

    /**
     * This method returns a new Multi object.
     * @param a a object that implements IComponent interface.
     * @param b another object that implements IComponent interface.
     * */
    protected Multi multiOp(IComponent a, IComponent b) {
        return new Multi(a,b);
    }

    /**
     * This method returns a new Div object.
     * @param a a object that implements IComponent interface.
     * @param b another object that implements IComponent interface.
     * */
    protected Div divOp(IComponent a, IComponent b) {
        return new Div(a,b);
    }

    /**
     * This method returns a new Or object.
     * @param a a object that implements IComponent interface.
     * @param b another object that implements IComponent interface.
     * */
    protected Or orOp(IComponent a, IComponent b){
        return new Or(a,b);
    }

    /**
     * This method returns a new And object.
     * @param a a object that implements IComponent interface.
     * @param b another object that implements IComponent interface.
     * */
    protected And andOp(IComponent a, IComponent b){
        return new And(a,b);
    }

    /**
     * This method returns a new Negation object.
     * @param a a object that implements IComponent interface.
     * */
    protected Negation negationOp(IComponent a){
        return new Negation(a);
    }

}
