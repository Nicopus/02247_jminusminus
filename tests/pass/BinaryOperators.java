package pass;

public class BinaryOperators {
	
	public int division(int x, int y) {
		return x / y;
	}
	
	public int remainder(int x, int y) {
		return x % y;
	}
	
	public int signedLeftShift(int x, int y) {
		return x << y;
	}
	
	public int signedRightShift(int x, int y) {
		return x >> y;	
	}
	
	public int unsignedRightShift(int x, int y) {
		return x >>> y;
	}
	
	public int bitwiseAnd(int x, int y) {
		return x & y;
	}
	
	public int bitwiseOr(int x, int y) {
		return x | y;
	}
	
	public int bitwiseXor(int x, int y) {
		return x ^ y;
	}
	
	public int minusAssign(int x, int y) {
		return x -= y;
	}
	
	public int divisionAssign(int x, int y) {
		return x /= y;
	}
	
	public int multiplyAssign(int x, int y) {
		return x *= y;
	}
	
	public int remainderAssign(int x, int y) {
		return x %= y;
	}
}
