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
	private ArrayList<Type> superTypes;

	/** This interface type. */
	private Type thisType;

	public JInterfaceDecleration(int line, ArrayList<String> mods, String name, ArrayList<Type> superTypes,
			ArrayList<JMember> interfaceBlock) {
		super(line);
		this.mods = mods;
		//this.mods.add("abstract");
		this.name = name;
		this.superTypes = superTypes;
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
		return null;
	}

	public ArrayList<Type> superTypes() {
		return superTypes;
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
		partial.addClass(mods, qualifiedName, Type.NULLTYPE.jvmName(), null, 
				false); // Object for superClass, just for now
		thisType = Type.typeFor(partial.toClass());
		context.addType(line, thisType);
	}

	public void preAnalyze(Context context) {
		// Construct a class context
		this.context = new ClassContext(this, context, true);
		
		//Add implicit abstract modifier
		if (!mods.contains("abstract")) {
			mods.add("abstract");
		}

		for (Type type : superTypes) {
			type = type.resolve(this.context);
			thisType.checkAccess(line, type);
			if (type.matchesExpected(Type.NULLTYPE) || !type.isInterface()) {
				JAST.compilationUnit.reportSemanticError(line, "Cannot extend a non interface type: %s",
						type.toString());
			}
		}

		// Create the (partial) class
		CLEmitter partial = new CLEmitter(false);

		// Add the class header to the partial class
		String superTypesjvmNames = (superTypes.size() > 0) ? "" : null;
		for (Type type : superTypes) {
			superTypesjvmNames = superTypesjvmNames + type.jvmName();
		}

		String qualifiedName = JAST.compilationUnit.packageName() == "" ? name
				: JAST.compilationUnit.packageName() + "/" + name;
		partial.addClass(mods, qualifiedName, superTypesjvmNames, null, false);

		// Pre-analyze the members and add them to the partial
		// class
		for (JMember member : interfaceBlock) {
			member.preAnalyze(this.context, partial);
		}

		// Get the Class rep for the (partial) class and make it
		// the
		// representation for this type
		Type id = this.context.lookupType(name);
		if (id != null && !JAST.compilationUnit.errorHasOccurred()) {
			id.setClassRep(partial.toClass());
		}
	}

	public JAST analyze(Context context) {
		return this;
	}

	public void codegen(CLEmitter output) {
		// The interface header
		String superTypesjvmNames = (superTypes.size() > 0) ? "" : null;
		for (Type type : superTypes) {
			superTypesjvmNames = superTypesjvmNames + type.jvmName();
		}
		String qualifiedName = JAST.compilationUnit.packageName() == "" ? name
				: JAST.compilationUnit.packageName() + "/" + name;
		output.addClass(mods, qualifiedName, superTypesjvmNames, null, false);

		// The members
		for (JMember member : interfaceBlock) {
			((JAST) member).codegen(output);
		}
	}

	public void writeToStdOut(PrettyPrinter p) {
		String superTypesString = "";
		for (Type type : superTypes) {
			superTypesString = superTypesString + type.toString();
		}

		p.printf("<JInterfaceDecleration line=\"%d\" name=\"%s\"" + " super=\"%s\">\n", line(), name, superTypesString);
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
	
	public void codegenPartialImplicitAbstract() {
		
		
		
	}
}