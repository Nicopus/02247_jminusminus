package jminusminus;

import static jminusminus.CLConstants.*;

public class JConditionalExpression extends JExpression {
	
	private JExpression cond;
	private JExpression thenBranch;
	private JExpression elseBranch;
	
	public JConditionalExpression(int line, JExpression cond, JExpression thenBranch, JExpression elseBranch) {
		super(line);
		this.cond = cond;
		this.thenBranch = thenBranch;
		this.elseBranch = elseBranch;
	}
	
	public void writeToStdOut(PrettyPrinter p) {
		
		p.printf("<JConditionalExpression line=\"%d\" type=\"%s\">\n", line(),
				((type == null) ? "" : type.toString()));
		p.indentRight();
		p.printf("<Condition>\n");
		p.indentRight();
		cond.writeToStdOut(p);
		p.indentLeft();
		p.printf("</Condition>\n");
		p.printf("<ThenBranch>\n");
		p.indentRight();
		thenBranch.writeToStdOut(p);
		p.indentLeft();
		p.printf("</ThenBranch>\n");
		p.printf("<ElseBranch>\n");
		p.indentRight();
		elseBranch.writeToStdOut(p);
		p.indentLeft();
		p.printf("</ElseBranch>\n");
		p.indentLeft();
		p.printf("</JConditionalExpression>\n");
	}
	
	public JExpression analyze(Context context) {

		
		return this;
	}
	
	public void codegen(CLEmitter output) {
	
		
	}
}
