package sigblockchain.projectred.client;

import java.math.BigInteger;

public class Block {
    private final int Version;          // uint16
    private final BigInteger Height;          // uint64
    private final long Timestamp;
    private final byte[] PreviousHash;
    private final byte[] MerkleRootHash;
    private final int DataLen;          // uint16
    private final byte[][] Data;        // [][]byte

    /**
     * @param version        Version of the blockchain the block was created on
     * @param height         A hex encoded 2's complement 64 bit integer string (without the prefix '0x'). Note, the height cannot be negative Represents the height of the block in the blokcchain.
     * @param timestamp      Unix time, representing the number of nanoseconds elapsed since January 1, 1970 UTC
     * @param previousHash   Hash of the previous block
     * @param merkleRootHash Hash of the merkle-root of all inputs
     * @param dataLen        Number of objects in the Data section
     * @param data           Actual contents of block
     */
    public Block(int version, String height, long timestamp, byte[] previousHash, byte[] merkleRootHash, int dataLen, byte[][] data) {
        this.Height = new BigInteger(height, 16);
        if (!checkCompatibility(version, this.Height, timestamp, dataLen))
            throw new IllegalArgumentException("Invalid Input Values");

//        //Individual values Checking
//        if (!checkUnsigned16(version))
//            throw new IllegalArgumentException("Invalid Input version");
//        if (!checkUnsigned64(height))
//            throw new IllegalArgumentException("Invalid Input height");
//        if (!checkSigned64(timestamp))
//            throw new IllegalArgumentException("Invalid Input timeStamp");
//        if (!checkUnsigned16(dataLen))
//            throw new IllegalArgumentException("Invalid Input dataLen");

        Version = version;
        Timestamp = timestamp;
        PreviousHash = previousHash;
        MerkleRootHash = merkleRootHash;
        DataLen = dataLen;
        Data = data;


    }

    private boolean checkCompatibility(int version, BigInteger height, long timestamp, int dataLen) {

        return checkUnsigned16(version) && checkUnsigned64(height) && checkSigned64(timestamp) && checkUnsigned16(dataLen);
    }

    private boolean checkUnsigned16(int value) {
        return (value >= 0) && (value < (65536));
    }

    private boolean checkUnsigned64(BigInteger value) {
        return value.compareTo(BigInteger.ZERO) >= 0;
    }

    private boolean checkSigned64(long value) {
        return (value >= -(Math.pow(2, 63))) && value < (Math.pow(2, 63));
    }

    public int getVersion() {
        return Version;
    }

    public BigInteger getHeight() {
        return Height;
    }

    public long getTimestamp() {
        return Timestamp;
    }

    public byte[] getPreviousHash() {
        return PreviousHash;
    }

    public byte[] getMerkleRootHash() {
        return MerkleRootHash;
    }

    public int getDataLen() {
        return DataLen;
    }

    public byte[][] getData() {
        return Data;
    }
}
