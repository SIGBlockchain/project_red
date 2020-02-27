package sigblockchain.projectred.keys;

import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.util.Properties;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;


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
	public static byte[] sign(ECPrivateKeyParameters privateKey, byte[] data)   {
//		1. Generate R and S (which are the two components of an ECDSA signature).
//		Use the  org.bouncycastle.crypto.signers.ECDSASigner object to do so.
		ECDSASigner signer = new ECDSASigner();
		signer.init(true, privateKey);
		BigInteger[] components = signer.generateSignature(data); //

//		2. Encode R and S into the DER format
		try {
			return encodeDerSignature(components);
		} catch (IOException e) {
			return null;
		}
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

		BigInteger[] rAndS = decodeDerSignature(signature);
		if (rAndS == null)
			return false;
		var r = rAndS[0];
		var s = rAndS[1];

		ECDSASigner signer = new ECDSASigner();
		signer.init(false, publicKey);

		 return signer.verifySignature(data, r, s);
	}

	private static BigInteger[] decodeDerSignature(byte[] signature){
		//first we have to decode the the DER encoded byte array
		BigInteger r, s;
		ASN1InputStream decoder = null;
		try {
			// BouncyCastle by default is strict about parsing ASN.1 integers.
			Properties.setThreadOverride("org.bouncycastle.asn1.allow_unsafe_integer", true);
			decoder = new ASN1InputStream(signature);

			final ASN1Primitive seqObj = decoder.readObject();

			final DLSequence seq = (DLSequence) seqObj;

			r = ((ASN1Integer) seq.getObjectAt(0)).getPositiveValue();
			s = ((ASN1Integer) seq.getObjectAt(1)).getPositiveValue();

		} catch (Exception e) {
			return null;
		} finally {
			try {
				decoder.close();
			} catch (Exception e) {
				return null;
			}
		}
		Properties.removeThreadOverride("org.bouncycastle.asn1.allow_unsafe_integer");
		return new BigInteger[] {r, s};
	}

	private static byte[] encodeDerSignature(BigInteger[] components) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(72);
		DERSequenceGenerator seq = new DERSequenceGenerator(bos);

		seq.addObject(new ASN1Integer(components[0]));
		seq.addObject(new ASN1Integer(components[1]));
		seq.close();

		return bos.toByteArray();
	}
}
