package cl.uchile.dcc.scrabble.syntax;

import cl.uchile.dcc.scrabble.syntax.flow.If;
import cl.uchile.dcc.scrabble.syntax.flow.While;
import cl.uchile.dcc.scrabble.syntax.flow.compare.CompareEquals;
import cl.uchile.dcc.scrabble.syntax.flow.compare.CompareMinor;
import cl.uchile.dcc.scrabble.syntax.operations.*;
import cl.uchile.dcc.scrabble.types.IVar;
import cl.uchile.dcc.scrabble.types.TypeBinary;
import cl.uchile.dcc.scrabble.types.Var;
import cl.uchile.dcc.scrabble.types.VarSyntax;
import cl.uchile.dcc.scrabble.types.vBool.IBooleanOperators;
import cl.uchile.dcc.scrabble.types.vBool.TypeBoolean;
import cl.uchile.dcc.scrabble.types.vNumbers.INumbers;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeFloat;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeInt;
import cl.uchile.dcc.scrabble.types.vString.TypeString;

public class ExecuteVisitor implements IVisitor{

    @Override
    public IVar executeAdd(Add op) {
        IVar izq = op.getChild(0).execute(this);
        IVar der = op.getChild(1).execute(this);
        if(izq == null || der == null){
            return null;
        }
        if(izq.getState().isTypeString()){
            return ((TypeString) izq).add(der);
        }
        else if(izq.getState().isTypeFloat() && !(der.getState().isTypeString() || der.getState().isTypeBoolean())){
            return (IVar) ((INumbers) der).add((INumbers) izq);
        }
        else if(izq.getState().isTypeInt() && !(der.getState().isTypeString() || der.getState().isTypeBoolean())){
            return (IVar) ((INumbers) der).add((INumbers) izq);
        }
        else if(izq.getState().isTypeBinary() && (der.getState().isTypeInt() || der.getState().isTypeBinary())){
            return (IVar) ((INumbers) der).add((INumbers) izq);
        }
        else{
            return null;
        }
    }

    @Override
    public IVar executeSubtract(Subtract op) {
        IVar izq = op.getChild(0).execute(this);
        IVar der = op.getChild(1).execute(this);
        if(izq == null || der == null){
            return null;
        }
        if(izq.getState().isTypeFloat() && !(der.getState().isTypeString() || der.getState().isTypeBoolean())){
            return (IVar) ((INumbers) der).subtract((INumbers) izq);
        }
        else if(izq.getState().isTypeInt() && !(der.getState().isTypeString() || der.getState().isTypeBoolean())){
            return (IVar) ((INumbers) der).subtract((INumbers) izq);
        }
        else if(izq.getState().isTypeBinary() && (der.getState().isTypeInt() || der.getState().isTypeBinary())){
            return (IVar) ((INumbers) der).subtract((INumbers) izq);
        }
        else{
            return null;
        }
    }

    @Override
    public IVar executeMulti(Multi op) {
        IVar izq = op.getChild(0).execute(this);
        IVar der = op.getChild(1).execute(this);
        if(izq == null || der == null){
            return null;
        }
        if(izq.getState().isTypeFloat() && !(der.getState().isTypeString() || der.getState().isTypeBoolean())){
            return (IVar) ((INumbers) der).multiply((INumbers) izq);
        }
        else if(izq.getState().isTypeInt() && !(der.getState().isTypeString() || der.getState().isTypeBoolean())){
            return (IVar) ((INumbers) der).multiply((INumbers) izq);
        }
        else if(izq.getState().isTypeBinary() && (der.getState().isTypeInt() || der.getState().isTypeBinary())){
            return (IVar) ((INumbers) der).multiply((INumbers) izq);
        }
        else{
            return null;
        }
    }

