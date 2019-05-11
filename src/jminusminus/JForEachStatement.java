
package jminusminus;

import static jminusminus.CLConstants.*;

import java.beans.Statement;
import java.util.ArrayList;
import java.util.Random;

public class JForEachStatement extends JStatement {
	
	protected JVariableDeclaration forInit;
	protected JExpression forCondition;
	protected JStatement forUpdate;
	protected JStatement body;

	private JFormalParameter param;
	private JExpression expr;
	
	
	public JForEachStatement(int line, JFormalParameter vecl, JExpression expr, JStatement body) {
		super(line);
		this.param = vecl;
		this.expr = expr;
		this.body = body;
		
		//Set variables
		ArrayList<JVariableDeclarator> forParams = new ArrayList<>();
		String randomName = "r" + (new Random().nextInt(10000));
		JVariable indexVar = new JVariable(line, randomName);
		forParams.add(new JVariableDeclarator(line, indexVar.name(), Type.INT, new JLiteralInt(line, "0")));
		this.forInit = new JVariableDeclaration(line, null, forParams);
		
		ArrayList<JVariableDeclarator> blockParams = new ArrayList<>();
		JVariable arrayVar = new JVariable(line, param.name());
		blockParams.add(new JVariableDeclarator(line, param.name(), param.type(), new JArrayExpression(line, expr, indexVar)));
		
		if (body instanceof JBlock == false) {
			ArrayList<JStatement> statements = new ArrayList<>();
			statements.add(body);
			this.body = new JBlock(line, statements);
		}
		((JBlock) this.body).statements.add(0, new JVariableDeclaration(line, null, blockParams));
		 
		
		//Set condition
		this.forCondition = new JLessOp(line, indexVar, new JFieldSelection(line, expr, "length"));
		
		//Set update
		JExpression incExpr = new JPostIncrementOp(line, indexVar);
		incExpr.isStatementExpression = true;
		this.forUpdate = new JStatementExpression(line, incExpr);
		
	}
	
    public JStatement analyze(Context context) {
    	param.analyze(context);
    	expr.analyze(context);
    	
    	forInit.analyze(context);
    	forCondition.analyze(context);
    	forCondition.type().mustMatchExpected(line, Type.BOOLEAN);
    	forUpdate.analyze(context);
    	body.analyze(context);

//    	
//    	if (!Type.ITERABLE.isJavaAssignableFrom(expr.type()) && !expr.type().isArray()) {
//    		JAST.compilationUnit.reportSemanticError(line,
//                    "Local variable must be of type array or itterable: \"%s\"", expr.type().toString());
//        }
//    	
//    	param.type().mustMatchExpected(line, expr.type().componentType());
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
        //element.codegen(output);

        // Unconditional jump back up to test
        output.addBranchInstruction(GOTO, test);

        // The label below and outside the loop
        output.addLabel(out);
    }
    
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JForEachStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<VariableDeclarator>\n");
        p.indentRight();
        param.writeToStdOut(p);
        p.indentLeft();
        p.printf("</VariableDeclarator>\n");
        p.printf("<Identifier>\n");
        p.indentRight();
        p.printf("<Identifier name=\"%s\"/>\n", expr.toString());
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