package sigblockchain.projectred.client;

import java.math.BigInteger;

public class IntUtils {

	private static BigInteger max64 = new BigInteger("ffffffffffffffff", 16);

	public static boolean isUnsigned64(BigInteger n) {
		return n.compareTo(BigInteger.ZERO) >= 0 && n.compareTo(max64) <= 0;
	}

	public static boolean isUnsigned16(int value){
		return value >= 0 && value <= 65535;
	}
}
