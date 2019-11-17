/**
 * BigTest class is used to test run-times for multiplication of large integers
 * @author Shelby Neal
 * Emplid: 6030859
 * Email: ssn287@email.vccs.edu
 * Purpose: Programming Assignment #8
 */
import java.math.BigInteger;
import java.util.Random;
public class BigTest {
	//getRandom returns a random positive integer within a given range
	public static BigInteger getRandom(BigInteger min, BigInteger max) {
		BigInteger range = max.subtract(min);
		Random r = new Random();
		BigInteger result = new BigInteger(max.bitLength(), r);
		if(result.compareTo(min) == -1) {
			result.add(min);
		}
		if(result.compareTo(range) >= 0) {
			result.mod(range).add(min);
		}
		return result;
	}
	/**
	 * Where n is the number of digits being multiplied,
	 * Karatsuba's multiplication operates in O(n^log2(3)) time (~ O(n^1.585))
	 * Grade-school multiplication operates in O(n^2) time.
	 * Karatsuba's multiplication is asymptotically faster.
	 */
	public static BigInteger karatSuba(BigInteger x, BigInteger y) {
		int m = Math.max(String.valueOf(x).length(), String.valueOf(y).length()); // the maximum number of digits
		// Base case: single digit multiplication
		if(x.compareTo(BigInteger.TEN) == -1 || y.compareTo(BigInteger.TEN) == -1) {
			return x.multiply(y);
		}
		// Recursive case: Karatsuba's algorithm
		else {
			int h = m / 2;
			// x = a + 2^n * b, y = c + 2^n * d
			BigInteger b = x.shiftRight(h);
			BigInteger a = x.subtract(b.shiftLeft(h));
			BigInteger d = y.shiftRight(h);
			BigInteger c = y.subtract(d.shiftLeft(h));
			//compute sub-expressions
			BigInteger z0 = karatSuba(b, d);
			BigInteger z1 = karatSuba(a.add(b), c.add(d));
			BigInteger z2 = karatSuba(a, c);
			return z2.add(z1.subtract(z2).subtract(z0).shiftLeft(h)).add(z0.shiftLeft(2 * h));
		}
	}
	/**
	 * For the BigInteger .multiply() function:
	 * The threshold value for using Karatsuba multiplication is 80.
	 * If the number of ints in both mag arrays are greater than this number,
	 * then Karatsuba multiplication will be used.
	 * The threshold for using squaring code for multiplication is 20.
	 * The assignment requires testing of values between 6-15 digits,
	 * so .multiply() calls .multiplyByInt(int[] x, int y, int sign)
	 * to use the grade school algorithm for multiplication.
	 */
	public static void main(String[] args) {
		for(int i = 6; i < 16; i++) {
			long s1, e1, s2, e2;
			BigInteger x = getRandom(BigInteger.TEN.pow(i - 1), BigInteger.TEN.pow(i).subtract(BigInteger.ONE));
			BigInteger y = getRandom(BigInteger.TEN.pow(i - 1), BigInteger.TEN.pow(i).subtract(BigInteger.ONE));
			s1 = System.nanoTime();
			BigInteger z1 = karatSuba(x, y);
			e1 = System.nanoTime() - s1;
			System.out.println("Using Karatsuba's Algorithm\n");
			System.out.printf("\t%d digits: %,d nanoseconds%n\n", i, e1);
			System.out.printf("\t\t%,d * %,d = %,d\n\n", x, y, z1);
			s2 = System.nanoTime();
			BigInteger z2 = x.multiply(y);
			e2 = System.nanoTime() - s2;
			System.out.println("Using BigInteger\n");
			System.out.printf("\t%d digits: %,d nanoseconds%n\n", i, e2);
			System.out.printf("\t\t%,d * %,d = %,d\n\n", x, y, z2);
		}
	}
}