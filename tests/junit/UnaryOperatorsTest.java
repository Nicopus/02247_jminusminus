package junit;

import junit.framework.TestCase;
import pass.UnaryOperators;

public class UnaryOperatorsTest extends TestCase {
	
	private UnaryOperators unaryOperators;
	
	protected void setUp() throws Exception { 
		super.setUp();
		unaryOperators = new UnaryOperators(); 
	}
	
	protected void tearDown() throws Exception { 
		super.tearDown();
	}
	
	public void testRemainder () { 
		this.assertEquals(unaryOperators.plus(10), 10); 
		this.assertEquals(unaryOperators.bitwiseCompliment(10), -11);
		this.assertEquals(unaryOperators.postIncrement(10), 10);
		this.assertEquals(unaryOperators.preDecrement(10), 9);
	}
}
