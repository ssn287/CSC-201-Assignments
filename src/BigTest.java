/**
 * BigTest class is used to test run-times for multiplication of large integers
 * @author Shelby Neal
 * Emplid: 6030859
 * Email: ssn287@email.vccs.edu
 * Purpose: Programming Assignment #8
 */
import java.math.BigInteger;
public class BigTest {
	public static BigInteger create(int n, int i) {//creates a random BigInteger with n digits
		if(n == 0) {
			return BigInteger.ZERO;
		}
		else {
			return BigInteger.valueOf((long)Math.random() * 10).multiply(BigInteger.TEN.pow(i)).add(create(--n, ++i));
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
			long start1, time1, start2, time2;
			BigInteger x = create(i, 0);
			BigInteger y = create(i, 0);
			start1 = System.nanoTime();
			BigInteger z1 = karatSuba(x, y);
			time1 = System.nanoTime() - start1;
			System.out.println("Using Karatsuba's Algorithm\n");
			System.out.printf("\t%,d digits: %,d nanoseconds%n\n", i, time1);
			start2 = System.nanoTime();
			BigInteger z2 = x.multiply(y);
			time2 = System.nanoTime() - start2;
			System.out.println("Using BigInteger\n");
			System.out.printf("\t%,d digits: %,d nanoseconds%n\n", i, time2);
			if(z1.compareTo(z2) == 0) {
				System.out.print(x + " * " + y + " = " + z1 + "\n\n");
			}
		}
	}
}