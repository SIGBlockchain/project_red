package sigblockchain.projectred.client;

import java.math.BigInteger;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;

public class Contract {

	private BigInteger value;
	//TODO create the rest of the variables

	/**
	 * Construct a Contract that has already been processed and added onto the blockchain.
	 *
	 * @param version     The version of Aurum in which this contract was created under. Must be an unsigned int less than 2^16.
	 * @param publicKey   The elliptic curve public key of the sender
	 * @param signature   The signature of the contract, signed by the publkic key
	 * @param recipPkHash The hash of the public key of the recipient of value
	 * @param value       Value transferred in the contract. String of a decimal unsigned 64 bit int
	 * @param stateNone   state nonce, indicating how many contracts associated with the public key. String of a decimal unsigned 64bit int.
	 */
	public Contract(int version, ECPublicKeyParameters publicKey, byte[] signature, byte[] recipPkHash, String value, String stateNone) {
		this.value = new BigInteger(value, 10);
		//TODO implement constructor
	}
}
