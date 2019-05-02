package jminusminus;

import java.util.Set;

public class JThrowStatement extends JStatement {
	
	private JExpression expr;
	
	public JThrowStatement(int line, JExpression expr) {
		super(line);
		this.expr = expr;
	}
	
    public JStatement analyze(Context context) {
    	MethodContext methodContext = context.methodContext();
    	
    	expr.analyze(context);
    	System.out.println(expr.type().toString());
    	Set<Type> throwTypes = methodContext.methodThrowTypes();
    	expr.type().mustMatchOneOf(line(), throwTypes.toArray(new Type[throwTypes.size()]));
    	
    	return this;
    }
    
    public void codegen(CLEmitter output) {
    	
    }
    
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JThrowStatement line=\"%d\">\n", line());
        p.indentRight();
        expr.writeToStdOut(p);
        p.indentLeft();
        p.printf("<JThrowStatement>\n");
    }
}
