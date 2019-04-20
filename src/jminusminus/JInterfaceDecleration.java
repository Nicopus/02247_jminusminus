package jminusminus;

import java.util.ArrayList;
import static jminusminus.CLConstants.*;


public class JInterfaceDecleration extends JAST implements JTypeDecl {
	
    /** Interface modifiers. */
    private ArrayList<String> mods;

    /** Interface name. */
    private String name;
    
    private ArrayList<JMember> interfaceBlock;
    
    /** Context for this interface. */
    private ClassContext context;
    
    /** Super interface type. */
    private Type superType;
    
    /** This interface type. */
    private Type thisType;
    
    public JInterfaceDecleration(int line, ArrayList<String> mods, String name,
            Type superType, ArrayList<JMember> interfaceBlock) {
        super(line);
        this.mods = mods;
        this.name = name;
        this.superType = superType;
        this.interfaceBlock = interfaceBlock;
    }
    
    /**
     * Return the class name.
     * 
     * @return the class name.
     */

    public String name() {
        return name;
    }

    /**
     * Return the class' super class type.
     * 
     * @return the super class type.
     */

    public Type superType() {
        return superType;
    }
    
    /**
     * Return the type that this class declaration defines.
     * 
     * @return the defined type.
     */
    
    public Type thisType() {
        return thisType;
    }
    
    public void declareThisType(Context context) {
        String qualifiedName = JAST.compilationUnit.packageName() == "" ? name
                : JAST.compilationUnit.packageName() + "/" + name;
        CLEmitter partial = new CLEmitter(false);
        partial.addClass(mods, qualifiedName, Type.OBJECT.jvmName(), null,
                false); // Object for superClass, just for now
        thisType = Type.typeFor(partial.toClass());
        context.addType(line, thisType);
    }
    
    public void preAnalyze(Context context) {
    	
    }
    
    public JAST analyze(Context context) {
    	return this;
    }
    
    public void codegen(CLEmitter output) {
        // The interface header
        String qualifiedName = JAST.compilationUnit.packageName() == "" ? name
                : JAST.compilationUnit.packageName() + "/" + name;
        output.addClass(mods, qualifiedName, superType.jvmName(), null, false);


        // The members
        for (JMember member : interfaceBlock) {
            ((JAST) member).codegen(output);
        }
    }
    
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JInterfaceDecleration line=\"%d\" name=\"%s\""
                + " super=\"%s\">\n", line(), name, superType.toString());
        p.indentRight();
        if (context != null) {
            context.writeToStdOut(p);
        }
        if (mods != null) {
            p.println("<Modifiers>");
            p.indentRight();
            for (String mod : mods) {
                p.printf("<Modifier name=\"%s\"/>\n", mod);
            }
            p.indentLeft();
            p.println("</Modifiers>");
        }
        if (interfaceBlock != null) {
            p.println("<InterfaceBlock>");
            for (JMember member : interfaceBlock) {
                ((JAST) member).writeToStdOut(p);
            }
            p.println("</InterfaceBlock>");
        }
        p.indentLeft();
        p.println("</JInterfaceDecleration>");
    }
}
