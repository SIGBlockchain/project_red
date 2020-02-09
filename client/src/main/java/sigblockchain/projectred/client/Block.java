package sigblockchain.projectred.client;

import java.math.BigInteger;

public class Block {
    private final int version;              // uint16
    private final BigInteger height;        // uint64
    private final long timestamp;           // int64
    private final byte[] previousHash;      // []byte
    private final byte[] merkleRootHash;    // []byte
    private final int dataLen;              // uint16
    private final byte[][] data;            // [][]byte

    /**
     * @param version        Version of the blockChain the block was created on
     * @param height         A hex encoded 2's complement 64 bit integer string (without the prefix '0x'). Note, the height cannot be negative Represents the height of the block in the blockChain.
     * @param timestamp      Unix time, representing the number of nanoseconds elapsed since January 1, 1970 UTC
     * @param previousHash   Hash of the previous block
     * @param merkleRootHash Hash of the merkle-root of all inputs
     * @param dataLen        Number of objects in the Data section
     * @param data           Actual contents of block
     */
    public Block(int version, String height, long timestamp, byte[] previousHash, byte[] merkleRootHash, int dataLen, byte[][] data) {
        //Individual values Checking
        if (checkUnsigned16(version))
            throw new IllegalArgumentException("Invalid Input version");
        if (!checkUnsigned64(new BigInteger(height, 16)))
            throw new IllegalArgumentException("Invalid Input height");
        if (!checkSigned64(timestamp))
            throw new IllegalArgumentException("Invalid Input timeStamp");
        if (checkUnsigned16(dataLen))
            throw new IllegalArgumentException("Invalid Input dataLen");

        this.height = new BigInteger(height, 16);
        this.version = version;
        this.timestamp = timestamp;
        this.previousHash = previousHash;
        this.merkleRootHash = merkleRootHash;
        this.dataLen = dataLen;
        this.data = data;
    }

    private boolean checkUnsigned16(int value) {
        return (value < 0) || (value >= (65536));
    }

    private boolean checkUnsigned64(BigInteger value) {
        return (value.compareTo(BigInteger.ZERO) >= 0);
    }

    private boolean checkSigned64(long value) {
        return (value >= -(Math.pow(2, 63))) && value < (Math.pow(2, 63));
    }

    /**
     * @return  This function returns the value of version from the data
     */
    public int getVersion() {
        return this.version;
    }

    /**
     * @return This function returns the value of height from the data
     */
    public BigInteger getHeight() {
        return this.height;
    }

    /**
     * @return This function returns the value of timestamp from the data
     */
    public long getTimestamp() {
        return this.timestamp;
    }

    /**
     * @return This function returns the value of previousHash from the data
     */
    public byte[] getPreviousHash() {
        return this.previousHash;
    }

    /**
     * @return This function returns the value of merkleRootHash from the data
     */
    public byte[] getMerkleRootHash() {
        return this.merkleRootHash;
    }

    /**
     * @return This function returns the value of dataLen from the data
     */
    public int getDataLen() {
        return this.dataLen;
    }

    /**
     * @return This function returns the data from the data
     */
    public byte[][] getData() {
        return this.data;
    }
}