    @Override
    public IVar executeDiv(Div op) {
        IVar izq = op.getChild(0).execute(this);
        IVar der = op.getChild(1).execute(this);
        if(izq == null || der == null){
            return null;
        }
        if(izq.getState().isTypeFloat() && !(der.getState().isTypeString() || der.getState().isTypeBoolean())){
            return (IVar) ((INumbers) der).divide((INumbers) izq);
        }
        else if(izq.getState().isTypeInt() && !(der.getState().isTypeString() || der.getState().isTypeBoolean())){
            return (IVar) ((INumbers) der).divide((INumbers) izq);
        }
        else if(izq.getState().isTypeBinary() && (der.getState().isTypeInt() || der.getState().isTypeBinary())){
            return (IVar) ((INumbers) der).divide((INumbers) izq);
        }
        else{
            return null;
        }
    }

    @Override
    public IVar executeOr(Or op) {
        IVar izq = op.getChild(0).execute(this);
        IVar der = op.getChild(1).execute(this);
        if(izq == null || der == null){
            return null;
        }
        if(izq.getState().isTypeBinary() && (der.getState().isTypeBoolean() || der.getState().isTypeBinary())){
            return (IVar) ((IBooleanOperators) der).or((IBooleanOperators) izq);
        }
        else if(izq.getState().isTypeBoolean() && (der.getState().isTypeBoolean() || der.getState().isTypeBinary())){
            return (IVar) ((IBooleanOperators) der).or((IBooleanOperators) izq);
        }
        else{
            return null;
        }
    }

    @Override
    public IVar executeAnd(And op) {
        IVar izq = op.getChild(0).execute(this);
        IVar der = op.getChild(1).execute(this);
        if(izq == null || der == null){
            return null;
        }
        if(izq.getState().isTypeBinary() && (der.getState().isTypeBoolean() || der.getState().isTypeBinary())){
            return (IVar) ((IBooleanOperators) der).and((IBooleanOperators) izq);
        }
        else if(izq.getState().isTypeBoolean() && (der.getState().isTypeBoolean() || der.getState().isTypeBinary())){
            return (IVar) ((IBooleanOperators) der).and((IBooleanOperators) izq);
        }
        else{
            return null;
        }
    }

    @Override
    public IVar executeNegation(Negation op) {
        IVar value = op.execute(this);
        if(value == null){
            return null;
        }
        if(op.getState().isTypeBoolean() || op.getState().isTypeBinary()){
            return (IVar) ((IBooleanOperators) value).negation();
        }
        else{
            return null;
        }
    }

    @Override
    public TypeBoolean executeBoolean(TypeBoolean value) {
        return value;
    }

    @Override
    public TypeString executeString(TypeString value) {
        return value;
    }

    @Override
    public TypeInt executeInt(TypeInt value) {
        return value;
    }

    @Override
    public TypeFloat executeFloat(TypeFloat value) {
        return value;
    }

    @Override
    public TypeBinary executeBinary(TypeBinary value) {
        return value;
    }

    @Override
    public IVar executeVar(Var value) {
        return value.getValue().execute(this);
    }

    @Override
    public IVar executeVarSyntax(VarSyntax op) {
        IVar result = op.getSyntax().execute(this);
        op.setState(result.getState());
        op.getVariable().setValue((IComponent) result);
        return result;
    }

    @Override
    public IVar executeWhile(While op) {
        while (op.getCond().execute(this)) {
            for (IComponent line : op.getCode()) line.execute(this);
        }
        return null;
    }

    @Override
    public IVar executeIf(If op) {
        if(op.getCond().execute(this)){
            for (IComponent iComponent : op.getCodeTrue()) iComponent.execute(this);
        }
        else{
            for(IComponent iComponent : op.getCodeFalse()) iComponent.execute(this);
        }
        return null;
    }

    @Override
    public boolean executeCompare(CompareMinor op) {
        int result = op.compareElements(op.getIzq(), op.getDer(), this);
        return (result == -1);
    }

    @Override
    public boolean executeCompareEquals(CompareEquals op) {
        int result = op.compareElements(op.getIzq(), op.getDer(), this);
        return (result == 0);    }
}
