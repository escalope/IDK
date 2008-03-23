/*
 * Created on 04/11/2006
 */
package javacc.parser.ast.stmt;

import javacc.parser.ast.visitor.GenericVisitor;
import javacc.parser.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class LabeledStmt extends Statement {

    public final String label;

    public final Statement stmt;

    public LabeledStmt(String label, Statement stmt) {
        this.label = label;
        this.stmt = stmt;
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }
}
