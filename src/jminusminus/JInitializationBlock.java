package jminusminus;

import java.util.ArrayList;

public class JInitializationBlock extends JBlock implements JMember {
	
	private boolean isStatic;
	
	public JInitializationBlock(int line, ArrayList<JStatement> statements, boolean isStatic) {
		super(line, statements);
		this.isStatic = isStatic;
	}
	
	public void preAnalyze(Context context, CLEmitter partial) {
		
	}
	
	public JInitializationBlock analyze(Context context) {
		super.analyze(context);
		return this;
	}
	
    public void codegen(CLEmitter output) {
        super.codegen(output);
    }
    
    public void writeToStdOut(PrettyPrinter p) {
    	String type = isStatic ? "JStaticInitBlock" : "JInstanceInitBlock";
    	
        p.printf("<%s line=\"%d\">\n", type, line());
        if (context != null) {
            p.indentRight();
            context.writeToStdOut(p);
            p.indentLeft();
        }
        for (JStatement statement : statements()) {
            p.indentRight();
            statement.writeToStdOut(p);
            p.indentLeft();
        }
        p.printf("</%s>\n", type);
    }

}
