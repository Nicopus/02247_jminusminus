package jminusminus;

import java.util.ArrayList;
import static jminusminus.CLConstants.*;

public class JTryBlockStatement extends JStatement {

	private JBlock body;
	private ArrayList<JFormalParameter> cParams;
	private ArrayList<JBlock> cBlocks;
	private JBlock fBlock;
	private LocalContext context;

	public JTryBlockStatement(int line, JBlock body, ArrayList<JFormalParameter> cParams, ArrayList<JBlock> cBlocks,
			JBlock fBlock) {
		super(line);
		this.body = body;
		this.cParams = cParams;
		this.cBlocks = cBlocks;
		this.fBlock = fBlock;
	}

	public JStatement analyze(Context context) {
		this.context = new LocalContext(context);
		body.analyze(this.context);
		
		//Check if exception is allready been caught
		for (JFormalParameter param1 : cParams) {
			for (JFormalParameter param2 : cParams) {
				if (param1 != param2 && param1.type() == param2.type()) {
					JAST.compilationUnit.reportSemanticError(line,
		                    "%s has allready been caught", param2.type().toString());
				}
			}
		}
		
		
		for (int i = 0; i < cParams.size(); i++) {
			LocalVariableDefn defn = new LocalVariableDefn(cParams.get(i).type(), this.context.nextOffset());
			defn.initialize();
			this.context.addEntry(cParams.get(i).line(), cParams.get(i).name(), defn);
			
			//Check for missing catch block
			if (cBlocks.size() < (i + 1)) {
				JAST.compilationUnit.reportSemanticError(line,
	                    "Missing catch block");
			} 
//			else {
//				cBlocks.get(i).analyze(this.context);
//			}
		}
		
		fBlock.analyze(this.context);
		
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
			cBlocks.get(i).writeToStdOut(p);
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
