package sigblockchain.projectred.client;

public class Block {
    private final int Version;          // uint16
    private final long Height;          // uint64
    private final long Timestamp;       // int64
    private final byte[] PreviousHash;  // []byte
    private final byte[] MerkleRootHash;// []byte
    private final int DataLen;          // uint16
    private final byte[][] Data;        // [][]byte

    public Block(int version, long height, long timestamp, byte[] previousHash, byte[] merkleRootHash, int dataLen, byte[][] data) {
        if (!checkCompatibility(version, height, timestamp, dataLen))
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
        Height = height;
        Timestamp = timestamp;
        PreviousHash = previousHash;
        MerkleRootHash = merkleRootHash;
        DataLen = dataLen;
        Data = data;
    }

    private boolean checkCompatibility(int version,
                                       long height,
                                       long timestamp,
                                       int dataLen)   {

        return checkUnsigned16(version) && checkUnsigned64(height) && checkSigned64(timestamp) && checkUnsigned16(dataLen);
    }

    private boolean checkUnsigned16(int value)   {
        return (value >= 0) && (value < (65536));
    }

    private boolean checkUnsigned64(long value)   {
        return value >=0;
    }

    private boolean checkSigned64(long value) {
        return (value >= -(Math.pow(2, 63))) && value < (Math.pow(2, 63));
    }

    public int getVersion() {
        return Version;
    }

    public long getHeight() {
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
