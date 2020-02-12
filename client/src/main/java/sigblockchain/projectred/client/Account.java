package sigblockchain.projectred.client;

import java.math.BigInteger;

public class Account {
	private String WalletAddress;
	private BigInteger Balance;
	private BigInteger StateNonce;

	public Account(String walletAddress, BigInteger balance, BigInteger stateNonce) {
		validateWalletAddress(walletAddress);

		WalletAddress = walletAddress;
		Balance = balance;
		StateNonce = stateNonce;
	}

	public Account(String walletAddress) {
		validateWalletAddress(walletAddress);

		WalletAddress = walletAddress;
		Balance = BigInteger.ZERO;
		StateNonce = BigInteger.ZERO;
	}

	private void validateWalletAddress(String walletAddress) {
        //        WalletAddress must not contain 'I', 'l', '0', or 'O'.d
		for (String invalidChar : new String[] {"I", "l", "0", "O"}) {
			if (walletAddress.contains(invalidChar)) {
				throw new IllegalArgumentException("Invalid WalletAddress: Illegal character(s).");
			}
		}
        //        WalletAddress must contain between 25 and 34 characters.
		if ((walletAddress.length() < 25) || (walletAddress.length() > 34)) {
			throw new IllegalArgumentException("Invalid WalletAddress: Illegal length.");
		}
        //        WalletAddress must begin with 1, 3, or bc1.
		if (!(walletAddress.startsWith("1") || walletAddress.startsWith("3") || walletAddress.startsWith("bc1"))) {
			throw new IllegalArgumentException("Invalid WalletAddress: Illegal header.");
		}
	}

	public String getWalletAddress() {
		return WalletAddress;
	}

	public BigInteger getBalance() {
		return Balance;
	}

	public BigInteger getStateNonce() {
		return StateNonce;
	}

	public void setBalance(BigInteger balance) {
		Balance = balance;
	}

	public void setStateNonce(BigInteger stateNonce) {
		StateNonce = stateNonce;
	}
}


