package sigblockchain.projectred.keys;

import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class KeyManagerTest {

	@Test
	public void testGenerateKeyPair() {
		var keyPair = KeyManager.generateKeyPair();
		var privateKey = (ECPrivateKeyParameters) keyPair.getPrivate();
		var actualPublicKey = (ECPublicKeyParameters) keyPair.getPublic();

		ECPoint Q = KeyManager.domainParams.getG().multiply(privateKey.getD());
		var expectedPublicKey = new ECPublicKeyParameters(Q, KeyManager.domainParams);
		assertEquals(actualPublicKey.getQ().getRawXCoord().toBigInteger(), expectedPublicKey.getQ().getRawXCoord().toBigInteger());
		assertEquals(actualPublicKey.getQ().getRawYCoord().toBigInteger(), expectedPublicKey.getQ().getRawYCoord().toBigInteger());

	}


	@Test
	public void testEcParameters() {
		// an example of the P-256 public private key pair.
		var expectedPrivateKey = new BigInteger("6548d29d5533079e28d5c0edecb6394ed4c0342bfa475fdc96c7bf978492f23d", 16);
		var expectedPublicKeyX = new BigInteger("fac843c2ccf0246eb00cb90e62dd96c64b669e2b867c0c2aaa835801aeae8295", 16);
		var expectedPublicKeyY = new BigInteger("a797eb07a5d8eef80d5705a7a627c73cf4e683ab98c7245101025be935c4053b", 16);

		var fpMultiplier = new FixedPointCombMultiplier();
		ECPoint Q = fpMultiplier.multiply(KeyManager.domainParams.getG(), expectedPrivateKey);

		var actualPublicKey = new ECPublicKeyParameters(Q, KeyManager.domainParams);
		var actualPrivateKey = new ECPrivateKeyParameters(expectedPrivateKey, KeyManager.domainParams);

		assertEquals(actualPrivateKey.getD(), expectedPrivateKey);
		assertEquals(actualPublicKey.getQ().getRawXCoord().toBigInteger(), expectedPublicKeyX);
		assertEquals(actualPublicKey.getQ().getRawYCoord().toBigInteger(), expectedPublicKeyY);

	}


	@Test
	public void testSigning1() {
		var keyPair = KeyManager.generateKeyPair();
		var privateKey = (ECPrivateKeyParameters) keyPair.getPrivate();
		var publicKey = (ECPublicKeyParameters) keyPair.getPublic();

		var data = "This is some random data".getBytes();
		var signedData = KeyManager.sign(privateKey, data);
		assertTrue(KeyManager.verify(publicKey, signedData, data));
	}

	@Test
	public void testSigning2() {
		var privateKey = new BigInteger("6548d29d5533079e28d5c0edecb6394ed4c0342bfa475fdc96c7bf978492f23d", 16);
		var publicKeyX = new BigInteger("fac843c2ccf0246eb00cb90e62dd96c64b669e2b867c0c2aaa835801aeae8295", 16);
		var publicKeyY = new BigInteger("a797eb07a5d8eef80d5705a7a627c73cf4e683ab98c7245101025be935c4053b", 16);

		var fpMultiplier = new FixedPointCombMultiplier();
		ECPoint Q = fpMultiplier.multiply(KeyManager.domainParams.getG(), privateKey);

		var publicKey = new ECPublicKeyParameters(Q, KeyManager.domainParams);

		//TODO create unit tests. 
	}
}
