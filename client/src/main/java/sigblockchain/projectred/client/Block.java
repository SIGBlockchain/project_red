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
	 * Stores data (typically contracts). The entire block is either added to the blockchain or the entirety
	 * of it is rejected.
	 *
	 * @param version        Version of the blockChain the block was created on
	 * @param height         A hex encoded 2's complement 64 bit integer string (without the prefix '0x'). Note,
	 *                       the height cannot be negative Represents the height of the block in the blockChain.
	 * @param timestamp      Unix time, representing the number of nanoseconds elapsed since January 1, 1970 UTC
	 * @param previousHash   Hash of the previous block
	 * @param merkleRootHash Hash of the merkle-root of all inputs
	 * @param dataLen        Number of objects in the Data section
	 * @param data           Actual contents of block
	 */
  
	public Block(int version, String height, long timestamp, byte[] previousHash,
			byte[] merkleRootHash, int dataLen, byte[][] data) {
		//Individual values Checking
		if (checkUnsigned16(version)) {
			throw new IllegalArgumentException("Invalid Input version");
		}
		if (!checkUnsigned64(new BigInteger(height, 16))) {
			throw new IllegalArgumentException("Invalid Input height");
		}
		if (!checkSigned64(timestamp)) {
			throw new IllegalArgumentException("Invalid Input timeStamp");
		}
		if (checkUnsigned16(dataLen)) {
			throw new IllegalArgumentException("Invalid Input dataLen");
		}

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
		return (value.compareTo(BigInteger.ZERO) >= 0)
				&& (value.compareTo(new BigInteger("ffffffffffffffff", 16)) <= 0);
	}

	private boolean checkSigned64(long value) {
		return (value >= -(Math.pow(2, 63))) && value < (Math.pow(2, 63));
	}

	/**
	 * Returns the version of Aurum in which the block was added/created.
	 *
	 * @return int value of version. Will be guaranteed to be unsigned 16bit value
	 */
	public int getVersion() {
		return this.version;
	}

	/**
	 * Returns height of the block.
	 *
	 * @return int value of the height of the block. Will be positive value less than 2^64
	 */
	public BigInteger getHeight() {
		return this.height;
	}

	/**
	 * Returns the unix timestamp of block.
	 *
	 * @return long timestamp.
	 */
	public long getTimestamp() {
		return this.timestamp;
	}

	/**
	 * The hash of the previous block.
	 *
	 * @return byte array representing the hash
	 */
	public byte[] getPreviousHash() {
		return this.previousHash;
	}

	/**
	 * Returns the merkle root hash of the block.
	 *
	 * @return byte array representing the hash
	 */
	public byte[] getMerkleRootHash() {
		return this.merkleRootHash;
	}

	/**
	 * Returns the length of data present in the block.
	 *
	 * @return int value, garuanteed to be unsigned 16 bit value
	 */
	public int getDataLen() {
		return this.dataLen;
	}

	/**
	 * Returns the data actually present in the block.
	 *
	 * @return An array of byte arrays.
	 */
	public byte[][] getData() {
		return this.data;
	}
}
