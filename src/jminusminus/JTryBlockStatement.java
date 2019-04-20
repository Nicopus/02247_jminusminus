package jminusminus;

import java.util.ArrayList;
import static jminusminus.CLConstants.*;

public class JTryBlockStatement extends JStatement {

	private JBlock body;
	private ArrayList<JFormalParameter> cParams;
	private ArrayList<JBlock> cBlock;
	private JBlock fBlock;

	public JTryBlockStatement(int line, JBlock body, ArrayList<JFormalParameter> cParams, ArrayList<JBlock> cBlock,
			JBlock fBlock) {
		super(line);
		this.body = body;
		this.cParams = cParams;
		this.cBlock = cBlock;
		this.fBlock = fBlock;
	}

	public JStatement analyze(Context context) {
		return this;
	}

	public void codegen(CLEmitter output) {

	}

	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JTryBlockStatement line=\"%d\">\n", line());
		p.indentRight();
		p.printf("<TryBlock>\n");
		p.indentRight();
		body.writeToStdOut(p);
		p.indentLeft();
		p.printf("</TryBlock>\n");

		for (int i = 0; i < cParams.size(); i++) {
			p.printf("<CatchParameter>\n");
			p.indentRight();
			cParams.get(i).writeToStdOut(p);
			p.indentLeft();
			p.printf("</CatchParameter>\n");
			p.printf("<CatchBlock>\n");
			p.indentRight();
			cBlock.get(i).writeToStdOut(p);
			p.indentLeft();
			p.printf("</CatchBlock>\n");
		}

		if (fBlock != null) {
			p.printf("<FinallyBlock>\n");
			p.indentRight();
			fBlock.writeToStdOut(p);
			p.indentLeft();
			p.printf("</FinallyBlock>\n");
		}
	}

}
