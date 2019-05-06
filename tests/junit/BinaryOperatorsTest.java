package junit;

import junit.framework.TestCase;
import pass.BinaryOperators;

public class BinaryOperatorsTest extends TestCase {
	
	private BinaryOperators binaryOperators;
	
	protected void setUp() throws Exception { 
		super.setUp();
		binaryOperators = new BinaryOperators(); 
	}
	
	protected void tearDown() throws Exception { 
		super.tearDown();
	}
	
	public void testRemainder () { 
		this.assertEquals(binaryOperators.division(10, 5), 2); 
		this.assertEquals(binaryOperators.remainder(10, 3), 1);
		this.assertEquals(binaryOperators.signedLeftShift(10, 1), 20);
		this.assertEquals(binaryOperators.signedRightShift(10, 1), 5);
		this.assertEquals(binaryOperators.unsignedRightShift(-10, 28), 15);
		this.assertEquals(binaryOperators.bitwiseAnd(10, 12), 8);
		this.assertEquals(binaryOperators.bitwiseOr(4, 8), 12);
		this.assertEquals(binaryOperators.bitwiseXor(4, 12), 8);
		this.assertEquals(binaryOperators.minusAssign(10, 2), 8);
		this.assertEquals(binaryOperators.divisionAssign(10, 2), 5);
		this.assertEquals(binaryOperators.multiplyAssign(10, 2), 20);
		this.assertEquals(binaryOperators.remainderAssign(10, 3), 1);
	}

}
