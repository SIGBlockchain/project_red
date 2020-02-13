package sigblockchain.projectred.client;

import java.math.BigInteger;

public class Account {
	private String walletAddress;
	private BigInteger balance;
	private BigInteger stateNonce;

	/**
	 * Represents all the information about a Aurum account. Corresponds to /accountinfo endpoint.
	 *
	 * @param walletAddress The public address of the Account
	 * @param balance       The current balance of the account
	 * @param stateNonce    The current state nonce of the account
	 */
	public Account(String walletAddress, BigInteger balance, BigInteger stateNonce) {
		validateUnsigned64(balance, "Balance");
		validateUnsigned64(stateNonce, "stateNonce");

		this.walletAddress = walletAddress;
		this.balance = balance;
		this.stateNonce = stateNonce;
	}

	/**
	 * Represents all the information about a Aurum account. Corresponds to /accountinfo endpoint.
	 *
	 * @param walletAddress The public address of the Account
	 */
	public Account(String walletAddress) {
		this.walletAddress = walletAddress;
		this.balance = BigInteger.ZERO;
		this.stateNonce = BigInteger.ZERO;
	}

	private void validateUnsigned64(BigInteger n, String s) {
		if (BigInteger.ZERO.compareTo(n) > 0 && n.compareTo(new BigInteger("ffffffffffffffff", 16)) <= 0) {
			throw new IllegalArgumentException("Invalid " + s + ":  Not an unsigned 64 bit integer");
		}
	}

	public String getWalletAddress() {
		return this.walletAddress;
	}

	public BigInteger getBalance() {
		return this.balance;
	}

	public BigInteger getStateNonce() {
		return this.stateNonce;
	}

	public void setBalance(BigInteger balance) {
		validateUnsigned64(balance, "balance");
		this.balance = balance;
	}

	public void setStateNonce(BigInteger stateNonce) {
		validateUnsigned64(stateNonce, "stateNonce");
		this.stateNonce = stateNonce;
	}
}


