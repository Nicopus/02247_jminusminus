// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * The AST node for an double literal.
 */

class JLiteralDouble extends JExpression {

    private String text;


    public JLiteralDouble(int line, String text) {
        super(line);
        this.text = text;
    }

    public JExpression analyze(Context context) {
    	type = Type.DOUBLE;
        return this;
    }

    public void codegen(CLEmitter output) {
        double d = Double.parseDouble(text);
        if (d == 0.0) {
        	output.addNoArgInstruction(DCONST_0);
        } else if (d == 1.0) {
        	output.addNoArgInstruction(DCONST_1);
        } else {
        	output.addLDCInstruction(d);
        }
    }

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JLiteralDouble line=\"%d\" type=\"%s\" " + "value=\"%s\"/>\n",
                line(), ((type == null) ? "" : type.toString()), text);
    }

}
