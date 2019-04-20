
package jminusminus;

import static jminusminus.CLConstants.*;

public class JForLoopStatement extends JStatement {

	private JVariableDeclaration forInit;
	private JExpression forExpr;
	private JStatement forUpdate;
	private JStatement body;

	public JForLoopStatement(int line, JVariableDeclaration forInit, JExpression forExpr, JStatement forUpdate, JStatement body) {
		super(line);
		this.forInit = forInit;
		this.forExpr = forExpr;
		this.forUpdate = forUpdate;
		this.body = body;
	}
	
    public JStatement analyze(Context context) {
    	return this;
    }
    
    public void codegen(CLEmitter output) {
    	
    }
    
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JForLoopStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<ForInit>\n");
        p.indentRight();
        forInit.writeToStdOut(p);
        p.indentLeft();
        p.printf("</ForInit>\n");
        p.printf("<ForExpr>\n");
        p.indentRight();
        forExpr.writeToStdOut(p);
        p.indentLeft();
        p.printf("</ForExpr>\n");
        p.printf("<ForUpdate>\n");
        p.indentRight();
        forUpdate.writeToStdOut(p);
        p.indentLeft();
        p.printf("</ForUpdate>\n");
        p.printf("</Budy>\n");
        p.indentRight();
        body.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Budy>\n"); 
        p.indentLeft();
        p.printf("<JForLoopStatement>\n");
    }
}