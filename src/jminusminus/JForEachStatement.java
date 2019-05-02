
package jminusminus;

import static jminusminus.CLConstants.*;

public class JForEachStatement extends JStatement {

	private JVariableDeclarator vdecl;
	private JExpression iden;
	private JStatement body;

	public JForEachStatement(int line, JVariableDeclarator vecl, JExpression iden, JStatement body) {
		super(line);
		this.vdecl = vecl;
		this.iden = iden;
		this.body = body;
	}
	
    public JStatement analyze(Context context) {
    	vdecl.analyze(context);
    	iden.analyze(context);
    	body.analyze(context);
    	
    	if (!Type.ITERABLE.isJavaAssignableFrom(iden.type()) && !iden.type().isArray()) {
    		JAST.compilationUnit.reportSemanticError(line,
                    "Local variable must be of type array or itterable: \"%s\"", iden.type().toString());
        }
    	
    	vdecl.type().mustMatchExpected(line, iden.type().componentType());
    	return this;
    }
    
    public void codegen(CLEmitter output) {
    	
    }
    
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JForEachStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<VariableDeclarator>\n");
        p.indentRight();
        vdecl.writeToStdOut(p);
        p.indentLeft();
        p.printf("</VariableDeclarator>\n");
        p.printf("<Identifier>\n");
        p.indentRight();
        p.printf("<Identifier name=\"%s\"/>\n", iden.toString());
        p.indentLeft();
        p.printf("</Identifier>\n");
        p.printf("</Body>\n");
        p.indentRight();
        body.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Body>\n"); 
        p.indentLeft();
        p.printf("<JForEachStatement>\n");
        
    }
}