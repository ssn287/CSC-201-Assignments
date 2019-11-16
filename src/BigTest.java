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
	public static BigInteger getRandom(BigInteger min, BigInteger max) {
		BigInteger range = max.subtract(min);
		Random r = new Random();
		int length = max.bitLength();
		BigInteger result = new BigInteger(length, r);
		if(result.compareTo(min) == -1) {
			result.add(min);
		}
		if(result.compareTo(range) >= 0) {
			result.mod(range).add(min);
		}
		return result;
	}
	/**
	 * @param n = number of digits
	 * @param i = power of 10
	 * @return random number in range (1 - 9) generated for each digit n
	 */
	public static BigInteger create(int n, int i) {
		if(n == 0) {
			return BigInteger.ZERO;
		}
		else {
			return BigInteger.valueOf(1 + (long)(Math.random() * 9)).multiply(BigInteger.TEN.pow(i)).add(create(--n, ++i));
		}
	}
	public static BigInteger karatSuba(BigInteger x, BigInteger y) {
		int m = Math.max(String.valueOf(x).length(), String.valueOf(y).length());
		if(x.compareTo(BigInteger.TEN) == -1 || y.compareTo(BigInteger.TEN) == -1) {
			return x.multiply(y);
		}
		else {
			int h = m / 2;
			// x = a + 2^n * b, y = c + 2^n * d
			BigInteger b = x.shiftRight(h);
			BigInteger a = x.subtract(b.shiftLeft(h));
			BigInteger d = y.shiftRight(h);
			BigInteger c = y.subtract(d.shiftLeft(h));
			BigInteger ac = karatSuba(a, c);
			BigInteger bd = karatSuba(b, d);
			BigInteger abcd = karatSuba(a.add(b), c.add(d));
			return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(h).add(bd.shiftLeft(2 * h)));
		}
	}
	public static void main(String[] args) {
		for(int i = 6; i < 16; i++) {
			long s1, e1, s2, e2;
			BigInteger x = getRandom(BigInteger.TEN.pow(i - 1), BigInteger.TEN.pow(i).subtract(BigInteger.ONE));
			BigInteger y = create(i, 0);
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