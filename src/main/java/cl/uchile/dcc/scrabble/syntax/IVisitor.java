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
import cl.uchile.dcc.scrabble.types.vBool.TypeBoolean;
import cl.uchile.dcc.scrabble.types.vNumbers.INumbers;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeFloat;
import cl.uchile.dcc.scrabble.types.vNumbers.TypeInt;
import cl.uchile.dcc.scrabble.types.vString.TypeString;


public interface IVisitor {

    IVar executeAdd(Add op);
    IVar executeSubtract(Subtract op);
    IVar executeMulti(Multi op);
    IVar executeDiv(Div op);
    IVar executeOr(Or op);
    IVar executeAnd(And op);
    IVar executeNegation(Negation value);
    TypeBoolean executeBoolean(TypeBoolean value);
    TypeString executeString(TypeString value);
    TypeInt executeInt(TypeInt value);
    TypeFloat executeFloat(TypeFloat value);
    TypeBinary executeBinary(TypeBinary value);
    IVar executeVar(Var value);
    IVar executeVarSyntax(VarSyntax syntax);
    IVar executeWhile(While op);
    IVar executeIf(If op);
    boolean executeCompare(CompareMinor op);
    boolean executeCompareEquals(CompareEquals op);

}
