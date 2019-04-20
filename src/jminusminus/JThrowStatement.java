package jminusminus;


public class JThrowStatement extends JStatement {
	
	private JExpression expr;
	
	public JThrowStatement(int line, JExpression expr) {
		super(line);
		this.expr = expr;
	}
	
    public JStatement analyze(Context context) {
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
