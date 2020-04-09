package sigblockchain.projectred.client;

import java.math.BigInteger;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;

public class Contract {

	private BigInteger value;
	private BigInteger stateNonce;
	private ECPublicKeyParameters publicKey;
	private byte[] recipPubKeyHash;
	private byte[] signature;
	private int version;

	/**
	 * Construct a Contract that has already been processed and added onto the blockchain.
	 *
	 * @param version     The version of Aurum in which this contract was created under. Must be an unsigned int less than 2^16.
	 * @param publicKey   The elliptic curve public key of the sender
	 * @param signature   The signature of the contract, signed by the public key
	 * @param recipPkHash The hash of the public key of the recipient of value
	 * @param value       Value transferred in the contract. String of a decimal unsigned 64 bit int
	 * @param stateNonce  state nonce, indicating how many contracts associated with the public key. String of a decimal unsigned 64bit int.
	 */
	public Contract(int version, ECPublicKeyParameters publicKey, byte[] signature, byte[] recipPkHash, String value, String stateNonce) {
		var valueInt = new BigInteger(value, 10);
		if (!IntUtils.isUnsigned64(valueInt)) {
			throw new IllegalArgumentException("Value is not an unsigned 64 bit int");
		}
		this.value = valueInt;

		var stateNonceInt = new BigInteger(stateNonce, 10);
		if (!IntUtils.isUnsigned64(stateNonceInt)) {
			throw new IllegalArgumentException("State nonce is not an unsigned 64 bit int");
		}
		this.stateNonce = stateNonceInt;

		if (!IntUtils.isUnsigned16(version)) {
			throw new IllegalArgumentException("Version is not an unsigned 16 bit integer");
		}
		this.version = version;

		this.publicKey = publicKey;
		if (signature != null && signature.length > 255)
			throw new IllegalArgumentException("Signature length is greater than 255");
		this.signature = signature;
		this.recipPubKeyHash = recipPkHash;
	}

	public BigInteger getStateNonce() {
		return stateNonce;
	}

	public BigInteger getValue() {
		return value;
	}

	public byte[] getRecipientPublicKeyHash() {
		return recipPubKeyHash;
	}

	public byte[] getSignature() {
		return signature;
	}

	public int getVersion() {
		return version;
	}

	public ECPublicKeyParameters getPublicKey() {
		return publicKey;
	}

	public void setStateNonce(String stateNonce) {
		var stateNonceInt = new BigInteger(stateNonce, 10);
		if (!IntUtils.isUnsigned64(this.stateNonce)) {
			throw new IllegalArgumentException("State nonce is not an unsigned 64 bit int");
		}
		this.stateNonce = stateNonceInt;
	}

	public void setValue(String value) {
		var valueInt = new BigInteger(value, 10);
		if (!IntUtils.isUnsigned64(this.value)) {
			throw new IllegalArgumentException("Value is not an unsigned 64 bit int");
		}
		this.value = valueInt;
	}

	public void setVersion(int version) {
		if (!IntUtils.isUnsigned16(version)) {
			throw new IllegalArgumentException("Version is not an unsigned 16 bit integer");
		}
		this.version = version;
	}

	public void setSignature(byte[] signature) {
		if (signature != null && signature.length > 255)
			throw new IllegalArgumentException("Signature length is greater than 255");
		this.signature = signature;
	}

	public void setRecipientPublicKeyHash(byte[] recipPubKeyHash) {
		this.recipPubKeyHash = recipPubKeyHash;
	}

	public void setStateNonce(BigInteger stateNonce) {
		this.stateNonce = stateNonce;
	}


	public boolean isSigned(){
		return signature != null;
	}
}
