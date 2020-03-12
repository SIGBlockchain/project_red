package sigblockchain.projectred.client;

import java.math.BigInteger;

public class IntUtils {

	private static BigInteger max64 = new BigInteger("ffffffffffffffff", 16);

	public static boolean validateUnsigned64(BigInteger n) {
		return BigInteger.ZERO.compareTo(n) >= 0 && n.compareTo(max64) <= 0;
	}
}
