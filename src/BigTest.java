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
//	private final static BigInteger D6 = new BigInteger("100000");
//	private final static BigInteger D7 = new BigInteger("1000000");
//	private final static BigInteger D8 = new BigInteger("10000000");
//	private final static BigInteger D9 = new BigInteger("100000000");
//	private final static BigInteger D10 = new BigInteger("1000000000");
//	private final static BigInteger D11 = new BigInteger("10000000000");
//	private final static BigInteger D12 = new BigInteger("100000000000");
//	private final static BigInteger D13 = new BigInteger("1000000000000");
//	private final static BigInteger D14 = new BigInteger("10000000000000");
	private final static BigInteger D15 = new BigInteger("100000000000000");
	public static BigInteger karatSuba(BigInteger x, BigInteger y) {
		int n = Math.max(x.bitLength(), y.bitLength());
		if(x.compareTo(D15) == -1 || y.compareTo(D15) == -1) {
			return x.multiply(y);
		}
		else {
			int half = n / 2;
			// x = a + 2^n * b, y = c + 2^n * d
			BigInteger b = x.shiftRight(half);
			BigInteger a = x.subtract(b.shiftLeft(half));
			BigInteger d = y.shiftRight(half);
			BigInteger c = y.subtract(d.shiftLeft(half));
			BigInteger ac = karatSuba(a, c);
			BigInteger bd = karatSuba(b, d);
			BigInteger abcd = karatSuba(a.add(b), c.add(d));
			return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(n).add(bd.shiftLeft(2 * half)));
		}
	}
	public static void main(String[] args) {
		long start1, time1, start2, time2;
		Random random = new Random();
		for(int bits = 1000; bits <= 10000000; bits *= 10) {
			BigInteger x = new BigInteger(bits, random);
			BigInteger y = new BigInteger(bits, random);
			start1 = System.nanoTime();
			BigInteger z1 = karatSuba(x, y);
			time1 = System.nanoTime() - start1;
			System.out.println("Using Karatsuba's Algorithm\n");
			System.out.printf("\t%,10d bits: %,d nanoseconds%n\n", bits, time1);
			start2 = System.nanoTime();
			BigInteger z2 = x.multiply(y);
			time2 = System.nanoTime() - start2;
			System.out.println("Using BigInteger\n");
			System.out.printf("\t%,10d bits: %,d nanoseconds%n\n", bits, time2);
			if(z1 == z2) {
				System.out.println("Thanks for Waiting!");
			}
		}
	}
}