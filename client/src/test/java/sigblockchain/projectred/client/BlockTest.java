package sigblockchain.projectred.client;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {

    private byte[] previousHash = "Previous Hash".getBytes();
    private byte[] hash = "Block hash".getBytes();

    private byte[][] data =
            {
                    {1, 2, 3, 4},
                    {'H', 'E', 'L', 'L', 'O'},
                    {5, 6, 7, 8},
                    {'W', 'O', 'R', 'L', 'D'}
            };


    @Test
    void testConstructor() {
        Block block = new Block(10, "2B", -3556L, previousHash, hash, 10, data);
        assertNotNull(block);
    }

    @Test
    void versionGetter() {
        Block block = new Block(10, "2B", -3556L, previousHash, hash, 10, data);
        int v = block.getVersion();
        assertEquals(v, 10);
    }

    @Test
    void heightGetter() {
        Block block = new Block(10, "2B", -3556L, previousHash, hash, 10, data);
        var v = block.getHeight();
        assertEquals(v, new BigInteger("2B", 16));
    }

    @Test
    void timeStampGetter() {
        Block block = new Block(10, "2B", -3556L, previousHash, hash, 10, data);
        long v = block.getTimestamp();
        assertEquals(v, -3556L);
    }

    @Test
    void previousHashGetter() {
        Block block = new Block(10, "2B", -3556L, previousHash, hash, 10, data);
        byte[] v = block.getPreviousHash();
        assertEquals(v, previousHash);
    }

    @Test
    void merkleRootHashGetter() {
        Block block = new Block(10, "2B", -3556L, previousHash, hash, 10, data);
        byte[] v = block.getMerkleRootHash();
        assertEquals(v, hash);
    }

    @Test
    void dataLenGetter() {
        Block block = new Block(10, "2B", -3556L, previousHash, hash, 10, data);
        int v = block.getDataLen();
        assertEquals(v, 10);
    }

    @Test
    void dataGetter() {
        Block block = new Block(10, "2B", -3556L, previousHash, hash, 10, data);
        byte[][] v = block.getData();
        assertEquals(v, data);
    }
}
