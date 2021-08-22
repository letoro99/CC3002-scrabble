package cl.uchile.dcc.scrabble.syntax.flow.compare;

import cl.uchile.dcc.scrabble.syntax.IVisitor;
import cl.uchile.dcc.scrabble.syntax.operations.IComponent;
import cl.uchile.dcc.scrabble.types.IVar;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeFloat;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeInt;

public abstract class AbstractCompare implements ICompare {
    protected int compareElements(IComponent a, IComponent b, IVisitor visitor) {
        int result = 2;
            IVar izq = a.execute(visitor);
            IVar der = b.execute(visitor);
            if(checkNumberType(izq) || checkNumberType(der)) return result;
            if(izq.getState().isTypeFloat()){
                result = ((TypeFloat) izq).compareTo(der);
            }
            else if(izq.getState().isTypeInt()){
                result = ((TypeInt) izq).compareTo(der);
            }
            return result;
    }

    protected boolean checkNumberType(IVar a) throws Error{
        if(a.getState().isTypeBinary() || a.getState().isTypeBoolean()){
            return true;
        }
        return false;
    }
}
