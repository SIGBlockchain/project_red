package sigblockchain.projectred.client;

public class Account {
    private String WalletAddress;
    private long Balance;
    private long StateNonce;

    public Account(String walletAddress, long balance, long stateNonce) {
        validateWalletAddress(walletAddress);

        WalletAddress = walletAddress;
        Balance = balance;
        StateNonce = stateNonce;
    }

    public Account(String walletAddress)
    {
        validateWalletAddress(walletAddress);

        WalletAddress = walletAddress;
        Balance = 0;
        StateNonce = 0;
    }

    private void validateWalletAddress (String walletAddress){
//        WalletAddress must not contain 'I', 'l', '0', or 'O'.d
        for(String invalidChar : new String[]{"I", "l", "0", "O"})
        {
            if (walletAddress.contains(invalidChar)) {
                throw new IllegalArgumentException("Invalid WalletAddress: Illegal character(s).");
            }
        }
//        WalletAddress must contain between 25 and 34 characters.
        if ((walletAddress.length() < 25) || (walletAddress.length() > 34)) {
            throw new IllegalArgumentException("Invalid WalletAddress: Illegal length.");
        }
//        WalletAddress must begin with 1, 3, or bc1.
        if (!(walletAddress.startsWith("1") || walletAddress.startsWith("3") || walletAddress.startsWith("bc1")))
        {
            throw new IllegalArgumentException("Invalid WalletAddress: Illegal header.");
        }
    }

    public String getWalletAddress() {
        return WalletAddress;
    }

    public long getBalance() {
        return Balance;
    }

    public long getStateNonce() {
        return StateNonce;
    }

    public void setBalance(long balance) {
        Balance = balance;
    }

    public void setStateNonce(long stateNonce) {
        StateNonce = stateNonce;
    }
}


