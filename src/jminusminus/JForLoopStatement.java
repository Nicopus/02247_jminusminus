
package jminusminus;

import static jminusminus.CLConstants.*;

import java.util.ArrayList;

public class JForLoopStatement extends JStatement {

	protected JVariableDeclaration forInit;
	protected JExpression forCondition;
	protected JStatement forUpdate;
	protected JStatement body;
	
	public JForLoopStatement(int line, JVariableDeclaration forInit, JExpression forCondition, JStatement forUpdate, JStatement body) {
		super(line);
		this.forInit = forInit;
		this.forCondition = forCondition;
		this.forUpdate = forUpdate;
		this.body = body;
	}
	
	public JForLoopStatement(int line) {
		super(line);
	}
	
	
    public JStatement analyze(Context context) {
    	forInit.analyze(context);
    	forCondition.analyze(context);
    	forCondition.type().mustMatchExpected(line, Type.BOOLEAN);
    	forUpdate.analyze(context);
    	body.analyze(context);
    	return this;
    }
    
    public void codegen(CLEmitter output) {
    	
    	forInit.codegen(output);
    	// Need two labels
        String test = output.createLabel();
        String out = output.createLabel();

        output.addLabel(test);
        forCondition.codegen(output, out, false);
        
        body.codegen(output);
        forUpdate.codegen(output);

        // Unconditional jump back up to test
        output.addBranchInstruction(GOTO, test);

        // The label below and outside the loop
        output.addLabel(out);	
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
        forCondition.writeToStdOut(p);
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