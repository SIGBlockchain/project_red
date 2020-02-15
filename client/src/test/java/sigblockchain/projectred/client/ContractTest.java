package sigblockchain.projectred.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


import java.math.BigInteger;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.junit.jupiter.api.Test;
import sigblockchain.projectred.keys.KeyManager;

public class ContractTest {

	private static final String tooBig = new BigInteger("10000000000000002", 16).toString(10); //exceed 64 bit int
	private static final String negative = "-1";
	private static final ECPublicKeyParameters publicKey = (ECPublicKeyParameters) KeyManager.generateKeyPair().getPublic();


	@Test
	public void testContractConstructor1() {
		Contract contract = new Contract(3, publicKey, "Signature".getBytes(), "Recipient Public Key Hash".getBytes(), "8589934593", "8589934593");

		//test all the getter functions too
		assertEquals(3, contract.getVersion());
		assertEquals(publicKey, contract.getPublicKey());
		assertEquals("Signature".getBytes(), contract.getSignature());
		assertEquals("Recipient Public Key Hash".getBytes(), contract.getRecipitentPublicKeyHash());
		assertEquals(new BigInteger("8589934593", 10), contract.getValue());
		assertEquals(new BigInteger("8589934593", 10), contract.getStateNonce());
	}

	@Test
	public void testContractConstructorOverflowValue() {
		//expecting an exception since value is bigger than maximum unsigned 64 bit integer
		var contract = new Contract(3, publicKey, "Signature".getBytes(), "Recipient Public Key Hash".getBytes(), tooBig, "4395");
		fail("Expected an exception due to value being greater than max 64 bit value");
	}

	@Test
	public void testContractConstructorOverflowNonce() {
		//expecting an exception since state nonce is bigger than maximum unsigned 64 bit integer
		var contract = new Contract(3, publicKey, "Signature".getBytes(), "Recipient Public Key Hash".getBytes(), "4395", tooBig);
		fail("Expected an exception due to state nonce being greater than max 64 bit value");
	}

	@Test
	public void testContractConstructorOverflowSigLen() {
		//expecting an exception since the signature length is bigger than 255 (max 8 bit value)
		var contract = new Contract(3, publicKey, "r".repeat(257).getBytes(), "Recipient Public Key Hash".getBytes(), "4397", "4395");
		fail("Expected an exception due to signature length being greater than max 8 bit value");
	}

	@Test
	public void testContractConstructorOverflowVersion() {
		//expecting an exception since version is bigger than maximum unsigned 16 bit integer
		var contract = new Contract(65537, publicKey, "Signature".getBytes(), "Recipient Public Key Hash".getBytes(), tooBig, "4395");
		fail("Expected an exception due to version being greater than max 16 bit value");
	}

	@Test
	public void testContractConstructorNegativeValue() {
		//expecting an exception since value is bigger than maximum unsigned 64 bit integer
		var contract = new Contract(3, publicKey, "Signature".getBytes(), "Recipient Public Key Hash".getBytes(), negative, "4395");
		fail("Expected an exception due to value being negative");
	}

	@Test
	public void testContractConstructorNegativeNonce() {
		//expecting an exception since state nonce is bigger than maximum unsigned 64 bit integer
		var contract = new Contract(3, publicKey, "Signature".getBytes(), "Recipient Public Key Hash".getBytes(), "4395", negative);
		fail("Expected an exception due to state nonce being negative");
	}


	@Test
	public void testContractConstructorNegativeVersion() {
		//expecting an exception since version is bigger than maximum unsigned 16 bit integer
		var contract = new Contract(65537, publicKey, "Signature".getBytes(), "Recipient Public Key Hash".getBytes(), tooBig, "4395");
		fail("Expected an exception due to version being greater than max 16 bit value");
	}

	@Test
	public void testContractValidSignatureLength() {
		var contract = new Contract(3, publicKey, "Signature".getBytes(), "Recipient Public Key Hash".getBytes(), "2", "3");
		fail("Should throw an exception because signature length does not match up with Signature")
	}

	@Test
	public void testContractSetters() {
		var contract = new Contract(3, publicKey, "Signature".getBytes(), "Recipient Public Key Hash".getBytes(), "2", "3");

		contract.setVersion(4);
		assertEquals(contract.getVersion(), 4);

		var newSig = "new signature".getBytes();
		contract.setSignature(newSig);
		assertEquals(newSig, contract.getSignature());

		var newRecipPkHash = "new pk hash".getBytes();
		contract.getRecipientPublicKeyHash(newRecipPkHash);
		assertEquals(newRecipPkHash, contract.getRecipitentPublicKeyHash());

		contract.setValue("3243");
		assertEquals(contract.getValue(), new BigInteger("3243", 10));

		contract.setStateNone("43454");
		assertEquals(contract.getStateNonce(), new BigInteger("43454", 10));

	}

	@Test
	public void testContractIsSigned() {
		var contract = new Contract(3, publicKey, "Signature".getBytes(), "Recipient Public Key Hash".getBytes(), "2", "3");
		assertTrue(contract.isSigned());
		contract.setSignature(null);
		assertFalse(contract.isSigned());
	}
}
