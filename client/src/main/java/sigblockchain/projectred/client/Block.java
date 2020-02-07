package sigblockchain.projectred.client;

public class Block {
    private Integer Version;
    private Integer Height;
    private Integer Timestamp;
    private String PreviousHash;
    private String MerkleRootHash;
    private Integer DataLen;
    private String Data;

    public Block(Integer version, Integer height, Integer timestamp, String previousHash, String merkleRootHash, Integer dataLen, String data) {
        Version = version;
        Height = height;
        Timestamp = timestamp;
        PreviousHash = previousHash;
        MerkleRootHash = merkleRootHash;
        DataLen = dataLen;
        Data = data;
    }

    public Integer getVersion() {
        return Version;
    }

    public Integer getHeight() {
        return Height;
    }

    public Integer getTimestamp() {
        return Timestamp;
    }

    public String getPreviousHash() {
        return PreviousHash;
    }

    public String getMerkleRootHash() {
        return MerkleRootHash;
    }

    public Integer getDataLen() {
        return DataLen;
    }

    public String getData() {
        return Data;
    }
}
