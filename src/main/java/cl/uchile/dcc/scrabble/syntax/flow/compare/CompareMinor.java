package cl.uchile.dcc.scrabble.syntax.flow.compare;

import cl.uchile.dcc.scrabble.syntax.IVisitor;
import cl.uchile.dcc.scrabble.syntax.operations.IComponent;

public class CompareMinor extends AbstractCompare{
    private IComponent der;
    private IComponent izq;

    public CompareMinor(IComponent a, IComponent b){
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
        return visitor.executeCompare(this);
    }

    public int compareElements(IComponent a, IComponent b, IVisitor visitor) {
        return super.compareElements(a, b, visitor);
    }
}
