package sigblockchain.projectred.client;
import java.math.BigInteger;

public class Account {
    private String walletAddress;
    private BigInteger balance;
    private BigInteger stateNonce;

    public Account(String walletAddress, BigInteger balance, BigInteger stateNonce) {
//        validateWalletAddress(walletAddress);
        validatePositive(balance, "Balance");
        validatePositive(stateNonce, "stateNonce");

        this.walletAddress = walletAddress;
        this.balance = balance;
        this.stateNonce = stateNonce;
    }

    public Account(String walletAddress)
    {
//        validateWalletAddress(walletAddress);

        this.walletAddress = walletAddress;
        this.balance = BigInteger.ZERO;
        this.stateNonce = BigInteger.ZERO;
    }

    private void validateWalletAddress (String walletAddress){
//        Todo: Validation for Aurum Wallet Input Address; these are for Bitcoin and adjustments will be made.

//        walletAddress must not contain 'I', 'l', '0', or 'O'.d
        for(String invalidChar : new String[]{"I", "l", "0", "O"})
        {
            if (walletAddress.contains(invalidChar)) {
                throw new IllegalArgumentException("Invalid walletAddress: Illegal character(s).");
            }
        }
//        walletAddress must contain between 25 and 34 characters.
        if ((walletAddress.length() < 25) || (walletAddress.length() > 34)) {
            throw new IllegalArgumentException("Invalid walletAddress: Illegal length.");
        }
//        walletAddress must begin with 1, 3, or bc1.
        if (!(walletAddress.startsWith("1") || walletAddress.startsWith("3") || walletAddress.startsWith("bc1")))
        {
            throw new IllegalArgumentException("Invalid walletAddress: Illegal header.");
        }
    }

    private void validatePositive(BigInteger n, String s) {
//        If 0 > n; raise exception
        if (BigInteger.ZERO.compareTo(n) > 0) {
            throw new IllegalArgumentException(String.format("Invalid %s: Negative integer.", s));
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
        validatePositive(balance, "balance");
        this.balance = balance;
    }

    public void setStateNonce(BigInteger stateNonce) {
        validatePositive(stateNonce, "stateNonce");
        this.stateNonce = stateNonce;
    }
}


