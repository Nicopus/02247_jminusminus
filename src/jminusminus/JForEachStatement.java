
package jminusminus;

import static jminusminus.CLConstants.*;

public class JForEachStatement extends JStatement {

	private JVariableDeclarator vdecl;
	private TypeName iden;
	private JStatement body;

	public JForEachStatement(int line, JVariableDeclarator vecl, TypeName iden, JStatement body) {
		super(line);
		this.vdecl = vecl;
		this.iden = iden;
		this.body = body;
	}
	
    public JStatement analyze(Context context) {
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
        p.printf("</Statement>\n");
        p.indentRight();
        body.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Statement>\n"); 
        p.indentLeft();
        p.printf("<JForEachStatement>\n");
        
    }
}