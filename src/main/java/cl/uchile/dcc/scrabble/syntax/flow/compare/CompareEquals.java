package cl.uchile.dcc.scrabble.syntax.flow.compare;

import cl.uchile.dcc.scrabble.syntax.IVisitor;
import cl.uchile.dcc.scrabble.syntax.operations.IComponent;
import cl.uchile.dcc.scrabble.types.IVar;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeFloat;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeInt;

public class CompareEquals extends AbstractCompare{
    private IComponent der;
    private IComponent izq;

    public CompareEquals(IComponent a, IComponent b){
        this.izq = a;
        this.der = b;
    }

    public IComponent getDer() {
        return der;
    }

    public IComponent getIzq() {
        return izq;
    }

    @Override
    public boolean execute(IVisitor visitor) {
        return visitor.executeCompareEquals(this);
    }

    public int compareElements(IComponent a, IComponent b, IVisitor visitor) {
        return super.compareElements(a,b,visitor);
    }
}
