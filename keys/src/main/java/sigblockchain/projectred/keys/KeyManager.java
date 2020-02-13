package sigblockchain.projectred.keys;

import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;

import java.security.SecureRandom;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;


public class KeyManager {

	public static final X9ECParameters ecp = SECNamedCurves.getByName("secp256r1");
	public static final ECDomainParameters domainParams = new ECDomainParameters(ecp.getCurve(),
		ecp.getG(), ecp.getN(), ecp.getH(), ecp.getSeed());

	/**
	 * Will generate a public/private key pair according to the NIST P-256 standards.
	 *
	 * @return a AsymmetricCipherKeyPair object
	 */
	public static AsymmetricCipherKeyPair generateKeyPair() {
		ECKeyGenerationParameters keyGenParams = new ECKeyGenerationParameters(domainParams, new SecureRandom());
		ECKeyPairGenerator generator = new ECKeyPairGenerator();
		generator.init(keyGenParams);
		return generator.generateKeyPair();
	}

	/**
	 * Signs data using the Elliptical curve private key, using the ECDSA SHA 256 signing algorithm.
	 *
	 * @param privateKey The private key to be used for signing
	 * @param data       The data that is to be signed
	 * @return a byte array that represents the signature
	 */
	public static byte[] sign(ECPrivateKeyParameters privateKey, byte[] data) {
		//TODO implement
		return null;
	}

	/**
	 * Verifies that the signature is valid according to the Elliptical curve public key.
	 *
	 * @param publicKey The public key corresponding to the private key that signed the data
	 * @param signature The ECDSA signature
	 * @param data      The data that was signed
	 * @return true if the public key verifies the signature, false it not
	 */
	public static boolean verify(ECPublicKeyParameters publicKey, byte[] signature, byte[] data) {
		// TODO implement
		return false;
	}
}
